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

            public String aircraft() {
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

    }

    public static class Sound extends Flow {
        private Sound() {
            super(new Resources(), "/sounds");
        }
    }

    public Img img() {
        return new Img();
    }

    public Sound sound() {
        return new Sound();
    }
}
