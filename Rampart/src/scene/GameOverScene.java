package scene;

import com.company.CommandSolver;
import com.company.Global;

import java.awt.*;

public class GameOverScene extends Scene {

    private String printAlive;
    private String printAttackPlane;

    public void setPrintAlive(String inputPrintOnBoard){
        printAlive = inputPrintOnBoard;
    }
    public void setAttackPlane(String inputPrintOnBoard){
        printAttackPlane = inputPrintOnBoard;
    }

    @Override
    public void sceneBegin() {

    }

    @Override
    public void sceneEnd() {

    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.RED);
        g.drawString("Game Over", Global.SCREEN_X / 2, Global.SCREEN_Y / 2);
        if(!printAlive.isEmpty() && ! printAttackPlane.isEmpty()) {
            g.setColor(Color.RED);
            g.drawString(printAlive, Global.SCREEN_X / 2, Global.SCREEN_Y / 2 - 50);

            g.setColor(Color.RED);
            g.drawString(printAttackPlane, Global.SCREEN_X / 2, Global.SCREEN_Y / 2 - 100);
        }
    }

    @Override
    public void update() {

    }

    @Override
    public CommandSolver.MouseCommandListener mouseListener() {
        return null;
    }

    @Override
    public CommandSolver.KeyListener keyListener() {
        return null;
    }
}
