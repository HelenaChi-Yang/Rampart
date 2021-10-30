package scene;

import Internet.Client;
import Internet.Servers;
import com.company.CommandSolver;
import com.company.Delay;
import com.company.Global;
import com.company.Path;
import controllers.AudioResourceController;
import controllers.SceneController;
import gameObject.Player;
import menu.*;
import menu.Button;
import menu.Label;
import menu.impl.MouseTriggerImpl;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import static com.company.Global.*;
import static com.company.Global.WINDOW_HEIGHT;

public class HowToPlayScene extends Scene {

    private Image img;
    private menu.Button buttonConfirm, buttonChinese;
    private Image title, introBack;
    private boolean isChinese;




    @Override
    public void sceneBegin() {




        isChinese = false;
        title = SceneController.getInstance().imageController().tryGetImage(new Path().img().objs().rampart());
        introBack = SceneController.getInstance().imageController().tryGetImage(new Path().img().backgrounds().howToPlay());
        img = SceneController.getInstance().imageController().tryGetImage(new Path().img().backgrounds().main());

        buttonConfirm = new menu.Button(WINDOW_WIDTH/2 -(WINDOW_WIDTH/5)+850 , WINDOW_HEIGHT/2 +WINDOW_HEIGHT/3+10, Theme.get(53)); //confirm
        buttonConfirm.setClickedActionPerformed((int x, int y) -> {
            AudioResourceController.getInstance().shot(new Path().sound().button());

                SceneController.getInstance().change(new MenuScene());

        });
        buttonChinese = new menu.Button(WINDOW_WIDTH/2 -(WINDOW_WIDTH/5) , WINDOW_HEIGHT/2 +WINDOW_HEIGHT/3+10, Theme.get(61)); //confirm
        buttonChinese.setClickedActionPerformed((int x, int y) -> {
            AudioResourceController.getInstance().shot(new Path().sound().button());
            introBack = SceneController.getInstance().imageController().tryGetImage(new Path().img().backgrounds().howToPlayCH());
            isChinese = true;
        });

    }

    @Override
    public void sceneEnd() {
        //inputIP = null;
        buttonConfirm = null;
        buttonChinese = null;
        img = null;
        introBack = null;
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(img, 0, 0, Global.SCREEN_X, Global.SCREEN_Y, 0, 0, img.getWidth(null), img.getHeight(null), null);
        g.drawImage(title, 250, WINDOW_HEIGHT-WINDOW_HEIGHT*93/100, WINDOW_WIDTH*2/3, WINDOW_HEIGHT/5, null);
        g.drawImage(introBack, WINDOW_WIDTH/2-650, WINDOW_HEIGHT/2-180, 1300, 550,null);


            if(!isChinese){
                this.buttonChinese.paint(g);
            }
            this.buttonConfirm.paint(g);


    }

    @Override
    public void update() {







    }




    @Override
    public CommandSolver.MouseCommandListener mouseListener() {
        return (MouseEvent e, CommandSolver.MouseState state, long trigTime) -> {

            MouseTriggerImpl.mouseTrig(buttonConfirm, e, state);

            if(!isChinese){
                MouseTriggerImpl.mouseTrig(buttonChinese, e, state);
            }

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
