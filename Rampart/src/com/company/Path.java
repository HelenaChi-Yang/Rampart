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

            public String artificer() { return this+"/hero_artificer.png";}
            public String warlord() { return this+"/hero_warlord.png";}
            public String crystalgolem() { return this+"/monster_crystalgolem.png";}
            public String medusa() { return this+"/monster_medusa.png";}
            public String artificerHover() { return this+"/hero_artificer_change.png";}
            public String warlordHover() { return this+"/hero_warlord_change.png";}
            public String crystalgolemHover() { return this+"/monster_crystalgolem_change.png";}
            public String medusaHover() { return this+"/monster_medusa_change.png";}

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


        }

        public static class Tower extends Flow {
            private Tower(Flow flow) {
                super(flow, "/tower");
            }

            public String lightening_1() {
                return this + "/lightening_1.png";
            }

            public String lightening_2() {
                return this + "/lightening_2.png";
            }

            public String lightening_3() {
                return this + "/lightening_3.png";
            }

            public String lightening_change1() {
                return this + "/lightening_change1.png";
            }
        }

        public static class Weapon extends Flow {
            private Weapon(Flow flow) {
                super(flow, "/weapon");
            }

            public String Arrow() {
                return this + "/Arrow.png";
            }

        }

        public static class Backgrounds extends Flow {

            private Backgrounds(Flow flow) {
                super(flow, "/backgrounds");
            }

            public String main() {
                return this + "/main.png";
            }
            public String popWindow() {return  this +"/popWindow.png";}

            public String grassBackground() {return  this +"/grassBackground.png";}


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
            public String mainMenu() { return  this + "/mainMenu.png";}
            public String newGame() {return this + "/newGame.png";}
            public String loadGame() {return this + "/loadGame.png";}
            public String host() {return this + "/host.png";}
            public String guest() {return this + "/guest.png";}
            public String confirm() {return this + "/confirm.png";}
            public String cancel() {return this + "/cancel.png";}
            public String gameButtonUpGrade() {return this + "/upgrade.png";}
            public String gameButtonSellTower() {return this + "/demolish.png";}
        }

        //因為constructor為private所以need add 接口
        public Actors actors() {
            return new Actors(this);
        }

        public Road road(){
            return new Road(this);
        }

        public Tower tower(){
            return new Tower(this);
        }

        public Weapon weapon(){
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
        public String background(){
            return this+"/background.wav";
        }
        public String menu(){
            return this+"/menu.wav";
        }
        public String button() {
            return this + "/spell3.wav";
        }
        public String gameButton() {
            return this + "/buttonzz.wav";}
    }

    public Img img() {
        return new Img();
    }

    public Sounds sound() {
        return new Sounds();
    }}
