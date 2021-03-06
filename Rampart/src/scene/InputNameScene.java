package scene;

import Internet.Client;
import Internet.Servers;
import controllers.AudioResourceController;
import controllers.SceneController;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.*;

import gameObject.Player;
import menu.*;
import menu.impl.MouseTriggerImpl;
import scene.Scene;
import com.company.CommandSolver;
import com.company.CommandSolver.MouseCommandListener;
import com.company.Global.*;

import com.company.Global;
import com.company.Path;
import java.awt.Image;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static com.company.Global.WINDOW_HEIGHT;
import static com.company.Global.WINDOW_WIDTH;

public class InputNameScene extends Scene {

    private Image img;
    private ArrayList<Label> labels;
    private EditText inputName;
    private Label titleInputName;
    private Button buttonConfirm;
    private int currentFocus;
    private Font fontString;
    private boolean isOnlineMOde;
    private String serverIP;
    private boolean isHost;

    public InputNameScene(boolean isOnlineMOde, boolean isHost,String serverIP){
        this.isOnlineMOde = isOnlineMOde;
        this.serverIP = serverIP;
        this.isHost = isHost;
    }

    @Override
    public void sceneBegin() {

        System.out.println("輸入名字場景開始");
        img = SceneController.getInstance().imageController().tryGetImage(new Path().img().backgrounds().main());
        inputName = this.addEditText(Global.SCREEN_X / 2, Global.SCREEN_Y / 3 + 50, "Please enter your name");
        inputName.setEditLimit(12);
        //inputIP = this.addEditText(Global.SCREEN_X / 2, Global.SCREEN_Y / 3 + 150, "Please enter IP");
        buttonConfirm = new Button(WINDOW_WIDTH/2 -(WINDOW_WIDTH/5) , WINDOW_HEIGHT/2 +WINDOW_HEIGHT/3, Theme.get(9)); //confirm
        buttonConfirm.setClickedActionPerformed((int x, int y) -> {
            //Player player = new Player(inputName.getEditText());
            Player player = new Player();
            AudioResourceController.getInstance().shot(new Path().sound().button());

            if(isOnlineMOde) {
                if(serverIP == "") {
                    serverIP = Servers.getInstance().getIP();
                    System.out.println("i am server");
                }
                Client.getInstance().start(5200,serverIP);
                SceneController.getInstance().change(new ChooseRoleScene(isOnlineMOde, isHost,serverIP));    // 雙人

            } else {
                saveFile(player);
                SceneController.getInstance().change(new ChooseRoleScene(false, false,""));   // 單人
            }
        });

        labels = new ArrayList<>();
        labels.add(inputName);
        //labels.add(inputIP);
//        labels.add(back);
//        labels.add(confirm);
        fontString = new Font("", Font.BOLD, 30);
    }

    @Override
    public void sceneEnd() {
        inputName = null;
        //inputIP = null;
        buttonConfirm = null;
        labels = null;
        img = null;
        titleInputName = null;
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(img, 0, 0, Global.SCREEN_X, Global.SCREEN_Y, 0, 0, img.getWidth(null), img.getHeight(null), null);
        g.setColor(Color.RED);
        g.setFont(fontString);
        g.drawString("Name: ", inputName.getX() - 100, inputName.getY() + 40);
        //g.drawString("IP", inputIP.getX() - 50, inputIP.getY() + 40);
        this.inputName.paint(g);
        //this.inputIP.paint(g);
        this.buttonConfirm.paint(g);

        titleInputName = new Label(WINDOW_WIDTH/2,100, new Style.StyleRect(WINDOW_WIDTH/2,60, true, new BackgroundType.BackgroundColor(new Color(17, 61, 143, 0)))
                .setTextColor(new Color(178, 39, 15))
                .setHaveBorder(true)
                .setBorderColor(new Color(215, 186, 50,0))
                .setBorderThickness(10)
                .setTextFont(new Font("", Font.TYPE1_FONT, 70))
                .setText("Please enter your name"));
        this.titleInputName.paint(g);

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
            MouseTriggerImpl.mouseTrig(inputName, e, state);
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
                inputName.keyTyped(c);
                //inputIP.keyTyped(c);
            }
        };
    }

    public void saveFile( Player player){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("save.txt"));
            //一個一個寫的方式

            bw.write(player.getLevel()+"");
            bw.newLine();
            bw.flush();
            bw.close();

        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }

}
