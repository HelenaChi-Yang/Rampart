package gameObject;

import java.awt.Color;

import java.awt.Graphics;

import java.awt.Graphics2D;

import java.awt.Rectangle;

import java.awt.geom.Rectangle2D;

public class Life {

    private int x;

    private int y;

    private int width = 32;

    private int height = 5;

    protected double life;

    private double lifeMax;

    public Life() {

        super();

// TODO Auto-generated constructor stub

    }

    public Life(int x, int y, double lifeMax) {

        super();

        this.x = x;

        this.y = y;

        this.life = this.lifeMax = lifeMax;

    }

    public int getX() {

        return x;

    }

    public void setX(int x) {

        this.x = x;

    }

    public int getY() {

        return y;

    }

    public void setY(int y) {

        this.y = y;

    }

    public double getLifeMax() {
        return lifeMax;
    }

    public void draw(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;

//画外圈

        Rectangle2D r2 = new Rectangle2D.Double(x, y - height, width, height);

        g2.setColor(Color.YELLOW);

        g2.draw(r2);

//根据血量比例画血条

        Rectangle2D r = new Rectangle2D.Double(x + 1, y - height + 1, width * ((double) life / lifeMax) - 1, height - 1);

        g2.setColor(Color.GREEN);

        g2.fill(r);

    }
}




//private void drawEnemyTanks(Graphics g) {
//
//// TODO Auto-generated method stub
//
//// 画敌方坦克
//
//        for (Tank t : enemyTanks) {
//
//        if (t.isbLive()) {
//
////画坦克
//
//        t.draw(g);
//
////画血条
//
//        Life life=new Life(t.getX(), t.getY(), t.getLive());
//
//        life.draw(g);
//
//        } else {
//
//// 坦克死亡 画爆炸
//
//        Bomb b = new Bomb(t.getX() - 50, t.getY() - 30);
//
//        b.draw(g);
//
//        enemyTanks.remove(t);
//
//        }
//
//        }
//
//        }因为我的坦克不在集合里面，自己写
//
//// 画我的坦克
//
//        if (myTank.isbLive()) {
//
//        myTank.draw(g);
//
//        Life life=new Life(myTank.getX(), myTank.getY(), myTank.getLive());
//
//        life.draw(g);
//
//        } else {
//
//// 游戏结束
//
//        isGameOn = false;
//
//        }

