package gameObject;


import com.company.Global;

public class Player {
    public void setName(String name) {
        this.name = name;
    }

    private String name;
    private int money;
    private int playerHp;

    public void setLevel(int level) {
        this.level = level;
    }

    private int level;
    private int monsterHitCount;

    public int getMonsterHitCount() {
        return monsterHitCount;
    }


    public Global.gameRole getHero() {
        return hero;
    }

    private Global.gameRole hero;

    public void setHero(Global.gameRole hero) {
        this.hero = hero;
    }


    public Player() {
        this.level = 1;
        this.money = 500;
        this.playerHp = 10;
        this.monsterHitCount = 0;
    }

    // money
    // 角色技能
    //頭像？

    public void reset(){
        this.money = 500;
        this.playerHp = 10;
    }

    public int getPlayerHp() {
        return playerHp;
    }

    public void setPlayerHp(int setHp) {
        this.playerHp = setHp;
    }


    public int getMoney() {
        return money;
    }

    public int getLevel() {
        return level;
    }

    public void gainMoneyByTime() {
        money += 10;
    }

    public void gainMoneyByMonsterHit() {
        money += 50;
    }

    public void monsterHit() {
        monsterHitCount++;
    }

    public String getName() {
        return name;
    }

    public void addMoney(int addMoney) {
        this.money += addMoney;
    }


}
