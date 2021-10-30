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
        Style sure = new Style.StyleRect(WINDOW_WIDTH/5,WINDOW_HEIGHT/7, true, new BackgroundType.BackgroundImage(SceneController.getInstance().imageController().tryGetImage(new Path().img().objs().go())))
                //.setTextColor(Color.RED)
                .setHaveBorder(false)
                .setBorderColor(new Color(215, 186, 0,0))
                .setBorderThickness(10)
                .setTextFont(new Font("", Font.TYPE1_FONT, 50))
                .setText("");
        Style sureSmall = new Style.StyleRect(WINDOW_WIDTH/8,WINDOW_HEIGHT/10, true, new BackgroundType.BackgroundImage(SceneController.getInstance().imageController().tryGetImage(new Path().img().objs().go())))
                //.setTextColor(Color.RED)
                .setHaveBorder(true)
                .setBorderColor(new Color(215, 186, 0,0))
                .setBorderThickness(10)
                .setTextFont(new Font("", Font.TYPE1_FONT, 50))
                .setText("");
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
        Style confirmPaper = new Style.StyleRect(WINDOW_WIDTH/8,WINDOW_HEIGHT/10, true, new BackgroundType.BackgroundImage(SceneController.getInstance().imageController().tryGetImage(new Path().img().objs().confirmPaper())))
                .setHaveBorder(false);
        Style chineseVersion = new Style.StyleRect(WINDOW_WIDTH/8,WINDOW_HEIGHT/10, true, new BackgroundType.BackgroundImage(SceneController.getInstance().imageController().tryGetImage(new Path().img().objs().chineseVersion())))
                .setHaveBorder(false);
        Style gameStart = new Style.StyleRect(WINDOW_WIDTH/5,WINDOW_HEIGHT/7, true, new BackgroundType.BackgroundImage(SceneController.getInstance().imageController().tryGetImage(new Path().img().objs().gameStart())))
                .setHaveBorder(false);
        Style cancel = new Style.StyleRect(WINDOW_WIDTH/5,WINDOW_HEIGHT/7, true, new BackgroundType.BackgroundImage(SceneController.getInstance().imageController().tryGetImage(new Path().img().objs().cancel())))
                .setHaveBorder(false);
        Style gameButtonUpGrade = new Style.StyleRect(GAMEBUTTON_X,GAMEBUTTON_Y, true, new BackgroundType.BackgroundImage(SceneController.getInstance().imageController().tryGetImage(new Path().img().objs().gameButtonUpGrade())))
                .setHaveBorder(false);
        Style gameButtonSellTower = new Style.StyleRect(GAMEBUTTON_X,GAMEBUTTON_Y, true, new BackgroundType.BackgroundImage(SceneController.getInstance().imageController().tryGetImage(new Path().img().objs().gameButtonSellTower())))
                .setHaveBorder(false);
        Style gameButtonArcherTower = new Style.StyleRect(GAMEBUTTON_X,GAMEBUTTON_Y, true, new BackgroundType.BackgroundImage(SceneController.getInstance().imageController().tryGetImage(new Path().img().objs().archerTower())))
                .setHaveBorder(false);
        Style gameButtonCannonTower = new Style.StyleRect(GAMEBUTTON_X,GAMEBUTTON_Y, true, new BackgroundType.BackgroundImage(SceneController.getInstance().imageController().tryGetImage(new Path().img().objs().cannonTower())))
                .setHaveBorder(false);
        Style gameButtonMagicTower = new Style.StyleRect(GAMEBUTTON_X,GAMEBUTTON_Y, true, new BackgroundType.BackgroundImage(SceneController.getInstance().imageController().tryGetImage(new Path().img().objs().magicTower())))
                .setHaveBorder(false);
        Style gameButtonArcherAVTower = new Style.StyleRect(GAMEBUTTON_X,GAMEBUTTON_Y, true, new BackgroundType.BackgroundImage(SceneController.getInstance().imageController().tryGetImage(new Path().img().objs().ArcherAVTower())))
                .setHaveBorder(false);
        Style gameButtonCrossbowTower = new Style.StyleRect(GAMEBUTTON_X,GAMEBUTTON_Y, true, new BackgroundType.BackgroundImage(SceneController.getInstance().imageController().tryGetImage(new Path().img().objs().CrossbowTower())))
                .setHaveBorder(false);
        Style gameButtonSpearTower = new Style.StyleRect(GAMEBUTTON_X,GAMEBUTTON_Y, true, new BackgroundType.BackgroundImage(SceneController.getInstance().imageController().tryGetImage(new Path().img().objs().SpearTower())))
                .setHaveBorder(false);
        Style gameButtonCannonAVTower = new Style.StyleRect(GAMEBUTTON_X,GAMEBUTTON_Y, true, new BackgroundType.BackgroundImage(SceneController.getInstance().imageController().tryGetImage(new Path().img().objs().CannonAVTower())))
                .setHaveBorder(false);
        Style gameButtonRocketTower = new Style.StyleRect(GAMEBUTTON_X,GAMEBUTTON_Y, true, new BackgroundType.BackgroundImage(SceneController.getInstance().imageController().tryGetImage(new Path().img().objs().RocketTower())))
                .setHaveBorder(false);
        Style gameButtonShockBulletTower = new Style.StyleRect(GAMEBUTTON_X,GAMEBUTTON_Y, true, new BackgroundType.BackgroundImage(SceneController.getInstance().imageController().tryGetImage(new Path().img().objs().ShockBulletTower())))
                .setHaveBorder(false);
        Style gameButtonMagicAVTower = new Style.StyleRect(GAMEBUTTON_X,GAMEBUTTON_Y, true, new BackgroundType.BackgroundImage(SceneController.getInstance().imageController().tryGetImage(new Path().img().objs().MagicAVTower())))
                .setHaveBorder(false);
        Style gameButtonDuckTower = new Style.StyleRect(GAMEBUTTON_X,GAMEBUTTON_Y, true, new BackgroundType.BackgroundImage(SceneController.getInstance().imageController().tryGetImage(new Path().img().objs().AnimalTower())))
                .setHaveBorder(false);
        Style gameButtonLighteningTower = new Style.StyleRect(GAMEBUTTON_X,GAMEBUTTON_Y, true, new BackgroundType.BackgroundImage(SceneController.getInstance().imageController().tryGetImage(new Path().img().objs().LighteningTower())))
                .setHaveBorder(false);
        Style labelPause = new Style.StyleRect(300,100, true, new BackgroundType.BackgroundImage(SceneController.getInstance().imageController().tryGetImage(new Path().img().objs().labelPause())))
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

        Style buttonRole1Ability = new Style.StyleRect(ABILITYBUTTON_X,ABILITYBUTTON_Y, true, new BackgroundType.BackgroundImage(SceneController.getInstance().imageController().tryGetImage(new Path().img().objs().poison())))
                .setHaveBorder(false);
        Style buttonRole2Ability = new Style.StyleRect(ABILITYBUTTON_X,ABILITYBUTTON_Y, true, new BackgroundType.BackgroundImage(SceneController.getInstance().imageController().tryGetImage(new Path().img().objs().lightening())))
                .setHaveBorder(false);
        Style buttonRole3Ability = new Style.StyleRect(ABILITYBUTTON_X,ABILITYBUTTON_Y, true, new BackgroundType.BackgroundImage(SceneController.getInstance().imageController().tryGetImage(new Path().img().objs().block())))
                .setHaveBorder(false);
        Style buttonRole4Ability = new Style.StyleRect(ABILITYBUTTON_X,ABILITYBUTTON_Y, true, new BackgroundType.BackgroundImage(SceneController.getInstance().imageController().tryGetImage(new Path().img().objs().petrifaction())))
                .setHaveBorder(false);

        Style buttonRole1AbilityCD = new Style.StyleRect(ABILITYBUTTON_X,ABILITYBUTTON_Y, true, new BackgroundType.BackgroundImage(SceneController.getInstance().imageController().tryGetImage(new Path().img().objs().poisonCD())))
                .setHaveBorder(false);
        Style buttonRole2AbilityCD = new Style.StyleRect(ABILITYBUTTON_X,ABILITYBUTTON_Y, true, new BackgroundType.BackgroundImage(SceneController.getInstance().imageController().tryGetImage(new Path().img().objs().lighteningCD())))
                .setHaveBorder(false);
        Style buttonRole3AbilityCD = new Style.StyleRect(ABILITYBUTTON_X,ABILITYBUTTON_Y, true, new BackgroundType.BackgroundImage(SceneController.getInstance().imageController().tryGetImage(new Path().img().objs().blockCD())))
                .setHaveBorder(false);
        Style buttonRole4AbilityCD = new Style.StyleRect(ABILITYBUTTON_X,ABILITYBUTTON_Y, true, new BackgroundType.BackgroundImage(SceneController.getInstance().imageController().tryGetImage(new Path().img().objs().petrifactionCD())))
                .setHaveBorder(false);

        Style bossHead = new Style.StyleRect(ABILITYBUTTON_X,ABILITYBUTTON_Y, true, new BackgroundType.BackgroundImage(SceneController.getInstance().imageController().tryGetImage(new Path().img().objs().bossHead())))
                .setHaveBorder(true);


        Style role1Intro = new Style.StyleRect(INTROPAGE_X,INTROPAGE_Y, true, new BackgroundType.BackgroundImage(SceneController.getInstance().imageController().tryGetImage(new Path().img().backgrounds().popIntroWindow())))
                .setHaveBorder(false)
                .setTextFont(new Font("", Font.ITALIC, 30))
                .setTextColor(new Color(103, 43, 8))
                .setText("Name: " + gameRole.ROLE1.getValue() +", Ability: " + gameRole.ROLE1.getAbility());

        Style role2Intro = new Style.StyleRect(INTROPAGE_X,INTROPAGE_Y, true, new BackgroundType.BackgroundImage(SceneController.getInstance().imageController().tryGetImage(new Path().img().backgrounds().popIntroWindow())))
                .setHaveBorder(false)
                .setTextFont(new Font("", Font.ITALIC, 30))
                .setTextColor(new Color(103, 43, 8))
                .setText("Name: " + gameRole.ROLE2.getValue() +", Ability: " + gameRole.ROLE2.getAbility());

        Style role3Intro = new Style.StyleRect(INTROPAGE_X,INTROPAGE_Y, true, new BackgroundType.BackgroundImage(SceneController.getInstance().imageController().tryGetImage(new Path().img().backgrounds().popIntroWindow())))
                .setHaveBorder(false)
                .setTextFont(new Font("", Font.ITALIC, 30))
                .setTextColor(new Color(103, 43, 8))
                .setText("Name: " + gameRole.ROLE3.getValue() +", Ability: " + gameRole.ROLE3.getAbility());

        Style role4Intro = new Style.StyleRect(INTROPAGE_X,INTROPAGE_Y, true, new BackgroundType.BackgroundImage(SceneController.getInstance().imageController().tryGetImage(new Path().img().backgrounds().popIntroWindow())))
                .setHaveBorder(false)
                .setTextFont(new Font("", Font.ITALIC, 30))
                .setTextColor(new Color(103, 43, 8))
                .setText("Name: " + gameRole.ROLE4.getValue() +", Ability: " + gameRole.ROLE4.getAbility());

        Style role1IntroOnline = new Style.StyleRect(INTROPAGE_X,INTROPAGE_Y, true, new BackgroundType.BackgroundImage(SceneController.getInstance().imageController().tryGetImage(new Path().img().backgrounds().popIntroWindow())))
                .setHaveBorder(false)
                .setTextFont(new Font("", Font.ITALIC, 30))
                .setTextColor(new Color(103, 43, 8))
                .setText("Name: " + gameRole.ROLE1.getValue() +", Ability: " + gameRole.ROLE1.getAbility2());

        Style role2IntroOnline = new Style.StyleRect(INTROPAGE_X,INTROPAGE_Y, true, new BackgroundType.BackgroundImage(SceneController.getInstance().imageController().tryGetImage(new Path().img().backgrounds().popIntroWindow())))
                .setHaveBorder(false)
                .setTextFont(new Font("", Font.ITALIC, 30))
                .setTextColor(new Color(103, 43, 8))
                .setText("Name: " + gameRole.ROLE2.getValue() +", Ability: " + gameRole.ROLE2.getAbility2());

        Style role3IntroOnline = new Style.StyleRect(INTROPAGE_X,INTROPAGE_Y, true, new BackgroundType.BackgroundImage(SceneController.getInstance().imageController().tryGetImage(new Path().img().backgrounds().popIntroWindow())))
                .setHaveBorder(false)
                .setTextFont(new Font("", Font.ITALIC, 30))
                .setTextColor(new Color(103, 43, 8))
                .setText("Name: " + gameRole.ROLE3.getValue() +", Ability: " + gameRole.ROLE3.getAbility2());

        Style role4IntroOnline = new Style.StyleRect(INTROPAGE_X,INTROPAGE_Y, true, new BackgroundType.BackgroundImage(SceneController.getInstance().imageController().tryGetImage(new Path().img().backgrounds().popIntroWindow())))
                .setHaveBorder(false)
                .setTextFont(new Font("", Font.ITALIC, 30))
                .setTextColor(new Color(103, 43, 8))
                .setText("Name: " + gameRole.ROLE4.getValue() +", Ability: " + gameRole.ROLE4.getAbility2());

        Style checkClient = new Style.StyleRect(WINDOW_WIDTH/5,WINDOW_HEIGHT/8, true, new BackgroundType.BackgroundImage(SceneController.getInstance().imageController().tryGetImage(new Path().img().objs().Waiting())))
                .setTextColor(new Color(17, 61, 143))
                .setHaveBorder(false)
                .setBorderColor(new Color(166, 186, 0,0))
                .setBorderThickness(10)
                .setTextFont(new Font("", Font.TYPE1_FONT, 50))
                .setText("");
        Style checkClientGo = new Style.StyleRect(WINDOW_WIDTH/3,WINDOW_HEIGHT/8, true, new BackgroundType.BackgroundImage(SceneController.getInstance().imageController().tryGetImage(new Path().img().objs().connectOK())))
                .setTextColor(new Color(183, 27, 152))
                .setHaveBorder(true)
                .setBorderColor(new Color(215, 186, 50,0))
                .setBorderThickness(10)
                .setTextFont(new Font("", Font.TYPE1_FONT, 50))
                .setText("");
        Style waitServerConfirm = new Style.StyleRect(WINDOW_WIDTH/3,WINDOW_HEIGHT/8, true, new BackgroundType.BackgroundImage(SceneController.getInstance().imageController().tryGetImage(new Path().img().objs().waitingServer())))
                .setTextColor(new Color(183, 27, 152))
                .setHaveBorder(true)
                .setBorderColor(new Color(215, 186, 50,0))
                .setBorderThickness(10)
                .setTextFont(new Font("", Font.TYPE1_FONT, 50))
                .setText("");
        Style mouseButton = new Style.StyleRect(TOWERWIDTH,TOWERHEIGHT, true, new BackgroundType.BackgroundColor(new Color(11, 215, 0,0)))
                //.setTextColor(new Color(183, 27, 152))
                .setHaveBorder(false)
                .setBorderColor(new Color(215, 186, 50,0))
                .setBorderThickness(10)
                .setTextFont(new Font("", Font.TYPE1_FONT, 50))
                .setText("");
        Style luaguage1 = new Style.StyleRect(100,20, true, new BackgroundType.BackgroundColor(new Color(11, 215, 0,0)))
                .setTextColor(new Color(17, 61, 143))
                .setHaveBorder(true)
                .setBorderColor(new Color(215, 186, 50,0))
                .setBorderThickness(10)
                .setTextFont(new Font("", Font.TYPE1_FONT, 30))
                .setText("Thank you for saving our country");

        Style luaguage2 = new Style.StyleRect(100,20, true, new BackgroundType.BackgroundColor(new Color(11, 215, 0,0)))
                .setTextColor(new Color(17, 61, 143))
                .setHaveBorder(true)
                .setBorderColor(new Color(215, 186, 50,0))
                .setBorderThickness(10)
                .setTextFont(new Font("", Font.TYPE1_FONT, 30))
                .setText("You are really a hero !!!!!!");

        Style buildButton = new Style.StyleRect(64,64, true, new BackgroundType.BackgroundImage(SceneController.getInstance().imageController().tryGetImage(new Path().img().road().building())))
                //.setTextColor(new Color(183, 27, 152))
                .setHaveBorder(false)
                .setBorderColor(new Color(215, 186, 50,0))
                .setBorderThickness(10)
                .setTextFont(new Font("", Font.TYPE1_FONT, 50))
                .setText("");
        Style mouseButtonCancel = new Style.StyleRect(48,48, true, new BackgroundType.BackgroundImage(SceneController.getInstance().imageController().tryGetImage(new Path().img().objs().cancelButton())))
                //.setTextColor(new Color(183, 27, 152))
                .setHaveBorder(false)
                .setBorderColor(new Color(215, 186, 50,0))
                .setBorderThickness(10)
                .setTextFont(new Font("", Font.TYPE1_FONT, 50))
                .setText("");
        Style mouseButtonPause = new Style.StyleRect(48,48, true, new BackgroundType.BackgroundImage(SceneController.getInstance().imageController().tryGetImage(new Path().img().objs().pause())))
                //.setTextColor(new Color(183, 27, 152))
                .setHaveBorder(false)
                .setBorderColor(new Color(215, 186, 50,0))
                .setBorderThickness(10)
                .setTextFont(new Font("", Font.TYPE1_FONT, 50))
                .setText("");
        Style mouseButtonPlay = new Style.StyleRect(48,48, true, new BackgroundType.BackgroundImage(SceneController.getInstance().imageController().tryGetImage(new Path().img().objs().play())))
                //.setTextColor(new Color(183, 27, 152))
                .setHaveBorder(false)
                .setBorderColor(new Color(215, 186, 50,0))
                .setBorderThickness(10)
                .setTextFont(new Font("", Font.TYPE1_FONT, 50))
                .setText("");
        Style mouseButtonFastRatio = new Style.StyleRect(48,48, true, new BackgroundType.BackgroundImage(SceneController.getInstance().imageController().tryGetImage(new Path().img().objs().fastRatioButton())))
                //.setTextColor(new Color(183, 27, 152))
                .setHaveBorder(false)
                .setBorderColor(new Color(215, 186, 50,0))
                .setBorderThickness(10)
                .setTextFont(new Font("", Font.TYPE1_FONT, 50))
                .setText("");
        Style statusButton = new Style.StyleRect(48,48, true, new BackgroundType.BackgroundColor(new Color(11, 215, 0,0)))
                //.setTextColor(new Color(183, 27, 152))
                .setHaveBorder(false)
                .setBorderColor(new Color(215, 186, 50,0))
                .setBorderThickness(10)
                .setTextFont(new Font("", Font.TYPE1_FONT, 50))
                .setText("");
        Style mouseButtonCallMonster = new Style.StyleRect(32,32, true, new BackgroundType.BackgroundImage(SceneController.getInstance().imageController().tryGetImage(new Path().img().objs().buttonCallMonster())))
                //.setTextColor(new Color(183, 27, 152))
                .setHaveBorder(false)
                .setBorderColor(new Color(215, 186, 50,0))
                .setBorderThickness(10)
                .setTextFont(new Font("", Font.TYPE1_FONT, 50))
                .setText("");
        Style mouseButtonCallMonsterHover = new Style.StyleRect(32,32, true, new BackgroundType.BackgroundImage(SceneController.getInstance().imageController().tryGetImage(new Path().img().objs().buttonCallMonsterHover())))
                //.setTextColor(new Color(183, 27, 152))
                .setHaveBorder(false)
                .setBorderColor(new Color(215, 186, 50,0))
                .setBorderThickness(10)
                .setTextFont(new Font("", Font.TYPE1_FONT, 50))
                .setText("");




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
        Theme.add(new Theme(luaguage1, luaguage1, luaguage1)); // luaguage1 20
        Theme.add(new Theme(role1Intro, role1Intro, role1Intro)); // role1Intro 21
        Theme.add(new Theme(role2Intro, role2Intro, role2Intro)); // role1Intro 22
        Theme.add(new Theme(role3Intro, role3Intro, role3Intro)); // role1Intro 23
        Theme.add(new Theme(role4Intro, role4Intro, role4Intro)); // role1Intro 24
        Theme.add(new Theme(role1IntroOnline, role1IntroOnline, role1IntroOnline)); // role1IntroOnline 25
        Theme.add(new Theme(role2IntroOnline, role2IntroOnline, role2IntroOnline)); // role1IntroOnline 26
        Theme.add(new Theme(role3IntroOnline, role3IntroOnline, role3IntroOnline)); // role1IntroOnline 27
        Theme.add(new Theme(role4IntroOnline, role4IntroOnline, role4IntroOnline)); // role1IntroOnline 28
        Theme.add(new Theme(buttonRole1Ability, buttonRole1Ability, buttonRole1Ability)); // buttonRole1Ability 29
        Theme.add(new Theme(buttonRole2Ability, buttonRole2Ability, buttonRole2Ability)); // buttonRole2Ability 30
        Theme.add(new Theme(buttonRole3Ability, buttonRole3Ability, buttonRole3Ability)); // buttonRole3Ability 31
        Theme.add(new Theme(buttonRole4Ability, buttonRole4Ability, buttonRole4Ability)); // buttonRole4Ability 32
        Theme.add(new Theme(buttonRole1AbilityCD, buttonRole1AbilityCD, buttonRole1AbilityCD)); // buttonRole1AbilityCD 33
        Theme.add(new Theme(buttonRole2AbilityCD, buttonRole2AbilityCD, buttonRole2AbilityCD)); // buttonRole2AbilityCD 34
        Theme.add(new Theme(buttonRole3AbilityCD, buttonRole3AbilityCD, buttonRole3AbilityCD)); // buttonRole3AbilityCD 35
        Theme.add(new Theme(buttonRole4AbilityCD, buttonRole4AbilityCD, buttonRole4AbilityCD)); // buttonRole4AbilityCD 36
        Theme.add(new Theme(gameStart, sure, gameStart)); // gameStart 37
        Theme.add(new Theme(buildButton, buildButton, buildButton)); // buildButton 38
        Theme.add(new Theme(gameButtonArcherTower, gameButtonArcherTower, gameButtonArcherTower)); // gameButtonArcherTower 39
        Theme.add(new Theme(gameButtonCannonTower, gameButtonCannonTower, gameButtonCannonTower)); // gameButtonCannonTower 40
        Theme.add(new Theme(gameButtonMagicTower, gameButtonMagicTower, gameButtonMagicTower)); // gameButtonMagicTower 41
        Theme.add(new Theme(gameButtonArcherAVTower, gameButtonArcherAVTower, gameButtonArcherAVTower)); // gameButtonUpGrade 42
        Theme.add(new Theme(gameButtonCrossbowTower, gameButtonCrossbowTower, gameButtonCrossbowTower)); // gameButtonCrossbowTower 43
        Theme.add(new Theme(gameButtonSpearTower, gameButtonSpearTower, gameButtonSpearTower)); // gameButtonSpearTower 44
        Theme.add(new Theme(gameButtonCannonAVTower, gameButtonCannonAVTower, gameButtonCannonAVTower)); // gameButtonSellTower 45
        Theme.add(new Theme(gameButtonRocketTower, gameButtonRocketTower, gameButtonRocketTower)); // gameButtonRocketTower 46
        Theme.add(new Theme(gameButtonShockBulletTower, gameButtonShockBulletTower, gameButtonShockBulletTower)); // gameButtonUpGrade 47
        Theme.add(new Theme(gameButtonMagicAVTower, gameButtonMagicAVTower, gameButtonMagicAVTower)); // gameButtonMagicAVTower 48
        Theme.add(new Theme(gameButtonDuckTower, gameButtonDuckTower, gameButtonDuckTower)); // gameButtonDuckTower 49
        Theme.add(new Theme(gameButtonLighteningTower, gameButtonLighteningTower, gameButtonLighteningTower)); // gameButtonSellTower 50
        Theme.add(new Theme(mouseButtonCancel, mouseButtonCancel, mouseButtonCancel)); // mouseButtonCancel 51
        Theme.add(new Theme(statusButton, statusButton, statusButton)); // statusButton 52
        Theme.add(new Theme(confirmPaper, sureSmall, confirmPaper)); // confirm 53
        Theme.add(new Theme(mouseButtonCallMonster, mouseButtonCallMonsterHover, mouseButtonCallMonster)); // mouseButtonCallMonster 54
        Theme.add(new Theme(mouseButtonPause, mouseButtonPause, mouseButtonPause)); // mouseButtonPause 55
        Theme.add(new Theme(mouseButtonPlay, mouseButtonPlay, mouseButtonPlay)); // mouseButtonPlay 56
        Theme.add(new Theme(luaguage2, luaguage2, luaguage2)); // luaguage2 57
        Theme.add(new Theme(labelPause, labelPause, labelPause)); // labelPause 58
        Theme.add(new Theme(waitServerConfirm, waitServerConfirm, waitServerConfirm)); // waitServerConfirm 59
        Theme.add(new Theme(bossHead, bossHead, bossHead)); // waitServerConfirm 60
        Theme.add(new Theme(chineseVersion, sureSmall, chineseVersion)); // confirm 61
        Theme.add(new Theme(mouseButtonFastRatio, mouseButtonFastRatio, mouseButtonFastRatio)); // mouseButtonPlay 62
    }

}
