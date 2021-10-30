package com.company;


import controllers.SceneController;
import java.awt.*;

import static com.company.Global.*;

public class BossAnimator extends ActorAnimator{

    Image boss;
    Delay delay;
    int[] bossArr;
    int count;


    public enum BossAnimatorType{
        BOSS1, BOSS2, BOSS3
    }
    public BossAnimator(BossAnimatorType bossAnimatorType) {
        super(0, State.WALK, Global.Direction.DOWN,ActorImage.monster);
        switch(bossAnimatorType){
            case BOSS1:
                boss = SceneController.getInstance().imageController().tryGetImage(new Path().img().actors().boss1());
                break;
            case BOSS2:
                boss = SceneController.getInstance().imageController().tryGetImage(new Path().img().actors().boss2());
                break;
            case BOSS3:
                boss = SceneController.getInstance().imageController().tryGetImage(new Path().img().actors().boss3());
                break;
        }

        delay = new Delay(20);
        delay.loop();
        bossArr = new int[]{0, 1, 2, 1};
        count = 0;
    }

    public void paint(int left, int top, int right, int bottom, Graphics g) {
        g.drawImage(boss,
                left,
                top,
                right,
                bottom,
                BOSS_X * bossArr[count], //左上 x 前面判斷要跳過幾個角色，一個角色有 3隻，後面3個不同動作同角色互換
                BOSS_Y * getDir().getValue(),//左上 y 前面判斷要跳過幾個角色，一個角色有 4隻，後面判斷角色走路方向
                BOSS_X + BOSS_X * bossArr[count], //右下 x
                BOSS_Y + BOSS_Y * getDir().getValue(), //右下 y
                null);
    }

    public void update() {
        //跑走動畫
        if(delay.count()){
            count = ++count % bossArr.length;     //動畫圖片依照arr.length大小循環
        }
    }

}
