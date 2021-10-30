package gameObject;


import com.company.ActorAnimator;
import com.company.BossAnimator;
import com.company.Global;
import gameObject.Projectile.Projectile;
import gameObject.Projectile.StraightFlight;

import java.awt.*;

public class Monster extends Actor {

    public MonsterType getMonsterType() {
        return monsterType;
    }

    private MonsterType monsterType;

    public enum MonsterType {
        Chicken, BOSS1, BOSS2, BOSS3, Bat, Slime, goblin, Minotaur, Demon, Skeleton, Ghost, DragonMan,
    }

    public Monster(int x, int y, int width, int high) {
        super(x, y, width, high);
    }

    public Monster(int cX, int cY, int cWidth, int cHigh, int pX, int pY, int pWidth, int pHigh) {
        super(cX, cY, cWidth, cHigh, pX, pY, pWidth, pHigh);
    }


    @Override
    public void update() {
        super.update();
        if (!isStun) {
            getActorAnimator().update();
        }
        if (isLongRangeAttack()) {      //遠程才掃
            for (Projectile p : getProjectiles()) {     //掃過投射物陣列
                if (p.getArriveTimeCounter().count()) {      //飛行時間到
                    getProjectiles().remove(p);     //移除投射物
                    getTarget().attackByPhysical(getAttackPower());      //目標扣血
                }
            }
        }
    }

    @Override
    public void useSkill(Actor target) {        //有技能才寫
        switch (monsterType) {

        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        getActorAnimator().paint(painter().left(), painter().top(), painter().right(), painter().bottom(), g);
        if (isCombating()) {
//            getAnimator()     //播放戰鬥動畫
        } else {
//        getAnimator()     //播放移動動畫
        }
    }

    @Override
    public void fireProjectile() {
        StraightFlight temp = new StraightFlight(collider().left(), collider().top(), 10, 10);
        temp.setProjectileType(StraightFlight.ProjectileType.MonsterProjectile);        //設定投射物種類，自動載入數值
        temp.towardTarget(getTarget());
        getProjectiles().add(temp);
    }

    @Override
    public void setAllAbility(int chooseMonster, int level) {
        double hp = 0, attPower = 0, attRange = 0, attSpeed = 0, detRange = 0, magiDefense = 0, phyDefense = 0, skillCoolDown = 0, moveSpeed = 0;
        boolean isLongRangeAtt = false;
        double abilityRatio = Math.pow(1.2, level);
        switch (chooseMonster) {
            case 0:
                System.out.println("chicken");
                monsterType = MonsterType.Chicken;
                hp = 1;
                attPower = 10;
                attRange = collider().width();
                attSpeed = 1;
                detRange = 10;
                phyDefense = 10;
                magiDefense = 10;
                moveSpeed = 10;
                isLongRangeAtt = false;
                setState(ActorAnimator.State.WALK);   //一開始都是走路 ， Animator.State.WALK  is  one of the object of State.
                setDir(Global.Direction.DOWN); //一開始都是向下
                actorAnimator = (new ActorAnimator(1, getState(), getDir(), ActorAnimator.ActorImage.chicken));  //把要畫哪個角色和 run or walk帶入animator
                break;

            case 1:
                monsterType = MonsterType.BOSS1;
                isBoss = true;
                hp = 300;
                attPower = 10;
                attRange = collider().width();
                attSpeed = 1;
                detRange = 10;
                phyDefense = 40;
                magiDefense = 40;
                moveSpeed = 5;
                isLongRangeAtt = false;
                setState(ActorAnimator.State.WALK);   //一開始都是走路 ， Animator.State.WALK  is  one of the object of State.
                setDir(Global.Direction.DOWN); //一開始都是向下
                actorAnimator = new BossAnimator(BossAnimator.BossAnimatorType.BOSS1);  //把要畫哪個角色和 run or walk帶入animator
                break;

            case 2:
                monsterType = MonsterType.BOSS2;
                isBoss = true;
                hp = 350;
                attPower = 10;
                attRange = collider().width();
                attSpeed = 1;
                detRange = 10;
                phyDefense = 40;
                magiDefense = 40;
                moveSpeed = 5;
                isLongRangeAtt = false;
                setState(ActorAnimator.State.WALK);   //一開始都是走路 ， Animator.State.WALK  is  one of the object of State.
                setDir(Global.Direction.DOWN); //一開始都是向下
                actorAnimator = new BossAnimator(BossAnimator.BossAnimatorType.BOSS2);  //把要畫哪個角色和 run or walk帶入animator
                break;

            case 3:
                monsterType = MonsterType.BOSS3;
                isBoss = true;
                hp = 450;
                attPower = 10;
                attRange = collider().width();
                attSpeed = 1;
                detRange = 10;
                phyDefense = 40;
                magiDefense = 40;
                moveSpeed = 5;
                isLongRangeAtt = false;
                setState(ActorAnimator.State.WALK);   //一開始都是走路 ， Animator.State.WALK  is  one of the object of State.
                setDir(Global.Direction.DOWN); //一開始都是向下
                actorAnimator = new BossAnimator(BossAnimator.BossAnimatorType.BOSS3);  //把要畫哪個角色和 run or walk帶入animator
                break;

            case 4:
                monsterType = MonsterType.Bat;
                hp = 50;
                phyDefense = 10;
                magiDefense = 10;
                moveSpeed = 10 + (int) (Math.random() * 40);
                if (moveSpeed <= 35) {
                    setState(ActorAnimator.State.WALK);   //一開始都是走路 ， Animator.State.WALK  is  one of the object of State.
                } else {
                    setState(ActorAnimator.State.RUN);
                    phyDefense *= 2;
                    magiDefense *= 2;
                }
                setDir(Global.Direction.DOWN); //一開始都是向下
                actorAnimator = (new ActorAnimator((int) (Math.random() * 8), getState(), getDir(), ActorAnimator.ActorImage.bat));  //把要畫哪個角色和 run or walk帶入animator
                attPower = 10;
                attRange = collider().width();
                attSpeed = 1;
                detRange = 150;
                isLongRangeAtt = false;
                break;
            case 5:
                monsterType = MonsterType.Slime;
                hp = 50;
                phyDefense = Math.random() * 20;
                magiDefense = Math.random() * 20;
                moveSpeed = 10 + (int) (Math.random() * 20);
                if (moveSpeed <= 15) {
                    setState(ActorAnimator.State.WALK);   //一開始都是走路 ， Animator.State.WALK  is  one of the object of State.
                } else {
                    setState(ActorAnimator.State.RUN);
                    phyDefense *= 2;
                    magiDefense *= 2;
                }
                setState(ActorAnimator.State.WALK);   //一開始都是走路 ， Animator.State.WALK  is  one of the object of State.
                setDir(Global.Direction.DOWN); //一開始都是向下
                actorAnimator = (new ActorAnimator((int) (Math.random() * 8), getState(), getDir(), ActorAnimator.ActorImage.monster));  //把要畫哪個角色和 run or walk帶入animator
        }
        double temp = hp * abilityRatio;
        if (chooseMonster == 0){
            temp = 1;
        }
        setHp(temp);
        life = new Life(painter().left(), painter().top() - 5, getHp());
        setAttackPower(attPower);
        setAttackRange(attRange);
        setAttackSpeed(attSpeed);
        setDetectRange(detRange);
        setMagicalDefense(magiDefense + level * 2);
        setPhysicalDefense(phyDefense + level * 2);
        setSkillCoolDown(skillCoolDown);
        setMoveSpeed(moveSpeed * Math.pow(abilityRatio,0.5));
        setLongRangeAttack(isLongRangeAtt);
        if (isLongRangeAtt) {
//            setProjectiles(new );     //設定投射物種類
        }
//        setAnimator(animator);
    }
}
