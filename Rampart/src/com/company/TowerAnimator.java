package com.company;

import controllers.SceneController;

import java.awt.*;

import static com.company.Global.*;

public class TowerAnimator{

    private Image lightening_1;
    private Image lightening_2;
    private Image lightening_3;
    private Image lightening_change;


    public TowerAnimator(){

//        lightening_1 = SceneController.getInstance().imageController().tryGetImage(new Path().img().tower().lightening_1());
//        lightening_2 = SceneController.getInstance().imageController().tryGetImage(new Path().img().tower().lightening_2());
//        lightening_3 = SceneController.getInstance().imageController().tryGetImage(new Path().img().tower().lightening_3());
          lightening_change = SceneController.getInstance().imageController().tryGetImage(new Path().img().tower().lightening_change1());


    }

    public void paint(int left, int top, int right, int bottom, Graphics g) {
        g.drawImage(lightening_change,
                left,
                top,
                right,
                bottom,
                0,
                0,
                TOWEL_X,
                TOWEL_Y, null);
    }

//    public void update() {
        //跑走動畫(ActorAnimator)
//        if(delay.count()){
//            count = ++count % state.arr.length;
//        }
//    }
}
