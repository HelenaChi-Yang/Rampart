package gameObject;



import com.company.Global;

import java.awt.*;

public class Player implements bigShot{
    private String name;
    private int money;
    private int level;
    private int monsterHitCount;
    private Image avator;
    private Global.gameRole hero;

    public Player(String name, Global.gameRole hero , int level) {
        this.name = name;
        money = 0;
        this.level = level;
        this.hero = hero;
        switch (hero) {
            case ROLE1:
                break;
            case ROLE2:
                break;
            case ROLE3:
                break;
            case ROLE4:
                break;
        }
    }

    // money
    // 角色技能
    //頭像？

    public int getMoney() {
        return money;
    }

    public int getLevel() {
        return level;
    }

    public void gainMoneyByTime(){
        money += 10;
    }

    public void gainMoneyByMonsterHit(){
        money += 50;
    }

    public void monsterHit(){
        monsterHitCount++;
    }

    public String getName() {
        return name;
    }


    @Override
    public void bigshot() {

    }
}
