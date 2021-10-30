package gameObject;

import com.company.Delay;
import com.company.GameKernel;
import com.company.Global;
import menu.Button;

import java.awt.*;
import java.util.Map;

public abstract class GameObject implements GameKernel.GameInterface {

    private final Rect collider;
    private final Rect painter;
    protected  Button mouseButton;
    protected AbnormalState abnormalState;
    protected boolean isStun,isSlow, isBreakDefense,isAccelerate,isDecreaseAttackSpeed, isDoubleDefense;
    protected Delay stunDelay,slowDelay, breakDefenseDelay,accelerateDelay,decreaseAttackSpeedDelay,doubleDefenseDelay;

    public enum AbnormalState{      //異常狀態:暈炫、緩速、破防，雙人模式:怪物加速、減少塔攻速
        Stun,Slow, BreakDefense, Accelerate,DecreaseAttackSpeed,DoubleDefense,
    }

    public void addAbnormalState(int duration, AbnormalState abnormalState){        //附加異常狀態，子類override
    }

    public GameObject(int x, int y, int width, int height) {
        this(x, y, width, height, x, y, width, height);
    }

    public GameObject(Rect rect) {
        collider = new Rect(rect);
        painter = new Rect(rect);
    }

    public GameObject(int x, int y, int width, int height, int x2, int y2, int width2, int height2) {
        collider = new Rect(x, y, width, height);
        painter = new Rect(x2, y2, width2, height2);
//        painter.generateWithCenter();  //如以座標點為中心點在打開，沒打開以左上角
//        collider.generateWithCenter();
    }

    public GameObject(Rect rect, Rect rect2) {
        painter = rect.clone();
        collider = rect2.clone();
        painter.setCenter(collider.centerX(), collider.centerY());
    }


    public final void translate(int x, int y) {
        collider.translate(x, y);
        painter.translate(x, y);
    }

    public final void translateX(int x) {
        collider.translateX(x);
        painter.translateX(x);
    }

    public final void translateY(int y) {
        collider.translateY(y);
        painter.translateY(y);
    }

    public boolean isCollision(GameObject object) {
        return collider.overlap(object.collider);
    }

    public final Rect collider() {
        return collider;
    }

    public final Rect painter() {
        return painter;
    }

    public boolean touchTop() {
        return collider.top() <= 0;
    }

    public boolean touchBottom() {
        return collider.bottom() >= Global.SCREEN_Y;
    }

    public boolean touchLeft() {
        return collider.left() <= 0;
    }

    public boolean touchRight() {
        return collider.right() >= Global.SCREEN_X;
    }

    @Override
    public final void paint(Graphics g) {
        paintComponent(g);
    }

    public void paintComponent(Graphics g){
        if (Global.IS_DEBUG) {
            g.setColor(Color.RED);
            collider.paint(g);
            g.setColor(Color.GREEN);
            painter.paint(g);
            g.setColor(Color.black);
        }
    }
}
