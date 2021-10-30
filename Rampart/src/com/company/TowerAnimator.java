package com.company;

import controllers.SceneController;
import gameObject.DefenseTower.DefenseTower;

import java.awt.*;
import java.awt.image.BufferedImage;

import static com.company.Global.*;

public class TowerAnimator {

    private Image image;

    //    private DefenseTower.TowerType towerType;
//    private DefenseTower.TowerState towerState;
    private String path;

    public TowerAnimator() {
    }

    public void setTowerAnimatorImage(DefenseTower.TowerType towerType, DefenseTower.TowerState towerState, int level) {
        switch (towerType) {
            case ArcherTower:
                switch (towerState) {
                    case Basic:
                        switch (level) {
                            case 1:
                                image = SceneController.getInstance().imageController().tryGetImage(new Path().img().tower().ArcherTowerBasic1());
                                break;
                            case 2:
                                image = SceneController.getInstance().imageController().tryGetImage(new Path().img().tower().ArcherTowerBasic2());
                                break;
                            case 3:
                                image = SceneController.getInstance().imageController().tryGetImage(new Path().img().tower().ArcherTowerBasic3());
                        }
                        break;
                    case Advance:
                        switch (level) {
                            case 1:
                                image = SceneController.getInstance().imageController().tryGetImage(new Path().img().tower().ArcherTowerAdvance1());
                                break;
                            case 2:
                                image = SceneController.getInstance().imageController().tryGetImage(new Path().img().tower().ArcherTowerAdvance2());
                                break;
                            case 3:
                                image = SceneController.getInstance().imageController().tryGetImage(new Path().img().tower().ArcherTowerAdvance3());
                        }
                        break;
                    case Specialization1:
                        switch (level) {
                            case 1:
                                image = SceneController.getInstance().imageController().tryGetImage(new Path().img().tower().CrossBow1());
                                break;
                            case 2:
                                image = SceneController.getInstance().imageController().tryGetImage(new Path().img().tower().CrossBow2());
                                break;
                            case 3:
                                image = SceneController.getInstance().imageController().tryGetImage(new Path().img().tower().CrossBow3());
                        }
                        break;
                    case Specialization2:
                        switch (level) {
                            case 1:
                                image = SceneController.getInstance().imageController().tryGetImage(new Path().img().tower().Spear1());
                                break;
                            case 2:
                                image = SceneController.getInstance().imageController().tryGetImage(new Path().img().tower().Spear2());
                                break;
                            case 3:
                                image = SceneController.getInstance().imageController().tryGetImage(new Path().img().tower().Spear3());
                        }
                }
                break;
            case MagicTower:
                switch (towerState) {
                    case Basic:
                        switch (level) {
                            case 1:
                                image = SceneController.getInstance().imageController().tryGetImage(new Path().img().tower().MagicTowerBasic1());
                                break;
                            case 2:
                                image = SceneController.getInstance().imageController().tryGetImage(new Path().img().tower().MagicTowerBasic2());
                                break;
                            case 3:
                                image = SceneController.getInstance().imageController().tryGetImage(new Path().img().tower().MagicTowerBasic3());
                        }
                        break;
                    case Advance:
                        switch (level) {
                            case 1:
                                image = SceneController.getInstance().imageController().tryGetImage(new Path().img().tower().MagicTowerAdvance1());
                                break;
                            case 2:
                                image = SceneController.getInstance().imageController().tryGetImage(new Path().img().tower().MagicTowerAdvance2());
                                break;
                            case 3:
                                image = SceneController.getInstance().imageController().tryGetImage(new Path().img().tower().MagicTowerAdvance3());
                        }
                        break;
                    case Specialization1:
                        switch (level) {
                            case 1:
                                image = SceneController.getInstance().imageController().tryGetImage(new Path().img().tower().Animal1());
                                break;
                            case 2:
                                image = SceneController.getInstance().imageController().tryGetImage(new Path().img().tower().Animal2());
                                break;
                            case 3:
                                image = SceneController.getInstance().imageController().tryGetImage(new Path().img().tower().Animal3());
                        }
                        break;
                    case Specialization2:
                        switch (level) {
                            case 1:
                                image = SceneController.getInstance().imageController().tryGetImage(new Path().img().tower().Lighting1());
                                break;
                            case 2:
                                image = SceneController.getInstance().imageController().tryGetImage(new Path().img().tower().Lighting2());
                                break;
                            case 3:
                                image = SceneController.getInstance().imageController().tryGetImage(new Path().img().tower().Lighting3());
                        }
                }
                break;
            case CannonTower:
                switch (towerState) {
                    case Basic:
                        switch (level) {
                            case 1:
                                image = SceneController.getInstance().imageController().tryGetImage(new Path().img().tower().CannonTowerBasic1());
                                break;
                            case 2:
                                image = SceneController.getInstance().imageController().tryGetImage(new Path().img().tower().CannonTowerBasic2());
                                break;
                            case 3:
                                image = SceneController.getInstance().imageController().tryGetImage(new Path().img().tower().CannonTowerBasic3());
                        }
                        break;
                    case Advance:
                        switch (level) {
                            case 1:
                                image = SceneController.getInstance().imageController().tryGetImage(new Path().img().tower().CannonTowerAdvance1());
                                break;
                            case 2:
                                image = SceneController.getInstance().imageController().tryGetImage(new Path().img().tower().CannonTowerAdvance2());
                                break;
                            case 3:
                                image = SceneController.getInstance().imageController().tryGetImage(new Path().img().tower().CannonTowerAdvance3());
                        }
                        break;
                    case Specialization1:
                        switch (level) {
                            case 1:
                                image = SceneController.getInstance().imageController().tryGetImage(new Path().img().tower().Rocket1());
                                break;
                            case 2:
                                image = SceneController.getInstance().imageController().tryGetImage(new Path().img().tower().Rocket2());
                                break;
                            case 3:
                                image = SceneController.getInstance().imageController().tryGetImage(new Path().img().tower().Rocket3());
                        }
                        break;
                    case Specialization2:
                        switch (level) {
                            case 1:
                                image = SceneController.getInstance().imageController().tryGetImage(new Path().img().tower().ShockBullet1());
                                break;
                            case 2:
                                image = SceneController.getInstance().imageController().tryGetImage(new Path().img().tower().ShockBullet2());
                                break;
                            case 3:
                                image = SceneController.getInstance().imageController().tryGetImage(new Path().img().tower().ShockBullet3());
                        }
                }
        }

    }

    public void paint(int left, int top, int right, int bottom, Graphics g) {
        g.drawImage(image,
                left,
                top,
                right,
                bottom,
                null);
    }


}
