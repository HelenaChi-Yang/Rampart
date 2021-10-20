package gameObject.DefenseTower;



import com.company.*;
import controllers.AudioResourceController;
import gameObject.Actor;
import gameObject.Projectile.StraightFlight;
import gameObject.Projectile.Projectile;
import menu.impl.MouseTriggerImpl;
import scene.PopupTowerScene;


import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;



public class ArcherTower extends DefenseTower {

    private archerTowerType archerTowerType;
    private Delay crossbowSkillDuration;    //技能"三倍攻速3秒"計時器
    private Projectile spearTowerSkill;

    private TowerAnimator towerAnimator;



    private enum archerTowerType {
        BASIC, ADVANCE, CROSSBOW, SPEAR
    }





    public ArcherTower(int x, int y, int width, int high) {     //建構時直接設為基本塔，因為無法直接蓋進階塔
        super(x, y, width, high);
        archerTowerType = archerTowerType.BASIC;
        setInitialTowerParameter();
        setProjectiles(new ArrayList<Projectile>(1));
        towerAnimator = new TowerAnimator();
    }

    public ArcherTower(int cX, int cY, int cWidth, int cHigh, int pX, int pY, int pWidth, int pHigh) {
        super(cX, cY, cWidth, cHigh, pX, pY, pWidth, pHigh);
        archerTowerType = archerTowerType.BASIC;
        setInitialTowerParameter();
    }

    @Override       //判定怪物進入攻擊範圍後，fireProject()，發射攻擊
    public void fireProjectile() {
        StraightFlight temp = new StraightFlight(collider().left(), collider().top(), 20, 20);
        temp.setProjectileType(StraightFlight.ProjectileType.Arrow);        //設定直射投射物種類，自動載入數值
        temp.towardTarget(getTarget());
        getProjectiles().add(temp);
    }

    public boolean isIntoAttackRange(Actor monster) {
        return super.isIntoAttackRange(monster);
    }

    @Override
    public void useSkill(Actor target) {
        switch (archerTowerType) {
            case CROSSBOW:
                crossbowSkillDuration = new Delay(3 * Global.UPDATE_TIMES_PER_SEC);       //持續時間3秒
                setAttackSpeed(getAttackSpeed() * 3);       //攻擊速度3倍
                break;
            case SPEAR:
                spearTowerSkill = new StraightFlight(collider().left(), collider().top(), 60, 60);    //射出標槍，有空的話用不同的圖
//                spearTowerSkill.setImg();
                spearTowerSkill.towardTarget(getTarget());
        }
    }

    @Override
    public void setInitialTowerParameter() {        //設定初始屬性
        double attackPower = 0, attackSpeed = 0, attackRange = 0;
        switch (archerTowerType) {
            case BASIC:
                attackPower = 10;
                attackSpeed = 1;
                attackRange = 100;
                break;
            case ADVANCE:
                attackPower = getAttackPower() * 1.4;
                attackSpeed = 1.4;
                attackRange = getAttackRange() * 1.4;
                break;
            case CROSSBOW:
                attackPower = 20;
                attackSpeed = 2;
                attackRange = 200;
                crossbowSkillDuration = new Delay(Global.UPDATE_TIMES_PER_SEC * 3);
                setSkillCoolDown(10);       //技能CD10秒
                break;
            case SPEAR:
                attackPower = 30;
                attackSpeed = 1;
                attackRange = 200;
                setSkillCoolDown(5);        //技能CD5秒
        }
        setAttackPower(attackPower);
        setAttackRange(attackRange);
        setTowerLevel(1);
        setSkillCoolDownDelayCount(new Delay((int) (Global.UPDATE_TIMES_PER_SEC * getSkillCoolDown())));      //技能冷卻計時器
        setAttackInterval(new Delay((int) ((1 / attackSpeed) * Global.UPDATE_TIMES_PER_SEC)));        //攻擊間格計時器
        getAttackInterval().play();   //攻擊間格器開始運作
    }

    @Override
    public void levelUp() {
        double increaseRatio = 1.2;
        if (archerTowerType == archerTowerType.ADVANCE) {
            increaseRatio = 1.4;
        }
        setAttackPower(getAttackPower() * increaseRatio);
        setAttackRange(getAttackRange() * increaseRatio);
        setTowerLevel(getTowerLevel() + 1);
    }

    @Override
    public void convertToAdvanceTower(int choose) {
        switch (choose) {
            case 1:
                archerTowerType = archerTowerType.ADVANCE;
                break;
            case 2:
                archerTowerType = archerTowerType.CROSSBOW;
                break;
            case 3:
                archerTowerType = archerTowerType.SPEAR;
        }
        setInitialTowerParameter();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        towerAnimator.paint(painter().left(), painter().top(), painter().right(), painter().bottom(), g);
    }

    @Override
    public void update() {
        super.update();     //基本update

        //各種防禦塔特別的update判定加在這邊
        if (crossbowSkillDuration!= null && crossbowSkillDuration.count()) {        //crossbow的技能持續時間到
            setAttackSpeed(getAttackSpeed() / 3);       //回復原本攻速
        }

        if (spearTowerSkill != null) {      //技能存在場上才做判斷
            spearTowerSkill.update();       //這邊只有移動位置; 由於會穿透所有怪物並造成傷害，要在Scene判定和monsterArr的碰撞與扣血
            if (spearTowerSkill.isOverScreen()) {       //超出螢幕移除
                spearTowerSkill = null;
            }
        }
    }
}
