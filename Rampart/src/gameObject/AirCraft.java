package gameObject;
import controllers.AudioResourceController;
import menu.Button;
import controllers.SceneController;
import menu.Label;
import menu.Theme;
import menu.impl.MouseTriggerImpl;
import scene.PopupTowerScene;
import scene.SingleModeScene;
import com.company.CommandSolver;
import com.company.Path;

import java.awt.*;
import java.awt.event.MouseEvent;

import static com.company.Global.*;

public class AirCraft extends  GameObject implements  CommandSolver.MouseCommandListener {

    private Image img;
//    private Button button;
    private PopupTowerScene popupTowerScene;
    private Life life;


    public AirCraft(int x, int y) {
        super(x, y, 50, 50);
        mouseButton = new Button(x , y, Theme.get(13));
        setButton();
        //img = ImageResourceController.instance().tryGetImage(new Path().img().actors().airCraft());
        img = SceneController.getInstance().imageController().tryGetImage(new Path().img().actors().airCraft());
//        popupTowerScene = new PopupTowerScene(x,y,POPWINDOW_WIDTH,POPWINDOW_HEIGHT);
//        life =new Life(x - painter().width()/2, y - painter().height()/2 -5, TOWERBLOOD);
        //button = new Button(50, 50, Theme.get(0));
    }

    public void setButton() {
        mouseButton.setClickedActionPerformed((int x, int y) -> {
            AudioResourceController.getInstance().shot(new Path().sound().gameButton());
            popupTowerScene.sceneBegin();
            popupTowerScene.show();
            popupTowerScene.setCancelable();
        });

    }







    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(img, painter().left(), painter().top(), painter().width(), painter().height(),null); // 給左上角座標 和 寬高
        this.mouseButton.paint(g);
        if (popupTowerScene.isShow()) {
            popupTowerScene.paint(g);
        }
        life.draw(g);
    }


    @Override
    public void update() {
        //每秒跑UPDATE_TIMES_PER_SEC次

    }

    @Override
    public void mouseTrig(MouseEvent e, CommandSolver.MouseState state, long trigTime) {  // 抓滑鼠在動的時候鼠標的位置
        if (!popupTowerScene.isShow()) {
            MouseTriggerImpl.mouseTrig(mouseButton, e, state);
        }
        if (popupTowerScene.isCancelable() && state == CommandSolver.MouseState.PRESSED) { //滑鼠點外面他會hide()
            popupTowerScene.isCancelableHide(e, state, trigTime);
        }

        if (state == CommandSolver.MouseState.PRESSED) {
            life.life--;  // test
        }


    }



//    @Override
//    public CommandSolver.MouseCommandListener mouseListener() {
//        return (MouseEvent e, CommandSolver.MouseState state, long trigTime) -> {
//            //MouseTriggerImpl.mouseTrig(a, e, state);
//            MouseTriggerImpl.mouseTrig(buttonSingleMode, e, state);
//            MouseTriggerImpl.mouseTrig(buttonHowToPlay, e, state);
//            MouseTriggerImpl.mouseTrig(buttonOnlineMode, e, state);
//            MouseTriggerImpl.mouseTrig(buttonExit, e, state);
//            //MouseTriggerImpl.mouseTrig(airCraft, e, state);
//            airCraft.mouseTrig(e,state,trigTime);
//            //MouseTriggerImpl.mouseTrig(ee, e, state);
//            /*if (testPop.isShow()) {
//                testPop.mouseListener().mouseTrig(e, state, trigTime);
//            }*/
//        };
//    }
}

