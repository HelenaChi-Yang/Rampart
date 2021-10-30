package com.company;


import controllers.SceneController;
import gameObject.Rect;

import java.awt.*;

import static com.company.Global.*;
import static com.company.Global.WEAPON_Y;

public class ActorAnimator implements CommandSolver.KeyListener {

    private Image img;
    private int currentSubMirror = 0, pixelX = UNIT_X, pixelY = UNIT_Y, subMirrorNumberX, subMirrorNumberY;     //當前顯示分鏡、X軸分鏡數、Y軸分鏡數
    private Delay delay;
    private int count;

    private State state;  //跑走狀態
    private Global.Direction dir; //怪獸目前行走方向
    private int type;  //哪隻角色
    private ActorImage thisActorImage;
    private int posX;//演員初始位置
    private int posY;//演員初始位置

    public enum ActorImage {
        bat, monster,chicken
    }

    //跑走改成state
    public enum State {
        WALK(new int[]{0, 1, 2, 1}, 30),
        RUN(new int[]{2, 0}, 20);

        private int[] arr;
        private int speed;

        State(int[] arr, int speed) {
            this.arr = arr;
            this.speed = speed;
        }
    }

    /**
     * type自己思考只要建構子傳進來就好，還是你的角色走一走會變身，那就要帶入paint參數
     * state為跑 or 走
     * dir 為角色行走上下左右
     * inputPosX/Y 為角色起始位置
     */
    public ActorAnimator(int type, State state, Global.Direction dir, ActorImage actorImage) {
        int temp = 0;
        switch (actorImage) {
            case bat:
                img = SceneController.getInstance().imageController().tryGetImage(new Path().img().actors().flying());
                subMirrorNumberX = 3;
                subMirrorNumberY = 4;
                temp = type;
                break;
            case monster:
                img = SceneController.getInstance().imageController().tryGetImage(new Path().img().actors().monster());
                subMirrorNumberX = 3;
                subMirrorNumberY = 4;
                temp = type;
                break;
            case chicken:
                img = SceneController.getInstance().imageController().tryGetImage(new Path().img().actors().chicken());
                subMirrorNumberX = 3;
                subMirrorNumberY = 4;
                pixelX = pixelY = 32;
                thisActorImage = ActorImage.chicken;
        }
        delay = new Delay(0);
        delay.loop();
        count = 0;
        //設哪一隻角色
        this.type = temp;   //圖中只有一隻腳色的話都設為1
        setState(state); //建構一個跑或走的實體
        setDirection(dir);
    }

    public final void setState(State state) {
        this.state = state;
        this.delay.setLimit(state.speed);
    }

    public final void setDirection(Global.Direction dir) {
        this.dir = dir;
    }

    //把畫角色獨立出來，因為每次畫都要從actor知道角色當前位置，所以要把參數傳進來，根本不須實現
    //Global.Direction dir設角色方位
    public void paint(int left, int top, int right, int bottom, Graphics g) {
        int tempDir;
        tempDir = dir.getValue();
        if (thisActorImage == ActorImage.chicken){      //分鏡位置不同，要轉換
            if (dir.getValue() == 3){
                tempDir = 0;
            }else if (dir.getValue() == 0){
                tempDir = 2;
            }else if (dir.getValue() == 1){
                tempDir = 3;
            }else if (dir.getValue() == 2){
                tempDir = 1;
            }
        }
        g.drawImage(img,
                left,
                top,
                right,
                bottom,
                ((type % subMirrorNumberY) * pixelX * subMirrorNumberX) + (pixelX * state.arr[count]), //左上 x 前面判斷要跳過幾個角色，一個角色有 3隻，後面3個不同動作同角色互換
                ((type / subMirrorNumberY) * pixelY * subMirrorNumberY) + (pixelY * tempDir),//左上 y 前面判斷要跳過幾個角色，一個角色有 4隻，後面判斷角色走路方向
                ((type % subMirrorNumberY) * pixelX * subMirrorNumberX) + (pixelX + pixelX * state.arr[count]), //右下 x
                ((type / subMirrorNumberY) * pixelY * subMirrorNumberY) + (pixelY + pixelY * tempDir), //右下 y
                null);
    }

//    public void changeDir(int directionValue){        //用這個會出問題，要用setDir改方向
//        dir.setValue(directionValue);
//    }

    public void changeState() {
        count = 0;
        if (state == State.RUN) {
            setState(State.WALK);
        } else if (state == State.WALK) {
            setState(State.RUN);
        }
    }

    public void update() {
        //跑走動畫
        if (delay.count()) {
            count = ++count % state.arr.length;     //動畫圖片依照arr.length大小循環
        }
    }

    //移動方向
    @Override
    public void keyPressed(int commandCode, long trigTime) {

    }

    @Override
    public void keyReleased(int commandCode, long trigTime) {

    }

    @Override
    public void keyTyped(char c, long trigTime) {

    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Global.Direction getDir() {
        return dir;
    }

}
