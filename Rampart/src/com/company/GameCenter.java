package com.company;

import controllers.SceneController;
import scene.MainScene;
import scene.MenuScene;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class GameCenter implements GameKernel.GameInterface, CommandSolver.MouseCommandListener, CommandSolver.KeyListener {

    public GameCenter() throws IOException {
        SceneController.getInstance().change(new MenuScene());
    }

    @Override
    public void paint(Graphics g) {
        SceneController.getInstance().paint(g);
    }

    @Override
    public void update() {
        SceneController.getInstance().update();
    }


    @Override
    public void mouseTrig(MouseEvent e, CommandSolver.MouseState state, long trigTime) {
        CommandSolver.MouseCommandListener listener = SceneController.getInstance().mouseListener();
        if (listener != null) {
            listener.mouseTrig(e, state, trigTime);
        }
    }

    @Override
    public void keyPressed(int commandCode, long trigTime) {
        CommandSolver.KeyListener listener = SceneController.getInstance().keyListener();
        if (listener != null) {
            listener.keyPressed(commandCode, trigTime);
        }
    }

    @Override
    public void keyReleased(int commandCode, long trigTime) {
        CommandSolver.KeyListener listener = SceneController.getInstance().keyListener();
        if (listener != null) {
            listener.keyReleased(commandCode, trigTime);
        }
    }

    @Override
    public void keyTyped(char c, long trigTime) {
        CommandSolver.KeyListener listener = SceneController.getInstance().keyListener();
        if (listener != null) {
            listener.keyTyped(c, trigTime);
        }
    }
}
