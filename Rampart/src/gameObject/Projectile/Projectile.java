package gameObject.Projectile;

import com.company.Delay;
import com.company.TowerAnimator;
import gameObject.GameObject;
import gameObject.Actor;
import gameObject.Rect;
import com.company.*;

import java.awt.*;

public abstract class Projectile extends GameObject {

    private double velocity;        //投射物速度
    private double arriveTime;      //投射物飛到目標的時間
    private Delay arriveTimeCounter;        //到達時間計時器
    private double distanceX;       //到目標的x軸距離
    private double distanceY;       //到目標的y軸距離
    private Actor target;       //攻擊的目標
    private TowerAnimator animator;


    public Actor getTarget() {
        return target;
    }

    public Delay getArriveTimeCounter() {
        return arriveTimeCounter;
    }

    public void setArriveTimeCounter(Delay arriveTimeCounter) {
        this.arriveTimeCounter = arriveTimeCounter;
    }

    public TowerAnimator getAnimator() {
        return animator;
    }

    public void setAnimator(TowerAnimator animator) {
        this.animator = animator;
    }

    public double getDistanceX() {
        return distanceX;
    }

    public double getDistanceY() {
        return distanceY;
    }

    public void setDistanceX(double distanceX) {
        this.distanceX = distanceX;
    }

    public void setDistanceY(double distanceY) {
        this.distanceY = distanceY;
    }

    public void setArriveTime(double arriveTime) {
        this.arriveTime = arriveTime;
    }

    public double getArriveTime() {
        return arriveTime;
    }

    public double getVelocity() {
        return velocity;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }


    public Projectile(int x, int y, int width, int high) {
        super(x, y, width, high);
    }

    public Projectile(int cX, int cY, int cWidth, int cHigh, int pX, int pY, int pWidth, int pHigh) {
        super(cX, cY, cWidth, cHigh, pX, pY, pWidth, pHigh);
    }

    //直線飛行物，計算飛行時間與X軸距離Y
    public void towardTarget(Actor monster) {
        Rect monsterRect = monster.collider(), projectile = collider();        //取得位置
        setDistanceX(monsterRect.left() - projectile.left());        //X距離
        setDistanceY(monsterRect.top() - projectile.top());        //Y距離
        double distance = Math.pow(Math.pow(monsterRect.left() - projectile.left(), 2) + Math.pow(monsterRect.top() - projectile.top(), 2), 0.5);       //總飛行路徑長
        setArriveTime(distance / getVelocity());        //飛行時間:總路徑/速度
        setArriveTimeCounter(new Delay((int)(Global.UPDATE_TIMES_PER_SEC * arriveTime)));     //根據飛行時間設定計時器
        getArriveTimeCounter().play();      //開始計時狀態(實際count在DefenseTower的update)，時間到就消失，怪物扣血，不用擊中判定
        this.target = monster;      //紀錄自己的攻擊目標
    }

    //判斷是否飛出螢幕外
    public boolean isOverScreen() {
        Rect temp = collider();
        if (temp.top() <= 0
                || temp.bottom() >= Global.WINDOW_HEIGHT
                || temp.left() <= 0
                || temp.right() >= Global.WINDOW_WIDTH) {
            return true;
        }
        return false;
    }

    public void paintComponent(Graphics g) {
        super.paint(g);
    }

    public void update() {
    }
}
