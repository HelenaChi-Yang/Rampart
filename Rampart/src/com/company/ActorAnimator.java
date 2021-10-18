package com.company;


import controllers.SceneController;

import java.awt.*;

import static com.company.Global.UNIT_X;
import static com.company.Global.UNIT_Y;

public class ActorAnimator implements CommandSolver.KeyListener{

    private Image img;

    private Delay delay;
    private int count;

    private State state;  //跑走狀態
    private Global.Direction dir; //怪獸目前行走方向
    private int type;  //哪隻角色

    private int posX;//演員初始位置
    private int posY;//演員初始位置


    //跑走改成state
    public enum State{
        WALK(new int[]{0, 1, 2, 1}, 30),
        RUN(new int[]{2, 0}, 20);

        private int[] arr;
        private int speed;

        State(int[] arr, int speed){
            this.arr = arr;
            this.speed = speed;
        }
    }

    /**type自己思考只要建構子傳進來就好，還是你的角色走一走會變身，那就要帶入paint參數
     * state為跑 or 走
     * dir 為角色行走上下左右
     * inputPosX/Y 為角色起始位置*/
    public ActorAnimator(int type, State state, Global.Direction dir, int inputPosX, int inputPosY){

        img = SceneController.getInstance().imageController().tryGetImage(new Path().img().actors().flying());
        delay = new Delay(0);
        delay.loop();
        count = 0;
        //設哪一隻角色
        this.type = type;
        setState(state); //建構一個跑或走的實體
        setDirection(dir);

        posX = inputPosX;
        posY = inputPosY;
    }

    public final void setState(State state){
        this.state = state;
        this.delay.setLimit(state.speed);
    }

    public final void setDirection(Global.Direction dir){
        this.dir = dir;
    }

    //把畫角色獨立出來，因為每次畫都要從actor知道角色當前位置，所以要把參數傳進來，根本不須實現
    //Global.Direction dir設角色方位
    public void paint(int left, int top, int right, int bottom, Graphics g) {
        g.drawImage(img,
                left,
                top,
                right,
                bottom,
                ((type % 4) * UNIT_X * 3) + (UNIT_X * state.arr[count]), //左上 x 前面判斷要跳過幾個角色，一個角色有 3隻，後面3個不同動作同角色互換
                ((type / 4) * UNIT_Y * 4) + (UNIT_Y * dir.getValue()),//左上 y 前面判斷要跳過幾個角色，一個角色有 4隻，後面判斷角色走路方向
                ((type % 4) * UNIT_X * 3) + (UNIT_X + UNIT_X * state.arr[count]), //右下 x
                ((type / 4) * UNIT_Y * 4) + (UNIT_Y + UNIT_Y * dir.getValue()), //右下 y
                null);
    }

    public void changeDir(int directionValue){
        dir.setValue(directionValue);
    }

    public void changeState(){
        count = 0;
        if(state == State.RUN){
            setState(State.WALK);
        }else if(state == State.WALK){
            setState(State.RUN);
        }
    }

    public void update() {
        //跑走動畫
        if(delay.count()){
            count = ++count % state.arr.length;
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

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX += posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY += posY;
    }

}
