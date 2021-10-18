package scene;

import com.company.*;
import controllers.SceneController;
import gameObject.Actor;
import gameObject.DefenseTower.ArcherTower;
import gameObject.DefenseTower.DefenseTower;
import gameObject.Monster;
import maploader.MapInfo;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

import static com.company.Global.*;

public class MainScene extends Scene implements CommandSolver.KeyListener{

    private Image img;
    private ArrayList<Actor> actorKungArr;
    private ArrayList<DefenseTower> defenseTowers;
    private MapTest mapTest;

    private int firstX;//演員起始X
    private int firstY;//演員起始Y

    private Delay delay;//演員move delay


    @Override
    public void sceneBegin() throws IOException {
        actorKungArr = new ArrayList<>();
        defenseTowers = new ArrayList<>();
        img= SceneController.getInstance().imageController().tryGetImage(new Path().img().actors().MapChange());

        mapTest = new MapTest();

        firstX = mapTest.firstPixel().getX();//起始X點
        firstY = mapTest.firstPixel().getY();//起始Y點

        delay = new Delay(60); //delay初始值
        delay.loop();

        actorKungArr.add(new Monster(firstX * MAP_PIXEL, (firstY-1) * MAP_PIXEL, 2, firstX, firstY-1));

        defenseTowers.add(new ArcherTower((firstX-1)* MAP_PIXEL, (firstY+1)* MAP_PIXEL, MAP_PIXEL, MAP_PIXEL*2));
    }

    @Override
    public void sceneEnd() {
        SceneController.getInstance().imageController().clear();
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(img, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, null);

        mapTest.paint(g);

        for(int i = 0; i < defenseTowers.size(); i++){
            defenseTowers.get(i).paint(g);
        }

        for(int i = 0; i < actorKungArr.size(); i++){
            actorKungArr.get(i).paint(g);
        }
    }

    @Override
    public void update() {
        for (int i = 0; i < actorKungArr.size(); i++) {
            Actor tmp = actorKungArr.get(i);
            if(delay.count()) {
                move(tmp);
            }
            tmp.update();

        }

        for(int i = 0; i < defenseTowers.size(); i++){
            DefenseTower tmp = defenseTowers.get(i);
            tmp.update();
            if (tmp.getTarget() == null) {
                for(int j = 0; j < actorKungArr.size(); j++) {
                    Actor tmp2 = actorKungArr.get(j);
                    if (tmp.isIntoAttackRange(tmp2)) {
                        tmp.setTarget(tmp2);
                    }
                }
            }
        }
    }

    @Override
    public CommandSolver.MouseCommandListener mouseListener() {
        return null;
    }

    @Override
    public CommandSolver.KeyListener keyListener() {
        return this;
    }

    @Override
    public void keyPressed(int commandCode, long trigTime) {

    }

    @Override
    public void keyReleased(int commandCode, long trigTime) {
        if (commandCode == Global.LEFT) {
            actorKungArr.get(0).getActorAnimator().changeDir(1);//改變方向為右邊
            actorKungArr.get(0).translateX(-MAP_PIXEL);
        }
        if (commandCode == Global.RIGHT) {
            actorKungArr.get(0).getActorAnimator().changeDir(2);//改變方向為左邊
            actorKungArr.get(0).translateX(MAP_PIXEL);
        }
        if (commandCode == Global.UP) {
            actorKungArr.get(0).getActorAnimator().changeDir(3);//改變方向為上面
            actorKungArr.get(0).translateY(-MAP_PIXEL);
        }
        if (commandCode == Global.DOWN) {
            actorKungArr.get(0).getActorAnimator().changeDir(0);//改變方向為下面
            actorKungArr.get(0).translateY(MAP_PIXEL);
        }
        if (commandCode == Global.SPACE) {
            actorKungArr.get(0).getActorAnimator().changeState();
        }
        if (commandCode == Global.SHIFT) {
            actorKungArr.get(0).getActorAnimator().setType(Global.random(0,7));
        }
    }

    @Override
    public void keyTyped(char c, long trigTime) {

    }

    /**演員沿著路徑行走*/
    public void move(Actor actorKung){
        for (MapInfo tmp : mapTest.getMapInfo()) {
            if(!tmp.isWalk() && tmp.getName().equals("path1")) {
                ActorAnimator actorMovingState = actorKung.getActorAnimator();
                //方向為下
                if (actorMovingState.getPosX() == tmp.getX() && actorMovingState.getPosY() + 1 == tmp.getY()) {
                    actorMovingState.changeDir(0); //改變角色方向為下面
                    actorKung.translateY(MAP_PIXEL); //移動角色位置
                    actorMovingState.setPosX(0); //設定角色在地圖X上移動格子數
                    actorMovingState.setPosY(1); //設定角色在地圖Y上移動格子數
                    tmp.setWalk(true); //設定角色已走過此格
                    break;
                }
                //方向為左
                if (actorMovingState.getPosX() - 1 == tmp.getX() && actorMovingState.getPosY() == tmp.getY()) {
                    actorMovingState.changeDir(1); //改變角色方向為
                    actorKung.translateX(-MAP_PIXEL); //移動角色位置
                    actorMovingState.setPosX(-1); //設定角色在地圖X上移動格子數
                    actorMovingState.setPosY(0); //設定角色在地圖Y上移動格子數
                    tmp.setWalk(true); //設定角色已走過此格
                    break;
                }
                //方向為右
                if (actorMovingState.getPosX() + 1 == tmp.getX() && actorMovingState.getPosY() == tmp.getY()) {
                    actorMovingState.changeDir(2); //改變角色方向為
                    actorKung.translateX(MAP_PIXEL); //移動角色位置
                    actorMovingState.setPosX(1); //設定角色在地圖X上移動格子數
                    actorMovingState.setPosY(0); //設定角色在地圖Y上移動格子數
                    tmp.setWalk(true); //設定角色已走過此格
                    break;
                }
                //方向為上
                if (actorMovingState.getPosX() == tmp.getX() && actorMovingState.getPosY() - 1 == tmp.getY()) {
                    actorMovingState.changeDir(3); //改變角色方向為下面
                    actorKung.translateY(-MAP_PIXEL); //移動角色位置
                    actorMovingState.setPosX(0); //設定角色在地圖X上移動格子數
                    actorMovingState.setPosY(-1); //設定角色在地圖Y上移動格子數
                    tmp.setWalk(true); //設定角色已走過此格
                    break;
                }
            }
        }
    }
}
