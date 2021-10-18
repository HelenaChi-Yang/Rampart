package gameObject;

import com.company.ActorAnimator;
import com.company.Delay;
import com.company.Global;
import gameObject.GameObject;
import gameObject.Projectile.Projectile;

import java.awt.*;
import java.util.ArrayList;

public abstract class Actor extends GameObject {

    private double hp;      //血量
    private double attackPower;     //攻擊力
    private double attackSpeed;     //每秒攻擊次數
    private double attackRange;     //攻擊範圍
    private double detectRange;     //警戒範圍，敵人進入此範圍進入戰鬥模式
    private double physicalDefense;     //物理防禦
    private double magicalDefense;      //魔法防禦
    private double moveSpeed;       //移動速度
    private double velocityX;       //update時X軸位移
    private double velocityY;       //update時Y軸位移
    private double moveAngle;       //移動面向角度
    public FaceTo faceTo;       //面相方位，控制Animator
    private boolean isLongRangeAttack;      //是否為遠程攻擊型，是的話攻擊會有投射物
    private double arrivalTime;     //到達目的地所需時間
    private Delay arriveTimeCounter;        //到達目的地所需時間計時器
    private Actor target;       //當前目標敵人
    private double SkillCoolDown;       //技能CD
    private boolean canUseSkill;      //同上，技能用
    private Delay attackInterval, skillCoolDownDelayCount;      //技能冷卻時間計算器、攻擊間格計算器
    private ArrayList<Projectile> projectiles;      //發射的攻擊投射物


    private ActorAnimator actorAnimator;
    private ActorAnimator.State state;
    private Global.Direction dir;


    public Actor(int x, int y, int type, int posX, int posY){
        super(x, y, 32,32);
        state = ActorAnimator.State.WALK;   //一開始都是走路 ， Animator.State.WALK  is  one of the object of State.
        dir = Global.Direction.DOWN; //一開始都是向下
        actorAnimator = new ActorAnimator(type, state, dir, posX, posY);  //把要畫哪個角色和 run or walk帶入animator
    }

    public Actor(int x, int y, int width, int high) {
        super(x, y, width, high);
    }

    public Actor(int cX, int cY, int cWidth, int cHigh, int pX, int pY, int pWidth, int pHigh) {
        super(cX, cY, cWidth, cHigh, pX, pY, pWidth, pHigh);
    }


    @Override
    public void paintComponent(Graphics g) {
//        super.paintComponent(g);
        if (Global.IS_DEBUG) {
            paintDetectRange(g);
            paintAttackRange(g);
        }
    }

    @Override
    public void update() {      //這邊判定有點複雜，怕會有BUG，要再檢查
        if (!isCombating()) {       //是否進入戰鬥狀態
            translate((int) velocityX, (int) velocityY);
            if (arriveTimeCounter.count()) {      //移動到目的地時速度歸零
                velocityX = 0;
                velocityY = 0;
            }
        } else {
            if (getTarget() != null && isIntoAttackRange(getTarget())) {       //判斷當前目標是否離開範圍，離開的話清除當前目標
                setTarget(null);        //清除當前目標
            }
        }


        if (isCanAttack()) {     //是否為可以攻擊狀態
            if (getTarget() != null) {      //有目標
                if (isIntoAttackRange(getTarget())) {       //在攻擊範圍內就攻擊
                    attackTarget();       //攻擊
                    getAttackInterval().play();     //攻擊間格重新計時
                } else {     //朝向敵人移動，同時繼續跑攻擊間格時間CD
                    Rect target = getTarget().collider();
                    moveToDestination(target.left(), target.top());        //設定目標當前位置為目的地
                    translate((int) velocityX, (int) velocityY);        //移動
                    if (getAttackInterval().count()) {        //攻擊間格計時++
                        getAttackInterval().stop();     //間格時間到
                        setCanAttack(true);     //轉為可以攻擊狀態
                    }
                }
            }
        } else {     //攻擊cd中
            if (getAttackInterval() != null && getAttackInterval().count()) {        //攻擊間格計時++
                getAttackInterval().stop();     //間格時間到
                setCanAttack(true);     //轉為可以攻擊狀態
            }
        }

        if (isCanUseSkill()) {      //和攻擊間隔做一樣的事
            if (getTarget() != null) {
                useSkill(getTarget());
                canUseSkill = false;
                getSkillCoolDownDelayCount().play();
            }
        } else {
            if (getSkillCoolDown() != 0 && getSkillCoolDownDelayCount().count()) {     //無技能的直接跳過，有技能的CD到自動使用
                canUseSkill = true;
            }
        }
    }

    //敵人是否在警戒範圍內，在Scene呼叫，Monster掃Soldier找目標，Soldier掃Monster找目標
    public boolean isEnemyInDetectRange(Actor enemy) {
        Rect rect = enemy.collider();
        return getAttackRange() < Math.pow(Math.pow(rect.left() - painter().left(), 2) + Math.pow(rect.top() - painter().top(), 2), 0.5);
    }

    //使用技能
    public abstract void useSkill(Actor target);

    //判定目標是否進入攻擊範圍
    public boolean isIntoAttackRange(Actor enemy) {
        Rect rect = enemy.collider();
        return getAttackRange() < Math.pow(Math.pow(rect.left() - painter().left(), 2) + Math.pow(rect.top() - painter().top(), 2), 0.5);
    }

    //顯示警戒範圍
    public void paintDetectRange(Graphics g) {
        Color transparentMagenta = new Color(255, 204, 0, 71);        //自訂半透明顏色，左邊可選
        g.setColor(transparentMagenta);     //載入顏色
        g.fillOval(painter().left(), painter().top(), (int) (detectRange * 2), (int) (detectRange * 2));
    }

    //顯示攻擊範圍
    public void paintAttackRange(Graphics g) {
        Color transparentMagenta = new Color(255, 0, 59, 55);        //自訂半透明顏色，左邊可選
        g.setColor(transparentMagenta);     //載入顏色
        g.fillOval(painter().left(), painter().top(), (int) (attackRange * 2), (int) (attackRange * 2));
    }

    //死亡判定
    public boolean isDead() {
        if (hp <= 0) {
            return true;
        }
        return false;
    }

    //是否進入戰鬥
    public boolean isCombating() {
        if (target != null) {
            return false;
        }
        return true;
    }

    //攻擊目標
    public void attackTarget() {
        if (isLongRangeAttack){
            //發射投射物，投射物到達後才扣血
            fireProjectile();
        }else {
            //播放攻擊動畫
            target.minusHp(this.attackPower);
        }
    }

    public abstract void fireProjectile();

    //減少血量
    public void minusHp(double enemyAttackPower) {
        this.hp -= enemyAttackPower;
    }

    //移動到目標地
    public void moveToDestination(int x, int y) {
        Rect actor = collider();
        double distance = Math.pow(Math.pow((actor.left() - x), 2) + Math.pow((actor.top() - y), 2), 2);
        double arrivalTime = distance / moveSpeed;
        arriveTimeCounter = new Delay((int) ((arrivalTime * Global.UPDATE_TIMES_PER_SEC)));        //移動計時器
        velocityX = (x - actor.left()) / arrivalTime;       //X軸移動速度
        velocityY = (y - actor.top()) / arrivalTime;       //Y軸移度速度
        arriveTimeCounter.play();
        moveAngle = Math.toDegrees(Math.atan(velocityY / velocityX));       //面向角度
        if (moveAngle <= 45 || moveAngle > 315) {        //設定面向方位
            faceTo = FaceTo.RIGHT;
        } else if (moveAngle <= 135) {
            faceTo = FaceTo.UP;
        } else if (moveAngle <= 225) {
            faceTo = FaceTo.LEFT;
        } else if (moveAngle <= 315) {
            faceTo = FaceTo.DOWN;
        }       //依角度決定動畫顯示哪個方向
    }

    public enum FaceTo {
        UP, DOWN, LEFT, RIGHT
    }



    public ActorAnimator getActorAnimator() {
        return actorAnimator;
    }

    public double getSkillCoolDown() {
        return SkillCoolDown;
    }

    public void setSkillCoolDown(double skillCoolDown) {
        SkillCoolDown = skillCoolDown;
    }

    public ArrayList<Projectile> getProjectiles() {
        return projectiles;
    }

    public boolean isCanAttack() {
        return canAttack;
    }

    public boolean isCanUseSkill() {
        return canUseSkill;
    }

    private boolean canAttack;      //攻擊間隔計時器到了，這邊true，暫停計時，等到有怪才攻擊，開火後轉false，繼續計時，LOOP

    public void setProjectiles(ArrayList<Projectile> projectiles) {
        this.projectiles = projectiles;
    }

    public void setCanAttack(boolean canAttack) {
        this.canAttack = canAttack;
    }

    public void setCanUseSkill(boolean canUseSkill) {
        this.canUseSkill = canUseSkill;
    }

    public Delay getSkillCoolDownDelayCount() {
        return skillCoolDownDelayCount;
    }

    public Delay getAttackInterval() {
        return attackInterval;
    }

    public void setSkillCoolDownDelayCount(Delay skillCoolDownDelayCount) {
        this.skillCoolDownDelayCount = skillCoolDownDelayCount;
    }

    public void setAttackInterval(Delay attackInterval) {
        this.attackInterval = attackInterval;
    }

    public void setMoveAngle(double moveAngle) {
        this.moveAngle = moveAngle;
    }

    public void setVelocityX(double velocityX) {
        this.velocityX = velocityX;
    }

    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
    }

    public double getMoveAngle() {
        return moveAngle;
    }

    public double getVelocityX() {
        return velocityX;
    }

    public double getVelocityY() {
        return velocityY;
    }

    public void setArriveDestination(boolean arriveDestination) {
        isArriveDestination = arriveDestination;
    }

    public void setArrivalTime(double arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setArriveTimeCounter(Delay arriveTimeCounter) {
        this.arriveTimeCounter = arriveTimeCounter;
    }

    public void setTarget(Actor target) {
        this.target = target;
    }

    private boolean isArriveDestination;        //是否到達目標地

    public boolean isArriveDestination() {
        return isArriveDestination;
    }

    public double getArrivalTime() {
        return arrivalTime;
    }

    public Delay getArriveTimeCounter() {
        return arriveTimeCounter;
    }

    public Actor getTarget() {
        return target;
    }

    public boolean isLongRangeAttack() {
        return isLongRangeAttack;
    }

    public void setLongRangeAttack(boolean longRangeAttack) {
        isLongRangeAttack = longRangeAttack;
    }

    public double getDetectRange() {
        return detectRange;
    }

    public void setDetectRange(double detectRange) {
        this.detectRange = detectRange;
    }

    public void setHp(double hp) {
        this.hp = hp;
    }

    public void setAttackPower(double attackPower) {
        this.attackPower = attackPower;
    }

    public void setAttackSpeed(double attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    public void setAttackRange(double attackRange) {
        this.attackRange = attackRange;
    }

    public void setPhysicalDefense(double physicalDefense) {
        this.physicalDefense = physicalDefense;
    }

    public void setMagicalDefense(double magicalDefense) {
        this.magicalDefense = magicalDefense;
    }

    public void setMoveSpeed(double moveSpeed) {
        this.moveSpeed = moveSpeed;
    }

    public double getHp() {
        return hp;
    }

    public double getAttackPower() {
        return attackPower;
    }

    public double getAttackSpeed() {
        return attackSpeed;
    }

    public double getAttackRange() {
        return attackRange;
    }

    public double getPhysicalDefense() {
        return physicalDefense;
    }

    public double getMagicalDefense() {
        return magicalDefense;
    }

    public double getMoveSpeed() {
        return moveSpeed;
    }
}

