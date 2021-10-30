package scene;

import java.awt.*;
import java.awt.event.MouseEvent;

import com.sun.security.auth.UnixNumericGroupPrincipal;
import controllers.AudioResourceController;
import controllers.SceneController;
import gameObject.DefenseTower.DefenseTower;
import gameObject.DefenseTower.MagicTower;
import gameObject.Player;
import menu.*;
import menu.Button;
import menu.Label;
import menu.impl.MouseTriggerImpl;
import com.company.CommandSolver;
import com.company.Path;

import static com.company.Global.*;

public class PopupTowerScene extends PopupWindow {

    private Image background;
    private Button button1, button2, button3, button4;
    private Label label1, label2, label3, label4;
    private DefenseTower.TowerType towerType;
    private DefenseTower.TowerState towerState;
    private DefenseTower defenseTower;
    private double towerLevel;
    private int upgradeNum;


    public PopupTowerScene(int x, int y, int width, int height, DefenseTower defenseTower) {
        super(x, y, width, height);
        this.towerType = defenseTower.getTowerType();
        this.towerState = defenseTower.getTowerState();
        this.towerLevel = defenseTower.getTowerLevel();
        this.defenseTower = defenseTower;

    }

    @Override
    public void sceneBegin() {
        Player player = MainScene.player;
        background = SceneController.getInstance().imageController().tryGetImage(new Path().img().backgrounds().popWindow());
        System.out.println("Scene: " + towerLevel + "," + towerType + "," + towerState);

        if (towerLevel == 1.0) {
            if (towerState == DefenseTower.TowerState.Basic) {
                if (towerType == DefenseTower.TowerType.MagicTower) {
                    upgradeNum = MAGICTOWER1TO2;
                    defenseTower.sellNum += (int) (MAGICTOWERCOST * 0.8);
                } else if (towerType == DefenseTower.TowerType.ArcherTower) {
                    upgradeNum = ARCHERTOWER1TO2;
                    defenseTower.sellNum += (int) (ARCHERTOWERCOST * 0.8);
                } else if (towerType == DefenseTower.TowerType.CannonTower) {
                    upgradeNum = CANNONTOWER1TO2;
                    defenseTower.sellNum += (int) (CANNONTOWERCOST * 0.8);
                }
            } else if (towerState == DefenseTower.TowerState.Advance) {
                if (towerType == DefenseTower.TowerType.MagicTower) {
                    upgradeNum = MAGICTOWERAV1TO2;
//                    sellNum = ADVANCESELL;
                } else if (towerType == DefenseTower.TowerType.ArcherTower) {
                    upgradeNum = ARCHERTOWERAV1TO2;
//                    sellNum = ADVANCESELL;
                } else if (towerType == DefenseTower.TowerType.CannonTower) {
                    upgradeNum = CANNONTOWERAV1TO2;
//                    sellNum = ADVANCESELL;
                }
            } else if (towerState == DefenseTower.TowerState.Specialization1) {
                if (towerType == DefenseTower.TowerType.MagicTower) {
                    upgradeNum = MAGICTOWERSP1TO2;
//                    sellNum = ADVANCESELL;
                } else if (towerType == DefenseTower.TowerType.ArcherTower) {
                    upgradeNum = ARCHERTOWERSP1TO2;
//                    sellNum = ADVANCESELL;
                } else if (towerType == DefenseTower.TowerType.CannonTower) {
                    upgradeNum = CANNONTOWERSP1TO2;
//                    sellNum = ADVANCESELL;
                }
            } else if (towerState == DefenseTower.TowerState.Specialization2) {
                if (towerType == DefenseTower.TowerType.MagicTower) {
                    upgradeNum = MAGICTOWERSP1TO2;
//                    sellNum = ADVANCESELL;
                } else if (towerType == DefenseTower.TowerType.ArcherTower) {
                    upgradeNum = ARCHERTOWERSP1TO2;
//                    sellNum = ADVANCESELL;
                } else if (towerType == DefenseTower.TowerType.CannonTower) {
                    upgradeNum = CANNONTOWERSP1TO2;
//                    sellNum = ADVANCESELL;
                }
            }
        } else if (towerLevel == 2.0) {
            if (towerState == DefenseTower.TowerState.Basic) {
                if (towerType == DefenseTower.TowerType.MagicTower) {
                    upgradeNum = MAGICTOWER2TO3;
//                    sellNum = PRIMARYSELL;
                } else if (towerType == DefenseTower.TowerType.ArcherTower) {
                    upgradeNum = ARCHERTOWER2TO3;
//                    sellNum = PRIMARYSELL;
                } else if (towerType == DefenseTower.TowerType.CannonTower) {
                    upgradeNum = CANNONTOWER2TO3;
//                    sellNum = PRIMARYSELL;
                }
            } else if (towerState == DefenseTower.TowerState.Advance) {
                if (towerType == DefenseTower.TowerType.MagicTower) {
                    upgradeNum = MAGICTOWERAV2TO3;
//                    sellNum = ADVANCESELL;
                } else if (towerType == DefenseTower.TowerType.ArcherTower) {
                    upgradeNum = ARCHERTOWERAV2TO3;
//                    sellNum = ADVANCESELL;
                } else if (towerType == DefenseTower.TowerType.CannonTower) {
                    upgradeNum = CANNONTOWERAV2TO3;
//                    sellNum = ADVANCESELL;
                }
            } else if (towerState == DefenseTower.TowerState.Specialization1) {
                if (towerType == DefenseTower.TowerType.MagicTower) {
                    upgradeNum = MAGICTOWERSP2TO3;
//                    sellNum = ADVANCESELL;
                } else if (towerType == DefenseTower.TowerType.ArcherTower) {
                    upgradeNum = ARCHERTOWERSP2TO3;
//                    sellNum = ADVANCESELL;
                } else if (towerType == DefenseTower.TowerType.CannonTower) {
                    upgradeNum = CANNONTOWERSP2TO3;
//                    sellNum = ADVANCESELL;
                }
            } else if (towerState == DefenseTower.TowerState.Specialization2) {
                if (towerType == DefenseTower.TowerType.MagicTower) {
                    upgradeNum = MAGICTOWERSP2TO3;
//                    sellNum = ADVANCESELL;
                } else if (towerType == DefenseTower.TowerType.ArcherTower) {
                    upgradeNum = ARCHERTOWERSP2TO3;
//                    sellNum = ADVANCESELL;
                } else if (towerType == DefenseTower.TowerType.CannonTower) {
                    upgradeNum = CANNONTOWERSP2TO3;
//                    sellNum = ADVANCESELL;
                }
            }
        }

        // basic tower 1~2 級
        if (towerState == DefenseTower.TowerState.Basic && towerLevel == 3.0) {
            // basic tower 3 級
            if (towerType == DefenseTower.TowerType.ArcherTower) {
                button1 = new Button(getWidth() / 2, 5, Theme.get(42)); // 進階
                button1.setClickedActionPerformed((int x, int y) -> { // 按下後
                    if (player.getMoney() >= ARCHERTOWERAV) {
                        player.addMoney(-ARCHERTOWERAV);
                        AudioResourceController.getInstance().shot(new Path().sound().gameButton());
                        defenseTower.convertToAdvanceTower(1);
                        System.out.println("進階喔");
                        defenseTower.sellNum += ARCHERTOWERAV * 0.8;
                        hide();
                    }
                });
                label1 = new Label(getWidth() / 2, -25, new Style.StyleRect(80, 40, true, new BackgroundType.BackgroundColor(new Color(0, 0, 0, 0)))
                        .setTextColor(new Color(87, 36, 2))
                        .setHaveBorder(true)
                        .setBorderColor(new Color(215, 186, 50, 0))
                        .setBorderThickness(10)
                        .setTextFont(new Font("", Font.TYPE1_FONT, 14))
                        .setText("Advanced  $" + ARCHERTOWERAV));


                button3 = new Button(0, getHeight() / 2, Theme.get(43));
                button3.setClickedActionPerformed((int x, int y) -> {  // 按下後
                    if (player.getMoney() >= ARCHERTOWERSP) {
                        AudioResourceController.getInstance().shot(new Path().sound().gameButton());
                        defenseTower.convertToAdvanceTower(2);
                        System.out.println("特化1喔");
                        player.addMoney(-ARCHERTOWERSP);
                        defenseTower.sellNum += ARCHERTOWERSP * 0.8;
                    }
                });
                label3 = new Label(-120, getHeight() / 2, new Style.StyleRect(80, 40, true, new BackgroundType.BackgroundColor(new Color(0, 0, 0, 0)))
                        .setTextColor(new Color(87, 36, 2))
                        .setHaveBorder(true)
                        .setBorderColor(new Color(215, 186, 50, 0))
                        .setBorderThickness(10)
                        .setTextFont(new Font("", Font.TYPE1_FONT, 14))
                        .setText("Crossbow tower $" + ARCHERTOWERSP));

                button4 = new Button(getWidth() - 5, getHeight() / 2, Theme.get(44)); // 魔法塔
                button4.setClickedActionPerformed((int x, int y) -> {  // 按下後
                    if (player.getMoney() >= ARCHERTOWERSP) {
                        AudioResourceController.getInstance().shot(new Path().sound().gameButton());
                        defenseTower.convertToAdvanceTower(3);
                        System.out.println("特化2喔");
                        player.addMoney(-ARCHERTOWERSP);
                        defenseTower.sellNum += ARCHERTOWERSP * 0.8;
                        hide();
                    }
                });
                label4 = new Label(200, getHeight() / 2, new Style.StyleRect(80, 40, true, new BackgroundType.BackgroundColor(new Color(0, 0, 0, 0)))
                        .setTextColor(new Color(87, 36, 2))
                        .setHaveBorder(true)
                        .setBorderColor(new Color(215, 186, 50, 0))
                        .setBorderThickness(10)
                        .setTextFont(new Font("", Font.TYPE1_FONT, 14))
                        .setText("Spear tower $" + ARCHERTOWERSP));


            } else if (towerType == DefenseTower.TowerType.CannonTower) {
                button1 = new Button(getWidth() / 2, 5, Theme.get(45)); // 進階
                button1.setClickedActionPerformed((int x, int y) -> { // 按下後
                    if (player.getMoney() >= CANNONTOWERAV) {
                        AudioResourceController.getInstance().shot(new Path().sound().gameButton());
                        defenseTower.convertToAdvanceTower(1);
                        System.out.println("進階喔");
                        player.addMoney(-CANNONTOWERAV);
                        defenseTower.sellNum += CANNONTOWERAV * 0.8;
                        hide();
                    }
                });
                label1 = new Label(getWidth() / 2, -25, new Style.StyleRect(80, 40, true, new BackgroundType.BackgroundColor(new Color(0, 0, 0, 0)))
                        .setTextColor(new Color(87, 36, 2))
                        .setHaveBorder(true)
                        .setBorderColor(new Color(215, 186, 50, 0))
                        .setBorderThickness(10)
                        .setTextFont(new Font("", Font.TYPE1_FONT, 14))
                        .setText("Advanced  $" + CANNONTOWERAV));


                button3 = new Button(0, getHeight() / 2, Theme.get(46)); // 魔法塔
                button3.setClickedActionPerformed((int x, int y) -> {  // 按下後
                    if (player.getMoney() >= CANNONTOWERSP) {
                        AudioResourceController.getInstance().shot(new Path().sound().gameButton());
                        defenseTower.convertToAdvanceTower(2);
                        System.out.println("特化1喔");
                        player.addMoney(-CANNONTOWERSP);
                        defenseTower.sellNum += CANNONTOWERSP * 0.8;
                    }
                });
                label3 = new Label(-100, getHeight() / 2, new Style.StyleRect(80, 40, true, new BackgroundType.BackgroundColor(new Color(0, 0, 0, 0)))
                        .setTextColor(new Color(87, 36, 2))
                        .setHaveBorder(true)
                        .setBorderColor(new Color(215, 186, 50, 0))
                        .setBorderThickness(10)
                        .setTextFont(new Font("", Font.TYPE1_FONT, 14))
                        .setText("Rocket tower $" + CANNONTOWERSP));

                button4 = new Button(getWidth() - 5, getHeight() / 2, Theme.get(47)); // 魔法塔
                button4.setClickedActionPerformed((int x, int y) -> {  // 按下後
                    if (player.getMoney() >= CANNONTOWERSP) {
                        AudioResourceController.getInstance().shot(new Path().sound().gameButton());
                        defenseTower.convertToAdvanceTower(3);
                        System.out.println("特化2喔");
                        player.addMoney(-CANNONTOWERSP);
                        defenseTower.sellNum += CANNONTOWERSP * 0.8;
                        hide();
                    }
                });
                label4 = new Label(220, getHeight() / 2, new Style.StyleRect(80, 40, true, new BackgroundType.BackgroundColor(new Color(0, 0, 0, 0)))
                        .setTextColor(new Color(87, 36, 2))
                        .setHaveBorder(true)
                        .setBorderColor(new Color(215, 186, 50, 0))
                        .setBorderThickness(10)
                        .setTextFont(new Font("", Font.TYPE1_FONT, 14))
                        .setText("ShockBullet tower $" + CANNONTOWERSP));
            } else if (towerType == DefenseTower.TowerType.MagicTower) {
                button1 = new Button(getWidth() / 2, 5, Theme.get(48)); // 進階
                button1.setClickedActionPerformed((int x, int y) -> { // 按下後
                    if (player.getMoney() >= MAGICTOWERAV) {
                        AudioResourceController.getInstance().shot(new Path().sound().gameButton());
                        defenseTower.convertToAdvanceTower(1);
                        System.out.println("進階喔");
                        player.addMoney(-MAGICTOWERAV);
                        defenseTower.sellNum += MAGICTOWERAV * 0.8;
                        hide();
                    }
                });
                label1 = new Label(getWidth() / 2, -25, new Style.StyleRect(80, 40, true, new BackgroundType.BackgroundColor(new Color(0, 0, 0, 0)))
                        .setTextColor(new Color(87, 36, 2))
                        .setHaveBorder(true)
                        .setBorderColor(new Color(215, 186, 50, 0))
                        .setBorderThickness(10)
                        .setTextFont(new Font("", Font.TYPE1_FONT, 14))
                        .setText("Advanced  $" + MAGICTOWERAV));


                button3 = new Button(0, getHeight() / 2, Theme.get(49)); // 魔法塔
                button3.setClickedActionPerformed((int x, int y) -> {  // 按下後
                    if (player.getMoney() >= MAGICTOWERSP) {
                        AudioResourceController.getInstance().shot(new Path().sound().gameButton());
                        defenseTower.convertToAdvanceTower(2);
                        System.out.println("特化1喔");
                        player.addMoney(-MAGICTOWERSP);
                        defenseTower.sellNum += MAGICTOWERSP * 0.8;
                    }
                });
                label3 = new Label(-100, getHeight() / 2, new Style.StyleRect(80, 40, true, new BackgroundType.BackgroundColor(new Color(0, 0, 0, 0)))
                        .setTextColor(new Color(87, 36, 2))
                        .setHaveBorder(true)
                        .setBorderColor(new Color(215, 186, 50, 0))
                        .setBorderThickness(10)
                        .setTextFont(new Font("", Font.TYPE1_FONT, 14))
                        .setText("Animal tower $" + MAGICTOWERSP));

                button4 = new Button(getWidth() - 5, getHeight() / 2, Theme.get(50)); // 魔法塔
                button4.setClickedActionPerformed((int x, int y) -> {  // 按下後
                    if (player.getMoney() >= MAGICTOWERSP) {
                        AudioResourceController.getInstance().shot(new Path().sound().gameButton());
                        defenseTower.convertToAdvanceTower(3);
                        System.out.println("特化2喔");
                        player.addMoney(-MAGICTOWERSP);
                        defenseTower.sellNum += MAGICTOWERSP * 0.8;
                        hide();
                    }
                });
                label4 = new Label(220, getHeight() / 2, new Style.StyleRect(80, 40, true, new BackgroundType.BackgroundColor(new Color(0, 0, 0, 0)))
                        .setTextColor(new Color(87, 36, 2))
                        .setHaveBorder(true)
                        .setBorderColor(new Color(215, 186, 50, 0))
                        .setBorderThickness(10)
                        .setTextFont(new Font("", Font.TYPE1_FONT, 14))
                        .setText("Lightening tower $" + MAGICTOWERSP));
            }
        } else {
            button1 = new Button(getWidth() / 2, 5, Theme.get(14)); // 升級
            button1.setClickedActionPerformed((int x, int y) -> { // 按下後
                if (player.getMoney() >= upgradeNum) {
                    AudioResourceController.getInstance().shot(new Path().sound().gameButton());
                    defenseTower.levelUp();
                    System.out.println("升級喔");
                    defenseTower.sellNum += upgradeNum * 0.8;
                    player.addMoney(-upgradeNum);
                    hide();
                }
            });
            label1 = new Label(getWidth() / 2, -20, new Style.StyleRect(80, 40, true, new BackgroundType.BackgroundColor(new Color(0, 0, 0, 0)))
                    .setTextColor(new Color(87, 36, 2))
                    .setHaveBorder(true)
                    .setBorderColor(new Color(215, 186, 50, 0))
                    .setBorderThickness(10)
                    .setTextFont(new Font("", Font.TYPE1_FONT, 14))
                    .setText("Upgrade $" + upgradeNum));
        }

        button2 = new Button(getWidth() / 2, getHeight() - 5, Theme.get(15)); // 賣塔
        button2.setClickedActionPerformed((int x, int y) -> {  // 按下後
            AudioResourceController.getInstance().shot(new Path().sound().gameButton());
            MapTest.turnOnButton(getX(), getY());
            MainScene.removeDefenseTower(defenseTower);
            System.out.println("賣塔喔");
            player.addMoney(defenseTower.sellNum);
            defenseTower.sellNum = 0;
            sceneEnd();
        });
        label2 = new Label(getWidth() / 2, getHeight() + 25, new Style.StyleRect(80, 40, true, new BackgroundType.BackgroundColor(new Color(0, 0, 0, 0)))
                .setTextColor(new Color(87, 36, 2))
                .setHaveBorder(true)
                .setBorderColor(new Color(215, 186, 50, 0))
                .setBorderThickness(10)
                .setTextFont(new Font("", Font.TYPE1_FONT, 14))
                .setText("Sell $" + defenseTower.sellNum));
    }

    @Override
    public void sceneEnd() {
        background = null;
        button1 = null;
        button2 = null;
        button3 = null;
        button4 = null;
        label1 = null;
        label2 = null;
        label3 = null;
        label4 = null;
    }

    @Override
    public void paintWindow(Graphics g) {
        g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
        if (towerLevel != 3.0 || (towerLevel == 3.0 && towerState == DefenseTower.TowerState.Basic)) {
            button1.paint(g);
        }

        button2.paint(g);
        label1.paint(g);
        label2.paint(g);
        if (towerState == DefenseTower.TowerState.Basic && towerLevel == 3.0) {
            button3.paint(g);
            button4.paint(g);
            label3.paint(g);
            label4.paint(g);
        }

    }

    @Override
    public void update() {
        label2 = new Label(getWidth() / 2, getHeight() + 25, new Style.StyleRect(80, 40, true, new BackgroundType.BackgroundColor(new Color(0, 0, 0, 0)))
                .setTextColor(new Color(87, 36, 2))
                .setHaveBorder(true)
                .setBorderColor(new Color(215, 186, 50, 0))
                .setBorderThickness(10)
                .setTextFont(new Font("", Font.TYPE1_FONT, 14))
                .setText("Sell $" + defenseTower.sellNum));
    }

    @Override
    public CommandSolver.KeyListener keyListener() {
        return null;
    }

    @Override
    protected void mouseTrig(MouseEvent e, CommandSolver.MouseState state, long trigTime) {

        if (towerLevel != 3.0 || (towerLevel == 3.0 && towerState == DefenseTower.TowerState.Basic)) {
            MouseTriggerImpl.mouseTrig(button1, e, state);
        }
        System.out.println(towerState + "," + towerLevel + "wwww");
        MouseTriggerImpl.mouseTrig(button2, e, state);

        if (towerState == DefenseTower.TowerState.Basic && towerLevel == 3.0 && button3 != null && button4 != null) {

            MouseTriggerImpl.mouseTrig(button3, e, state);
            MouseTriggerImpl.mouseTrig(button4, e, state);

        }
    }
}
