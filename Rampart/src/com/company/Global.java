package com.company;

public class Global {

    public enum Direction {
        //根據actor y軸下去做調整
        UP(3),
        DOWN(0),
        LEFT(1),
        RIGHT(2);

        private int value;
        Direction(int value){
            this.value = value;
        }

        public int getValue(){
            return value;
        }
        public void setValue(int value){
            this.value = value;
        }

    }

    public static final boolean IS_DEBUG = true;

    public static final int UPDATE_TIMES_PER_SEC = 60;
    public static final int NANOSECOUND_PER_UPDATE = 1000000000 / UPDATE_TIMES_PER_SEC;

    public static final int FRAME_LIMIT = 60;
    public static final int LIMIT_DELTA_TIME = 1000000000 / FRAME_LIMIT;

    //視窗大小
    public static final int WINDOW_WIDTH = 1600;
    public static final int WINDOW_HEIGHT = 896;
    public static final int SCREEN_X = WINDOW_WIDTH - 8 - 8;
    public static final int SCREEN_Y = WINDOW_HEIGHT - 31 - 8;


    public static final int UP = 1;
    public static final int DOWN = 2;
    public static final int LEFT = 3;
    public static final int RIGHT = 4;
    public static final int SPACE = 5;
    public static final int SHIFT = 6;

    //怪獸切圖像素單位
    public static final int UNIT_X = 48;
    public static final int UNIT_Y = 48;

    //地圖每隔像素
    public static final int MAP_PIXEL = 32;

    //塔的切圖單位
    public static final int TOWEL_X = 32;
    public static final int TOWEL_Y = 64;

    //箭切圖單位
    public static final int WEAPON_X = 32;
    public static final int WEAPON_Y = 32;

    //物件單位大小
    public static final int ACTOR_WIDTH = 56;
    public static final int ACTOR_HEIGHT = 80;
    public static final int GAMEBUTTON_X = 32;
    public static final int GAMEBUTTON_Y = 32;
    public static final int ROLEBUTTON_X = 224;
    public static final int ROLEBUTTON_Y = 224;
    public static final int TOWERWIDTH = 32;
    public static final int TOWERHEIGHT = 64;

    public static int countClient = 0;

    public enum gameRole {
        ROLE1("Heisenberg"),
        ROLE2("Thor"),
        ROLE3("Stone Man"),
        ROLE4("Medusa");

        public String getValue() {
            return value;
        }

        public String value;

        gameRole(String value) {
            this.value = value;
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
