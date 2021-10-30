package com.company;

public class Path {
    public static abstract class Flow {
        private String path;

        public Flow(String path) {
            this.path = path;
        }

        public Flow(Flow flow, String path) {
            this.path = flow.path + path;
        }

        @Override
        public String toString() {
            return path;
        }
    }

    public static class Resources extends Flow {
        private Resources() {
            super("/resource");
        }
    }

    public static class Img extends Flow {
        private Img() {
            super(new Resources(), "/imgs");
        }

        public static class Actors extends Flow {
            private Actors(Flow flow) {
                super(flow, "/actors");
            }

            public String airCraft() {
                return this + "/airplane1.png";
            }

            public String enemy() {
                return this + "/enemy1.png";
            }

            public String Actor1() {
                return this + "/Actor1.png";
            }

            public String flying() {
                return this + "/flying.png";
            }

            public String chicken(){
                return this + "/chicken.png";
            }

            public String monster() {
                return this + "/monster.png";
            }

            public String Map() {
                return this + "/map.png";
            }

            public String Map2() {
                return this + "/map.jpg";
            }

            public String MapChange() {
                return this + "/mapChange.png";
            }

            public String artificer() {
                return this + "/hero_artificer.png";
            }

            public String warlord() {
                return this + "/hero_warlord.png";
            }

            public String crystalgolem() {
                return this + "/monster_crystalgolem.png";
            }

            public String medusa() {
                return this + "/monster_medusa.png";
            }

            public String artificerHover() {
                return this + "/hero_artificer_change.png";
            }

            public String warlordHover() {
                return this + "/hero_warlord_change.png";
            }

            public String crystalgolemHover() {
                return this + "/monster_crystalgolem_change.png";
            }

            public String medusaHover() {
                return this + "/monster_medusa_change.png";
            }

            public String boss1() {
                return this + "/boss1.png";
            }
            public String boss2() {
                return this + "/boss2.png";
            }
            public String boss3() {
                return this + "/boss3.png";
            }

            public String cat1() {
                return this + "/cat1.png";
            }
            public String cat2() {
                return this + "/cat2.png";
            }
            public String cat3() {
                return this + "/cat3.png";
            }
            public String cat4() {
                return this + "/cat4.png";
            }
            public String cat5() {
                return this + "/cat5.png";
            }
            public String cat6() {
                return this + "/cat6.png";
            }
            public String cat7() {
                return this + "/cat7.png";
            }
            public String cat8() {
                return this + "/cat8.png";
            }
            public String cat9() {
                return this + "/cat9.png";
            }
            public String cat10() {
                return this + "/cat10.png";
            }
            public String cat11() {
                return this + "/cat11.png";
            }
            public String cat12() {
                return this + "/cat12.png";
            }
            public String cat13() {
                return this + "/cat13.png";
            }
            public String cat14() {
                return this + "/cat14.png";
            }
            public String cat15() {
                return this + "/cat15.png";
            }
            public String cat16() {return this + "/cat16.png";}
            public String cat17() {
                return this + "/cat17.png";
            }
            public String cat18() {
                return this + "/cat18.png";
            }
            public String cat19() {
                return this + "/cat19.png";
            }
            public String cat20() {
                return this + "/cat20.png";
            }

        }

        public static class Road extends Flow {
            private Road(Flow flow) {
                super(flow, "/road");
            }

            public String path() {
                return this + "/path.png";
            }

            public String path1() {
                return this + "/path1.png";
            }

            public String path2() {
                return this + "/path2.png";
            }

            public String path3() {
                return this + "/path3.png";
            }

            public String path3_1() {
                return this + "/path3(1).png";
            }

            public String path3_2() {
                return this + "/path3(2).png";
            }

            public String path3_3() {
                return this + "/path3(3).png";
            }

            public String path3_4() {
                return this + "/path3(4).png";
            }

            public String path4() {
                return this + "/path4.png";
            }

            public String path5() {
                return this + "/path5.png";
            }

            public String pathSmall() {
                return this + "/pathSmall.png";
            }

            public String building() {
                return this + "/building.png";
            }

            public String corner1() {
                return this + "/corner1.png";
            }

            public String corner2() {
                return this + "/corner2.png";
            }

            public String corner3() {
                return this + "/corner3.png";
            }

            public String corner4() {
                return this + "/corner4.png";
            }

            public String youCanNotPass(){return this + "/YouCanNotPass!.png";}
        }

        public static class Tower extends Flow {
            private Tower(Flow flow) {
                super(flow, "/tower");
            }

            public String ArcherTowerBasic1() {
                return this + "/ArcherTowerBasic1.png";
            }

            public String ArcherTowerBasic2() {
                return this + "/ArcherTowerBasic2.png";
            }

            public String ArcherTowerBasic3() {
                return this + "/ArcherTowerBasic3.png";
            }

            public String ArcherTowerAdvance1() {
                return this + "/ArcherTowerAdvance1.png";
            }

            public String ArcherTowerAdvance2() {
                return this + "/ArcherTowerAdvance2.png";
            }

            public String ArcherTowerAdvance3() {
                return this + "/ArcherTowerAdvance3.png";
            }

            public String CrossBow1() {
                return this + "/CrossBow1.png";
            }

            public String CrossBow2() {
                return this + "/CrossBow2.png";
            }

            public String CrossBow3() {
                return this + "/CrossBow3.png";
            }

            public String Spear1() {
                return this + "/Spear1.png";
            }

            public String Spear2() {
                return this + "/Spear2.png";
            }

            public String Spear3() {
                return this + "/Spear3.png";
            }


            public String MagicTowerBasic1() {
                return this + "/MagicTowerBasic1.png";
            }

            public String MagicTowerBasic2() {
                return this + "/MagicTowerBasic2.png";
            }

            public String MagicTowerBasic3() {
                return this + "/MagicTowerBasic3.png";
            }

            public String MagicTowerAdvance1() {
                return this + "/MagicTowerAdvance1.png";
            }

            public String MagicTowerAdvance2() {
                return this + "/MagicTowerAdvance2.png";
            }

            public String MagicTowerAdvance3() {
                return this + "/MagicTowerAdvance3.png";
            }

            public String Lighting1() {
                return this + "/Lighting1.png";
            }

            public String Lighting2() {
                return this + "/Lighting2.png";
            }

            public String Lighting3() {
                return this + "/Lighting3.png";
            }

            public String Animal1() {
                return this + "/Animal1.png";
            }

            public String Animal2() {
                return this + "/Animal2.png";
            }

            public String Animal3() {
                return this + "/Animal3.png";
            }



            public String CannonTowerBasic1() {
                return this + "/CannonTowerBasic1.png";
            }

            public String CannonTowerBasic2() {
                return this + "/CannonTowerBasic2.png";
            }

            public String CannonTowerBasic3() {
                return this + "/CannonTowerBasic3.png";
            }

            public String CannonTowerAdvance1() {
                return this + "/CannonTowerAdvance1.png";
            }

            public String CannonTowerAdvance2() {
                return this + "/CannonTowerAdvance2.png";
            }

            public String CannonTowerAdvance3() {
                return this + "/CannonTowerAdvance3.png";
            }

            public String Rocket1() {
                return this + "/Rocket1.png";
            }

            public String Rocket2() {
                return this + "/Rocket2.png";
            }

            public String Rocket3() {
                return this + "/Rocket3.png";
            }

            public String ShockBullet1() {
                return this + "/ShockBullet1.png";
            }

            public String ShockBullet2() {
                return this + "/ShockBullet2.png";
            }

            public String ShockBullet3() {
                return this + "/ShockBullet3.png";
            }

        }

        public static class Weapon extends Flow {
            private Weapon(Flow flow) {
                super(flow, "/weapon");
            }

            public String Arrow() {
                return this + "/Arrow.png";
            }

            public String Spear() {
                return this + "/Spear.png";
            }

            public String MagicBall() {
                return this + "/MagicBall.png";
            }

            public String Lighting() {
                return this + "/Lighting.png";
            }

            public String CannonBall() {
                return this + "/CannonBall.png";
            }

            public String Rocket() {
                return this + "/Rocket.png";
            }

            public String CannonBallExplosion() {
                return this + "/CannonBallExplosion.png";
            }

            public String RocketExplosion() {
                return this + "/RocketExplosion.png";
            }

            public String AnimalMagicCircle() {
                return this + "/AnimalMagicCircle.png";
            }
        }

        public static class Backgrounds extends Flow {

            private Backgrounds(Flow flow) {
                super(flow, "/backgrounds");
            }

            public String main() {
                return this + "/New-Tower-Defense-Poster.jpeg";
            }

            public String popWindow() {
                return this + "/popWindow.png";
            }

            public String popIntroWindow() {
                return this + "/popWindow.jpg";
            }

            public String grassBackground() {
                return this + "/grassBackground.png";
            }

            public String grassBackground1() {
                return this + "/grassBackground1.png";
            }

            public String grassBackground2() {
                return this + "/grassBackground2.png";
            }

            public String grassBackground3() {
                return this + "/grassBackground3.png";
            }


            public String stage1() {
                return this + "/Awesome_map_stage1.png";
            }

            public String stage2() {
                return this + "/Awesome_map_stage2.png";
            }

            public String stage3() {
                return this + "/Awesome_map_stage3.png";
            }

            public String gameWinAll() {return this + "/gameWinAll.png";
            }

            public String gameWin() {return this + "/gameWin.png";
            }

            public String howToPlay() {return this + "/howToPlay.png";}

            public String howToPlayCH() {return this + "/howToPlayChinese.png";}

            public String talk() {return this + "/popup_2.png";}

            public String gameOver() {return this + "/gameFail.png";}

        }

        public static class Objs extends Flow {

            private Objs(Flow flow) {
                super(flow, "/objs");
            }


            public String rampart() {
                return this + "/rampart.png";
            }

            public String howToPlay() {
                return this + "/howtoplay.png";
            }

            public String singleMode() {
                return this + "/singleMode.png";
            }

            public String onlineMode() {
                return this + "/onlineMode.png";
            }

            public String exit() {
                return this + "/exit.png";
            }

            public String mainMenu() {
                return this + "/mainMenu.png";
            }

            public String newGame() {
                return this + "/newGame.png";
            }

            public String loadGame() {
                return this + "/loadGame.png";
            }

            public String host() {
                return this + "/host.png";
            }

            public String guest() {
                return this + "/guest.png";
            }

            public String confirm() {
                return this + "/confirm.png";
            }

            public String confirmPaper() {
                return this + "/confirmPaper.png";
            }

            public String cancel() {
                return this + "/cancel.png";
            }

            public String gameStart() {
                return this + "/gameStart.png";
            }

            public String gameButtonUpGrade() {
                return this + "/upgrade.png";
            }

            public String gameButtonSellTower() {
                return this + "/demolish.png";
            }

            public String block() {
                return this + "/block.png";
            }

            public String lightening() {
                return this + "/lightening.png";
            }

            public String poison() {
                return this + "/poison.png";
            }

            public String petrifaction() {
                return this + "/petrifaction.png";
            }

            public String blockCD() {
                return this + "/blockCD.png";
            }

            public String lighteningCD() {
                return this + "/lighteningCD.png";
            }

            public String poisonCD() {
                return this + "/poisonCD.png";
            }

            public String petrifactionCD() {
                return this + "/petrifactionCD.png";
            }

            public String coin() {
                return this + "/coin.png";
            }

            public String archerTower() {
                return this + "/archerTower.png";
            }

            public String cannonTower() {
                return this + "/cannonTower.png";
            }

            public String magicTower() {
                return this + "/magicTower.png";
            }

            public String ArcherAVTower() {
                return this + "/ArcherAVTower.png";
            }

            public String CannonAVTower() {
                return this + "/CannonAVTower.png";
            }

            public String CrossbowTower() {
                return this + "/CrossbowTower.png";
            }

            public String SpearTower() {
                return this + "/SpearTower.png";
            }

            public String RocketTower() {
                return this + "/RocketTower.png";
            }

            public String ShockBulletTower() {
                return this + "/ShockBulletTower.png";
            }

            public String MagicAVTower() {
                return this + "/MagicAVTower.png";
            }

            public String AnimalTower() {
                return this + "/chicken.png";
            }

            public String LighteningTower() {
                return this + "/LighteningTower.png";
            }

            public String cancelButton() {
                return this + "/cancelButton.png";
            }

            public String titleChooseHero() {
                return this + "/titleChooseHero.png";
            }

            public String buttonCallMonster() {
                return this + "/silver.png";
            }

            public String buttonCallMonsterHover() {
                return this + "/bronze.png";
            }

            public String play() {
                return this + "/play.png";
            }

            public String pause() {
                return this + "/pause.png";
            }

            public String playerHp(){return this + "/PlayerHp.png";}

            public String go(){return this + "/Go.png";}

            public String Waiting(){return this + "/Waiting.png";}

            public String connectOK(){return this + "/connectOK.png";}

            public String waitingServer(){return this + "/waitingServer.png";}

            public String labelPause(){return this + "/labelPause.png";}

            public String bossHead() {return this + "/bossHead.png";}

            public String chineseVersion() {return this + "/Chinese.png";}

            public String fastRatioButton() {
                return this + "/FastRatioButton.png";
            }

        }

        //因為constructor為private所以need add 接口
        public Actors actors() {
            return new Actors(this);
        }

        public Road road() {
            return new Road(this);
        }

        public Tower tower() {
            return new Tower(this);
        }

        public Weapon weapon() {
            return new Weapon(this);
        }

        public Objs objs() {
            return new Objs(this);
        }

        public Backgrounds backgrounds() {
            return new Backgrounds(this);
        }

    }

    public static class Sounds extends Flow {
        private Sounds() {
            super(new Resources(), "/sounds");
        }

        public String background() {
            return this + "/background.wav";
        }

        public String menu() {
            return this + "/menu.wav";
        }

        public String button() {
            return this + "/spell3.wav";
        }

        public String gameButton() {
            return this + "/buttonzz.wav";
        }

        public String statusScene() {return this + "/TownTheme.wav";}

        public String battle() {return this + "/battleThemeA.wav";};

        public String gameWin() {return this + "/win.wav";};

        public String gameOver() {return this + "/fail.wav";};

        public String alert() {return this + "/alert.wav";};

        public String bitme() {return this + "/bitme6.wav";};

        public static class GameObjectSound extends Flow{
            private GameObjectSound(Flow flow){super(flow,"/GameObjectSound");}

            public String animalSpells(){return this + "/AnimalSpells.wav";}
            public String buildTower(){return this + "/BuildTower.wav";}
            public String duck(){return this + "/Duck.wav";}
            public String explosion(){return this + "/Explosion.wav";}
            public String fireCannonBall(){return this + "/FireCannonBall.wav";}
            public String fireRocket(){return this + "/FireRocket.wav";}
            public String lighting(){return this + "/Lighting.wav";}
            public String magicBall(){return this + "/MagicBall.wav";}
            public String sellTower(){return this + "/SellTower.wav";}
            public String shootArrow(){return this + "/ShootArrow.wav";}
            public String throwSpear(){return this + "/ThrowSpear.wav";}
            public String throwSpearSkill(){return this + "/ThrowSpearSkill.wav";}
        }

        public GameObjectSound gameObjectSound(){return new GameObjectSound(this);}
    }

    public Img img() {
        return new Img();
    }

    public Sounds sound() {
        return new Sounds();
    }
}
