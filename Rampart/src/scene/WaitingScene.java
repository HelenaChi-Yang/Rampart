package scene;
import Internet.Client;
import Internet.Server;
import controllers.AudioResourceController;
import controllers.SceneController;
import menu.Button;
import menu.Label;
import menu.*;
import menu.impl.MouseTriggerImpl;
import com.company.CommandSolver;
import com.company.Global;
import com.company.Path;

import javax.swing.event.CaretListener;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;

import static com.company.Global.WINDOW_HEIGHT;
import static com.company.Global.WINDOW_WIDTH;

public class WaitingScene extends Scene {

    private Image img;
    //private ArrayList<Label> labels;
    private Button buttonConfirm , buttonCancel;
    private int currentFocus;
    private Font fontString;
    private Label connectStatus, connectStatusGo;
    private boolean isHost;

    public WaitingScene (boolean isHost) {
        this.isHost = isHost;
    }



    @Override
    public void sceneBegin() {

        System.out.println("等待連線景開始");
        img = SceneController.getInstance().imageController().tryGetImage(new Path().img().backgrounds().main());
        connectStatus = new Label(WINDOW_WIDTH/2, WINDOW_HEIGHT/2, Theme.get(10));
        connectStatusGo = new Label(WINDOW_WIDTH/2, WINDOW_HEIGHT/2, Theme.get(11));
        buttonConfirm = new Button(WINDOW_WIDTH/2 -(WINDOW_WIDTH/5 ) , WINDOW_HEIGHT/2 +WINDOW_HEIGHT/3 + 20, Theme.get(9)); //confirm
        buttonCancel = new Button(WINDOW_WIDTH/2 +(WINDOW_WIDTH/5 ), WINDOW_HEIGHT/2 +WINDOW_HEIGHT/3 + 20, Theme.get(12)); //confirm
        buttonConfirm.setClickedActionPerformed((int x, int y) -> {
            AudioResourceController.getInstance().shot(new Path().sound().button());
            SceneController.getInstance().change(new MenuScene());
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
                    Server.getInstance().closeConnect();
                    isHost = false;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            SceneController.getInstance().change(new MenuScene());

        });

//        labels = new ArrayList<>();;
//        labels.add(connectStatus);
//        labels.add(back);
//        labels.add(confirm);
        currentFocus = 0;
        fontString = new Font("", Font.BOLD, 30);
    }

    @Override
    public void sceneEnd() {
        //inputIP = null;
        buttonConfirm = null;
        buttonCancel = null;
        connectStatus = null;
        connectStatusGo = null;
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(img, 0, 0, Global.SCREEN_X, Global.SCREEN_Y, 0, 0, img.getWidth(null), img.getHeight(null), null);
        g.setColor(Color.white);
        g.setFont(fontString);

        //g.drawString("IP", inputIP.getX() - 50, inputIP.getY() + 40);

        if (Global.countClient >= 3 ) {
            this.connectStatus.paint(g);
            this.buttonCancel.paint(g);

            //System.out.println("ID測試: " + Client.getInstance().getClientID());
        } else if (Global.countClient >= 1){
            this.connectStatusGo.paint(g);
            this.buttonCancel.paint(g);
            if(isHost) {
                this.buttonConfirm.paint(g);
            }
        } else {
            this.connectStatus.paint(g);
        }
    }

    @Override
    public void update() {

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
            MouseTriggerImpl.mouseTrig(buttonConfirm, e, state);
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
//                if (c == KeyEvent.VK_ENTER) {
//                    if (labels.get(currentFocus).getIsFocus()) {
//                        labels.get(currentFocus).unHover();
//                        labels.get(currentFocus).unFocus();
//                        currentFocus = currentFocus + 1 >= labels.size() ? currentFocus : currentFocus + 1;
//                    }
//                    if (labels.get(currentFocus) instanceof EditText) {
//                        if (labels.get(currentFocus).getIsFocus()) {
//                            labels.get(currentFocus).unFocus();
//                            labels.get(currentFocus).unHover();
//                        } else {
//                            labels.get(currentFocus).isFocus();
//                        }
//                    } else {
//                        if (labels.get(currentFocus).getIsHover()) {
//                            labels.get(currentFocus).clickedActionPerformed();
//                        } else {
//                            labels.get(currentFocus).isHover();
//                        }
//                    }
//                }
            }
        };
    }

}
