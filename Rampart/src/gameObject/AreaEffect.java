package gameObject;

import com.company.Global;
import com.company.WeaponAnimator;

import java.awt.*;
import java.util.ArrayList;

public class AreaEffect extends GameObject {

    private WeaponAnimator animator;
    private int x, y, width, high;
    public ArrayList<Actor> haveBeenHitTargets = new ArrayList<Actor>(1);

    public AreaEffect(int x, int y, int width, int high, WeaponAnimator.WeaponAnimatorType weaponAnimatorType) {
        super(x, y, width, high);
        this.x = x;
        this.y = y;
        this.width = width;
        this.high = high;
        animator = new WeaponAnimator(weaponAnimatorType);
    }

    public void paintComponent(Graphics g) {
        if (Global.IS_DEBUG) {
            Color transparentMagenta = new Color(238, 0, 255, 124);        //自訂半透明顏色，左邊可選
            g.setColor(transparentMagenta);     //載入顏色
            g.fillOval(x, y, width, high);
        }
        //這邊要畫效果圖
        animator.paintAnime(x, y, width, high, g);
    }

    public boolean isIntoEffectArea(Actor actor) {
        Rect rect = actor.collider();
        return width/2 > Math.pow(Math.pow(rect.centerX() - painter().centerX(), 2) + Math.pow(rect.centerY() - painter().centerY(), 2), 0.5);
    }

    public boolean hasBeenHitByThis(Actor target) {
        for (Actor a : haveBeenHitTargets) {
            if (a == target)
                return true;
        }
        return false;
    }

    @Override
    public void update() {
        animator.update();
    }
}
