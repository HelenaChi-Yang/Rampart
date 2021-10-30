package scene;

import com.company.Global;
import controllers.AudioResourceController;
import controllers.SceneController;
import gameObject.Player;
import menu.Button;
import menu.Theme;
import menu.impl.MouseTriggerImpl;
import com.company.CommandSolver;
import com.company.Path;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static com.company.Global.WINDOW_HEIGHT;
import static com.company.Global.WINDOW_WIDTH;

public class SingleModeScene extends Scene{

    private Button buttonNewGame, buttonLoadGame;
    private Image background;
    private Image title;

    @Override
    public void sceneBegin() {
        System.out.println("單人模式場景開始");
        background = SceneController.getInstance().imageController().tryGetImage(new Path().img().backgrounds().main());
        title = SceneController.getInstance().imageController().tryGetImage(new Path().img().objs().rampart());
        buttonNewGame = new Button(WINDOW_WIDTH/2 -(WINDOW_WIDTH/5), WINDOW_HEIGHT/2 +140, Theme.get(5)); // 新遊戲
        buttonNewGame.setClickedActionPerformed((int x, int y) -> {
            AudioResourceController.getInstance().shot(new Path().sound().button());
            SceneController.getInstance().change(new ChooseRoleScene(false,false,""));
            //SceneController.getInstance().change(new InputNameScene(false,false ,""));
        });
        buttonLoadGame = new Button(WINDOW_WIDTH/2 +(WINDOW_WIDTH/5), WINDOW_HEIGHT/2 +140, Theme.get(6)); //遊戲說明
        buttonLoadGame.setClickedActionPerformed((int x, int y) -> {
            AudioResourceController.getInstance().shot(new Path().sound().button());
            Player player = readFile();
            SceneController.getInstance().change(new StatusScene(player, false));
        });
    }

    @Override
    public void sceneEnd() {
        buttonNewGame = null;
        buttonLoadGame = null;
        background = null;
        title = null;
        System.out.println("單人模式場景結束");
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(background, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, null);
        g.drawImage(title, 250, WINDOW_HEIGHT-WINDOW_HEIGHT*93/100, WINDOW_WIDTH*2/3, WINDOW_HEIGHT/5, null);
//        p1.paint(Direction.RIGHT, WINDOW_WIDTH-WINDOW_WIDTH*8/9, WINDOW_HEIGHT-WINDOW_HEIGHT*30/100, WINDOW_WIDTH-WINDOW_WIDTH*8/9+100, WINDOW_HEIGHT-WINDOW_HEIGHT*30/100+100, g);
//        p2.paint(Direction.RIGHT, WINDOW_WIDTH-WINDOW_WIDTH*7/9, WINDOW_HEIGHT-WINDOW_HEIGHT*30/100, WINDOW_WIDTH-WINDOW_WIDTH*7/9+100, WINDOW_HEIGHT-WINDOW_HEIGHT*30/100+100, g);
//        p3.paint(Direction.LEFT, WINDOW_WIDTH-WINDOW_WIDTH*2/9, WINDOW_HEIGHT-WINDOW_HEIGHT*30/100, WINDOW_WIDTH-WINDOW_WIDTH*2/9+100, WINDOW_HEIGHT-WINDOW_HEIGHT*30/100+80, g);
        //a.paint(g);
        buttonNewGame.paint(g);
        buttonLoadGame.paint(g);

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
            MouseTriggerImpl.mouseTrig(buttonNewGame, e, state);
            MouseTriggerImpl.mouseTrig(buttonLoadGame, e, state);
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


    public Player readFile(){

        try{
            Player tmp = new Player();
            BufferedReader br = new BufferedReader(new FileReader("save.txt"));
            if(br.ready()){
                switch (br.readLine()){
                    case "Heisenberg":
                        tmp.setHero(Global.gameRole.ROLE1);
                        break;
                    case "Thor":
                        tmp.setHero(Global.gameRole.ROLE2);
                        break;
                    case "Stone Man":
                        tmp.setHero(Global.gameRole.ROLE3);
                        break;
                    case "Medusa":
                        tmp.setHero(Global.gameRole.ROLE4);
                        break;
                    default:
                        tmp.setHero(Global.gameRole.ROLE1);
                }

            }
            if(br.ready()){
                tmp.setLevel(Integer.parseInt(br.readLine()));
            }
            br.close();
            return tmp;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }
}
