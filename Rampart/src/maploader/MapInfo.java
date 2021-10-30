package maploader;

import com.company.CommandSolver;
import com.company.Global;
import com.company.Path;
import controllers.AudioResourceController;
import gameObject.GameObject;
import gameObject.Rect;
import menu.Button;
import menu.Theme;
import menu.impl.MouseTriggerImpl;
import scene.PopupBuildScene;
import scene.PopupTowerScene;

import java.awt.event.MouseEvent;

import static com.company.Global.TOWERHEIGHT;
import static com.company.Global.TOWERWIDTH;

public class MapInfo implements CommandSolver.MouseCommandListener{

    private final String name;
    private final int x;
    private final int y;
    private final int sizeX;
    private final int sizeY;

    private PopupBuildScene popupBuildScene;
    private Button mouseButton;

    private boolean isWalk;
    private Global.Direction dir; //儲存下一格的方向


    public MapInfo(String name, int x, int y, int sizeX, int sizeY) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.isWalk = false;
        dir = Global.Direction.DOWN; //儲存下一格的方向

        mouseButton = new Button(x + sizeX / 2, y + sizeY / 2, Theme.get(9));
        setButton();
        popupBuildScene = new PopupBuildScene(x + sizeX / 2, y + sizeY / 2, TOWERWIDTH + 48, TOWERHEIGHT + 48);

    }

    public void setButton() {
        mouseButton.setClickedActionPerformed((int x, int y) -> {
            AudioResourceController.getInstance().shot(new Path().sound().gameButton());
            System.out.println("Help");
            popupBuildScene.sceneBegin();
            popupBuildScene.show();
            popupBuildScene.setCancelable();
        });
    }




    public Global.Direction getDir() {
        return dir;
    }

    public void setDir(Global.Direction dir) {
        this.dir = dir;
    }
    public String getName() {
        return name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public int getAbsCoordinateX() {
        return (int) (x * sizeX + 0.5 * sizeX);
    }

    public int getAbsCoordinateY() {
        return (int) (y * sizeY + 0.5 * sizeY);
    }

    public boolean isInThisGrid(GameObject gameObject) {
        Rect object = gameObject.collider();
        int gridSize = Global.MAP_PIXEL;
        if (object.centerX() >= x * gridSize && object.centerX() <= (x * gridSize) + sizeX * gridSize && object.centerY() >= y * gridSize && object.centerY() <= (y * gridSize) + sizeY * gridSize) {
            return true;
        }
        return false;
    }

    public boolean isWalk() {
        return isWalk;
    }

    public void setWalk(boolean walk) {
        isWalk = walk;
    }

    @Override
    public void mouseTrig(MouseEvent e, CommandSolver.MouseState state, long trigTime) {
        if (!popupBuildScene.isShow()) {
                MouseTriggerImpl.mouseTrig(mouseButton, e, state);
            } else {
                popupBuildScene.mouseListener().mouseTrig(e, state, trigTime);
            }
    }
}
