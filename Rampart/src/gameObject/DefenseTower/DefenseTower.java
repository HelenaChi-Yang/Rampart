package gameObject.DefenseTower;


import com.company.Delay;
import com.company.Global;
import gameObject.Actor;
import gameObject.Projectile.Projectile;
import gameObject.Rect;
import gameObject.GameObject;


import java.awt.*;
import java.util.ArrayList;

public abstract class DefenseTower extends GameObject {


    private double attackPower;     //攻擊力
    private double towerLevel;      //塔等級
    private double attackRange;     //攻擊範圍半徑
    private double attackSpeed;     //每秒攻擊次數
    private double skillCoolDown;       //技能冷卻時間
    private Delay skillCoolDownDelayCount, attackInterval;      //技能冷卻時間計算器、攻擊間格計算器
    private Actor target;       //目前鎖定的目標
    private ArrayList<Projectile> projectiles;      //發射的攻擊
    private boolean canAttack;      //攻擊間隔計時器到了，這邊true，暫停計時，等到有怪才攻擊，開火後轉false，繼續計時，LOOP
    private boolean canUseSkill;      //同上，技能用
    private Image img;


    public void setCanUseSkill(boolean canUseSkill) {
        this.canUseSkill = canUseSkill;
    }

    public boolean isCanUseSkill() {
        return canUseSkill;
    }

    public boolean isCanAttack() {
        return canAttack;
    }

    public void setCanAttack(boolean canAttack) {
        this.canAttack = canAttack;
    }

    public Delay getAttackInterval() {
        return attackInterval;
    }

    public void setAttackInterval(Delay attackInterval) {
        this.attackInterval = attackInterval;
    }

    public ArrayList<Projectile> getProjectiles() {
        return projectiles;
    }

    public void setProjectiles(ArrayList<Projectile> projectiles) {
        this.projectiles = projectiles;
    }

    public Actor getTarget() {
        return target;
    }

    public void setTarget(Actor target) {
        this.target = target;
    }

    public Delay getSkillCoolDownDelayCount() {
        return skillCoolDownDelayCount;
    }

    public void setSkillCoolDownDelayCount(Delay skillCoolDownDelayCount) {
        this.skillCoolDownDelayCount = skillCoolDownDelayCount;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public void setAttackPower(double attackPower) {
        this.attackPower = attackPower;
    }

    public void setTowerLevel(double towerLevel) {
        this.towerLevel = towerLevel;
    }

    public void setAttackSpeed(double attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    public void setAttackRange(double attackRange) {
        this.attackRange = attackRange;
    }

    public double getSkillCoolDown() {
        return skillCoolDown;
    }

    public void setSkillCoolDown(double skillCoolDown) {
        this.skillCoolDown = skillCoolDown;
    }

    public double getAttackRange() {
        return attackRange;
    }

    public double getAttackPower() {
        return attackPower;
    }

    public double getAttackSpeed() {
        return attackSpeed;
    }

    public double getTowerLevel() {
        return towerLevel;
    }

    public Image getImg() {
        return img;
    }

    public DefenseTower(int x, int y, int width, int high) {
        super(x, y, width, high);
    }

    public DefenseTower(int cX, int cY, int cWidth, int cHigh, int pX, int pY, int pWidth, int pHigh) {
        super(cX, cY, cWidth, cHigh, pX, pY, pWidth, pHigh);
    }


    //在Scene判定怪物進入攻擊範圍後，fireProject.towardTarget，發射攻擊
    public abstract void fireProjectile();

    //發動技能
    public abstract void useSkill(Actor target);

    //設置一等塔的初始屬性
    public abstract void setInitialTowerParameter();

    //升級防禦塔
    public abstract void levelUp();

    //進化防禦塔(變高階塔lv1)
    public abstract void convertToAdvanceTower(int choose);

    //顯示攻擊範圍
    public void paintAttackRange(Graphics g) {
        Color transparentMagenta = new Color(0, 81, 255, 34);        //自訂半透明顏色，左邊可選
        g.setColor(transparentMagenta);     //載入顏色
        g.fillOval(painter().left() - (int) (attackRange - painter().width() / 2), painter().top() - (int) (attackRange - painter().height() / 2), (int) (attackRange * 2), (int) (attackRange * 2));
    }

    //判定怪物進入攻擊範圍，在Scene呼叫 if(DefenseTower.getTarget == null)，防禦塔目前沒目標才掃過怪物array，掃到攻擊範圍內有目標的話，setTarget該目標，break怪物陣列掃描
    public boolean isIntoAttackRange(Actor monster) {
        Rect rect = monster.collider();
        return getAttackRange() > Math.pow(Math.pow(rect.left() - painter().left(), 2) + Math.pow(rect.top() - painter().top(), 2), 0.5);
    }

    public void paintComponent(Graphics g) {
        for (int i = 0; i < projectiles.size(); i++) {
            projectiles.get(i).paintComponent(g);
        }
        super.paint(g);
        if (Global.IS_DEBUG) {
            paintAttackRange(g);
        }
    }

    @Override
    public void update() {      //所有塔共用的update

        if (getTarget() != null && !isIntoAttackRange(getTarget())) {       //判斷當前目標是否離開範圍，離開的話清除當前目標
            setTarget(null);        //清除當前目標
        }

        if (isCanAttack()) {     //是否為可以攻擊狀態
            if (getTarget() != null) {      //有目標
                fireProjectile();       //發射攻擊
                getAttackInterval().play();     //攻擊間格重新計時
                setCanAttack(false);
            }
        } else {     //攻擊cd中
            if (getAttackInterval().count()) {        //攻擊間格計時++
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
            if (getSkillCoolDown() != 0 && getSkillCoolDownDelayCount().count()) {     //無技能的塔直接跳過，有技能的CD到自動使用
                canUseSkill = true;
            }
        }


        for (int i = 0; i < projectiles.size(); ++i) {     //掃過投射物array
            Projectile temp = projectiles.get(i);
            temp.update();     //移動功能在投射物的update
            if (temp.getArriveTimeCounter().count()) {       //投射物飛行時間到(也就是飛到目標身上了，這樣寫就必中，不用碰撞判定)
                getProjectiles().remove(temp);     //投射物消失
                temp.getTarget().minusHp(attackPower);        //怪物扣血
            }

//            if (p.isOverScreen()) {      //超出螢幕移除，必中用不到
//                getProjectiles().remove(p);
//            }
        }
    }
}
