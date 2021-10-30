package gameObject.DefenseTower;


import com.company.*;
import controllers.AudioResourceController;
import gameObject.Actor;
import gameObject.AreaEffectJudge;
import gameObject.GameObject;
import gameObject.Projectile.Projectile;
import gameObject.Rect;
import menu.Button;
import menu.Theme;
import menu.impl.MouseTriggerImpl;
import scene.PopupTowerScene;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import static com.company.Global.*;

public abstract class DefenseTower extends GameObject implements CommandSolver.MouseCommandListener, AreaEffectJudge {

    private double attackPower;     //攻擊力
    protected int towerLevel;      //塔等級
    private double attackRange;     //攻擊範圍半徑
    private double attackSpeed;     //每秒攻擊次數
    private double skillCoolDown;       //技能冷卻時間
    private Delay skillCoolDownDelayCount, attackInterval;      //技能冷卻時間計算器、攻擊間格計算器
    private Actor target;       //目前鎖定的目標
    private ArrayList<Projectile> projectiles;      //發射的攻擊
    private boolean canAttack;      //攻擊間隔計時器到了，這邊true，暫停計時，等到有怪才攻擊，開火後轉false，繼續計時，LOOP
    private boolean canUseSkill;      //同上，技能用
    protected TowerAnimator towerAnimator;
    protected Image img;
    private int slowCount = 0;
    public int sellNum;

    protected PopupTowerScene popupTowerScene;
    protected Button mouseButton;
    protected TowerType towerType;      //在Scene取得塔類型用的
    protected TowerState towerState;        //在Scene取得塔類型用的

    public enum TowerType {      //三大種類的塔:弓箭、魔法、大砲
        ArcherTower, MagicTower, CannonTower,
    }

    public enum TowerState {     //塔的型態:基礎、進階、特化1、特化2
        Basic, Advance, Specialization1, Specialization2,
    }

    public DefenseTower(int x, int y, int width, int height, double towerLevel, TowerType towerType, TowerState towerState) {
        super(x, y, width, height);
        mouseButton = new Button(x + width / 2, y + height / 2, Theme.get(13));
        setButton();
        popupTowerScene = new PopupTowerScene(x + width / 2, y + height / 2, TOWERWIDTH + 80, TOWERHEIGHT + 64, this);
        this.towerType = towerType;
        this.towerState = towerState;
    }

    public void setButton() {
        mouseButton.setClickedActionPerformed((int x, int y) -> {
            AudioResourceController.getInstance().shot(new Path().sound().gameButton());
            System.out.println(towerType + "," + towerState);
            popup();
            popupTowerScene.sceneBegin();
            popupTowerScene.show();
            popupTowerScene.setCancelable();
        });
    }

    public int getTowerLevel() {
        return towerLevel;
    }

    public TowerType getTowerType() {
        return towerType;
    }

    public TowerState getTowerState() {
        return towerState;
    }

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

    public Image getImg() {
        return img;
    }


    public DefenseTower(int cX, int cY, int cWidth, int cHigh, int pX, int pY, int pWidth, int pHigh) {
        super(cX, cY, cWidth, cHigh, pX, pY, pWidth, pHigh);
        mouseButton = new Button(cX, cY, Theme.get(13));
        setButton();
        popupTowerScene = new PopupTowerScene(cX, cY, TOWERWIDTH + 80, TOWERHEIGHT + 64, this);
    }

    @Override
    public void mouseTrig(MouseEvent e, CommandSolver.MouseState state, long trigTime) {
        if (!popupTowerScene.isShow()) {
            MouseTriggerImpl.mouseTrig(mouseButton, e, state);
        } else {
            popupTowerScene.mouseListener().mouseTrig(e, state, trigTime);
        }
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
        return getAttackRange() > Math.pow(Math.pow(rect.centerX() - painter().centerX(), 2) + Math.pow(rect.centerY() - painter().centerY(), 2), 0.5);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < projectiles.size(); i++) {
            projectiles.get(i).paintComponent(g);
        }
        if (Global.IS_DEBUG) {
            paintAttackRange(g);
        }
        this.mouseButton.paint(g);
        if (popupTowerScene.isShow()) {
            popupTowerScene.paint(g);
            paintAttackRange(g);        //顯示攻擊範圍
        }
    }

    public void popup() {
        popupTowerScene = new PopupTowerScene(painter().left() + painter().width() / 2, painter().top() + painter().height() / 2,
                TOWERWIDTH + 80, TOWERHEIGHT + 64, this);

    }

    @Override
    public void addAbnormalState(int duration, AbnormalState abnormalState) {        //附加異常狀態，子類override
        switch (abnormalState) {
            case Stun:
                isStun = true;
                stunDelay = new Delay(duration * UPDATE_TIMES_PER_SEC);
                stunDelay.play();
                break;

            case Slow:
                isSlow = true;
                slowDelay = new Delay(duration * UPDATE_TIMES_PER_SEC);
                slowDelay.play();
        }
    }

    @Override
    public void update() {      //所有塔共用的update
        if (isCanUseSkill()) {      //和攻擊間隔做一樣的事
            if (getTarget() != null) {
                if (IS_DEBUG) {
                    System.out.println("useSkill");
                }
                useSkill(getTarget());
                canUseSkill = false;
                getSkillCoolDownDelayCount().play();
            }
        } else {
            if (getSkillCoolDown() != 0 && getSkillCoolDownDelayCount().count()) {     //無技能的塔直接跳過，有技能的CD到自動使用
                canUseSkill = true;
                getSkillCoolDownDelayCount().stop();        //重製計時器count
            }
        }

        if (isStun) {        //防禦塔失效技能
            if (stunDelay.count()) {
                isStun = false;
            }
            return;
        }

        if (isSlow) {       //防禦塔減攻速技能
            if (slowDelay.count()) {
                isSlow = false;
            }
            if (++slowCount % 3 == 0) {      //減少三分之一攻速
                slowCount = 0;
                return;
            }
        }
        if (getTarget() != null && (!isIntoAttackRange(getTarget()) || getTarget().isDead() || getTarget().isArriveFinalGrid)) {       //判斷當前目標是否離開範圍，離開的話清除當前目標
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

    }

}
