package scene;

import com.company.CommandSolver;
import com.company.Global;
import com.company.Main;
import com.company.Path;
import controllers.AudioResourceController;
import controllers.SceneController;
import gameObject.Player;
import menu.*;
import menu.Button;
import menu.impl.MouseTriggerImpl;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import static com.company.Global.*;
import static com.company.Global.PETRIFACTIONCDTIME;

public class StatusScene extends Scene{
    private Image img, avator;
    private menu.Button buttonGameStart, buttonCancel, stage1, stage2, stage3;
    private Font fontString;
    private boolean isHost;
    private Player player;
    private int stage;

    public StatusScene(Player player, boolean isHost){
        this.player = player;
        this.isHost = isHost;
    }

    @Override
    public void sceneBegin() {
        player.reset();     //重置錢和剩餘血量
        System.out.println("準備場景開始");
        AudioResourceController.getInstance().stop(new Path().sound().menu()); // 停音樂
        AudioResourceController.getInstance().loop(new Path().sound().statusScene(),99);
        System.out.println(player.getLevel());
        switch (player.getLevel()){
            case 1:
                img = SceneController.getInstance().imageController().tryGetImage(new Path().img().backgrounds().stage1());
                break;
            case 2:
                img = SceneController.getInstance().imageController().tryGetImage(new Path().img().backgrounds().stage2());
                break;
            case 3:
                img = SceneController.getInstance().imageController().tryGetImage(new Path().img().backgrounds().stage3());
                break;
            default:
                img = SceneController.getInstance().imageController().tryGetImage(new Path().img().backgrounds().stage1());
                break;
        }

        switch (player.getHero()) {
            case ROLE1:
                avator = SceneController.getInstance().imageController().tryGetImage(new Path().img().actors().artificer());
                break;
            case ROLE2:
                avator = SceneController.getInstance().imageController().tryGetImage(new Path().img().actors().warlord());
                break;
            case ROLE3:
                avator = SceneController.getInstance().imageController().tryGetImage(new Path().img().actors().crystalgolem());
                break;
            case ROLE4:
                avator = SceneController.getInstance().imageController().tryGetImage(new Path().img().actors().medusa());
                break;
        }


        stage = player.getLevel();
        buttonGameStart = new Button(WINDOW_WIDTH/2 +(WINDOW_WIDTH/3) + 20 , WINDOW_HEIGHT/2 +WINDOW_HEIGHT/3, Theme.get(37)); //game start
        buttonGameStart.setClickedActionPerformed((int x, int y) -> {

            AudioResourceController.getInstance().shot(new Path().sound().button());

                SceneController.getInstance().change(new MainScene(player, false,false,stage) );    //進入遊戲

        });
        buttonCancel = new Button(1475 , 65, Theme.get(52));
        buttonCancel.setClickedActionPerformed((int x, int y) -> {

            AudioResourceController.getInstance().shot(new Path().sound().button());
            AudioResourceController.getInstance().stop(new Path().sound().statusScene()); // 停音樂
            SceneController.getInstance().change(new MenuScene());    //回主選單

        });

        stage1 = new Button(305 , 525, Theme.get(52));
        stage1.setClickedActionPerformed((int x, int y) -> {
            stage = 1;

        });
        stage2 = new Button(255 , 330, Theme.get(52));
        stage2.setClickedActionPerformed((int x, int y) -> {

            stage = 2;

        });
        stage3 = new Button(525 , 170, Theme.get(52));
        stage3.setClickedActionPerformed((int x, int y) -> {

            stage = 3;

        });



        fontString = new Font("", Font.BOLD, 30);
    }

    @Override
    public void sceneEnd() {
        buttonGameStart = null;
        buttonCancel = null;
        img = null;
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(img, 0, 0, Global.SCREEN_X, Global.SCREEN_Y, 0, 0, img.getWidth(null), img.getHeight(null), null);
        if (stage == 1) {
            g.drawImage(avator, 1100, 520, 160, 160, null);
        } else if (stage == 2) {
            g.drawImage(avator, 200, 460, 160, 160, null);
        } else if (stage == 3) {
            g.drawImage(avator, 180, 230, 160, 160, null);
        }
        g.setColor(Color.white);
        g.setFont(fontString);
        //g.drawString("IP", inputIP.getX() - 50, inputIP.getY() + 40);
        //this.inputIP.paint(g);
        this.buttonGameStart.paint(g);
        this.buttonCancel.paint(g);
        g.setColor(new Color(154, 27, 183));
        stage1.paint(g);
        if(player.getLevel() >= 2) {
            stage2.paint(g);
        }
        if(player.getLevel() >= 3) {
            stage3.paint(g);
        }
    }

    @Override
    public void update() {


    }

    @Override
    public CommandSolver.MouseCommandListener mouseListener() {
        return (MouseEvent e, CommandSolver.MouseState state, long trigTime) -> {
            //MouseTriggerImpl.mouseTrig(inputIP, e, state);
            MouseTriggerImpl.mouseTrig(buttonGameStart, e, state);
            MouseTriggerImpl.mouseTrig(buttonCancel, e, state);
//            MouseTriggerImpl.mouseTrig(stage1, e, state);
//            if(player.getLevel() >= 2) {
//                MouseTriggerImpl.mouseTrig(stage2, e, state);
//            }
//            if(player.getLevel() >= 3) {
//                MouseTriggerImpl.mouseTrig(stage3, e, state);
//            }
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

