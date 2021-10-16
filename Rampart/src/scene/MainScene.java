package scene;

import com.company.CommandSolver;
import com.company.Global;
import com.company.Path;
import controllers.SceneController;
import gameObject.Actor;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class MainScene extends Scene implements CommandSolver.KeyListener{

    private Image img;
    private ArrayList<Actor> actorArr;
    private MapTest mapTest;

    @Override
    public void sceneBegin() throws IOException {
        actorArr = new ArrayList<>();
        actorArr.add(new Actor(Global.SCREEN_X/2, Global.SCREEN_Y/2, 2));
        img= SceneController.getInstance().imageController().tryGetImage(new Path().img().actors().map());
        mapTest = new MapTest();
//        for(int i = 0; i < )
//        actor.add(Global.SCREEN_X/2, Global.SCREEN_Y/2, 3);
//        actor = new Actor(Global.SCREEN_X/2, Global.SCREEN_Y/2, 3);
//        actor = new Actor(Global.SCREEN_X/2, Global.SCREEN_Y/2, 3);
    }

    @Override
    public void sceneEnd() {
        SceneController.getInstance().imageController().clear();
    }

    @Override
    public void paint(Graphics g) {
        for(int i = 0; i < actorArr.size(); i++){
            actorArr.get(i).paint(g);
        }
        mapTest.paint(g);
    }

    @Override
    public void update() {
        for(int i = 0; i < actorArr.size(); i++){
            actorArr.get(i).update();
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
            actorArr.get(0).getActorAnimator().changeDir(1);
            actorArr.get(0).translateX(-24);
        }
        if (commandCode == Global.RIGHT) {
            actorArr.get(0).getActorAnimator().changeDir(2);
            actorArr.get(0).translateX(24);
        }
        if (commandCode == Global.UP) {
            actorArr.get(0).getActorAnimator().changeDir(3);
            actorArr.get(0).translateY(-24);
        }
        if (commandCode == Global.DOWN) {
            actorArr.get(0).getActorAnimator().changeDir(0);
            actorArr.get(0).translateY(24);
        }
        if (commandCode == Global.SPACE) {
            actorArr.get(0).getActorAnimator().changeState();
        }
        if (commandCode == Global.SHIFT) {
            actorArr.get(0).getActorAnimator().setType(Global.random(0,7));
        }
    }

    @Override
    public void keyTyped(char c, long trigTime) {

    }
}
