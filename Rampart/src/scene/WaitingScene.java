package scene;

import Internet.Client;
import Internet.Servers;
import com.company.CommandSolver;
import com.company.Global;
import com.company.Path;
import controllers.AudioResourceController;
import controllers.SceneController;
import gameObject.Player;
import menu.Button;
import menu.Label;
import menu.*;
import menu.impl.MouseTriggerImpl;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

import static com.company.Global.*;

public class WaitingScene extends Scene {

    private Image img;
    private Button buttonConfirm , buttonCancel;
    private Label connectStatus, connectStatusGo;
    private boolean isHost;
    private Player player;

    public WaitingScene (boolean isHost, Player player) {
        this.player = player;
        this.isHost = isHost;
    }



    @Override
    public void sceneBegin() {

        System.out.println("等待連線景開始");
        img = SceneController.getInstance().imageController().tryGetImage(new Path().img().backgrounds().main());
        if(isHost) {
            connectStatus = new Label(WINDOW_WIDTH / 2, WINDOW_HEIGHT / 2, Theme.get(10));
        }else {
            connectStatus = new Label(WINDOW_WIDTH / 2, WINDOW_HEIGHT / 2, Theme.get(59));
        }
        connectStatusGo = new Label(WINDOW_WIDTH/2, WINDOW_HEIGHT/2, Theme.get(11));
        buttonConfirm = new Button(WINDOW_WIDTH/2 -(WINDOW_WIDTH/5 ) , WINDOW_HEIGHT/2 +WINDOW_HEIGHT/3 -20, Theme.get(9)); //confirm
        buttonCancel = new Button(WINDOW_WIDTH/2 +(WINDOW_WIDTH/5 ), WINDOW_HEIGHT/2 +WINDOW_HEIGHT/3 -20, Theme.get(12)); //cancel
        buttonConfirm.setClickedActionPerformed((int x, int y) -> {
            AudioResourceController.getInstance().shot(new Path().sound().button());
            int stage = random(1,3);
            System.out.println("stage: " + stage);
            Client.getInstance().send(1000+"," + stage);
//            SceneController.getInstance().change(new MenuScene());
        });
        buttonCancel.setClickedActionPerformed((int x, int y) -> {
            AudioResourceController.getInstance().shot(new Path().sound().button());
            try {
                Client.getInstance().closeConnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (isHost) {
                try {
                    Servers.getInstance().closeConnect();
                    isHost = false;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            SceneController.getInstance().change(new MenuScene());


        });

    }

    @Override
    public void sceneEnd() {
        buttonConfirm = null;
        buttonCancel = null;
        connectStatus = null;
        connectStatusGo = null;
        img =null;
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(img, 0, 0, Global.SCREEN_X, Global.SCREEN_Y, 0, 0, img.getWidth(null), img.getHeight(null), null);
        g.setColor(Color.white);
        this.buttonCancel.paint(g);

        if(isHost) {
            if (Global.countClient >= 2 ) {
                this.connectStatusGo.paint(g);
                this.buttonConfirm.paint(g);
            }else {
                this.connectStatus.paint(g);
            }
        } else {
            this.connectStatus.paint(g);
        }

    }

    @Override
    public void update() {
        if (Client.getInstance().getData()[0].equals("1000") ) {
            SceneController.getInstance().change(new MainScene(player,isHost,true,Integer.parseInt(Client.getInstance().getData()[1])));
        }

    }



    public EditText addEditText(int x, int y, String str) {
        Style normal = new Style.StyleRect(250, 70,true, new BackgroundType.BackgroundColor(Color.DARK_GRAY))
                .setTextColor(Color.white)
                .setTextFont(new Font("", Font.BOLD, 20));
        Style hover = new Style.StyleRect(250, 70, true,new BackgroundType.BackgroundColor(Color.gray))
                .setTextFont(new Font("", Font.BOLD, 20));;
        Style focus = new Style.StyleRect(250, 70, true, new BackgroundType.BackgroundColor(Color.WHITE))
                .setTextColor(Color.black)
                .setTextFont(new Font("", Font.BOLD, 20));
        EditText input = new EditText(x - 125, y - 35, str, normal);
        input.setStyleHover(hover);
        input.setStyleFocus(focus);
        return input;
    }

    @Override
    public CommandSolver.MouseCommandListener mouseListener() {
        return (MouseEvent e, CommandSolver.MouseState state, long trigTime) -> {
            //MouseTriggerImpl.mouseTrig(inputIP, e, state);
            if (isHost && Global.countClient >= 2) {
                MouseTriggerImpl.mouseTrig(buttonConfirm, e, state);
            }
            MouseTriggerImpl.mouseTrig(buttonCancel, e, state);

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
