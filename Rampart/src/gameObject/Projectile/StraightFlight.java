package gameObject.Projectile;




import  com.company.Global;
import gameObject.Actor;

import java.awt.*;
//直線飛行
public class StraightFlight extends Projectile{

    public ProjectileType projectileType;

    public enum ProjectileType {
        Arrow,MonsterProjectile,
    }

    public StraightFlight(int x, int y, int width, int high) {
        super(x, y, width, high);
    }

    public StraightFlight(int cX, int cY, int cWidth, int cHigh, int pX, int pY, int pWidth, int pHigh) {
        super(cX, cY, cWidth, cHigh, pX, pY, pWidth, pHigh);
    }


    @Override   //呼叫這個方法，投射物會自動射向monster，需在Scene做碰撞判定
    public void towardTarget(Actor monster) {
        super.towardTarget(monster);
    }

    @Override
    public void update() {
        translate((int)(getDistanceX() / getArriveTime() / Global.UPDATE_TIMES_PER_SEC), (int)(getDistanceX() / getArriveTime()/Global.UPDATE_TIMES_PER_SEC));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
//        g.drawImage(getImg(), getCollider().getX(), getCollider().getY(), getCollider().getWidth(),getCollider().getHigh(),null);
//        getAnimator();
    }

    public void setProjectileType(ProjectileType input){
        projectileType = input;
        switch (projectileType){
            case Arrow:
                setVelocity(300);
                break;

            case MonsterProjectile:
                setVelocity(60);
        }
    }
}

