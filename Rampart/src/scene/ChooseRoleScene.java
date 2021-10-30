package scene;

import Internet.Client;
import Internet.Servers;
import com.company.CommandSolver;
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
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static com.company.Global.*;


public class ChooseRoleScene extends Scene {

    private Image img;
    private Button buttonConfirm, buttonRole1, buttonRole2, buttonRole3, buttonRole4 ;
    private int currentFocus;
    private Font fontString;
    private boolean isOnlineMOde;
    private boolean isHost;
    private Player player;
    private Global.gameRole gamerole;
    private Label  roleIntro;
    private String serverIP;
    private Image title, introBack, titleChooseHero;

    public ChooseRoleScene(boolean isOnlineMOde, boolean isHost, String serverIP){
        this.isOnlineMOde = isOnlineMOde;
        this.isHost = isHost;
        this.player = new Player();
        this.serverIP = serverIP;
    }

    @Override
    public void sceneBegin() {
        title = SceneController.getInstance().imageController().tryGetImage(new Path().img().objs().rampart());
        introBack = SceneController.getInstance().imageController().tryGetImage(new Path().img().backgrounds().popIntroWindow());
        titleChooseHero = SceneController.getInstance().imageController().tryGetImage(new Path().img().objs().titleChooseHero());
        System.out.println("選角色場景開始");
        img = SceneController.getInstance().imageController().tryGetImage(new Path().img().backgrounds().main());
        gamerole = gameRole.NONE;
        //titleChooseRoleScene = new Label(WINDOW_WIDTH/2,100, Theme.get(20));
        roleIntro = new Label(WINDOW_WIDTH/2,200, Theme.get(13));

        buttonConfirm = new Button(WINDOW_WIDTH/2 -(WINDOW_WIDTH/5)+700 , WINDOW_HEIGHT/2 +WINDOW_HEIGHT/3+10, Theme.get(53)); //confirm
        buttonConfirm.setClickedActionPerformed((int x, int y) -> {
            AudioResourceController.getInstance().shot(new Path().sound().button());
            player.setHero(gamerole);


            if(isOnlineMOde) {
                if(isHost) {
                    serverIP = Servers.getInstance().getIP();
                    System.out.println("i am server");
                }
                try {
                    Client.getInstance().start(5200, serverIP);
                } catch (Exception e) {
                    e.printStackTrace();
                    SceneController.getInstance().change(new MenuScene());
                }
                SceneController.getInstance().change(new WaitingScene(isHost, player));
            } else {
                saveFile(player);
                SceneController.getInstance().change(new StatusScene(player,isHost));
            }

        });

        buttonRole1 = new Button(WINDOW_WIDTH/2 - 3*ROLEBUTTON_X/2, WINDOW_HEIGHT/2 +WINDOW_HEIGHT/9 -40 , Theme.get(16)); //role1
        buttonRole1.setClickedActionPerformed((int x, int y) -> {
            AudioResourceController.getInstance().shot(new Path().sound().button());
            gamerole = gameRole.ROLE1;
        });

        buttonRole2 = new Button(WINDOW_WIDTH/2 - ROLEBUTTON_X/2 , WINDOW_HEIGHT/2 +WINDOW_HEIGHT/9-40, Theme.get(17)); //role2
        buttonRole2.setClickedActionPerformed((int x, int y) -> {
            AudioResourceController.getInstance().shot(new Path().sound().button());
            gamerole = gameRole.ROLE2;

        });

        buttonRole3 = new Button(WINDOW_WIDTH/2 + ROLEBUTTON_X/2 , WINDOW_HEIGHT/2 +WINDOW_HEIGHT/9-40, Theme.get(18)); //role3
        buttonRole3.setClickedActionPerformed((int x, int y) -> {
            AudioResourceController.getInstance().shot(new Path().sound().button());
            gamerole = gameRole.ROLE3;
        });

        buttonRole4 = new Button(WINDOW_WIDTH/2 + 3*ROLEBUTTON_X/2 , WINDOW_HEIGHT/2 +WINDOW_HEIGHT/9-40, Theme.get(19)); //role4
        buttonRole4.setClickedActionPerformed((int x, int y) -> {
            AudioResourceController.getInstance().shot(new Path().sound().button());
            gamerole = gameRole.ROLE4;

        });




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
        gamerole = null;
        img = null;
        introBack = null;
        titleChooseHero = null;
    }

    public void saveFile( Player player){
        try {
            String temp = player.getHero().getValue();
            BufferedWriter bw = new BufferedWriter(new FileWriter("save.txt"));
            //一個一個寫的方式
            bw.write(temp);
            bw.newLine();
            bw.write(player.getLevel()+"");
            bw.newLine();
            bw.flush();
            bw.close();

        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }




    @Override
    public void paint(Graphics g) {
        g.drawImage(img, 0, 0, Global.SCREEN_X, Global.SCREEN_Y, 0, 0, img.getWidth(null), img.getHeight(null), null);
        g.drawImage(title, 250, WINDOW_HEIGHT-WINDOW_HEIGHT*93/100, WINDOW_WIDTH*2/3, WINDOW_HEIGHT/5, null);
        g.drawImage(introBack, WINDOW_WIDTH/2-650, WINDOW_HEIGHT/2-180, 1300, 550,null);
        g.drawImage(titleChooseHero, WINDOW_WIDTH/2-250, WINDOW_HEIGHT/2-150, 500, 100,null);
        g.setColor(Color.white);
        g.setFont(fontString);


        if(isOnlineMOde){
            switch(gamerole){
                case ROLE1:
                    g.drawString("Name: " + gameRole.ROLE1.getValue(), WINDOW_WIDTH/2-580, WINDOW_HEIGHT/2+220);
                    g.drawString("Ability: " + gameRole.ROLE1.getAbility2(), WINDOW_WIDTH/2-580, WINDOW_HEIGHT/2+260);
                    break;
                case ROLE2:
                    g.drawString("Name: " + gameRole.ROLE2.getValue(), WINDOW_WIDTH/2-580, WINDOW_HEIGHT/2+220);
                    g.drawString("Ability: " + gameRole.ROLE2.getAbility2(), WINDOW_WIDTH/2-580, WINDOW_HEIGHT/2+260);
                    break;
                case ROLE3:
                    g.drawString("Name: " + gameRole.ROLE3.getValue(), WINDOW_WIDTH/2-580, WINDOW_HEIGHT/2+220);
                    g.drawString("Ability: " + gameRole.ROLE3.getAbility2(), WINDOW_WIDTH/2-580, WINDOW_HEIGHT/2+260);
                    break;
                case ROLE4:
                    g.drawString("Name: " + gameRole.ROLE4.getValue(), WINDOW_WIDTH/2-580, WINDOW_HEIGHT/2+220);
                    g.drawString("Ability: " + gameRole.ROLE4.getAbility2(), WINDOW_WIDTH/2-580, WINDOW_HEIGHT/2+260);
                    break;
                default:
                    break;
            }
        } else {
            switch (gamerole) {
                case ROLE1:
                    g.drawString("Name: " + gameRole.ROLE1.getValue(), WINDOW_WIDTH / 2 - 560, WINDOW_HEIGHT / 2 + 220);
                    g.drawString("Ability: " + gameRole.ROLE1.getAbility(), WINDOW_WIDTH / 2 - 560, WINDOW_HEIGHT / 2 + 260);
                    break;
                case ROLE2:
                    g.drawString("Name: " + gameRole.ROLE2.getValue(), WINDOW_WIDTH / 2 - 560, WINDOW_HEIGHT / 2 + 220);
                    g.drawString("Ability: " + gameRole.ROLE2.getAbility(), WINDOW_WIDTH / 2 - 560, WINDOW_HEIGHT / 2 + 260);
                    break;
                case ROLE3:
                    g.drawString("Name: " + gameRole.ROLE3.getValue(), WINDOW_WIDTH / 2 - 560, WINDOW_HEIGHT / 2 + 220);
                    g.drawString("Ability: " + gameRole.ROLE3.getAbility(), WINDOW_WIDTH / 2 - 560, WINDOW_HEIGHT / 2 + 260);
                    break;
                case ROLE4:
                    g.drawString("Name: " + gameRole.ROLE4.getValue(), WINDOW_WIDTH / 2 - 560, WINDOW_HEIGHT / 2 + 220);
                    g.drawString("Ability: " + gameRole.ROLE4.getAbility(), WINDOW_WIDTH / 2 - 560, WINDOW_HEIGHT / 2 + 260);
                    break;
                default:
                    break;
            }
        }
        if(gamerole != gameRole.NONE) {
               this.buttonConfirm.paint(g);
        }
//        if(gamerole == gameRole.NONE) {
//            //this.titleChooseRoleScene.paint(g);
//        } else {
//            titleDesideRoleScene = new Label(WINDOW_WIDTH/2,100, new Style.StyleRect(WINDOW_WIDTH/2,60, true, new BackgroundType.BackgroundColor(new Color(17, 61, 143, 0)))
//                    .setTextColor(new Color(17, 143, 61))
//                    .setHaveBorder(true)
//                    .setBorderColor(new Color(215, 186, 50,0))
//                    .setBorderThickness(10)
//                    .setTextFont(new Font("", Font.TYPE1_FONT, 70))
//                    .setText("You have chosen " + gamerole.getValue()));
//            this.titleDesideRoleScene.paint(g);
//
//            this.roleIntro.paint(g);
//        }
        this.buttonRole1.paint(g);
        this.buttonRole2.paint(g);
        this.buttonRole3.paint(g);
        this.buttonRole4.paint(g);
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

            }
        };
    }


}
