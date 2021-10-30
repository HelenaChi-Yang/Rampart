package scene;

import Internet.Client;
import com.company.*;
import controllers.AudioResourceController;
import controllers.SceneController;
import gameObject.Actor;
import gameObject.AllCanUseSkill;
import gameObject.DefenseTower.DefenseTower;
import gameObject.Monster;
import gameObject.Player;
import maploader.MapInfo;
import menu.BackgroundType;
import menu.Button;
import menu.Label;
import menu.Style;
import menu.Theme;
import menu.impl.MouseTriggerImpl;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static com.company.Global.*;

public class MainScene extends Scene implements CommandSolver.KeyListener {

    private Image img;
    private ArrayList<Actor> actorKungArr;
    private ArrayList<Actor> bosses = new ArrayList<Actor>(0);
    private static ArrayList<DefenseTower> defenseTowers;
    private MapTest mapTest;
    private int fastRatio = 1;

    //場景
    private Image coin, avator, playerHpImg, silver;
    private Button ability, abilityCD, buttonCancel, buttonCallMonster, buttonPause, buttonPlay, buttonAddBossToCompetitor, buttonFastRatio;
    private Label coinNum, CDTimeMonitor, abilityName, waveLabel, playerHp, labelPause, addBossName;
    public static Player player;
    private Delay delay;
    private int CD;
    private boolean isNormal;
    private boolean isHost;
    private boolean isButtonCallMonsterWork;
    private boolean isPause;
    private int stage;

    private int firstX;//演員起始X
    private int firstY;//演員起始Y
    ArrayList<MapInfo> finalGrids = new ArrayList<MapInfo>(0);
    private int generateMonsterCount = 60, updateCount, wave = 0, generateNumber, totalGenerateNumber;      //生怪頻率、計數器、第幾波、這波目前生多少怪、這波總共要生多少怪
    private ArrayList<Integer> generateBossWave = new ArrayList<Integer>(0);       //產生boss的波數，要生多隻就多加同波
    private int finalWave;      //關卡有幾波
    private Delay bossGenerateDelay;
    private boolean canSummonMonster = false;
    private int playerIDforOnlineMode;
    private boolean isOnlineMode;
    private Delay onlineModeWaveDelay;


    private boolean isControlInternet1,isControlInternet2;
    private Delay controlInternet1, controlInternet2,controlInternet3,controlInternet4;
    private int delayControl;

    private ArrayList<String> catSet;
    private Delay catDelay;
    private Label catSetLabel1;
    private int count;

    private Image talk;
    private Delay stan;
    private boolean isStan;



    public MainScene(Player player, boolean isHost, boolean isOnlineMode, int stage) {
        this.isHost = isHost;
        this.player = player;
        this.stage = stage;
        this.isOnlineMode = isOnlineMode;
    }


    @Override
    public void sceneBegin() {
        talk = SceneController.getInstance().imageController().tryGetImage(new Path().img().backgrounds().talk());
        stan = new Delay(300);
        stan.loop();
        isStan = false;
        initCatSet();
        if(isOnlineMode) {
            fastRatio = 2;
        }
        catDelay = new Delay(3);
        delayControl = 600;
        controlInternet1 = new Delay(delayControl);
        controlInternet2 = new Delay(delayControl);
        controlInternet3 = new Delay(delayControl-180);
        controlInternet4 = new Delay(delayControl-180);
        isControlInternet1 = true;
        isControlInternet2 = true;
        AudioResourceController.getInstance().stop(new Path().sound().statusScene()); // 停音樂
        AudioResourceController.getInstance().stop(new Path().sound().menu()); // 停音樂
        AudioResourceController.getInstance().loop(new Path().sound().battle(), 99);
        buttonCancel = new Button(1550, 30, Theme.get(51));
        buttonCancel.setClickedActionPerformed((int x, int y) -> {

            AudioResourceController.getInstance().shot(new Path().sound().button());
            AudioResourceController.getInstance().stop(new Path().sound().battle()); // 停音樂
            SceneController.getInstance().change(new StatusScene(player, isHost));    //回主選單

        });
        buttonPause = new Button(1500, 30, Theme.get(55));
        buttonPause.setClickedActionPerformed((int x, int y) -> {

            AudioResourceController.getInstance().shot(new Path().sound().gameButton());
            isPause = true;

        });
        buttonPlay = new Button(1500, 30, Theme.get(56));
        buttonPlay.setClickedActionPerformed((int x, int y) -> {

            AudioResourceController.getInstance().shot(new Path().sound().gameButton());
            isPause = false;

        });

        buttonAddBossToCompetitor = new Button(80, 650, Theme.get(60));
        buttonAddBossToCompetitor.setClickedActionPerformed((int x, int y) -> {

            AudioResourceController.getInstance().shot(new Path().sound().gameButton());
            if (generateBossToOpponent()) {
                player.addMoney(-(100 + wave * 10));
                Client.getInstance().send(playerIDforOnlineMode + "," + 88);
                controlInternet4.play();
                //Client.getInstance().send(playerIDforOnlineMode + "," + 99);
            }

        });

        buttonFastRatio = new Button(1450, 30, Theme.get(62));
        buttonFastRatio.setClickedActionPerformed((int x, int y) -> {

            AudioResourceController.getInstance().shot(new Path().sound().gameButton());
            if (fastRatio == 3){
                fastRatio = 1;
            }else {
                fastRatio++;
            }
        });



        isButtonCallMonsterWork = true;
        isPause = false;
        actorKungArr = new ArrayList<>();
        defenseTowers = new ArrayList<>();

        try {
            if (stage == 1) {
                mapTest = new MapTest("/maploader/map1/genMap.bmp", "/maploader/map1/genMap.txt");
                img = SceneController.getInstance().imageController().tryGetImage(new Path().img().backgrounds().grassBackground1());
                firstX = mapTest.specifyGrid(0, 6).getX();//起始X點
                firstY = mapTest.specifyGrid(0, 6).getY();//起始Y點
                finalGrids.add(mapTest.specifyGrid(49, 20));//終點格
                finalWave = 5;
                singleModeAddBoss(1, 4);
                singleModeAddBoss(2, 5);
            } else if (stage == 2) {
                mapTest = new MapTest("/maploader/map2/genMap.bmp", "/maploader/map2/genMap.txt");
                img = SceneController.getInstance().imageController().tryGetImage(new Path().img().backgrounds().grassBackground2());
//                firstX = mapTest.firstGrid(49, 6).getX();//起始X點
//                firstY = mapTest.firstGrid(49, 6).getY();//起始Y點
                firstX = mapTest.specifyGrid(0, 15).getX();//起始X點
                firstY = mapTest.specifyGrid(0, 15).getY();//起始Y點
                finalGrids.add(mapTest.specifyGrid(49, 20));//終點格
                player.addMoney(200);
                finalWave = 7;
                singleModeAddBoss(1, 4);
                singleModeAddBoss(2, 5);
                singleModeAddBoss(3, 6);
                singleModeAddBoss(4, 7);

            } else if (stage == 3) {
                mapTest = new MapTest("/maploader/map3/genMap.bmp", "/maploader/map3/genMap.txt");
                img = SceneController.getInstance().imageController().tryGetImage(new Path().img().backgrounds().grassBackground3());
                firstX = mapTest.specifyGrid(6, 0).getX();//起始X點
                firstY = mapTest.specifyGrid(6, 0).getY();//起始Y點
                finalGrids.add(mapTest.specifyGrid(49, 25));//終點格1
                finalGrids.add(mapTest.specifyGrid(8, 27));//終點格2
                player.addMoney(500);
                finalWave = 10;
                singleModeAddBoss(1, 4);
                singleModeAddBoss(2, 5);
                singleModeAddBoss(3, 6);
                singleModeAddBoss(4, 7);
                singleModeAddBoss(6, 8);
                singleModeAddBoss(10, 9);
                singleModeAddBoss(20, 10);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        int temp = 0;
        if (stage == 3) {
            temp = 1;
        }
        buttonCallMonster = new Button(firstX * MAP_PIXEL + MAP_PIXEL, (firstY + temp) * MAP_PIXEL, Theme.get(54));
        buttonCallMonster.setClickedActionPerformed((int x, int y) -> {
            wave++;     //波數+1
            canSummonMonster = true;
            isButtonCallMonsterWork = false;

        });

        //場景
        coin = SceneController.getInstance().imageController().tryGetImage(new Path().img().objs().coin());
        playerHpImg = SceneController.getInstance().imageController().tryGetImage(new Path().img().objs().playerHp());
        silver = SceneController.getInstance().imageController().tryGetImage(new Path().img().objs().buttonCallMonster());
        switch (player.getHero()) {
            case ROLE1:
                avator = SceneController.getInstance().imageController().tryGetImage(new Path().img().actors().artificer());
                ability = new Button(80, 750, Theme.get(29));
                abilityCD = new Button(80, 750, Theme.get(33));
                CD = POISONCDTIME;
                break;
            case ROLE2:
                avator = SceneController.getInstance().imageController().tryGetImage(new Path().img().actors().warlord());
                ability = new Button(80, 750, Theme.get(30));
                abilityCD = new Button(80, 750, Theme.get(34));
                CD = LITENINGCDTIME;
                break;
            case ROLE3:
                avator = SceneController.getInstance().imageController().tryGetImage(new Path().img().actors().crystalgolem());
                ability = new Button(80, 750, Theme.get(31));
                abilityCD = new Button(80, 750, Theme.get(35));
                CD = BLOCKCDTIME;
                break;
            case ROLE4:
                avator = SceneController.getInstance().imageController().tryGetImage(new Path().img().actors().medusa());
                ability = new Button(80, 750, Theme.get(32));
                abilityCD = new Button(80, 750, Theme.get(36));
                CD = PETRIFACTIONCDTIME;
                break;
        }

        delay = new Delay(CD);
        isNormal = true;

        playerHp = new Label(140, 100, new Style.StyleRect(160, 40, true, new BackgroundType.BackgroundColor(new Color(0, 0, 0, 60)))
                .setTextColor(new Color(87, 36, 2))
                .setHaveBorder(true)
                .setBorderColor(new Color(215, 186, 50, 0))
                .setBorderThickness(10)
                .setTextFont(new Font("", Font.TYPE1_FONT, 24))
                .setText(player.getPlayerHp() + ""));

        waveLabel = new Label((WINDOW_WIDTH) / 2, 40, new Style.StyleRect(160, 40, true, new BackgroundType.BackgroundColor(new Color(0, 0, 0, 60)))
                .setTextColor(new Color(87, 36, 2))
                .setHaveBorder(true)
                .setBorderColor(new Color(215, 186, 50, 0))
                .setBorderThickness(10)
                .setTextFont(new Font("", Font.TYPE1_FONT, 24))
                .setText(wave + " / " + finalWave));


        coinNum = new Label(100, 40, new Style.StyleRect(80, 40, true, new BackgroundType.BackgroundColor(new Color(0, 0, 0, 60)))
                .setTextColor(new Color(87, 36, 2))
                .setHaveBorder(true)
                .setBorderColor(new Color(215, 186, 50, 0))
                .setBorderThickness(10)
                .setTextFont(new Font("", Font.TYPE1_FONT, 24))
                .setText(player.getMoney() + ""));

        labelPause = new Label(WINDOW_WIDTH / 2, WINDOW_HEIGHT / 2, Theme.get(58));

        CDTimeMonitor = new Label(80, 715, new Style.StyleRect(80, 40, true, new BackgroundType.BackgroundColor(new Color(0, 0, 0, 0)))
                .setTextColor(new Color(87, 36, 2))
                .setHaveBorder(true)
                .setBorderColor(new Color(215, 186, 50, 0))
                .setBorderThickness(10)
                .setTextFont(new Font("", Font.TYPE1_FONT, 12))
                .setText(""));


        abilityName = new Label(80, 700, new Style.StyleRect(90, 20, true, new BackgroundType.BackgroundColor(new Color(0, 0, 0, 80)))
                .setTextColor(new Color(248, 139, 0))
                .setHaveBorder(true)
                .setBorderColor(new Color(215, 186, 50, 0))
                .setBorderThickness(10)
                .setTextFont(new Font("", Font.BOLD, 16))
                .setText(player.getHero().getAbiltyName()));

        addBossName = new Label(80, 600, new Style.StyleRect(90, 20, true, new BackgroundType.BackgroundColor(new Color(0, 0, 0, 80)))
                .setTextColor(new Color(248, 139, 0))
                .setHaveBorder(true)
                .setBorderColor(new Color(215, 186, 50, 0))
                .setBorderThickness(10)
                .setTextFont(new Font("", Font.BOLD, 16))
                .setText("add boss"));

        ability.setClickedActionPerformed((int x, int y) -> {
            AudioResourceController.getInstance().shot(new Path().sound().gameButton());
            //Client.getInstance().send(playerIDforOnlineMode + "," + 64);
            //Client.getInstance().send(playerIDforOnlineMode + "," + 99);
            System.out.println("送出: " + playerIDforOnlineMode );
            switch (player.getHero()) {
                case ROLE1:
                    if (isOnlineMode) {
                        Client.getInstance().send(playerIDforOnlineMode + "," + 87 + "," + 1);
                        controlInternet3.play();
                        //Client.getInstance().send(playerIDforOnlineMode + "," + 99);
                    } else {
                        AllCanUseSkill.SlowAllMonster(actorKungArr, 10);
                        AllCanUseSkill.SlowAllMonster(bosses, 10);
                    }
                    break;
                case ROLE2:
                    if (isOnlineMode) {
                        Client.getInstance().send(playerIDforOnlineMode + "," + 87 + "," + 2);
                        controlInternet3.play();
                        //Client.getInstance().send(playerIDforOnlineMode + "," + 99);
                    } else {
                        AllCanUseSkill.electricShockAllMonster(actorKungArr, 10);
                    }
                    break;
                case ROLE3:
                    if (isOnlineMode) {
                        Client.getInstance().send(playerIDforOnlineMode + "," + 87 + "," + 3);
                        controlInternet3.play();
                        //Client.getInstance().send(playerIDforOnlineMode + "," + 99);
                    } else {
                        AllCanUseSkill.breakAllMonsterDefense(actorKungArr, 15);
                        AllCanUseSkill.breakAllMonsterDefense(bosses, 15);

                    }
                    break;
                case ROLE4:
                    if (isOnlineMode) {
                        Client.getInstance().send(playerIDforOnlineMode + "," + 87 + "," + 4);
                        controlInternet3.play();
                        //Client.getInstance().send(playerIDforOnlineMode + "," + 99);
                    } else {
                        AllCanUseSkill.StunAllMonster(actorKungArr, 5);
                        AllCanUseSkill.StunAllMonster(bosses, 5);
                    }
                    break;
            }
            delay.play();
            isNormal = false;
        });

        if (isOnlineMode) {
            if (isHost) {
                playerIDforOnlineMode = 1;
            } else {
                playerIDforOnlineMode = 2;
            }

        } else {
            playerIDforOnlineMode = 0;
        }
        if (isOnlineMode) {
            finalWave = 999;
            generateBossWave.clear();
            generateMonsterCount *= 5;
            canSummonMonster = true;
        }
    }

    @Override
    public void sceneEnd() {
        coin = null;
        coinNum = null;
        waveLabel = null;
        playerHp = null;
        abilityName = null;
        CDTimeMonitor = null;
        delay = null;
        ability = null;
        buttonCallMonster = null;
        buttonPause = null;
        buttonCancel = null;
        buttonFastRatio = null;
        buttonPlay = null;
        labelPause = null;
        addBossName = null;
        buttonAddBossToCompetitor = null;
        SceneController.getInstance().imageController().clear();
    }

    public void saveFile(Player player) {
        try {
            String temp = player.getHero().getValue();
            BufferedWriter bw = new BufferedWriter(new FileWriter("save.txt"));
            //一個一個寫的方式
            bw.write(temp);
            bw.newLine();
            bw.write(player.getLevel() + "");
            bw.newLine();
            bw.flush();
            bw.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public void paint(Graphics g) {
        g.drawImage(img, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, null);
        mapTest.paint(g);
        //畫終點標示牌
        int paintPosition1X = 0, paintPosition1Y = 0, paintPosition2X, paintPosition2Y;
        switch (stage) {
            case 1:
            case 2:
                paintPosition1X = (int) ((finalGrids.get(0).getX() - 1) * MAP_PIXEL);
                paintPosition1Y = (int) ((finalGrids.get(0).getY() - 1) * MAP_PIXEL);
                break;
            case 3:
                paintPosition1X = (int) ((finalGrids.get(0).getX() - 1) * MAP_PIXEL);
                paintPosition1Y = (int) ((finalGrids.get(0).getY() - 1) * MAP_PIXEL);
                paintPosition2X = (int) ((finalGrids.get(1).getX() - 1) * MAP_PIXEL);
                paintPosition2Y = (int) ((finalGrids.get(1).getY() - 1) * MAP_PIXEL);
                g.drawImage(SceneController.getInstance().imageController().tryGetImage(new Path().img().road().youCanNotPass()),
                        paintPosition2X, paintPosition2Y, 64, 64, null);
        }
        g.drawImage(SceneController.getInstance().imageController().tryGetImage(new Path().img().road().youCanNotPass()),
                paintPosition1X, paintPosition1Y, 64, 64, null);

        for (int i = 0; i < actorKungArr.size(); i++) {
            actorKungArr.get(i).paintComponent(g);
        }

        for (Actor boss : bosses) {
            boss.paintComponent(g);
        }

        for (int i = 0; i < defenseTowers.size(); i++) {
            defenseTowers.get(i).paintComponent(g);
        }

        //場景

        if (isOnlineMode && generateBossToOpponent() && isControlInternet2  ) {
            addBossName.paint(g);
            buttonAddBossToCompetitor.paint(g);
        }

        g.drawImage(coin, 20, 20, 40, 40, null);        //金幣
        coinNum.paint(g);

        waveLabel.paint(g);
        g.drawImage(silver, (WINDOW_WIDTH) / 2 - 120, 20, 40, 40, null);     //波數骷髏
        playerHp.paint(g);
        g.drawImage(playerHpImg, 20, 80, 40, 40, null);        //血量愛心
        abilityName.paint(g);
        buttonCancel.paint(g);
        if (!isPause) {
            if (!isOnlineMode) {
                buttonPause.paint(g);
                buttonFastRatio.paint(g);
            }
        } else {
            buttonPlay.paint(g);
            if (!isOnlineMode) {
                labelPause.paint(g);
            }
        }
        if (isButtonCallMonsterWork) {
            if (!isOnlineMode) {
                buttonCallMonster.paint(g);
            }
        }

        if (isNormal) {
            ability.paint(g);
        } else {
            abilityCD.paint(g);
            CDTimeMonitor.paint(g);
        }
        g.drawImage(avator, 20, 780, 96, 96, null);


        for (int i = 0; i < bosses.size(); i++) {
            Monster temp = (Monster)bosses.get(i);
           if(temp.getMonsterType() == Monster.MonsterType.BOSS2 && isStan) {
                System.out.println(isStan);
                g.drawImage(talk, temp.painter().left()-30, temp.painter().top()-45, 100, 30, null);
                g.setColor(Color.RED);
                g.setFont(new Font("TimesRoman", Font.PLAIN, 14));
                g.drawString("我要回家囉",temp.painter().left()-18, temp.painter().top()-31);
            }

        }

        //cat

        this.catSetLabel1.paint(g);
    }

    @Override
    public void update() {
        for (int k = 0; k < fastRatio; ++k) {
            if (!isPause) {       //遊戲暫停

                if (wave >= finalWave && !canSummonMonster && actorKungArr.size() == 0 && bosses.size() == 0) {
                    //過關
                    System.out.println("過關!!");
                    if (!isOnlineMode) {
                        saveFile(player);

                    } else {
//                        try {
//                            Client.getInstance().closeConnect();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
                    }

                    SceneController.getInstance().change(new GameWinScene(player, isOnlineMode, isHost));
                    break;
                }


                if (player.getPlayerHp() <= 0) {
                    if (isOnlineMode) {
                        Client.getInstance().send(playerIDforOnlineMode + "," + 89);
//                        Client.getInstance().send(playerIDforOnlineMode + "," + 99);
//                        try {
//                            Client.getInstance().closeConnect();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
                    }
                    SceneController.getInstance().change(new GameOverScene(player, isOnlineMode, isHost));
                }

                if (canSummonMonster && totalGenerateNumber == 0) {     //避免每次update都重設
                    totalGenerateNumber = (int) Math.round(10 * Math.pow((1 + wave * 0.3), 2));     //依照當前波數設定生多少怪
                    int count = 0;
                    for (int waveCount : generateBossWave) {        //算同一波有多少boss
                        if (waveCount == wave) {
                            count++;
                        }
                    }
                    if (count== 0){
                        count = 1;
                    }
                    bossGenerateDelay = new Delay((int) (10 * Math.pow((1 + wave * 0.3), 2)) / count - 1);      //依照當波生怪時間平均生boss
                    bossGenerateDelay.loop();
                }

                if (!isOnlineMode && !canSummonMonster && wave < finalWave) {        //顯示生成下一波怪按鈕，多人模式不顯示
                    isButtonCallMonsterWork = true;
                }

                if (isOnlineMode && !canSummonMonster) {        //多人模式每波怪間隔5秒生
                    if (onlineModeWaveDelay.count()) {
                        wave++;
                        canSummonMonster = true;
                    }
                }

                generateMonster();       //生怪

                for (int i = 0; i < actorKungArr.size(); i++) {
                    Actor tmp = actorKungArr.get(i);

                    if (tmp.isArriveDestination()) {        //走到目的地才指定下一格要到哪
                        tmp.move(mapTest);
                    }
                    tmp.update();
                    for (MapInfo grid : finalGrids) {     //終點判定
                        if (grid.isInThisGrid(tmp)) {
                            actorKungArr.remove(i--);     //怪物走到終點移除
                            player.setPlayerHp(player.getPlayerHp() - 1);       //玩家扣血
                        }
                    }
                    if (tmp.isDead()) {       //怪物死亡移除
                        player.addMoney(3 + (int) Math.round(Math.pow(1 + (wave * 0.5), 2)));      //依照怪物等級給錢
                        actorKungArr.remove(i--);
                    }
                }
                /**boss move*/
                for (int i = 0; i < bosses.size(); i++) {
                    if (bosses.get(i).isArriveDestination()) {        //走到目的地才指定下一格要到哪
                        bosses.get(i).move(mapTest);
                    }
                    Actor temp = bosses.get(i);
                    temp.update();
                    for (MapInfo grid : finalGrids) {     //終點判定
                        if (grid.isInThisGrid(temp)) {
                            bosses.remove(i--);     //boss走到終點移除
                            player.setPlayerHp(player.getPlayerHp() - 9);       //玩家直接扣到剩1滴血
                        }
                    }
                    if (temp.isDead()) {       //血量歸0移除
                        player.addMoney((int) temp.getHp() + (int) Math.round(Math.pow(10 + (wave), 2)));      //依照boss等級給錢
                        bosses.remove(i);
                        i--;
                        //紀錄擊殺數
                    }
                }

                for (int i = 0; i < defenseTowers.size(); i++) {
                    DefenseTower tmp = defenseTowers.get(i);
                    tmp.areaEffectJudge(bosses);
                    tmp.areaEffectJudge(actorKungArr);
                    tmp.update();
                    if (tmp.getTarget() == null) {
                        for (int j = actorKungArr.size() - 1; j >= 0; j--) {     //倒著掃塔才會先打前面的怪
                            Actor tmp2 = actorKungArr.get(j);
                            if (tmp.isIntoAttackRange(tmp2)) {
                                tmp.setTarget(tmp2);
                            }
                        }
                        for (int j = bosses.size() - 1; j >= 0; j--) {     //優先攻擊boss(放後面掃)
                            Actor tmp2 = bosses.get(j);
                            if (tmp.isIntoAttackRange(tmp2)) {
                                tmp.setTarget(tmp2);
                            }
                        }
                    }

                }
                //場景
                if (delay.count()) {
                    isNormal = true;
                }


                playerHp.setStyleNormal(new Style.StyleRect(80, 40, true, new BackgroundType.BackgroundColor(new Color(0, 0, 0, 100)))
                        .setTextColor(new Color(255, 72, 72, 255))
                        .setHaveBorder(true)
                        .setBorderColor(new Color(215, 186, 50, 0))
                        .setBorderThickness(10)
                        .setTextFont(new Font("", Font.TYPE1_FONT, 24))
                        .setText(player.getPlayerHp() + ""));
                waveLabel.setStyleNormal(new Style.StyleRect(80, 40, true, new BackgroundType.BackgroundColor(new Color(0, 0, 0, 100)))
                        .setTextColor(new Color(255, 255, 255, 255))
                        .setHaveBorder(true)
                        .setBorderColor(new Color(215, 186, 50, 0))
                        .setBorderThickness(10)
                        .setTextFont(new Font("", Font.TYPE1_FONT, 24))
                        .setText(wave + " / " + finalWave));
                coinNum.setStyleNormal(new Style.StyleRect(80, 40, true, new BackgroundType.BackgroundColor(new Color(0, 0, 0, 100)))
                        .setTextColor(new Color(241, 236, 20))
                        .setHaveBorder(true)
                        .setBorderColor(new Color(215, 186, 50, 0))
                        .setBorderThickness(10)
                        .setTextFont(new Font("", Font.TYPE1_FONT, 24))
                        .setText(player.getMoney() + ""));
                CDTimeMonitor = new Label(80, 715, new Style.StyleRect(80, 40, true, new BackgroundType.BackgroundColor(new Color(0, 0, 0, 0)))
                        .setTextColor(new Color(255, 140, 0))
                        .setHaveBorder(true)
                        .setBorderColor(new Color(215, 186, 50, 0))
                        .setBorderThickness(10)
                        .setTextFont(new Font("", Font.TYPE1_FONT, 12))
                        .setText("CD time: " + ((CD - delay.getCount()) / 60 + 1) + " s"));


                //連線接收中心
                if (isOnlineMode) {
                    if (Integer.parseInt(Client.getInstance().getData()[0]) != playerIDforOnlineMode) {
                        System.out.println("第一號: " + Integer.parseInt(Client.getInstance().getData()[1]));
                        if (Integer.parseInt(Client.getInstance().getData()[1]) == 87) {
                            if (isControlInternet1){
                             switch (Integer.parseInt(Client.getInstance().getData()[2])) {
                                case 1:
                                    AllCanUseSkill.decreaseDefenseTowerAttackSpeed(defenseTowers, 10);
                                    AudioResourceController.getInstance().loop(new Path().sound().bitme(),3);
                                    catDelay.loop();
                                    isControlInternet1 = false;
                                    controlInternet1.play();
                                    break;
                                case 2:
                                    AllCanUseSkill.doubleAllMonsterDefense(actorKungArr, 15);
                                    AllCanUseSkill.doubleAllMonsterDefense(bosses, 10);
                                    AudioResourceController.getInstance().loop(new Path().sound().bitme(),3);
                                    catDelay.loop();
                                    isControlInternet1 = false;
                                    controlInternet1.play();
                                    break;
                                case 3:
                                    AllCanUseSkill.accelerateAllMonster(actorKungArr, 15);
                                    AllCanUseSkill.accelerateAllMonster(bosses, 10);
                                    AudioResourceController.getInstance().loop(new Path().sound().bitme(),3);
                                    catDelay.loop();
                                    isControlInternet1 = false;
                                    controlInternet1.play();
                                    break;
                                case 4:
                                    AllCanUseSkill.neutralizeDefenseTower(defenseTowers, 5);
                                    AudioResourceController.getInstance().loop(new Path().sound().bitme(),3);
                                    catDelay.loop();
                                    isControlInternet1 = false;
                                    controlInternet1.play();
                                    break;
                                default:
                                    break;
                             }
                            }
                        } else if (Integer.parseInt(Client.getInstance().getData()[1]) == 88) {
                            if (isControlInternet2) {
                                generateBoss();
                                isControlInternet2 = false;
                                controlInternet2.play();
                            }

                        } else if (Integer.parseInt(Client.getInstance().getData()[1]) == 89) {
                            SceneController.getInstance().change(new GameWinScene(player, isOnlineMode, isHost));
                        }


                    }
                }


                if(stan.count()){
                    if(isStan == true) {
                        isStan = false;
                    } else {
                        isStan = true;
                    }
                }



            }
            // cat
            if (catDelay.count()) {
                catSetLabel1 = new Label(800, 400, new Style.StyleRect(600, 600, true, new BackgroundType.BackgroundImage(SceneController.getInstance().imageController().tryGetImage(catSet.get(count++)))));
                if (count > 59) {
                    count = 0;
                    catDelay.stop();
                    catSetLabel1 = new Label(800, 400, new Style.StyleRect(800, 800, true, new BackgroundType.BackgroundColor(new Color(0, 0, 0, 0)))
                            .setTextColor(new Color(87, 36, 2)));
                }
            }

            if(controlInternet1.count()){
                isControlInternet1 = true;
            }
            if(controlInternet2.count()){
                isControlInternet2 = true;
            }

            if(controlInternet3.count()){
                Client.getInstance().send(playerIDforOnlineMode + "," + 99);
            }

            if(controlInternet4.count()){
                Client.getInstance().send(playerIDforOnlineMode + "," + 99);
            }

        }
    }

    @Override
    public CommandSolver.MouseCommandListener mouseListener() {

        return (MouseEvent e, CommandSolver.MouseState state, long trigTime) -> {

//            for (DefenseTower df : defenseTowers) {
//                df.mouseTrig(e, state, trigTime);
//            }
            if (!isPause) {

                for (int i = 0; i < defenseTowers.size(); i++) {
                    defenseTowers.get(i).mouseTrig(e, state, trigTime);
                }

                MouseTriggerImpl.mouseTrig(ability, e, state);
                if (!isOnlineMode) {
                    MouseTriggerImpl.mouseTrig(buttonPause, e, state);
                    MouseTriggerImpl.mouseTrig(buttonFastRatio, e, state);
                }
                mapTest.mouseListener().mouseTrig(e, state, trigTime);
                if (isButtonCallMonsterWork) {
                    if (!isOnlineMode) {
                        MouseTriggerImpl.mouseTrig(buttonCallMonster, e, state);
                    }
                }

            } else {
                MouseTriggerImpl.mouseTrig(buttonPlay, e, state);
            }

            MouseTriggerImpl.mouseTrig(buttonCancel, e, state);

            if (isOnlineMode && generateBossToOpponent() && isControlInternet2 ) {

                MouseTriggerImpl.mouseTrig(buttonAddBossToCompetitor, e, state);
            }

        };
    }

    @Override
    public CommandSolver.KeyListener keyListener() {
        return this;
    }

    @Override
    public void keyPressed(int commandCode, long trigTime) {

    }

    @Override
    public void keyReleased(int commandCode, long trigTime) {
        if (IS_DEBUG) {
            if (commandCode == Global.LEFT) {
                actorKungArr.get(0).getActorAnimator().setDirection(Direction.RIGHT);//改變方向為右邊
                actorKungArr.get(0).translateX(-MAP_PIXEL);
            }
            if (commandCode == Global.RIGHT) {
                actorKungArr.get(0).getActorAnimator().setDirection(Direction.LEFT);//改變方向為左邊
                actorKungArr.get(0).translateX(MAP_PIXEL);
            }
            if (commandCode == Global.UP) {
                actorKungArr.get(0).getActorAnimator().setDirection(Direction.UP);//改變方向為上面
                actorKungArr.get(0).translateY(-MAP_PIXEL);
            }
            if (commandCode == Global.DOWN) {
                actorKungArr.get(0).getActorAnimator().setDirection(Direction.DOWN);//改變方向為下面
                actorKungArr.get(0).translateY(MAP_PIXEL);
            }
            if (commandCode == Global.SPACE) {
                actorKungArr.get(0).getActorAnimator().changeState();
            }
            if (commandCode == Global.SHIFT) {
                actorKungArr.get(0).getActorAnimator().setType(Global.random(0, 7));
            }
        }
    }

    @Override
    public void keyTyped(char c, long trigTime) {

    }


    public static void newDefenseTower(DefenseTower defenseTower) {

        defenseTowers.add(defenseTower);
        AudioResourceController.getInstance().shot(new Path().sound().gameObjectSound().buildTower());
    }

    public static void removeDefenseTower(DefenseTower defenseTower) {

        for (int i = 0; i < defenseTowers.size(); ++i) {
            DefenseTower temp = defenseTowers.get(i);
            if (temp.painter().left() == defenseTower.painter().left() && temp.painter().top() == defenseTower.painter().top()) {
                defenseTowers.remove(temp);
                AudioResourceController.getInstance().shot(new Path().sound().gameObjectSound().sellTower());
                return;
            }
        }
    }

    public void generateMonster() {
        if (canSummonMonster && ++updateCount % generateMonsterCount == 0) {       //生怪
            for (int i = 0; i < generateBossWave.size(); ++i) {
                int bossWave = generateBossWave.get(i);
                if (wave == bossWave) {//        指定波數產生boss
                    if (isOnlineMode || bossGenerateDelay.count()) {        //生boss間隔
                        Monster temp = new Monster(firstX * MAP_PIXEL, firstY * MAP_PIXEL, 32, 32, (firstX) * MAP_PIXEL, (firstY - 1) * MAP_PIXEL, BOSS_PAINT_X, BOSS_PAINT_Y);
                        if (stage == 1) {
                            temp.setAllAbility(1, bossWave);
                        } else if (stage == 2) {
                            temp.setAllAbility(2, bossWave);
                        } else if (stage == 3) {
                            temp.setAllAbility(3, bossWave);
                        }
                        if (isOnlineMode){
                            temp.setMoveSpeed(temp.getMoveSpeed() * 2);
                        }
                        bosses.add(temp);
                        generateBossWave.remove(i--);
                        System.out.println("generateBoss");
                    }
                }
            }
            updateCount = 0;
            generateMonsterCount = 1 + (int) (Math.random() * (150 - Math.pow((1 + wave * 0.8), 2)));     //隨機間隔時間生怪、隨怪量增加減少間隔
            if (generateMonsterCount < 1) {
                generateMonsterCount = 1;
            }
            generateNumber++;       //紀錄產生多少怪

            if (generateNumber >= totalGenerateNumber) {         //產生完指定量之後不再產生
                canSummonMonster = false;
                generateNumber = 0;     //歸零
                totalGenerateNumber = 0;
                if (isOnlineMode) {
                    onlineModeWaveDelay = new Delay(UPDATE_TIMES_PER_SEC * 5);
                    onlineModeWaveDelay.play();
                }
            }

            if (stage != 1) {
                int randomInitialPosition = random(0, 1);
                if (randomInitialPosition == 1) {
                    firstX = mapTest.specifyGrid(49, 6).getX();//起始X點
                    firstY = mapTest.specifyGrid(49, 6).getY();//起始Y點
                } else {
                    if (stage == 2) {
                        firstX = mapTest.specifyGrid(0, 15).getX();//起始X點
                        firstY = mapTest.specifyGrid(0, 15).getY();//起始Y點
                    } else if (stage == 3) {
                        firstX = mapTest.specifyGrid(6, 0).getX();//起始X點
                        firstY = mapTest.specifyGrid(6, 0).getY();//起始Y點
                    }
                }
            }
            Monster temp = new Monster(firstX * MAP_PIXEL, (firstY) * MAP_PIXEL, 32, 32);
            temp.setAllAbility(4 + (int) (Math.random() * 2), wave / 2);
            actorKungArr.add(temp);
        }
    }

    public void singleModeAddBoss(int bossNum, int addWave) {
        for (int i = 0; i < bossNum; ++i) {
            generateBossWave.add(addWave);
        }
    }


    public void initCatSet() {
        catSet = new ArrayList<>();
        catSet.add(new Path().img().actors().cat1());
        catSet.add(new Path().img().actors().cat2());
        catSet.add(new Path().img().actors().cat3());
        catSet.add(new Path().img().actors().cat4());
        catSet.add(new Path().img().actors().cat5());
        catSet.add(new Path().img().actors().cat6());
        catSet.add(new Path().img().actors().cat7());
        catSet.add(new Path().img().actors().cat8());
        catSet.add(new Path().img().actors().cat9());
        catSet.add(new Path().img().actors().cat10());
        catSet.add(new Path().img().actors().cat11());
        catSet.add(new Path().img().actors().cat12());
        catSet.add(new Path().img().actors().cat13());
        catSet.add(new Path().img().actors().cat14());
        catSet.add(new Path().img().actors().cat15());
        catSet.add(new Path().img().actors().cat16());
        catSet.add(new Path().img().actors().cat17());
        catSet.add(new Path().img().actors().cat18());
        catSet.add(new Path().img().actors().cat19());
        catSet.add(new Path().img().actors().cat20());
        catSet.add(new Path().img().actors().cat1());
        catSet.add(new Path().img().actors().cat2());
        catSet.add(new Path().img().actors().cat3());
        catSet.add(new Path().img().actors().cat4());
        catSet.add(new Path().img().actors().cat5());
        catSet.add(new Path().img().actors().cat6());
        catSet.add(new Path().img().actors().cat7());
        catSet.add(new Path().img().actors().cat8());
        catSet.add(new Path().img().actors().cat9());
        catSet.add(new Path().img().actors().cat10());
        catSet.add(new Path().img().actors().cat11());
        catSet.add(new Path().img().actors().cat12());
        catSet.add(new Path().img().actors().cat13());
        catSet.add(new Path().img().actors().cat14());
        catSet.add(new Path().img().actors().cat15());
        catSet.add(new Path().img().actors().cat16());
        catSet.add(new Path().img().actors().cat17());
        catSet.add(new Path().img().actors().cat18());
        catSet.add(new Path().img().actors().cat19());
        catSet.add(new Path().img().actors().cat20());
        catSet.add(new Path().img().actors().cat1());
        catSet.add(new Path().img().actors().cat2());
        catSet.add(new Path().img().actors().cat3());
        catSet.add(new Path().img().actors().cat4());
        catSet.add(new Path().img().actors().cat5());
        catSet.add(new Path().img().actors().cat6());
        catSet.add(new Path().img().actors().cat7());
        catSet.add(new Path().img().actors().cat8());
        catSet.add(new Path().img().actors().cat9());
        catSet.add(new Path().img().actors().cat10());
        catSet.add(new Path().img().actors().cat11());
        catSet.add(new Path().img().actors().cat12());
        catSet.add(new Path().img().actors().cat13());
        catSet.add(new Path().img().actors().cat14());
        catSet.add(new Path().img().actors().cat15());
        catSet.add(new Path().img().actors().cat16());
        catSet.add(new Path().img().actors().cat17());
        catSet.add(new Path().img().actors().cat18());
        catSet.add(new Path().img().actors().cat19());
        catSet.add(new Path().img().actors().cat20());


        catSetLabel1 = new Label(800, 400, new Style.StyleRect(800, 800, true, new BackgroundType.BackgroundColor(new Color(0, 0, 0, 0)))
                .setTextColor(new Color(87, 36, 2)));


        count = 0;
    }


    public boolean generateBossToOpponent() {
        if (player.getMoney() > 100 + wave * 10) {

            return true;
        }
        return false;
    }

    public void generateBoss() {
        generateBossWave.add(wave);
    }


}

