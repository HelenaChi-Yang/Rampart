package scene;
import com.company.CommandSolver;
import com.company.Path;
import gameObject.AirCraft;
import menu.Label;
import menu.Button;
import controllers.AudioResourceController;
import controllers.SceneController;
import menu.Theme;
import menu.impl.MouseTriggerImpl;


import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import static com.company.Global.WINDOW_HEIGHT;
import static com.company.Global.WINDOW_WIDTH;

public class MenuScene extends Scene {

    //private Label a;
    private Button buttonSingleMode, buttonHowToPlay, buttonOnlineMode, buttonExit;
    //private EditText ee;
    private Image background;
    private Image title;
//    private AirCraft airCraft;
//    private ArrayList<Player> players;
//    private PlayerAnimator p1;
//    private PlayerAnimator p2;
//    private PlayerAnimator p3;

    @Override
    public void sceneBegin() {
        System.out.println("主選單場景開始");
//        AudioResourceController.getInstance().stop(new Path().sound().menu()); // 停音樂
//        AudioResourceController.getInstance().stop(new Path().sound().hanamatsuri()); // 停音樂
        background = SceneController.getInstance().imageController().tryGetImage(new Path().img().backgrounds().main());
        title = SceneController.getInstance().imageController().tryGetImage(new Path().img().objs().rampart());

        Theme.initTheme(); // 主題
//        airCraft = new AirCraft(WINDOW_WIDTH/2,WINDOW_HEIGHT/2);
        //testPop = new PopupWindowScene(300, 200, 650, 450);  // 建構彈出視窗
        //testPop.setCancelable();
        //a = new Label(430, 122);
//        p1 = new PlayerAnimator(PlayerAnimator.State.WALK, PlayerAnimator.ActorType.NARUTOHD);
//        p2 = new PlayerAnimator(PlayerAnimator.State.WALK,ActorType.SASUKEHD);
//        p3 = new PlayerAnimator(PlayerAnimator.State.SAKURASTOP,ActorType.SAKURAHD);
        buttonSingleMode = new Button(WINDOW_WIDTH/2 -(WINDOW_WIDTH/5), WINDOW_HEIGHT/2 +WINDOW_HEIGHT/5, Theme.get(0)); // 單人遊戲
        buttonSingleMode.setClickedActionPerformed((int x, int y) -> {
            AudioResourceController.getInstance().shot(new Path().sound().button());
            SceneController.getInstance().change(new SingleModeScene());
        });
        buttonHowToPlay = new Button(WINDOW_WIDTH/2 +(WINDOW_WIDTH/5), WINDOW_HEIGHT/2 +WINDOW_HEIGHT/5, Theme.get(2)); //遊戲說明
        buttonHowToPlay.setClickedActionPerformed((int x, int y) -> {
            AudioResourceController.getInstance().shot(new Path().sound().button());
            SceneController.getInstance().change(new MainScene());
        });
        buttonOnlineMode = new Button(WINDOW_WIDTH/2 -(WINDOW_WIDTH/5 ) , WINDOW_HEIGHT/2 +WINDOW_HEIGHT/3 + 20, Theme.get(1)); // 雙人遊戲
        buttonOnlineMode.setClickedActionPerformed((int x, int y) -> {
            AudioResourceController.getInstance().shot(new Path().sound().button());
            SceneController.getInstance().change(new OnlineMode());
        });
        buttonExit = new Button(WINDOW_WIDTH/2 +(WINDOW_WIDTH/5 ), WINDOW_HEIGHT/2 +WINDOW_HEIGHT/3 + 20, Theme.get(3)); // 離開遊戲
        buttonExit.setClickedActionPerformed((int x, int y) -> {
            AudioResourceController.getInstance().shot(new Path().sound().button());
            System.exit(0);
        });
        AudioResourceController.getInstance().loop(new Path().sound().menu(), 99);  // 放音樂
    }

    @Override
    public void sceneEnd() {
        buttonSingleMode = null;
        buttonHowToPlay = null;
        buttonOnlineMode = null;
        buttonExit = null;
//        p1=null;
//        p2=null;
//        p3=null;
        background = null;
        title = null;
        System.out.println("主選單場景結束");
        SceneController.getInstance().imageController().clear();
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(background, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, null);
        g.drawImage(title, WINDOW_WIDTH/2-(WINDOW_WIDTH*3/5)/2, WINDOW_HEIGHT-WINDOW_HEIGHT*93/100, WINDOW_WIDTH*2/3, WINDOW_HEIGHT/5, null);
//        p1.paint(Global.Direction.RIGHT, WINDOW_WIDTH-WINDOW_WIDTH*8/9, WINDOW_HEIGHT-WINDOW_HEIGHT*30/100, WINDOW_WIDTH-WINDOW_WIDTH*8/9+100, WINDOW_HEIGHT-WINDOW_HEIGHT*30/100+100, g);
//        p2.paint(Direction.RIGHT, WINDOW_WIDTH-WINDOW_WIDTH*7/9, WINDOW_HEIGHT-WINDOW_HEIGHT*30/100, WINDOW_WIDTH-WINDOW_WIDTH*7/9+100, WINDOW_HEIGHT-WINDOW_HEIGHT*30/100+100, g);
//        p3.paint(Direction.LEFT, WINDOW_WIDTH-WINDOW_WIDTH*2/9, WINDOW_HEIGHT-WINDOW_HEIGHT*30/100, WINDOW_WIDTH-WINDOW_WIDTH*2/9+100, WINDOW_HEIGHT-WINDOW_HEIGHT*30/100+80, g);
        //a.paint(g);
        buttonSingleMode.paint(g);
        buttonHowToPlay.paint(g);
        buttonOnlineMode.paint(g);
        buttonExit.paint(g);
//        airCraft.paint(g);
        
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
            //MouseTriggerImpl.mouseTrig(a, e, state);
            MouseTriggerImpl.mouseTrig(buttonSingleMode, e, state);
            MouseTriggerImpl.mouseTrig(buttonHowToPlay, e, state);
            MouseTriggerImpl.mouseTrig(buttonOnlineMode, e, state);
            MouseTriggerImpl.mouseTrig(buttonExit, e, state);
            //MouseTriggerImpl.mouseTrig(airCraft, e, state);
//            airCraft.mouseTrig(e,state,trigTime);
            //MouseTriggerImpl.mouseTrig(ee, e, state);
            /*if (testPop.isShow()) {
                testPop.mouseListener().mouseTrig(e, state, trigTime);
            }*/
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
