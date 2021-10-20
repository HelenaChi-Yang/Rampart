package scene;


import com.company.CommandSolver;

import java.awt.*;
import java.io.IOException;

public abstract class Scene {

    public abstract void sceneBegin();

    public abstract void sceneEnd();

    public abstract void paint(Graphics g);

    public abstract void update();

    public abstract CommandSolver.MouseCommandListener mouseListener();

    public abstract CommandSolver.KeyListener keyListener();
}
