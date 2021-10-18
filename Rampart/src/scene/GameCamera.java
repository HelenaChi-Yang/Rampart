package scene;

import com.company.CommandSolver;
import com.company.Global;
import gameObject.GameObject;
import gameObject.Actor;

import java.awt.*;

public class GameCamera extends GameObject implements CommandSolver.KeyListener {

        private float xOffset;
        private float yOffset;
        private GameObject monster;
        private int time;

        private Image img;
        private int monsterSpeed;
        public GameCamera(float xOffset, float yOffset, Image SceneImg, Actor actor){
            super(0,0,1600,1000);
            this.xOffset=xOffset;
            this.yOffset=yOffset;
            this.monsterSpeed = 8; //先隨便訂一個，要改
            this.img=SceneImg;
            monster = actor;
            time=1;
        }

        public void move(float xAmt,float yAmt){
            xOffset+=xAmt;
            yOffset+=yAmt;
        }


        public void setxOffset(float xOffset) {
            this.xOffset = xOffset;
        }

        public void setyOffset(float yOffset) {
            this.yOffset = yOffset;
        }

        public float getxOffset() {
            return xOffset;
        }

        public float getyOffset() {
            return yOffset;
        }

        public boolean touchLeft(int imgWidth,GameObject obj){
            return (obj.collider().left()-(Global.SCREEN_X/2))<0;
        }
        public void touchRight(int imgHeight){

        }
        @Override
        public void paintComponent(Graphics g) {
            g.drawImage(img,(int)(painter().left()+getxOffset()),(int)(painter().top()+getyOffset()),painter().width()*time, painter().height()*time,null);
        }

        @Override
        public void update() {

        }

        @Override
        public void keyPressed(int commandCode, long trigTime) {
//            if (getxOffset()>=(collider().width()-Global.SCREEN_X/2)*-1 && getxOffset()<=Global.SCREEN_X/2) {
////            if (obj.isCollision(this.)) {
//                System.out.println(getxOffset());
//                if (commandCode == Global.LEFT) {
////                move(planeSpeed, 0);
//                    time++;
//
//
//                }
//                if (commandCode == Global.RIGHT) {
//                    move(planeSpeed * -1, 0);
//                    time--;
//                }
//                if (commandCode == Global.DOWN) {
//                    move(0, planeSpeed * -1);
//
//                }
//                if (commandCode == Global.UP) {
//                    move(0, planeSpeed * 1);
//
//                }
//
//            }
        }

        @Override
        public void keyReleased(int commandCode, long trigTime) {

        }

        @Override
        public void keyTyped(char c, long trigTime) {

        }

    }
