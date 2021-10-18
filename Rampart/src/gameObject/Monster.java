package gameObject;


import com.company.ActorAnimator;
import com.company.Global;
import gameObject.Projectile.Projectile;
import gameObject.Projectile.StraightFlight;
import gameObject.*;

import java.awt.*;

public class Monster extends Actor {

    private MonsterType monsterType;

    private enum MonsterType {
        A,
    }


    private ActorAnimator actorAnimator;
    private ActorAnimator.State state;
    private Global.Direction dir;


    public Monster(int x, int y, int type, int posX, int posY){
        super(x, y, 32,32);
        state = ActorAnimator.State.WALK;   //一開始都是走路 ， Animator.State.WALK  is  one of the object of State.
        dir = Global.Direction.DOWN; //一開始都是向下
        actorAnimator = new ActorAnimator(type, state, dir, posX, posY);  //把要畫哪個角色和 run or walk帶入animator
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
        actorAnimator.update();
        if (isLongRangeAttack()) {      //遠程才掃
            for (Projectile p : getProjectiles()) {     //掃過投射物陣列
                if (p.getArriveTimeCounter().count()) {      //飛行時間到
                    getProjectiles().remove(p);     //移除投射物
                    getTarget().minusHp(getAttackPower());      //目標扣血
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
//        super.paintComponent(g);
        actorAnimator.paint(painter().left(), painter().top(), painter().right(), painter().bottom(), g);
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

    public void setAllAbility(int chooseMonster) {
        double hp = 0, attPower = 0, attRange = 0, attSpeed = 0, detRange = 0, magiDefense = 0, phyDefense = 0, skillCoolDown = 0, moveSpeed = 0;
        boolean isLongRangeAtt = false;
        ActorAnimator actorAnimator = null;
        switch (chooseMonster) {
            case 1:
                monsterType = MonsterType.A;
                hp = 100;
                attPower = 10;
                attRange = collider().left();
                attSpeed = 1;
                detRange = 150;
                phyDefense = 30;
                magiDefense = 30;
                moveSpeed = 40;
                isLongRangeAtt = false;
//                animator =
                break;
        }
        setHp(hp);
        setAttackPower(attPower);
        setAttackRange(attRange);
        setAttackSpeed(attSpeed);
        setDetectRange(detRange);
        setMagicalDefense(magiDefense);
        setPhysicalDefense(phyDefense);
        setSkillCoolDown(skillCoolDown);
        setMoveSpeed(moveSpeed);
        setLongRangeAttack(isLongRangeAtt);
        if (isLongRangeAtt) {
//            setProjectiles(new );     //設定投射物種類
        }
//        setAnimator(animator);
    }

    @Override
    public ActorAnimator getActorAnimator() {
        return actorAnimator;
    }

    public void setActorAnimator(ActorAnimator actorAnimator) {
        this.actorAnimator = actorAnimator;
    }
}