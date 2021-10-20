package scene;

import com.company.CommandSolver;
import com.company.Global;
import com.company.Path;
import controllers.AudioResourceController;
import controllers.SceneController;
import menu.*;
import menu.Button;
import menu.Label;
import menu.impl.MouseTriggerImpl;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import static com.company.Global.*;


public class ChooseRoleScene extends Scene {

    private Image img;
    private ArrayList<Label> labels;
    private Button buttonConfirm, buttonRole1, buttonRole2, buttonRole3, buttonRole4 ;
    private int currentFocus;
    private Font fontString;
    private boolean isOnlineMOde;
    private String serverIP;
    private boolean isHost;
    private Global.gameRole gamerole;
    private Label titleChooseRoleScene;

//    public ChooseRoleScene(boolean isOnlineMOde, boolean isHost,String serverIP){
//        this.isOnlineMOde = isOnlineMOde;
//        this.serverIP = serverIP;
//        this.isHost = isHost;
//    }

    @Override
    public void sceneBegin() {

        System.out.println("選角色場景開始");
        img = SceneController.getInstance().imageController().tryGetImage(new Path().img().backgrounds().main());
        titleChooseRoleScene = new Label(WINDOW_WIDTH/2,40, Theme.get(20));


        buttonConfirm = new Button(WINDOW_WIDTH/2 -(WINDOW_WIDTH/5) , WINDOW_HEIGHT/2 +WINDOW_HEIGHT/3, Theme.get(9)); //confirm
        buttonConfirm.setClickedActionPerformed((int x, int y) -> {
            AudioResourceController.getInstance().shot(new Path().sound().button());
        });

        buttonRole1 = new Button(WINDOW_WIDTH/2 - 3*ROLEBUTTON_X/2, WINDOW_HEIGHT/2 +WINDOW_HEIGHT/9 , Theme.get(16)); //role1
        buttonRole1.setClickedActionPerformed((int x, int y) -> {
            AudioResourceController.getInstance().shot(new Path().sound().button());
            gamerole = gameRole.ROLE1;
        });

        buttonRole2 = new Button(WINDOW_WIDTH/2 - ROLEBUTTON_X/2 , WINDOW_HEIGHT/2 +WINDOW_HEIGHT/9, Theme.get(17)); //role2
        buttonRole2.setClickedActionPerformed((int x, int y) -> {
            AudioResourceController.getInstance().shot(new Path().sound().button());
            gamerole = gameRole.ROLE2;
        });

        buttonRole3 = new Button(WINDOW_WIDTH/2 + ROLEBUTTON_X/2 , WINDOW_HEIGHT/2 +WINDOW_HEIGHT/9, Theme.get(18)); //role3
        buttonRole3.setClickedActionPerformed((int x, int y) -> {
            AudioResourceController.getInstance().shot(new Path().sound().button());
            gamerole = gameRole.ROLE3;
        });

        buttonRole4 = new Button(WINDOW_WIDTH/2 + 3*ROLEBUTTON_X/2 , WINDOW_HEIGHT/2 +WINDOW_HEIGHT/9, Theme.get(19)); //role4
        buttonRole4.setClickedActionPerformed((int x, int y) -> {
            AudioResourceController.getInstance().shot(new Path().sound().button());
            gamerole = gameRole.ROLE4;
        });



        labels = new ArrayList<>();
        labels.add(titleChooseRoleScene);
        //labels.add(inputIP);
//        labels.add(back);
//        labels.add(confirm);
        currentFocus = 0;
        fontString = new Font("", Font.BOLD, 30);
    }

    @Override
    public void sceneEnd() {
        //inputIP = null;
        buttonConfirm = null;
        buttonRole1 = null;
        buttonRole2 = null;
        buttonRole3 = null;
        buttonRole4 = null;

        labels = null;
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(img, 0, 0, Global.SCREEN_X, Global.SCREEN_Y, 0, 0, img.getWidth(null), img.getHeight(null), null);
        g.setColor(Color.white);
        g.setFont(fontString);
        //g.drawString("Name", inputName.getX() - 100, inputName.getY() + 40);
        //g.drawString("IP", inputIP.getX() - 50, inputIP.getY() + 40);
        //this.inputName.paint(g);
        //this.inputIP.paint(g);
        this.buttonConfirm.paint(g);
        this.buttonRole1.paint(g);
        this.buttonRole2.paint(g);
        this.buttonRole3.paint(g);
        this.buttonRole4.paint(g);
        this.titleChooseRoleScene.paint(g);
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
            //MouseTriggerImpl.mouseTrig(inputName, e, state);
            //MouseTriggerImpl.mouseTrig(inputIP, e, state);
            MouseTriggerImpl.mouseTrig(buttonRole1, e, state);
            MouseTriggerImpl.mouseTrig(buttonRole2, e, state);
            MouseTriggerImpl.mouseTrig(buttonRole3, e, state);
            MouseTriggerImpl.mouseTrig(buttonRole4, e, state);

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
                if (c == KeyEvent.VK_ENTER) {
                    if (labels.get(currentFocus).getIsFocus()) {
                        labels.get(currentFocus).unHover();
                        labels.get(currentFocus).unFocus();
                        currentFocus = currentFocus + 1 >= labels.size() ? currentFocus : currentFocus + 1;
                    }
                    if (labels.get(currentFocus) instanceof EditText) {
                        if (labels.get(currentFocus).getIsFocus()) {
                            labels.get(currentFocus).unFocus();
                            labels.get(currentFocus).unHover();
                        } else {
                            labels.get(currentFocus).isFocus();
                        }
                    } else {
                        if (labels.get(currentFocus).getIsHover()) {
                            labels.get(currentFocus).clickedActionPerformed();
                        } else {
                            labels.get(currentFocus).isHover();
                        }
                    }
                }
                //inputName.keyTyped(c);
                //inputIP.keyTyped(c);
            }
        };
    }


}
