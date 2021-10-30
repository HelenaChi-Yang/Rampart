package gameObject.DefenseTower;

import com.company.*;
import controllers.AudioResourceController;
import gameObject.Actor;
import gameObject.AreaEffect;
import gameObject.AreaEffectJudge;
import gameObject.Projectile.Projectile;
import gameObject.Projectile.StraightFlight;

import java.awt.*;
import java.util.ArrayList;

public class CannonTower extends DefenseTower implements AreaEffectJudge {

    private CannonTowerType cannonTowerType;        //自用的，外面拿不到
    private int cannonTowerExplosionRange = 100;

    private ArrayList<AreaEffect> explosionArea = new ArrayList<>(0);
    private ArrayList<Delay> explosionAreaDelay = new ArrayList<>(0);

    private enum CannonTowerType {
        BASIC, ADVANCE, Rocket, ShockBullet,
    }

    public CannonTower(int x, int y, int width, int height, double towerLevel, TowerType towerType, TowerState towerState) {
        super(x, y, width, height, towerLevel, towerType, towerState);
        cannonTowerType = CannonTowerType.BASIC;
        setInitialTowerParameter();
        setProjectiles(new ArrayList<Projectile>(1));
        towerAnimator = new TowerAnimator();
        towerAnimator.setTowerAnimatorImage(towerType, towerState, this.towerLevel);
    }

    public CannonTower(int cX, int cY, int cWidth, int cHigh, int pX, int pY, int pWidth, int pHigh) {
        super(cX, cY, cWidth, cHigh, pX, pY, pWidth, pHigh);
    }

    @Override
    public void areaEffectJudge(ArrayList<Actor> actors) {
        for (AreaEffect area : explosionArea) {//爆炸範圍傷害判定
            for (Actor a : actors) {
                if (area.isIntoEffectArea(a)) {        //怪物在爆炸範圍內才檢查
                    if (!area.hasBeenHitByThis(a)) {       //是否被炸過
                        area.haveBeenHitTargets.add(a);        //紀下被炸過的怪(下次update檢查用)
                        a.attackByPhysical(getAttackPower());        //扣血
                        if (cannonTowerType == CannonTowerType.ShockBullet) {        //暈眩效果
                            a.addAbnormalState(1, AbnormalState.Stun);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void fireProjectile() {
        StraightFlight temp = new StraightFlight(collider().centerX(), collider().centerY(), 40, 40);
        switch (cannonTowerType) {
            case Rocket:
                temp.setProjectileType(StraightFlight.ProjectileType.Rocket);
                AudioResourceController.getInstance().shot(new Path().sound().gameObjectSound().fireRocket());
                break;
            default:
                temp.setProjectileType(StraightFlight.ProjectileType.CannonBall);        //設定直射投射物種類，自動載入數值
                AudioResourceController.getInstance().shot(new Path().sound().gameObjectSound().fireCannonBall());
        }
        temp.towardTarget(getTarget());
        getProjectiles().add(temp);
    }

    @Override
    public void useSkill(Actor target) {
    }

    @Override
    public void setInitialTowerParameter() {
        double attackPower = 0, attackSpeed = 0, attackRange = 0;
        switch (cannonTowerType) {
            case BASIC:
                attackPower = 20;
                attackSpeed = 0.5;
                attackRange = 100;
                break;
            case ADVANCE:
                attackPower = getAttackPower() * 1.4;
                attackSpeed = getAttackSpeed() * 1.2;
                attackRange = getAttackRange() * 1.4;
                break;
            case Rocket:
                attackPower = getAttackPower() * 1.4;
                attackSpeed = getAttackSpeed();
                attackRange = Global.WINDOW_WIDTH;
                break;
            case ShockBullet:
                attackPower = getAttackPower() * 1.4;
                attackSpeed = getAttackSpeed();
                attackRange = getAttackRange() * 1.2;
        }
        setAttackPower(attackPower);
        setAttackRange(attackRange);
        setAttackSpeed(attackSpeed);
        towerLevel = 1;
        setAttackInterval(new Delay((int) ((1 / getAttackSpeed()) * Global.UPDATE_TIMES_PER_SEC)));        //攻擊間格計時器
        getAttackInterval().play();   //攻擊間格器開始運作
    }

    @Override
    public void levelUp() {
        double increaseRatio = 1.2;
        if (cannonTowerType == CannonTowerType.ADVANCE) {
            increaseRatio = 1.4;
            setAttackSpeed(getAttackSpeed() * 1.2);
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
                cannonTowerType = CannonTowerType.ADVANCE;
                towerState = TowerState.Advance;
                break;
            case 2:
                cannonTowerType = CannonTowerType.Rocket;
                towerState = TowerState.Specialization1;
                break;
            case 3:
                cannonTowerType = CannonTowerType.ShockBullet;
                towerState = TowerState.Specialization2;
        }
        setInitialTowerParameter();
        popup();
        AudioResourceController.getInstance().shot(new Path().sound().gameObjectSound().buildTower());
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //爆炸效果
        for (AreaEffect area : explosionArea) {
            area.paintComponent(g);
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
            if (temp.getArriveTimeCounter().count()) {       //投射物飛行時間到
                WeaponAnimator.WeaponAnimatorType weaponAnimatorType;
                switch (cannonTowerType) {       //選擇爆炸效果
                    case Rocket:
                        weaponAnimatorType = WeaponAnimator.WeaponAnimatorType.RocketExplosion;
                        break;
                    default:
                        weaponAnimatorType = WeaponAnimator.WeaponAnimatorType.CannonBallExplosion;
                }
                AreaEffect tempAreaEffect = new AreaEffect(temp.collider().centerX() - cannonTowerExplosionRange, temp.collider().centerY() - cannonTowerExplosionRange,
                        cannonTowerExplosionRange * 2, cannonTowerExplosionRange * 2, weaponAnimatorType);       //新增爆炸區域
                AudioResourceController.getInstance().shot(new Path().sound().gameObjectSound().explosion());
                Delay tempDelay = new Delay((int) (Global.UPDATE_TIMES_PER_SEC * 1));
                tempDelay.play();       //記時器啟動

                explosionArea.add(tempAreaEffect);        //爆炸區和計時器加進array
                explosionAreaDelay.add(tempDelay);
                getProjectiles().remove(temp);      //移除投射物
            }
        }
        //爆炸效果Delay:
        for (int i = 0; i < explosionAreaDelay.size(); ++i) {
            Delay tempDelay = explosionAreaDelay.get(i);
            AreaEffect tempArea = explosionArea.get(i);
            tempArea.update();        //爆炸區更新(更新animator用)
            if (tempDelay.count()) {      //時間到移除
                explosionArea.remove(i);
                explosionAreaDelay.remove(i);
                i--;
            }
        }

    }
}
