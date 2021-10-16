package com.company;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        JFrame frame = new JFrame();
        frame.setTitle("Rampart");
        frame.setSize(Global.WINDOW_WIDTH, Global.WINDOW_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //NONE(4),
        //        UP(3),
        //        DOWN(0),
        //        LEFT(1),
        //        RIGHT(2);
        int[][] commands = {
                {KeyEvent.VK_UP, Global.UP},
                {KeyEvent.VK_DOWN, Global.DOWN},
                {KeyEvent.VK_LEFT, Global.LEFT},
                {KeyEvent.VK_RIGHT, Global.RIGHT},
                {KeyEvent.VK_SPACE, Global.SPACE},
                {KeyEvent.VK_SHIFT, Global.SHIFT},
        };

        GameCenter gi = new GameCenter();
        GameKernel kernel = new GameKernel.Builder(gi, Global.LIMIT_DELTA_TIME, Global.NANOSECOUND_PER_UPDATE)
                .initListener(commands)
                .enableMouseTrack(gi)
                .enableKeyboardTrack(gi)
                .keyCleanMode()
                .gen();


        frame.add(kernel);
        frame.setVisible(true);
        kernel.run(Global.IS_DEBUG);

    }
}
