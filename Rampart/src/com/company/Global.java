package com.company;

public class Global {

    public enum Direction {
        //根據actor y軸下去做調整
        UP(3),
        DOWN(0),
        LEFT(1),
        RIGHT(2);

        private int value;

        Direction(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

    }

    public static boolean isPause = false;
    public static final boolean IS_DEBUG = false;

    public static final int UPDATE_TIMES_PER_SEC = 60;
    public static final int NANOSECOUND_PER_UPDATE = 1000000000 / UPDATE_TIMES_PER_SEC;

    public static final int FRAME_LIMIT = 60;
    public static final int LIMIT_DELTA_TIME = 1000000000 / FRAME_LIMIT;

    //視窗大小
    public static final int WINDOW_WIDTH = 1616;
    public static final int WINDOW_HEIGHT = 935;
    public static final int SCREEN_X = WINDOW_WIDTH - 8 - 8;
    public static final int SCREEN_Y = WINDOW_HEIGHT - 31 - 8;


    public static final int UP = 1;
    public static final int DOWN = 2;
    public static final int LEFT = 3;
    public static final int RIGHT = 4;
    public static final int SPACE = 5;
    public static final int SHIFT = 6;

    //BOSS切圖像素單位
    public static final int BOSS_X = 216;
    public static final int BOSS_Y = 216;
    public static final int BOSS_PAINT_X = 64;
    public static final int BOSS_PAINT_Y = 64;

    //怪獸切圖像素單位
    public static final int UNIT_X = 48;
    public static final int UNIT_Y = 48;

    //地圖每隔像素
    public static final int MAP_PIXEL = 32;

    //塔的切圖單位
    public static final int TOWEL_X = 64;
    public static final int TOWEL_Y = 64;

    //箭切圖單位
    public static final int WEAPON_X = 32;
    public static final int WEAPON_Y = 32;

    //物件單位大小
    public static final int ACTOR_WIDTH = 56;
    public static final int ACTOR_HEIGHT = 80;
    public static final int GAMEBUTTON_X = 32;
    public static final int GAMEBUTTON_Y = 32;
    public static final int ABILITYBUTTON_X = 64;
    public static final int ABILITYBUTTON_Y = 64;
    public static final int ROLEBUTTON_X = 224;
    public static final int ROLEBUTTON_Y = 224;
    public static final int TOWERWIDTH = 32;
    public static final int TOWERHEIGHT = 64;
    public static final int INTROPAGE_X = 1500;
    public static final int INTROPAGE_Y = 200;

    //CD time
    public static final int POISONCDTIME = 30 * UPDATE_TIMES_PER_SEC;
    public static final int LITENINGCDTIME = 30 * UPDATE_TIMES_PER_SEC;
    public static final int BLOCKCDTIME = 30 * UPDATE_TIMES_PER_SEC;
    public static final int PETRIFACTIONCDTIME = 30 * UPDATE_TIMES_PER_SEC;

    //連線
    public static int countClient = 0;

    //建初級塔費用;
    public static final int ARCHERTOWERCOST = 80;
    public static final int CANNONTOWERCOST = 200;
    public static final int MAGICTOWERCOST = 100;

    //初級塔升級
    public static final int ARCHERTOWER1TO2 = (int) (ARCHERTOWERCOST * 1.5);
    public static final int ARCHERTOWER2TO3 = (int) (ARCHERTOWERCOST * 2.5);
    public static final int CANNONTOWER1TO2 = (int) (CANNONTOWERCOST * 1);
    public static final int CANNONTOWER2TO3 = (int) (CANNONTOWERCOST * 2);
    public static final int MAGICTOWER1TO2 = (int) (MAGICTOWERCOST * 1.5);
    public static final int MAGICTOWER2TO3 = (int) (MAGICTOWERCOST * 2.5);

    //進階塔
    public static final int ARCHERTOWERAV = (int) (ARCHERTOWERCOST * 6);
    public static final int CANNONTOWERAV = (int) (CANNONTOWERCOST * 6);
    public static final int MAGICTOWERAV = (int) (MAGICTOWERCOST * 6);

    //進階塔升級
    public static final int ARCHERTOWERAV1TO2 = (int) (ARCHERTOWERAV * 1);
    public static final int ARCHERTOWERAV2TO3 = (int) (ARCHERTOWERAV * 2);
    public static final int CANNONTOWERAV1TO2 = (int) (CANNONTOWERAV * 1);
    public static final int CANNONTOWERAV2TO3 = (int) (CANNONTOWERAV * 2);
    public static final int MAGICTOWERAV1TO2 = (int) (MAGICTOWERAV * 1);
    public static final int MAGICTOWERAV2TO3 = (int) (MAGICTOWERAV * 2);

    //特化塔
    public static final int ARCHERTOWERSP = (int) (ARCHERTOWERCOST * 8);
    public static final int CANNONTOWERSP = (int) (CANNONTOWERCOST * 6);
    public static final int MAGICTOWERSP = (int) (MAGICTOWERCOST * 8);

    //特殊塔升級
    public static final int ARCHERTOWERSP1TO2 = (int) (ARCHERTOWERSP * 1);
    public static final int ARCHERTOWERSP2TO3 = (int) (ARCHERTOWERSP * 1.5);
    public static final int CANNONTOWERSP1TO2 = (int) (CANNONTOWERSP * 1);
    public static final int CANNONTOWERSP2TO3 = (int) (CANNONTOWERSP * 1.5);
    public static final int MAGICTOWERSP1TO2 = (int) (MAGICTOWERSP * 1);
    public static final int MAGICTOWERSP2TO3 = (int) (MAGICTOWERSP * 1.5);


    //1~2級賣塔
    public static final int PRIMARYSELL = 100;
    //3級以上賣塔
    public static final int ADVANCESELL = 300;


    public enum gameRole {
        ROLE1("Heisenberg", "Poison (Slow down all enemies for 10s and CD time is 30s)", "Poison (Slow down competitor's towers for 10s and CD time is 30s)", "Poison"),
        ROLE2("Thor", "Lightning (Reduce enemy's blood to half and CD time is 30s)", "Lightning (upgrade competitor's enemies for 10s and CD time is 30s)", "Lightning"),
        ROLE3("Stone Man", "Break (Reduce enemy's defense to 0 for 10s and CD time is 30s)", "Break (Accelerate competitor's enemies for 10s and CD time is 30s)", "Break"),
        ROLE4("Medusa", "Petrifaction (Stop all enemies for 10s and CD time is 30s)", "Petrifaction (Stop competitor's towers for 10s and CD time is 30s)", "Petrifaction"),
        NONE("none", "none", "none", "none");

        public String getValue() {
            return value;
        }


        private String value;

        public String getAbility() {
            return ability;
        }

        private String ability;
        private String ability2;

        public String getAbiltyName() {
            return abiltyName;
        }

        public String abiltyName;

        public String getAbility2() {
            return ability2;
        }

        gameRole(String value, String ability, String ability2, String abiltyName) {

            this.value = value;
            this.ability = ability;
            this.ability2 = ability2;
            this.abiltyName = abiltyName;
        }
    }

    //血量
//    public static final int TOWERBLOOD = 50;

    /**
     * 輸出範圍內的隨機數字
     *
     * @param min
     * @param max
     * @return
     */
    public static int random(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }
}
