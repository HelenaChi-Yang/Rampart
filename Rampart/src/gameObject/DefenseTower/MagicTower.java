package gameObject.DefenseTower;

import com.company.*;
import controllers.AudioResourceController;
import gameObject.Actor;


import gameObject.AreaEffect;
import gameObject.Projectile.Projectile;
import gameObject.Projectile.StraightFlight;
import gameObject.Rect;

import java.awt.*;
import java.util.ArrayList;

public class MagicTower extends DefenseTower {

    private MagicTowerType magicTowerType;      //自用的，外面拿不到
    private AreaEffect animalTowerSkillArea;
    private int animalTowerSkillRange;
    private Delay animalTowerDuration;

    private enum MagicTowerType {
        BASIC, ADVANCE, Animal, Lighting,
    }

    public MagicTower(int x, int y, int width, int height, double towerLevel, TowerType towerType, TowerState towerState) {
        super(x, y, width, height, towerLevel, towerType, towerState);
        magicTowerType = MagicTowerType.BASIC;
        setInitialTowerParameter();
        setProjectiles(new ArrayList<Projectile>(1));
        towerAnimator = new TowerAnimator();
        towerAnimator.setTowerAnimatorImage(towerType, towerState, this.towerLevel);
    }

    public MagicTower(int cX, int cY, int cWidth, int cHigh, int pX, int pY, int pWidth, int pHigh) {
        super(cX, cY, cWidth, cHigh, pX, pY, pWidth, pHigh);
    }

    @Override
    public void areaEffectJudge(ArrayList<Actor> actors) {
        if (actors.size() == 0) {
            return;
        }
        switch (magicTowerType) {
            case Animal:
                if (animalTowerSkillArea != null) {     //場上有技能才進判斷
                    for (Actor a : actors) {
                        if (animalTowerSkillArea.isIntoEffectArea(a) && a.getHp() != 1 && !a.isBoss) {        //進範圍的變雞，變過不再變，羊只有1滴血判斷、不能變boss
                            a.setAllAbility(0, 1);
                            AudioResourceController.getInstance().shot(new Path().sound().gameObjectSound().duck());
                        }
                    }
                }
                break;
            case Lighting:
                for (Projectile projectile : getProjectiles()) {        //投射物陣列
                    int projectileX = projectile.collider().centerX(), projectileY = projectile.collider().centerY();
                    boolean isBreak = false;
                    projectile.haveEnemyInDetectRange = false;      //預設為範圍內沒目標，有掃到才轉為true

                    for (Actor a : actors) {        //怪物陣列
                        if (a == getTarget()) {        //當前目標跳過不掃
                            continue;
                        }

                        int monsterX = a.collider().centerX(), monsterY = a.collider().centerY();
                        if (projectile.getDetectRange() >= Math.pow(Math.pow(monsterX - projectileX, 2) + Math.pow(monsterY - projectileY, 2), 0.5)
                                && !projectile.hasBeenHitByThis(a)) {      //投射物偵測怪物是否在範圍內且還沒被打過
                            projectile.haveEnemyInDetectRange = true;       //紀錄範圍內還有怪物可彈射，在DefenseTower更新時該投射物就不會被移除
                            if (projectile.isHitTarget) {      //投射物打到之前的目標了
                                projectile.towardTarget(a);     //閃電攻擊彈射其他敵人
                                projectile.isHitTarget = false;     //重設打到敵人判定
                                isBreak = true;     //剩下的怪不用掃
                            }
                        }
                        if (isBreak) {
                            break;
                        }
                    }
                }
        }
        //碰撞判定，閃電塔不用
        if (magicTowerType != MagicTowerType.Lighting) {
            for (int i = 0; i < getProjectiles().size(); ++i) {     //掃過投射物array
                Projectile temp = getProjectiles().get(i);
                for (Actor a : actors) {
                    if (temp.isCollision(a)) {       //投射物飛行時間到(也就是飛到目標身上了，這樣寫就必中，不用碰撞判定)
                        getProjectiles().remove(temp);     //投射物消失
                        a.attackByPhysical(getAttackPower());        //怪物扣血
                    }
                }
            }
        }
    }

    @Override
    public void fireProjectile() {
        StraightFlight temp = new StraightFlight(collider().centerX(), collider().centerY(), 40, 40);
        switch (magicTowerType) {
            case Lighting:
                temp.setProjectileType(StraightFlight.ProjectileType.Lighting);
                temp.setDetectRange((int) (getAttackRange() / 2));
                AudioResourceController.getInstance().shot(new Path().sound().gameObjectSound().lighting());
                break;
            default:
                temp.setProjectileType(StraightFlight.ProjectileType.MagicBall);        //設定直射投射物種類，自動載入數值
                AudioResourceController.getInstance().shot(new Path().sound().gameObjectSound().magicBall());
        }
        temp.towardTarget(getTarget());
        getProjectiles().add(temp);
    }

    @Override
    public void useSkill(Actor target) {
        switch (magicTowerType) {
            case Animal:
                Rect rect = getTarget().collider();
                animalTowerSkillArea = new AreaEffect(rect.centerX() - animalTowerSkillRange,
                        rect.centerY() - animalTowerSkillRange, animalTowerSkillRange * 2,
                        animalTowerSkillRange * 2, WeaponAnimator.WeaponAnimatorType.AnimalMagicCircle);
                AudioResourceController.getInstance().shot(new Path().sound().gameObjectSound().animalSpells());
                animalTowerDuration = new Delay(Global.UPDATE_TIMES_PER_SEC * 1);
                animalTowerDuration.play();
                break;
            default:
        }
    }

    @Override
    public void setInitialTowerParameter() {
        double attackPower = 0, attackSpeed = 0, attackRange = 0;
        switch (magicTowerType) {
            case BASIC:
                attackPower = 25;
                attackSpeed = 0.7;
                attackRange = 100;
                break;
            case ADVANCE:
                attackPower = getAttackPower() * 1.4 * 2;
                attackSpeed = getAttackSpeed() * 1.4;
                attackRange = getAttackRange() * 1.4;
                break;
            case Animal:
                attackPower = 60;
                attackSpeed = getAttackSpeed();
                attackRange = 140;
                setSkillCoolDown(15);       //技能CD25秒
                animalTowerSkillRange = 100;      //技能範圍半徑
                break;
            case Lighting:
                attackPower = 60;
                attackSpeed = getAttackSpeed();
                attackRange = 140;
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
        if (magicTowerType == MagicTowerType.ADVANCE) {
            increaseRatio = 1.4;
            setAttackSpeed(getAttackSpeed() * increaseRatio);
        }
        if(magicTowerType == MagicTowerType.Animal){        //升等減少CD
            setSkillCoolDown(15 - (getTowerLevel() * 5));
            setSkillCoolDownDelayCount(new Delay((int) (Global.UPDATE_TIMES_PER_SEC * getSkillCoolDown())));      //技能冷卻計時器
        }
        setAttackPower(getAttackPower() * increaseRatio);
        setAttackRange(getAttackRange() * increaseRatio);
        towerLevel += 1;
        popup();
        AudioResourceController.getInstance().shot(new Path().sound().gameObjectSound().buildTower());
        setAttackInterval(new Delay((int) ((1 / getAttackSpeed()) * Global.UPDATE_TIMES_PER_SEC)));        //攻擊間格計時器
        getAttackInterval().play();   //攻擊間格器開始運作
    }

    @Override
    public void convertToAdvanceTower(int choose) {
        switch (choose) {
            case 1:
                magicTowerType = MagicTowerType.ADVANCE;
                towerState = TowerState.Advance;
                break;
            case 2:
                magicTowerType = MagicTowerType.Animal;
                towerState = TowerState.Specialization1;
                break;
            case 3:
                magicTowerType = MagicTowerType.Lighting;
                towerState = TowerState.Specialization2;
        }
        setInitialTowerParameter();
        popup();
        AudioResourceController.getInstance().shot(new Path().sound().gameObjectSound().buildTower());
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (animalTowerSkillArea != null) {       //綿羊塔技能顯示
            if (Global.IS_DEBUG) {
                animalTowerSkillArea.paintComponent(g);
            }
            //綿羊塔技能動畫
            animalTowerSkillArea.paintComponent(g);
        }
        towerAnimator.setTowerAnimatorImage(towerType, towerState, towerLevel);
        towerAnimator.paint(painter().left(), painter().top(), painter().width(), painter().height(), g);
    }

    @Override
    public void update() {
        super.update();     //基本update

        //各種防禦塔特別的update判定加在這邊:
        for (int i = 0; i < getProjectiles().size(); ++i) {     //掃過投射物array
            Projectile temp = getProjectiles().get(i);
            temp.update();     //移動功能在投射物的update
            if (temp.getArriveTimeCounter().count()) {       //投射物飛行時間到(也就是飛到目標身上了，這樣寫就必中，不用碰撞判定)
                temp.isHitTarget = true;        //紀錄投射物狀態，已打到怪物
                temp.getTarget().attackByMagical(getAttackPower());        //怪物扣血
                if (!temp.hasBeenHitByThis(temp.getTarget())) {     //紀錄已被該投射物打過的怪，記下來過的不再記(閃電塔用)，投射物的hasBeenHitByThis會用到
                    temp.haveBeenHitTargets.add(temp.getTarget());
                }
                if (temp.projectileType != StraightFlight.ProjectileType.Lighting) {       //非閃電塔移除投射物
                    getProjectiles().remove(temp);
                } else if (!temp.haveEnemyInDetectRange) {     //閃電塔範圍內無目標投射物消失
                    getProjectiles().remove(temp);
                } else {
                    temp.setArriveTimeCounter(new Delay(2));        //因為彈射後的投射物會錯過count的移除，所以要給他新的count，才能在沒目標後順利移除
                    temp.getArriveTimeCounter().play();
                }
            }
        }

        //綿羊塔
        if (animalTowerSkillArea != null) {
            animalTowerSkillArea.update();
        }

        if (animalTowerDuration != null && animalTowerDuration.count()) {      //變羊技能範圍持續2秒
            animalTowerSkillArea = null;
        }

        //閃電塔

    }
}

