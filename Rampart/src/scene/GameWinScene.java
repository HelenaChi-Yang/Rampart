package scene;

import Internet.Servers;
import com.company.CommandSolver;
import com.company.Global;
import com.company.Path;
import controllers.AudioResourceController;
import controllers.ImageResourceController;
import controllers.SceneController;
import gameObject.Player;
import menu.*;
import menu.Button;
import menu.Label;
import menu.impl.MouseTriggerImpl;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.IOException;

import static com.company.Global.WINDOW_HEIGHT;
import static com.company.Global.WINDOW_WIDTH;

public class GameWinScene extends Scene {

    private Image background, talk;
    private String name;
    private String[] s = new String[]{"恭喜你破關 辛苦你了 喔耶  cool  yayayaya "};
    private Label language1, language2;
    private Button buttonCancel;
    private Player player;
    private boolean isOnlineMode;
    private boolean isHost;

    public GameWinScene(Player player, boolean isOnlineMode, boolean isHost) {

        this.player = player;
        this.isOnlineMode = isOnlineMode;
        this.isHost = isHost;
    }






    @Override
    public void sceneBegin() {
        player.setLevel(player.getLevel() + 1);
        AudioResourceController.getInstance().stop(new Path().sound().battle());
        AudioResourceController.getInstance().loop(new Path().sound().gameWin(), 99);
        if (player.getLevel() > 3) {
            background = SceneController.getInstance().imageController().tryGetImage(new Path().img().backgrounds().gameWinAll());
            talk = SceneController.getInstance().imageController().tryGetImage(new Path().img().backgrounds().talk());
            language1 = new Label(700,300,Theme.get(20));
            language2 = new Label(635,350,Theme.get(57));
        } else {
            background = SceneController.getInstance().imageController().tryGetImage(new Path().img().backgrounds().gameWin());
        }

        //AudioResourceController.getInstance().loop(new Path().sound().battle());

        buttonCancel = new Button(1550, 30, Theme.get(51));
        buttonCancel.setClickedActionPerformed((int x, int y) -> {

            AudioResourceController.getInstance().shot(new Path().sound().button());
            AudioResourceController.getInstance().stop(new Path().sound().gameWin()); // 停音樂

            if(isOnlineMode) {
                if(isHost) {
//                    try {
//                        Servers.getInstance().closeConnect();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
                }
                SceneController.getInstance().change(new MenuScene());    //回主選單
            }else {
                if (player.getLevel() > 3) {
                    SceneController.getInstance().change(new MenuScene());
                } else {
                    SceneController.getInstance().change(new StatusScene(player,false));
                }
            }
        });



    }

    @Override
    public void sceneEnd() {
        background = null;
        talk = null;
        buttonCancel = null;
        language1 = null;
        language2 = null;
        player = null;

    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(background, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, null);

//        g.setColor(Color.BLUE);
//        g.drawString("Thank you for saving our country", 400 , 250 );
//        g.drawString("You are really a hero", 400 , 280 );
        if (player.getLevel() > 3) {
            g.drawImage(talk, 400, 245, 600, 200, null);
            language1.paint(g);
            language2.paint(g);
        }

        buttonCancel.paint(g);
        //g.drawString( new WriterType(new ImageResourceController().tryGetImage(new Path().img().backgrounds().main()),s,50,5000) );      , 400, 60  );
    }

    @Override
    public void update() {
//        language = new Label(WINDOW_WIDTH/2,100, new Style.StyleRect(WINDOW_WIDTH/2,60, true, new BackgroundType.BackgroundColor(new Color(17, 61, 143, 0)))
//                .setTextColor(new Color(178, 39, 15))
//                .setHaveBorder(true)
//                .setBorderColor(new Color(215, 186, 50,0))
//                .setBorderThickness(10)
//                .setTextFont(new Font("", Font.TYPE1_FONT, 70))
//                .setText("Please enter server IP"));
    }

    @Override
    public CommandSolver.MouseCommandListener mouseListener() {

        return (MouseEvent e, CommandSolver.MouseState state, long trigTime) -> {

            MouseTriggerImpl.mouseTrig(buttonCancel, e, state);

        };
    }

    @Override
    public CommandSolver.KeyListener keyListener() {
        return null;
    }
}
