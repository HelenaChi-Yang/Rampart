package scene;

import com.company.*;
import controllers.SceneController;
import gameObject.Actor;
import gameObject.DefenseTower.ArcherTower;
import gameObject.DefenseTower.DefenseTower;
import gameObject.Monster;
import maploader.MapInfo;
import menu.Theme;
import menu.impl.MouseTriggerImpl;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

import static com.company.Global.*;

public class MainScene extends Scene implements CommandSolver.KeyListener {

    private Image img;
    private ArrayList<Actor> actorKungArr;
    private ArrayList<DefenseTower> defenseTowers;
    private MapTest mapTest;

    private int firstX;//演員起始X
    private int firstY;//演員起始Y


    @Override
    public void sceneBegin()  {
        actorKungArr = new ArrayList<>();
        defenseTowers = new ArrayList<>();
        img = SceneController.getInstance().imageController().tryGetImage(new Path().img().backgrounds().grassBackground());
        try {
            mapTest = new MapTest();
        } catch (IOException e) {
            e.printStackTrace();
        }


        firstX = mapTest.firstGrid().getX();//起始X點
        firstY = mapTest.firstGrid().getY();//起始Y點

        Monster temp = new Monster((firstX) * MAP_PIXEL, (firstY) * MAP_PIXEL, MAP_PIXEL, MAP_PIXEL);
        temp.setAllAbility(1);
        actorKungArr.add(temp);

        DefenseTower tower = new ArcherTower((firstX - 1) * MAP_PIXEL, (firstY + 1) * MAP_PIXEL, MAP_PIXEL, MAP_PIXEL * 2);
        tower.convertToAdvanceTower(2);
        defenseTowers.add(tower);

    }

    @Override
    public void sceneEnd() {
        SceneController.getInstance().imageController().clear();
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(img, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, null);

        mapTest.paint(g);

        for (int i = 0; i < defenseTowers.size(); i++) {
            defenseTowers.get(i).paintComponent(g);
        }

        for (int i = 0; i < actorKungArr.size(); i++) {
            actorKungArr.get(i).paintComponent(g);
        }
    }

    @Override
    public void update() {
        for (int i = 0; i < actorKungArr.size(); i++) {
            Actor tmp = actorKungArr.get(i);
            if (tmp.isArriveDestination()) {
                move(tmp);
            }
            tmp.update();
        }

        for (int i = 0; i < defenseTowers.size(); i++) {
            DefenseTower tmp = defenseTowers.get(i);
            tmp.update();
            if (tmp.getTarget() == null) {
                for (int j = 0; j < actorKungArr.size(); j++) {
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

        return (MouseEvent e, CommandSolver.MouseState state, long trigTime) -> {

            for (DefenseTower df: defenseTowers) {
                df.mouseTrig(e,state,trigTime);
            }


        };
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
            actorKungArr.get(0).getActorAnimator().setType(Global.random(0, 7));
        }
    }

    @Override
    public void keyTyped(char c, long trigTime) {

    }

    /**
     * 演員沿著路徑行走
     */
    public void move(Actor actorKung) {
        MapInfo currentGrid = null, nextGrid = null;
        for (int i = 0; i < mapTest.getMapInfo().size(); ++i) {     //找到當前格子位置
            if (mapTest.getMapInfo().get(i).isInThisGrid(actorKung)) {
                currentGrid = mapTest.getMapInfo().get(i);
                currentGrid.setWalk(true);
                break;
            }
        }
//        System.out.println(currentGrid.getX() +";"+ currentGrid.getY());        //debug用，顯示當前格子座標

        for (int i = 0; i < mapTest.getMapInfo().size(); ++i) {     //掃整個arr，找到相鄰的就移動過去
            nextGrid = mapTest.getMapInfo().get(i);       //要前往的下一格
            if (!nextGrid.isWalk() && nextGrid.getName().equals("path1")) {     //已經走過的話不掃
                ActorAnimator actorMovingState = actorKung.getActorAnimator();

                //方向為左
                if (currentGrid.getX()-1 == nextGrid.getX() && currentGrid.getY() == nextGrid.getY()) {
                    actorMovingState.changeDir(1); //改變角色方向為
                    actorKung.moveToDestination((int)((nextGrid.getX()) * MAP_PIXEL), (int)((nextGrid.getY()) * MAP_PIXEL), LEFT); //移動角色位置
                     nextGrid.setWalk(true); //設定角色已走過此格
                    System.out.println("2");
                    break;
                }
                //方向為右
                if (currentGrid.getX() + 1 == nextGrid.getX() && currentGrid.getY() == nextGrid.getY()) {
                    actorMovingState.changeDir(2); //改變角色方向為
                    actorKung.moveToDestination((int)((nextGrid.getX()) * MAP_PIXEL), (int)((nextGrid.getY()) * MAP_PIXEL),RIGHT); //移動角色位置
                     nextGrid.setWalk(true); //設定角色已走過此格
                    System.out.println("3");
                    break;
                }

                //方向為下
                if (currentGrid.getX() == nextGrid.getX() && currentGrid.getY() + 1 == nextGrid.getY()) {
                    actorMovingState.changeDir(0); //改變角色方向為下面
                    actorKung.moveToDestination((int)((nextGrid.getX()) * MAP_PIXEL), (int)((nextGrid.getY()) * MAP_PIXEL), DOWN); //移動角色位置
                    nextGrid.setWalk(true); //設定角色已走過此格
                    System.out.println("1");
                    break;
                }
                //方向為上
                if (currentGrid.getX() == nextGrid.getX() && currentGrid.getY()-1 == nextGrid.getY()) {
                    actorMovingState.changeDir(3); //改變角色方向為下面
                    actorKung.moveToDestination((int)((nextGrid.getX()) * MAP_PIXEL), (int)((nextGrid.getY()) * MAP_PIXEL),UP); //移動角色位置
                    nextGrid.setWalk(true); //設定角色已走過此格
                    System.out.println("4");
                    break;
                }
            }
        }
    }
}
