package menu;



import com.company.Path;
import controllers.SceneController;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import static com.company.Global.*;


public class Theme {

    public static final Theme DEFAULT_THEME = new Theme(
            new Style.StyleRect(150, 70, true, new BackgroundType.BackgroundColor(new Color(112, 128, 105)))
                    .setText("GOOD")
                    .setTextFont(new Font("TimesRoman", Font.BOLD, 30))
                    .setTextColor(Color.WHITE)
                    .setHaveBorder(true)
                    .setBorderColor(new Color(211, 211, 211))
                    .setBorderThickness(5),
            new Style.StyleRect(150, 70, true, new BackgroundType.BackgroundColor(new Color(169, 169, 169)))
                    .setHaveBorder(true)
                    .setBorderColor(Color.WHITE)
                    .setBorderThickness(5)
                    .setText("HOVER")
                    .setTextColor(Color.WHITE)
                    .setTextFont(new Font("TimesRoman", Font.BOLD, 30)),
            new Style.StyleRect(150, 70, true, new BackgroundType.BackgroundColor(new Color(178, 34, 34)))
                    .setText("FOCUS")
                    .setTextFont(new Font("TimesRoman", Font.BOLD, 35))
                    .setTextColor(new Color(255, 228, 255))
                    .setHaveBorder(true)
                    .setBorderColor(new Color(250, 128, 144))
                    .setBorderThickness(5)
    );


    private final Style styleNormal;
    private final Style styleHover;
    private final Style styleFocus;

    public Theme(Style styleNormal, Style styleHover, Style styleFocus) {
        this.styleNormal = styleNormal;
        this.styleHover = styleHover;
        this.styleFocus = styleFocus;
    }

    public Style normal() {
        return styleNormal;
    }

    public Style hover() {
        return styleHover;
    }

    public Style focus() {
        return styleFocus;
    }

//    private static ArrayList<Theme> getInstance() {
//        if (themes == null) {
//            themes = new ArrayList<>();
//        }
//        return themes;
//    }

    private static ArrayList<Theme> themes = new ArrayList<>();

    public static void add(Theme theme) {
        themes.add(theme);
    }

    public static Theme get(int index) {
        return themes.get(index);
    }


    public static void initTheme() {
        Style sure = new Style.StyleRect(WINDOW_WIDTH/5,WINDOW_HEIGHT/7, true, new BackgroundType.BackgroundColor(new Color(11, 215, 0,0)))
                //.setTextColor(Color.RED)
                .setHaveBorder(true)
                .setBorderColor(new Color(215, 186, 0,0))
                .setBorderThickness(10)
                .setTextFont(new Font("", Font.TYPE1_FONT, 50))
                .setText("Go?");
        Style home = new Style.StyleRect(WINDOW_WIDTH/5,WINDOW_HEIGHT/7,true,new BackgroundType.BackgroundImage(SceneController.getInstance().imageController().tryGetImage(new Path().img().objs().mainMenu())))
                .setHaveBorder(false);

        Style singleMode = new Style.StyleRect(WINDOW_WIDTH/5,WINDOW_HEIGHT/7,true,new BackgroundType.BackgroundImage(SceneController.getInstance().imageController().tryGetImage(new Path().img().objs().singleMode())))/*new Style.StyleOval(300, 100, new BackgroundType.BackgroundImage(SceneController.getInstance().imageController().tryGetImage(new Path().img().backgrounds().background2())))*/
                .setHaveBorder(false);

        Style onlineMode = new Style.StyleRect(WINDOW_WIDTH/5,WINDOW_HEIGHT/7,true,new BackgroundType.BackgroundImage(SceneController.getInstance().imageController().tryGetImage(new Path().img().objs().onlineMode())))
                .setHaveBorder(false);

        Style howToPlay = new Style.StyleRect(WINDOW_WIDTH/5,WINDOW_HEIGHT/7,true,new BackgroundType.BackgroundImage(SceneController.getInstance().imageController().tryGetImage(new Path().img().objs().howToPlay())))
                .setHaveBorder(false);

        Style exit = new Style.StyleRect(WINDOW_WIDTH/5,WINDOW_HEIGHT/7,true,new BackgroundType.BackgroundImage(SceneController.getInstance().imageController().tryGetImage(new Path().img().objs().exit())))
                .setHaveBorder(false);

        Style newGame = new Style.StyleRect(WINDOW_WIDTH/5,WINDOW_HEIGHT/7, true, new BackgroundType.BackgroundImage(SceneController.getInstance().imageController().tryGetImage(new Path().img().objs().newGame())))
                .setHaveBorder(false);

        Style loadGame = new Style.StyleRect(WINDOW_WIDTH/5,WINDOW_HEIGHT/7, true, new BackgroundType.BackgroundImage(SceneController.getInstance().imageController().tryGetImage(new Path().img().objs().loadGame())))
                .setHaveBorder(false);

        Style host = new Style.StyleRect(WINDOW_WIDTH/5,WINDOW_HEIGHT/7, true, new BackgroundType.BackgroundImage(SceneController.getInstance().imageController().tryGetImage(new Path().img().objs().host())))
                .setHaveBorder(false);

        Style guest = new Style.StyleRect(WINDOW_WIDTH/5,WINDOW_HEIGHT/7, true, new BackgroundType.BackgroundImage(SceneController.getInstance().imageController().tryGetImage(new Path().img().objs().guest())))
                .setHaveBorder(false);

        Style confirm = new Style.StyleRect(WINDOW_WIDTH/5,WINDOW_HEIGHT/7, true, new BackgroundType.BackgroundImage(SceneController.getInstance().imageController().tryGetImage(new Path().img().objs().confirm())))
                .setHaveBorder(false);
        Style cancel = new Style.StyleRect(WINDOW_WIDTH/5,WINDOW_HEIGHT/7, true, new BackgroundType.BackgroundImage(SceneController.getInstance().imageController().tryGetImage(new Path().img().objs().cancel())))
                .setHaveBorder(false);
        Style gameButtonUpGrade = new Style.StyleRect(GAMEBUTTON_X,GAMEBUTTON_Y, true, new BackgroundType.BackgroundImage(SceneController.getInstance().imageController().tryGetImage(new Path().img().objs().gameButtonUpGrade())))
                .setHaveBorder(false);
        Style gameButtonSellTower = new Style.StyleRect(GAMEBUTTON_X,GAMEBUTTON_Y, true, new BackgroundType.BackgroundImage(SceneController.getInstance().imageController().tryGetImage(new Path().img().objs().gameButtonSellTower())))
                .setHaveBorder(false);
        Style buttonRole1 = new Style.StyleRect(ROLEBUTTON_X,ROLEBUTTON_Y, true, new BackgroundType.BackgroundImage(SceneController.getInstance().imageController().tryGetImage(new Path().img().actors().artificer())))
                .setHaveBorder(false);

        Style buttonRole2 = new Style.StyleRect(ROLEBUTTON_X,ROLEBUTTON_Y, true, new BackgroundType.BackgroundImage(SceneController.getInstance().imageController().tryGetImage(new Path().img().actors().warlord())))
                .setHaveBorder(false);
        Style buttonRole3 = new Style.StyleRect(ROLEBUTTON_X,ROLEBUTTON_Y, true, new BackgroundType.BackgroundImage(SceneController.getInstance().imageController().tryGetImage(new Path().img().actors().crystalgolem())))
                .setHaveBorder(false);
        Style buttonRole4 = new Style.StyleRect(ROLEBUTTON_X,ROLEBUTTON_Y, true, new BackgroundType.BackgroundImage(SceneController.getInstance().imageController().tryGetImage(new Path().img().actors().medusa())))
                .setHaveBorder(false);

        Style buttonRole1Hover = new Style.StyleRect(ROLEBUTTON_X,ROLEBUTTON_Y, true, new BackgroundType.BackgroundImage(SceneController.getInstance().imageController().tryGetImage(new Path().img().actors().artificerHover())))
                .setHaveBorder(false);

        Style buttonRole2Hover = new Style.StyleRect(ROLEBUTTON_X,ROLEBUTTON_Y, true, new BackgroundType.BackgroundImage(SceneController.getInstance().imageController().tryGetImage(new Path().img().actors().warlordHover())))
                .setHaveBorder(false);
        Style buttonRole3Hover = new Style.StyleRect(ROLEBUTTON_X,ROLEBUTTON_Y, true, new BackgroundType.BackgroundImage(SceneController.getInstance().imageController().tryGetImage(new Path().img().actors().crystalgolemHover())))
                .setHaveBorder(false);
        Style buttonRole4Hover = new Style.StyleRect(ROLEBUTTON_X,ROLEBUTTON_Y, true, new BackgroundType.BackgroundImage(SceneController.getInstance().imageController().tryGetImage(new Path().img().actors().medusaHover())))
                .setHaveBorder(false);

        Style checkClient = new Style.StyleRect(WINDOW_WIDTH/2,WINDOW_HEIGHT/2, true, new BackgroundType.BackgroundColor(new Color(11, 215, 0,0)))
                .setTextColor(new Color(17, 61, 143))
                .setHaveBorder(true)
                .setBorderColor(new Color(166, 186, 0,0))
                .setBorderThickness(10)
                .setTextFont(new Font("", Font.TYPE1_FONT, 50))
                .setText("Waiting.......");
        Style checkClientGo = new Style.StyleRect(WINDOW_WIDTH/2,WINDOW_HEIGHT/2, true, new BackgroundType.BackgroundColor(new Color(11, 215, 0,0)))
                .setTextColor(new Color(183, 27, 152))
                .setHaveBorder(true)
                .setBorderColor(new Color(215, 186, 50,0))
                .setBorderThickness(10)
                .setTextFont(new Font("", Font.TYPE1_FONT, 50))
                .setText("Guest is connecting. Go!!!!!!!");
        Style mouseButton = new Style.StyleRect(TOWERWIDTH,TOWERHEIGHT, true, new BackgroundType.BackgroundColor(new Color(11, 215, 0,0)))
                //.setTextColor(new Color(183, 27, 152))
                .setHaveBorder(false)
                .setBorderColor(new Color(215, 186, 50,0))
                .setBorderThickness(10)
                .setTextFont(new Font("", Font.TYPE1_FONT, 50))
                .setText("");
        Style titleChooseRoleScene = new Style.StyleRect(WINDOW_WIDTH/2,WINDOW_HEIGHT/2, true, new BackgroundType.BackgroundColor(new Color(11, 215, 0,0)))
                .setTextColor(new Color(17, 61, 143))
                .setHaveBorder(true)
                .setBorderColor(new Color(215, 186, 50,0))
                .setBorderThickness(10)
                .setTextFont(new Font("", Font.TYPE1_FONT, 50))
                .setText("Please choose your hero");



        Theme.add(new Theme(singleMode, sure, singleMode)); // 開始主題(平常，滑鼠移上去，按下) 0
        Theme.add(new Theme(onlineMode, sure, onlineMode)); // 多人 1
        Theme.add(new Theme(howToPlay, sure, howToPlay)); // 說明主題 2
        Theme.add(new Theme(exit, sure, exit)); // 離開主題 3
        Theme.add(new Theme(home, sure, home)); // 回主選單 4
        Theme.add(new Theme(newGame, sure, newGame)); // 新遊戲 5
        Theme.add(new Theme(loadGame, sure, loadGame)); // 讀取遊戲 6
        Theme.add(new Theme(host, sure, host)); // host server 7
        Theme.add(new Theme(guest, sure, guest)); // client 8
        Theme.add(new Theme(confirm, sure, confirm)); // confirm 9
        Theme.add(new Theme(checkClient, sure, checkClient)); // checkClient 10
        Theme.add(new Theme(checkClientGo, sure, checkClientGo)); // checkClient 11
        Theme.add(new Theme(cancel, sure, cancel)); // cancel 12
        Theme.add(new Theme(mouseButton, mouseButton, mouseButton)); // mouseLabel 13
        Theme.add(new Theme(gameButtonUpGrade, gameButtonUpGrade, gameButtonUpGrade)); // gameButtonUpGrade 14
        Theme.add(new Theme(gameButtonSellTower, gameButtonSellTower, gameButtonSellTower)); // gameButtonSellTower 15
        Theme.add(new Theme(buttonRole1, buttonRole1Hover, buttonRole1)); // buttonRole1 16
        Theme.add(new Theme(buttonRole2, buttonRole2Hover, buttonRole2)); // buttonRole2 17
        Theme.add(new Theme(buttonRole3, buttonRole3Hover, buttonRole3)); // buttonRole3 18
        Theme.add(new Theme(buttonRole4, buttonRole4Hover, buttonRole4)); // buttonRole4 19
        Theme.add(new Theme(titleChooseRoleScene, titleChooseRoleScene, titleChooseRoleScene)); // titleChooseRoleScene 20
    }


}
