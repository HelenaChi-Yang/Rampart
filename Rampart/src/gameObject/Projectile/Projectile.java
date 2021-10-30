package gameObject.Projectile;

import com.company.Delay;
import com.company.Global;
import com.company.WeaponAnimator;
import gameObject.Actor;
import gameObject.GameObject;
import gameObject.Rect;

import java.awt.*;
import java.util.ArrayList;

public abstract class Projectile extends GameObject {

    private double velocity, velocityX, velocityY;        //投射物速度、X和Y速度分量
    private double arriveTime;      //投射物飛到目標的時間
    private Delay arriveTimeCounter;        //到達時間計時器
    private double distanceX;       //到目標的x軸距離
    private double distanceY;       //到目標的y軸距離
    protected double fireAngle;       //發射角度
    private Actor target;       //攻擊的目標
    //XY的更新計數器要分開
    protected int velocityUpdateCountX;       //更新velocityUpdateCountX次執行一次
    protected int velocityUpdateCountY;       //更新velocityUpdateCountY次執行一次
    protected int updateCountX;       //更新計數器X
    protected int updateCountY;       //更新計數器Y
    protected int detectRange;      //偵測範圍半徑
    public boolean haveEnemyInDetectRange;      //偵測範圍內是否有怪物
    public boolean isHitTarget;     //是否已打到目標
    public ArrayList<Actor> haveBeenHitTargets = new ArrayList<>(1);
    public StraightFlight.ProjectileType projectileType;
    protected WeaponAnimator animator;

    public void paintDetectRange(Graphics g) {
        if (detectRange != 0) {
            int x = collider().centerX(), y = collider().centerY();
            Color color = new Color(161, 0, 255, 42);
            g.setColor(color);
            g.fillOval(x - detectRange, y - detectRange, detectRange * 2, detectRange * 2);
            g.setColor(Color.BLACK);
        }
    }

    public boolean hasBeenHitByThis(Actor target) {
        for (Actor a : haveBeenHitTargets) {
            if (a == target)
                return true;
        }
        return false;
    }

    public int getDetectRange() {
        return detectRange;
    }

    public void setDetectRange(int detectRange) {
        this.detectRange = detectRange;
    }

    public Actor getTarget() {
        return target;
    }

    public Delay getArriveTimeCounter() {
        return arriveTimeCounter;
    }

    public void setArriveTimeCounter(Delay arriveTimeCounter) {
        this.arriveTimeCounter = arriveTimeCounter;
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

    public double getFireAngle() {
        return fireAngle;
    }

    public void setFireAngle(double fireAngle) {
        this.fireAngle = fireAngle;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }


    public double getVelocityX() {
        return velocityX;
    }

    public void setVelocityX(double velocityX) {
        this.velocityX = velocityX;
    }

    public double getVelocityY() {
        return velocityY;
    }

    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
    }

    public int getVelocityUpdateCountX() {
        return velocityUpdateCountX;
    }

    public void setVelocityUpdateCountX(int velocityUpdateCountX) {
        this.velocityUpdateCountX = velocityUpdateCountX;
    }

    public int getVelocityUpdateCountY() {
        return velocityUpdateCountY;
    }

    public void setVelocityUpdateCountY(int velocityUpdateCountY) {
        this.velocityUpdateCountY = velocityUpdateCountY;
    }

    public int getUpdateCountX() {
        return updateCountX;
    }

    public void setUpdateCountX(int updateCountX) {
        this.updateCountX = updateCountX;
    }

    public int getUpdateCountY() {
        return updateCountY;
    }

    public void setUpdateCountY(int updateCountY) {
        this.updateCountY = updateCountY;
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
        setDistanceX(monsterRect.centerX() - projectile.centerX());        //X距離
        setDistanceY(-monsterRect.centerY() + projectile.centerY());        //Y距離，正負要倒過來因為圖片向下移動是加Y軸數值
        double distance = Math.pow(Math.pow(monsterRect.left() - projectile.left(), 2) + Math.pow(monsterRect.top() - projectile.top(), 2), 0.5);       //總飛行路徑長
        setArriveTime(distance / getVelocity());        //飛行時間:總路徑/速度
        setArriveTimeCounter(new Delay((int) Math.round(Global.UPDATE_TIMES_PER_SEC * arriveTime)));     //根據飛行時間設定計時器
        getArriveTimeCounter().play();      //開始計時狀態(實際count在DefenseTower的update)，時間到就消失，怪物扣血，不用擊中判定
        this.target = monster;      //紀錄自己的攻擊目標

        fireAngle = Math.atan2(getDistanceY(), getDistanceX());      //計算發射角度，這邊輸出是弧度(arc)

        velocityX = velocity * Math.cos(fireAngle);     //速度X分量
        velocityY = -velocity * Math.sin(fireAngle);     //速度Y分量，要加負號

        velocityUpdateCountX = (int) Math.round(Global.UPDATE_TIMES_PER_SEC / velocityX);       //X分量計算，速度慢就多次更新才移動1Pixel
        if (velocityUpdateCountX == 0) {      //在更新的地方是updateCountX % velocityUpdateCountX，所以velocityUpdateCountX要>0
            velocityUpdateCountX = 1;
        }

        if (Math.abs(velocityX / Global.UPDATE_TIMES_PER_SEC) > 1) {     //速度快就移動多格
            velocityX = Math.round(velocityX / Global.UPDATE_TIMES_PER_SEC);
        } else {
            if (velocityX / Global.UPDATE_TIMES_PER_SEC > 0) {
                velocityX = 1;      //慢的話更新移動1格，多次更新才移動
            } else if (velocityX / Global.UPDATE_TIMES_PER_SEC < 0) {
                velocityX = -1;
            }
        }

        velocityUpdateCountY = (int) Math.round(Global.UPDATE_TIMES_PER_SEC / velocityY);       //Y分量計算，速度慢就多次更新才移動1Pixel
        if (velocityUpdateCountY == 0) {      //在更新的地方是updateCountY % velocityUpdateCountY，所以velocityUpdateCountY要>0
            velocityUpdateCountY = 1;
        }

        if (Math.abs(velocityY / Global.UPDATE_TIMES_PER_SEC) > 1) {     //速度快就移動多格
            velocityY = Math.round(velocityY / Global.UPDATE_TIMES_PER_SEC);
        } else {
            if (velocityY / Global.UPDATE_TIMES_PER_SEC > 0) {
                velocityY = 1;      //慢的話更新移動1格，多次更新才移動
            } else if (velocityY / Global.UPDATE_TIMES_PER_SEC < 0) {
                velocityY = -1;
            }
        }

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
        if (Global.IS_DEBUG){
            paintDetectRange(g);
        }
        super.paintComponent(g);
    }

    public void update() {
        animator.update();
    }
}
