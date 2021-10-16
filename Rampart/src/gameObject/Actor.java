package gameObject;


import com.company.ActorAnimator;
import com.company.Global;

import java.awt.*;

import static com.company.Global.Direction;

public class Actor extends com.company.gameObject.GameObject {

    private ActorAnimator actorAnimator;
    private ActorAnimator.State state;
    private Global.Direction dir;


    public Actor(int x, int y, int type){
        super(x, y, 128,128);
        state = ActorAnimator.State.WALK;   //一開始都是走路 ， Animator.State.WALK  is  one of the object of State.
        dir = Global.Direction.DOWN; //一開始都是向下
        actorAnimator = new ActorAnimator(type, state, dir);  //把要畫哪個角色和 run or walk帶入animator
    }

    @Override
    public void paintComponent(Graphics g) {
        actorAnimator.paint(painter().left(), painter().top(), painter().right(), painter().bottom(), g);
    }

    @Override
    public void update() {
        actorAnimator.update();
    }


    public ActorAnimator getActorAnimator() {
        return actorAnimator;
    }

}
