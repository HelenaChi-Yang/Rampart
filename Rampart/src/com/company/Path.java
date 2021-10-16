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

            public String map() {
                return this + "/map.jpg";
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

        public static class Objs extends Flow {
            private Objs(Flow flow) {
                super(flow, "/objs");
            }

            public String boom() {
                return this + "/boom.png";
            }

            public String boom2() {
                return this + "/boom2.png";
            }
        }

        public static class Background extends Flow {
            private Background(Flow flow) {
                super(flow, "/backgrounds");
            }
            public String Map() {
                return this + "/map.jpg";
            }
        }
        //因為constructor為private所以need add 接口
        public Actors actors() {
            return new Actors(this);
        }

        public Objs objs() {
            return new Objs(this);
        }

        public Road road(){
            return new Road(this);
        }

        public Background background() {
            return new Background(this);
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
