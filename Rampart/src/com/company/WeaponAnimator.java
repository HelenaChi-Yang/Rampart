package com.company;

import controllers.SceneController;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import static com.company.Global.*;


public class WeaponAnimator {

    private BufferedImage img;
    private Delay printSubMirrorDelay;
    private int currentSubMirror = 0, subMirrorNumberTotal, pixelX = WEAPON_X, pixelY = WEAPON_Y, subMirrorNumberX, subMirrorNumberY, repeatTimesLimit, repeatTimes = 0;     //當前顯示分鏡、分鏡總數、X軸分鏡數、Y軸分鏡數、重複播放次數
    private double duration;        //動畫播放時間長度
    private boolean isAnime;
    private WeaponAnimatorType weaponAnimatorType;

    public enum WeaponAnimatorType {
        Arrow, Spear, SpearSkill, MagicBall, Lighting, CannonBall, Rocket, CannonBallExplosion, RocketExplosion, AnimalMagicCircle, BigStone, MonsterProjectile,
    }

    public WeaponAnimator(WeaponAnimatorType chooseProjectileAnimator) {
        switch (chooseProjectileAnimator) {
            case Arrow:
                img = (BufferedImage) SceneController.getInstance().imageController().tryGetImage(new Path().img().weapon().Arrow());
                break;

            case SpearSkill:
                weaponAnimatorType = WeaponAnimatorType.SpearSkill;
            case Spear:
                img = (BufferedImage) SceneController.getInstance().imageController().tryGetImage(new Path().img().weapon().Spear());
                pixelX = 45;
                pixelY = 18;
                break;

            case MagicBall:
                img = (BufferedImage) SceneController.getInstance().imageController().tryGetImage(new Path().img().weapon().MagicBall());
                pixelX = pixelY = 120;
                break;
            case Lighting:
                img = (BufferedImage) SceneController.getInstance().imageController().tryGetImage(new Path().img().weapon().Lighting());
                repeatTimesLimit = 1;
                duration = 1;
                subMirrorNumberX = 4;
                subMirrorNumberY = 1;
                subMirrorNumberTotal = 4;
                pixelX = pixelY = 192;
                isAnime = true;
                break;
            case CannonBall:
                img = (BufferedImage) SceneController.getInstance().imageController().tryGetImage(new Path().img().weapon().CannonBall());
                break;
            case Rocket:
                img = (BufferedImage) SceneController.getInstance().imageController().tryGetImage(new Path().img().weapon().Rocket());
                pixelX = 83;
                pixelY = 32;
                break;
            case CannonBallExplosion:
                img = (BufferedImage) SceneController.getInstance().imageController().tryGetImage(new Path().img().weapon().CannonBallExplosion());
                repeatTimesLimit = 1;
                duration = 1;
                subMirrorNumberX = 5;
                subMirrorNumberY = 6;
                subMirrorNumberTotal = 30;
                pixelX = pixelY = 192;
                isAnime = true;
                break;
            case RocketExplosion:
                img = (BufferedImage) SceneController.getInstance().imageController().tryGetImage(new Path().img().weapon().RocketExplosion());
                repeatTimesLimit = 1;
                duration = 1;
                subMirrorNumberX = 5;
                subMirrorNumberY = 5;
                subMirrorNumberTotal = 25;
                pixelX = pixelY = 192;
                isAnime = true;
                break;
            case AnimalMagicCircle:
                img = (BufferedImage) SceneController.getInstance().imageController().tryGetImage(new Path().img().weapon().AnimalMagicCircle());
                repeatTimesLimit = 1;
                duration = 2;
                subMirrorNumberX = 5;
                subMirrorNumberY = 3;
                subMirrorNumberTotal = 14;
                pixelX = pixelY = 192;
                isAnime = true;
            case BigStone:
                break;
            case MonsterProjectile:
                break;
        }
        printSubMirrorDelay = new Delay((int) (duration * UPDATE_TIMES_PER_SEC / subMirrorNumberTotal) - 1);
        printSubMirrorDelay.loop();
    }

    public void paintAnime(int left, int top, int right, int bottom, Graphics g) {
        if (repeatTimes > repeatTimesLimit) {
            return;
        }
        g.drawImage(img,        //畫圖
                left,
                top,
                left+right,
                top+bottom,
                pixelX * (currentSubMirror % subMirrorNumberX),
                pixelY * (currentSubMirror / subMirrorNumberY),
                pixelX * (currentSubMirror % subMirrorNumberX) + pixelX,
                pixelY * (currentSubMirror / subMirrorNumberY) + pixelY, null);
    }

    public void paintRotate(int left, int top, int width, int high, double radian, Graphics g) {
        Graphics2D g2d = (Graphics2D) g;     //轉成g2d，才能用二維處理器畫
        double convertRadian = radian > 0 ? Math.toRadians(360 - Math.toDegrees(radian)) : -radian;       //角度計算為順時鐘轉角度
        AffineTransform affineTransform = AffineTransform.getTranslateInstance(left, top);
        if (weaponAnimatorType == WeaponAnimatorType.SpearSkill) {
            affineTransform.scale(2, 2);     //設定XY軸大小倍數
        }
        affineTransform.rotate(convertRadian, (img.getWidth() / 2), (img.getHeight() / 2));
        g2d.drawImage(img, affineTransform, null);        //畫圖、設定的處理器
    }

    public void update() {
        if (isAnime) {
            if (printSubMirrorDelay.count()) {
                currentSubMirror = currentSubMirror % subMirrorNumberTotal;
                currentSubMirror++;
//                if (currentSubMirror == 0) {
//                    repeatTimes++;
//                }
            }
        }
    }

}
