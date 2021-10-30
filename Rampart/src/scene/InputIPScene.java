package scene;

import Internet.Client;
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

import static com.company.Global.WINDOW_HEIGHT;
import static com.company.Global.WINDOW_WIDTH;


public class InputIPScene extends Scene{
    private Image img;
    private ArrayList<Label> labels;
    private Label titleInputIP;
    private EditText inputIP;
    private Button buttonConfirm;
    private int currentFocus;
    private Font fontString;
    private String serverIP;

    @Override
    public void sceneBegin() {
        System.out.println("輸入IP場景開始");
        img = SceneController.getInstance().imageController().tryGetImage(new Path().img().backgrounds().main());
        inputIP = this.addEditText(Global.SCREEN_X / 2, Global.SCREEN_Y / 3 + 50, "Please enter server IP");
        inputIP.setEditLimit(12);
        //inputIP = this.addEditText(Global.SCREEN_X / 2, Global.SCREEN_Y / 3 + 150, "Please enter IP");
        buttonConfirm = new Button(WINDOW_WIDTH/2 +(WINDOW_WIDTH/5 ), WINDOW_HEIGHT/2 +WINDOW_HEIGHT/3 -20, Theme.get(9)); //confirm
        buttonConfirm.setClickedActionPerformed((int x, int y) -> {
            AudioResourceController.getInstance().shot(new Path().sound().button());
            serverIP =inputIP.getEditText();
            System.out.println("serverIP: " + serverIP);
//            Client.getInstance().start(5200,serverIP);
            SceneController.getInstance().change(new ChooseRoleScene(true,false,serverIP));
        });

        labels = new ArrayList<>();
        labels.add(inputIP);
        //labels.add(inputIP);
//        labels.add(back);
//        labels.add(confirm);
        currentFocus = 0;
        fontString = new Font("", Font.BOLD, 30);
    }

    @Override
    public void sceneEnd() {
        inputIP = null;
        //inputIP = null;
        buttonConfirm = null;
        labels = null;
        img = null;
        titleInputIP = null;
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(img, 0, 0, Global.SCREEN_X, Global.SCREEN_Y, 0, 0, img.getWidth(null), img.getHeight(null), null);
        g.setColor(Color.red);
        g.setFont(fontString);
        g.drawString("Please enter server IP", inputIP.getX() , inputIP.getY()-40 );
        //g.drawString("IP", inputIP.getX() - 50, inputIP.getY() + 40);
        this.inputIP.paint(g);
        //this.inputIP.paint(g);
        this.buttonConfirm.paint(g);
//        titleInputIP = new Label(WINDOW_WIDTH/2,100, new Style.StyleRect(WINDOW_WIDTH/2,60, true, new BackgroundType.BackgroundColor(new Color(17, 61, 143, 0)))
//                .setTextColor(new Color(178, 39, 15))
//                .setHaveBorder(true)
//                .setBorderColor(new Color(215, 186, 50,0))
//                .setBorderThickness(10)
//                .setTextFont(new Font("", Font.TYPE1_FONT, 70))
//                .setText("Please enter server IP"));
        //this.titleInputIP.paint(g);
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
            MouseTriggerImpl.mouseTrig(inputIP, e, state);
            //MouseTriggerImpl.mouseTrig(inputIP, e, state);
            MouseTriggerImpl.mouseTrig(buttonConfirm, e, state);

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
                inputIP.keyTyped(c);
                //inputIP.keyTyped(c);
            }
        };
    }

}