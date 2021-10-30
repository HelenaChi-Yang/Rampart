package scene;

import com.company.CommandSolver;
import com.company.Global;
import com.company.Path;
import controllers.AudioResourceController;
import controllers.SceneController;
import gameObject.DefenseTower.ArcherTower;
import gameObject.DefenseTower.CannonTower;
import gameObject.DefenseTower.DefenseTower;
import gameObject.DefenseTower.MagicTower;
import menu.*;
import menu.Button;
import menu.Label;
import menu.impl.MouseTriggerImpl;

import java.awt.*;
import java.awt.event.MouseEvent;

import static com.company.Global.*;

import static gameObject.DefenseTower.DefenseTower.TowerState.Basic;


public class PopupBuildScene extends PopupWindow {
    private Image background;
    private Button archerButton, cannonButton, magicButton;
    private Label archerPrice, cannonPrice, magicPrice;

    public PopupBuildScene(int x, int y, int width, int height) {
        super(x, y, width, height);

    }

    @Override
    public void sceneBegin() {
        System.out.println("scene: " + getX() + "," + getY());
        background = SceneController.getInstance().imageController().tryGetImage(new Path().img().backgrounds().popWindow());
        archerButton = new menu.Button(getWidth() / 2, 5, Theme.get(39)); // 弓箭塔
        archerButton.setClickedActionPerformed((int x, int y) -> { // 按下後
            AudioResourceController.getInstance().shot(new Path().sound().gameButton());
            if (MainScene.player.getMoney() >= ARCHERTOWERCOST) {
                MainScene.newDefenseTower(new ArcherTower(getLeft() + 24, getTop() + 24, 64, 64, 3.0, DefenseTower.TowerType.ArcherTower, Basic));
                MainScene.player.addMoney(-ARCHERTOWERCOST);
//                PopupTowerScene().sellNum+=ARCHERTOWERCOST*0.8;
                MapTest.closeButton(getX(), getY());
                System.out.println("蓋塔function");
                hide();
            }
        });
        archerPrice = new Label(getWidth() / 2, -20, new Style.StyleRect(80, 40, true, new BackgroundType.BackgroundColor(new Color(0, 0, 0, 0)))
                .setTextColor(new Color(87, 36, 2))
                .setHaveBorder(true)
                .setBorderColor(new Color(215, 186, 50, 0))
                .setBorderThickness(10)
                .setTextFont(new Font("", Font.TYPE1_FONT, 14))
                .setText("Archer tower $" + ARCHERTOWERCOST));


        cannonButton = new Button(getWidth() / 2, getHeight() - 5, Theme.get(40)); // 火炮塔
        cannonButton.setClickedActionPerformed((int x, int y) -> {  // 按下後
            AudioResourceController.getInstance().shot(new Path().sound().gameButton());
            if (MainScene.player.getMoney() >= CANNONTOWERCOST) {
                MainScene.player.addMoney(-CANNONTOWERCOST);
                MainScene.newDefenseTower(new CannonTower(getLeft() + 24, getTop() + 24, 64, 64, 3.0, DefenseTower.TowerType.CannonTower, Basic));
                MapTest.closeButton(getX(), getY());
//                PopupTowerScene.sellNum+=CANNONTOWERCOST*0.8;
                System.out.println("蓋塔function");
                hide();
            }
        });
        cannonPrice = new Label(getWidth() / 2, getHeight() + 25, new Style.StyleRect(80, 40, true, new BackgroundType.BackgroundColor(new Color(0, 0, 0, 0)))
                .setTextColor(new Color(87, 36, 2))
                .setHaveBorder(true)
                .setBorderColor(new Color(215, 186, 50, 0))
                .setBorderThickness(10)
                .setTextFont(new Font("", Font.TYPE1_FONT, 14))
                .setText("Cannon tower $" + CANNONTOWERCOST));


        magicButton = new Button(0, getHeight() / 2, Theme.get(41)); // 魔法塔
        magicButton.setClickedActionPerformed((int x, int y) -> {  // 按下後
            AudioResourceController.getInstance().shot(new Path().sound().gameButton());
            if (MainScene.player.getMoney() >= MAGICTOWERCOST) {
                MainScene.player.addMoney(-MAGICTOWERCOST);
                MainScene.newDefenseTower(new MagicTower(getLeft() + 24, getTop() + 24, 64, 64, 3.0, DefenseTower.TowerType.MagicTower, Basic));
                MapTest.closeButton(getX(), getY());
                System.out.println("蓋塔function");
//                PopupTowerScene.sellNum+=MAGICTOWERCOST*0.8;
                hide();
            }
        });
        magicPrice = new Label(-90, getHeight() / 2, new Style.StyleRect(80, 40, true, new BackgroundType.BackgroundColor(new Color(0, 0, 0, 0)))
                .setTextColor(new Color(87, 36, 2))
                .setHaveBorder(true)
                .setBorderColor(new Color(215, 186, 50, 0))
                .setBorderThickness(10)
                .setTextFont(new Font("", Font.TYPE1_FONT, 14))
                .setText("Magic tower $" + MAGICTOWERCOST));


    }

    @Override
    public void sceneEnd() {
        background = null;
        archerButton = null;
        cannonButton = null;
        magicButton = null;
        magicPrice = null;
        cannonPrice = null;
        archerPrice = null;
    }

    @Override
    public void paintWindow(Graphics g) {
        g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
        archerButton.paint(g);
        cannonButton.paint(g);
        magicButton.paint(g);
        archerPrice.paint(g);
        cannonPrice.paint(g);
        magicPrice.paint(g);
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
        MouseTriggerImpl.mouseTrig(archerButton, e, state);
        MouseTriggerImpl.mouseTrig(cannonButton, e, state);
        MouseTriggerImpl.mouseTrig(magicButton, e, state);
    }

}
