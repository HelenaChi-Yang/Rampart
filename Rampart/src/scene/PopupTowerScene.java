package scene;

import java.awt.*;
import java.awt.event.MouseEvent;

import controllers.AudioResourceController;
import controllers.SceneController;
import menu.*;
import menu.Button;
import menu.impl.MouseTriggerImpl;
import com.company.CommandSolver;
import com.company.Path;

import static com.company.Global.*;

public class PopupTowerScene extends PopupWindow {

    private Image background;
    private Button levelUpButton, sellButton;


    public PopupTowerScene(int x, int y, int width, int height) {
        super(x, y, width, height);

    }

    @Override
    public void sceneBegin() {
//        System.out.println("彈出");
        background = SceneController.getInstance().imageController().tryGetImage(new Path().img().backgrounds().popWindow());
        levelUpButton = new Button(getWidth()/2 , 5, Theme.get(14)); // 升級
        levelUpButton.setClickedActionPerformed((int x, int y) -> { // 按下後
            AudioResourceController.getInstance().shot(new Path().sound().gameButton());
            System.out.println("升級喔");
        });
        sellButton = new Button(getWidth()/2 , getHeight()-5, Theme.get(15)); // 賣塔
        sellButton.setClickedActionPerformed((int x, int y) -> {  // 按下後
            AudioResourceController.getInstance().shot(new Path().sound().gameButton());
            System.out.println("炸塔喔");
        });

    }

    @Override
    public void sceneEnd() {
        background = null;
        levelUpButton = null;
        sellButton = null;
    }

    @Override
    public void paintWindow(Graphics g) {
        g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
        levelUpButton.paint(g);
        sellButton.paint(g);
    }

    @Override
    public void update() {

    }

    @Override
    public CommandSolver.KeyListener keyListener() {
        return null;
    }

    @Override
    protected void mouseTrig(MouseEvent e, CommandSolver.MouseState state, long trigTime) {
        MouseTriggerImpl.mouseTrig(levelUpButton, e, state);
        MouseTriggerImpl.mouseTrig(sellButton, e, state);
    }
}
