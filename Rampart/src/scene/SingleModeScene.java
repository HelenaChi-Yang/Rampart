package scene;

import controllers.AudioResourceController;
import controllers.SceneController;
import menu.Button;
import menu.Theme;
import menu.impl.MouseTriggerImpl;
import com.company.CommandSolver;
import com.company.Path;

import java.awt.*;
import java.awt.event.MouseEvent;

import static com.company.Global.WINDOW_HEIGHT;
import static com.company.Global.WINDOW_WIDTH;

public class SingleModeScene extends Scene{

    private Button buttonNewGame, buttonLoadGame;
    private Image background;
    private Image title;

    @Override
    public void sceneBegin() {
        System.out.println("單人模式場景開始");
        background = SceneController.getInstance().imageController().tryGetImage(new Path().img().backgrounds().main());
        title = SceneController.getInstance().imageController().tryGetImage(new Path().img().objs().rampart());
        buttonNewGame = new Button(WINDOW_WIDTH/2 -(WINDOW_WIDTH/5), WINDOW_HEIGHT/2 +WINDOW_HEIGHT/5, Theme.get(5)); // 新遊戲
        buttonNewGame.setClickedActionPerformed((int x, int y) -> {
            AudioResourceController.getInstance().shot(new Path().sound().button());
            SceneController.getInstance().change(new InputNameScene(false,false ,""));
        });
        buttonLoadGame = new Button(WINDOW_WIDTH/2 +(WINDOW_WIDTH/5), WINDOW_HEIGHT/2 +WINDOW_HEIGHT/5, Theme.get(6)); //遊戲說明
        buttonLoadGame.setClickedActionPerformed((int x, int y) -> {
            AudioResourceController.getInstance().shot(new Path().sound().button());
            SceneController.getInstance().change(new MenuScene());
        });
    }

    @Override
    public void sceneEnd() {
        buttonNewGame = null;
        buttonLoadGame = null;
        background = null;
        title = null;
        System.out.println("單人模式場景結束");
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(background, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, null);
        g.drawImage(title, WINDOW_WIDTH/2-(WINDOW_WIDTH*3/5)/2, WINDOW_HEIGHT-WINDOW_HEIGHT*93/100, WINDOW_WIDTH*2/3, WINDOW_HEIGHT/5, null);
//        p1.paint(Direction.RIGHT, WINDOW_WIDTH-WINDOW_WIDTH*8/9, WINDOW_HEIGHT-WINDOW_HEIGHT*30/100, WINDOW_WIDTH-WINDOW_WIDTH*8/9+100, WINDOW_HEIGHT-WINDOW_HEIGHT*30/100+100, g);
//        p2.paint(Direction.RIGHT, WINDOW_WIDTH-WINDOW_WIDTH*7/9, WINDOW_HEIGHT-WINDOW_HEIGHT*30/100, WINDOW_WIDTH-WINDOW_WIDTH*7/9+100, WINDOW_HEIGHT-WINDOW_HEIGHT*30/100+100, g);
//        p3.paint(Direction.LEFT, WINDOW_WIDTH-WINDOW_WIDTH*2/9, WINDOW_HEIGHT-WINDOW_HEIGHT*30/100, WINDOW_WIDTH-WINDOW_WIDTH*2/9+100, WINDOW_HEIGHT-WINDOW_HEIGHT*30/100+80, g);
        //a.paint(g);
        buttonNewGame.paint(g);
        buttonLoadGame.paint(g);

        //ee.paint(g);
        /*if (testPop.isShow()) {
            testPop.paint(g);
        }*/
    }

    @Override
    public void update() {
//        p1.update();
//        p2.update();
//        p3.update();
    }

    @Override
    public CommandSolver.MouseCommandListener mouseListener() {
        return (MouseEvent e, CommandSolver.MouseState state, long trigTime) -> {
            MouseTriggerImpl.mouseTrig(buttonNewGame, e, state);
            MouseTriggerImpl.mouseTrig(buttonLoadGame, e, state);
        };
    }

    @Override
    public CommandSolver.KeyListener keyListener() {
        return new CommandSolver.KeyListener() {
            @Override
            public void keyPressed(int commandCode, long trigTime) {
            }

            @Override
            public void keyReleased(int commandCode, long trigTime) {
            }

            @Override
            public void keyTyped(char c, long trigTime) {
            }
        };
    }
}
