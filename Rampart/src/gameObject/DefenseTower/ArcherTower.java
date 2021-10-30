package gameObject.DefenseTower;


import com.company.Delay;
import com.company.Global;
import com.company.Path;
import com.company.TowerAnimator;
import controllers.AudioResourceController;
import controllers.SceneController;
import gameObject.Actor;
import gameObject.Projectile.Projectile;
import gameObject.Projectile.StraightFlight;

import java.awt.*;
import java.util.ArrayList;


public class ArcherTower extends DefenseTower {

    private archerTowerType archerTowerType;
    private Delay crossbowSkillDuration;    //技能"三倍攻速n秒"計時器
    private double attackSpeedTempStorage;
    private Projectile spearTowerSkill;     //自用的，外面拿不到

    private enum archerTowerType {
        BASIC, ADVANCE, CROSSBOW, SPEAR
    }


    public ArcherTower(int x, int y, int width, int height, double towerLevel, TowerType towerType, TowerState towerState) {     //建構時直接設為基本塔，因為無法直接蓋進階塔
        super(x, y, width, height, towerLevel, towerType, towerState);
        archerTowerType = archerTowerType.BASIC;
        setInitialTowerParameter();
        setProjectiles(new ArrayList<Projectile>(1));
        towerAnimator = new TowerAnimator();
        towerAnimator.setTowerAnimatorImage(towerType, towerState, this.towerLevel);
    }

    public ArcherTower(int cX, int cY, int cWidth, int cHigh, int pX, int pY, int pWidth, int pHigh) {
        super(cX, cY, cWidth, cHigh, pX, pY, pWidth, pHigh);
        archerTowerType = archerTowerType.BASIC;
        setInitialTowerParameter();
    }

    @Override
    public void areaEffectJudge(ArrayList<Actor> actors) {
        //碰撞判定
        for (int i = 0; i < getProjectiles().size(); ++i) {     //掃過投射物array
            Projectile temp = getProjectiles().get(i);
            for (Actor a : actors) {
                if (temp.isCollision(a)) {       //投射物飛行時間到(也就是飛到目標身上了，這樣寫就必中，不用碰撞判定)
                    getProjectiles().remove(temp);     //投射物消失
                    a.attackByPhysical(getAttackPower());        //怪物扣血
                }
            }
        }

        if (spearTowerSkill != null) {
            for (Actor a : actors) {
                if (spearTowerSkill.isCollision(a)) {
                    a.attackByPhysical(getAttackPower() * (getTowerLevel() * 0.1 + 0.1));       //每次update打0.2、0.3、0.4攻擊傷害
                }
            }
        }
    }

    @Override       //判定怪物進入攻擊範圍後，fireProject()，發射攻擊
    public void fireProjectile() {
        StraightFlight temp = new StraightFlight(collider().centerX(), collider().centerY(), 40, 40);
        switch (archerTowerType) {
            case SPEAR:
                temp.setProjectileType(StraightFlight.ProjectileType.Spear);
                AudioResourceController.getInstance().shot(new Path().sound().gameObjectSound().throwSpear());
                break;
            default:
                temp.setProjectileType(StraightFlight.ProjectileType.Arrow);        //設定直射投射物種類，自動載入數值
                AudioResourceController.getInstance().shot(new Path().sound().gameObjectSound().shootArrow());
        }
        temp.towardTarget(getTarget());
        getProjectiles().add(temp);
    }

    @Override
    public boolean isIntoAttackRange(Actor monster) {
        return super.isIntoAttackRange(monster);
    }

    @Override
    public void useSkill(Actor target) {
        switch (archerTowerType) {
            case CROSSBOW:
                crossbowSkillDuration = new Delay(5 * Global.UPDATE_TIMES_PER_SEC);       //持續時間5秒
                crossbowSkillDuration.play();
                attackSpeedTempStorage = getAttackSpeed();      //儲存原本攻速
                setAttackSpeed(getAttackSpeed() * (1 + getTowerLevel() * 3));       //攻擊速度4,7,10倍
                setAttackInterval(new Delay((int) ((1 / getAttackSpeed()) * Global.UPDATE_TIMES_PER_SEC)));        //重設攻擊間格計時器
                getAttackInterval().play();
                break;
            case SPEAR:
                StraightFlight temp = new StraightFlight(collider().centerX(), collider().centerY(), 80, 80);    //射出標槍，有空的話用不同的圖
                AudioResourceController.getInstance().shot(new Path().sound().gameObjectSound().throwSpearSkill());
                temp.setProjectileType(StraightFlight.ProjectileType.SpearSkill);
                temp.towardTarget(getTarget());
                spearTowerSkill = temp;

        }
    }

    @Override
    public void setInitialTowerParameter() {        //設定初始屬性
        double attackPower = 0, attackSpeed = 0, attackRange = 0;
        switch (archerTowerType) {
            case BASIC:
                attackPower = 10;
                attackSpeed = 1;
                attackRange = 130;
                break;
            case ADVANCE:
                attackPower = getAttackPower() * 1.4 * 2;
                attackSpeed = getAttackSpeed() * 1.4;
                attackRange = getAttackRange() * 1.4;
                break;
            case CROSSBOW:
                attackPower = 30;
                attackSpeed = getAttackSpeed() * 1.4;
                attackRange = 200;
                setSkillCoolDown(10);       //技能CD10秒
                break;
            case SPEAR:
                attackPower = 30;
                attackSpeed = getAttackSpeed();
                attackRange = 200;
                setSkillCoolDown(5);        //技能CD5秒
        }
        setAttackPower(attackPower);
        setAttackRange(attackRange);
        setAttackSpeed(attackSpeed);
        towerLevel = 1;
        setSkillCoolDownDelayCount(new Delay((int) (Global.UPDATE_TIMES_PER_SEC * getSkillCoolDown())));      //技能冷卻計時器
        setAttackInterval(new Delay((int) ((1 / getAttackSpeed()) * Global.UPDATE_TIMES_PER_SEC)));        //攻擊間格計時器
        getAttackInterval().play();   //攻擊間格器開始運作
        getSkillCoolDownDelayCount().play();
    }

    @Override
    public void levelUp() {
        double increaseRatio = 1.2;
        if (archerTowerType == archerTowerType.ADVANCE) {
            increaseRatio = 1.4;
            setAttackSpeed(getAttackSpeed() * increaseRatio);
        }
        setAttackPower(getAttackPower() * increaseRatio);
        setAttackRange(getAttackRange() * increaseRatio);
        towerLevel += 1;
        popup();
        switch ((int) getTowerLevel()) {
            case 2:
                img = SceneController.getInstance().imageController().tryGetImage(new Path().img().tower().ArcherTowerBasic2());
                break;
            case 3:
                img = SceneController.getInstance().imageController().tryGetImage(new Path().img().tower().ArcherTowerBasic3());
        }
        AudioResourceController.getInstance().shot(new Path().sound().gameObjectSound().buildTower());
        setAttackInterval(new Delay((int) ((1 / getAttackSpeed()) * Global.UPDATE_TIMES_PER_SEC)));        //攻擊間格計時器
        getAttackInterval().play();   //攻擊間格器開始運作
    }

    @Override
    public void convertToAdvanceTower(int choose) {
        switch (choose) {
            case 1:
                archerTowerType = archerTowerType.ADVANCE;
                towerState = TowerState.Advance;
                break;
            case 2:
                archerTowerType = archerTowerType.CROSSBOW;
                towerState = TowerState.Specialization1;
                break;
            case 3:
                archerTowerType = archerTowerType.SPEAR;
                towerState = TowerState.Specialization2;
        }
        setInitialTowerParameter();
        popup();
        AudioResourceController.getInstance().shot(new Path().sound().gameObjectSound().buildTower());
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (crossbowSkillDuration != null) {     //crossbow技能開啟狀態下顯示
            Color color = new Color(255, 204, 0, 228);
            g.setColor(color);
            g.fillOval(collider().left(), collider().top(), collider().width(), collider().height());
            g.setColor(Color.BLACK);
        }
        if (spearTowerSkill != null) {       //spear技能顯示
            spearTowerSkill.paintComponent(g);
        }
        towerAnimator.setTowerAnimatorImage(towerType, towerState, towerLevel);
        towerAnimator.paint(painter().left(), painter().top(), painter().width(), painter().height(), g);
    }

    @Override
    public void update() {
        super.update();     //基本update

        for (int i = 0; i < getProjectiles().size(); ++i) {     //掃過投射物array
            Projectile temp = getProjectiles().get(i);
            temp.update();     //移動功能在投射物的update
            if (temp.getArriveTimeCounter().count()) {       //投射物飛行時間到(也就是飛到目標身上了，這樣寫就必中，不用碰撞判定)
                getProjectiles().remove(temp);     //投射物消失
                temp.getTarget().attackByPhysical(getAttackPower());        //怪物扣血
            }
        }

        //各種防禦塔特別的update判定加在這邊
        if (crossbowSkillDuration != null && crossbowSkillDuration.count()) {        //crossbow的技能持續時間到
            setAttackSpeed(attackSpeedTempStorage);
            setAttackInterval(new Delay((int) ((1 / getAttackSpeed()) * Global.UPDATE_TIMES_PER_SEC)));        //重設攻擊間格計時器
            getAttackInterval().play();
            crossbowSkillDuration = null;
            if (Global.IS_DEBUG) {
                System.out.println("stopCrossbowSkill");
            }
        }

        if (spearTowerSkill != null) {      //spear技能存在場上才做判斷
            spearTowerSkill.update();       //這邊只有移動位置; 由於會穿透所有怪物並造成傷害，要在AreaEffect判定和monsterArr的碰撞與扣血
            if (spearTowerSkill.isOverScreen()) {       //超出螢幕移除
                spearTowerSkill = null;
            }
        }
    }
}
