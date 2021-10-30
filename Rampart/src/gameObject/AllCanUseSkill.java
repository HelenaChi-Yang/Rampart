package gameObject;

import gameObject.DefenseTower.DefenseTower;

import java.util.ArrayList;

public class AllCanUseSkill {

    public static void StunAllMonster(ArrayList<Actor> actors, int duration) {       //暈眩全部怪物  //block
        for (Actor a : actors) {
            a.addAbnormalState(duration, GameObject.AbnormalState.Stun);
        }
    }

    public static void SlowAllMonster(ArrayList<Actor> actors, int duration) {       //緩速全部怪物50%  //poison
        for (Actor a : actors) {
            a.addAbnormalState(duration, GameObject.AbnormalState.Slow);
        }
    }

    public static void electricShockAllMonster(ArrayList<Actor> actors, int duration) {      //所有怪物減少一半當前血量 // lightr
        for (Actor a : actors) {
            a.setHp(a.getHp() * 0.5);
        }
    }

    public static void breakAllMonsterDefense(ArrayList<Actor> actors, int duration) {       //怪物破防(可以對boss作用)  //block
        for (Actor a : actors) {
            a.addAbnormalState(duration, GameObject.AbnormalState.BreakDefense);
        }
    }

    //以下為雙人模式技能
    public static void accelerateAllMonster(ArrayList<Actor> actors, int duration) {       //加速全部怪物100%  //poison
        for (Actor a : actors) {
            a.addAbnormalState(duration, GameObject.AbnormalState.Accelerate);
        }
    }

    public static void doubleAllMonsterDefense(ArrayList<Actor> actors, int duration) {       //怪物雙倍防(可以對boss作用)  //block
        for (Actor a : actors) {
            a.addAbnormalState(duration, GameObject.AbnormalState.DoubleDefense);
        }
    }

    public static void decreaseDefenseTowerAttackSpeed(ArrayList<DefenseTower> defenseTowers, int duration){        //防禦塔減少三分之一攻速
        for (DefenseTower tower : defenseTowers){
            tower.addAbnormalState(duration, GameObject.AbnormalState.Slow);
        }
    }

    public static void neutralizeDefenseTower(ArrayList<DefenseTower> defenseTowers, int duration){        //防禦塔無效
        for (DefenseTower tower : defenseTowers){
            tower.addAbnormalState(duration, GameObject.AbnormalState.Stun);
        }
    }
}
