package com.company;

import controllers.SceneController;

import java.awt.*;

import static com.company.Global.*;


public class WeaponAnimator{

    private Image arrow;

    public WeaponAnimator(){
        arrow = SceneController.getInstance().imageController().tryGetImage(new Path().img().weapon().Arrow());
    }

    public void paint(int left, int top, int right, int bottom, Graphics g) {
        g.drawImage(arrow,
                left,
                top,
                right,
                bottom,
                0,
                0,
                WEAPON_X,
                WEAPON_Y, null);
    }

//    public void update() {
        //跑走動畫(ActorAnimator)
//        if(delay.count()){
//            count = ++count % state.arr.length;
//        }
//    }
}
