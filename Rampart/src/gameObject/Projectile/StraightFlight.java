package gameObject.Projectile;


import com.company.WeaponAnimator;
import gameObject.Actor;

import java.awt.*;

//直線飛行
public class StraightFlight extends Projectile {

    public enum ProjectileType {
        Arrow, Spear, SpearSkill, MagicBall, Lighting, CannonBall, Rocket, MonsterProjectile,
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
        if (updateCountX % velocityUpdateCountX == 0) {      //X座標移動更新多次執行一次
            translateX((int) getVelocityX());
            updateCountX = 0;
        }
        updateCountX++;

        if (updateCountY % velocityUpdateCountY == 0) {      //Y座標移動更新多次執行一次
            translateY((int) getVelocityY());
            updateCountY = 0;
        }
        updateCountY++;

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        switch (projectileType) {
            case Lighting:      //閃電球顯示動畫
                animator.paintAnime(collider().left(),collider().top(),collider().width(),collider().height(),g);
                break;
            default:
                animator.paintRotate(collider().left(),collider().top(),collider().width(),collider().height(),fireAngle,g);
        }
        //顯示時呼叫getProjectileAngle得到發射弧度(要換成角度要toDegrees)，配合圖片旋轉角度
//        g.drawImage(getImg(), getCollider().getX(), getCollider().getY(), getCollider().getWidth(),getCollider().getHigh(),null);
//        getAnimator();
    }

    public void setProjectileType(ProjectileType input) {
        projectileType = input;
        switch (projectileType) {       //設定速度和圖片
            case Arrow:
                setVelocity(300);
                animator = new WeaponAnimator(WeaponAnimator.WeaponAnimatorType.Arrow);
                break;

            case Spear:
                setVelocity(250);
                animator = new WeaponAnimator(WeaponAnimator.WeaponAnimatorType.Spear);
                break;

            case SpearSkill:
                setVelocity(400);
                animator = new WeaponAnimator(WeaponAnimator.WeaponAnimatorType.SpearSkill);
                break;

            case MagicBall:
                setVelocity(200);
                animator = new WeaponAnimator(WeaponAnimator.WeaponAnimatorType.MagicBall);
                break;

            case CannonBall:
                animator = new WeaponAnimator(WeaponAnimator.WeaponAnimatorType.CannonBall);
                setVelocity(200);
                break;

            case Lighting:
                setVelocity(400);
                animator = new WeaponAnimator(WeaponAnimator.WeaponAnimatorType.Lighting);
                break;

            case Rocket:
                setVelocity(600);
                animator = new WeaponAnimator(WeaponAnimator.WeaponAnimatorType.Rocket);
                break;

            case MonsterProjectile:
                animator = new WeaponAnimator(WeaponAnimator.WeaponAnimatorType.MonsterProjectile);
                setVelocity(60);
        }
    }

}


