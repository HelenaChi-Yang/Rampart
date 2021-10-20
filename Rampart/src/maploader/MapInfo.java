package maploader;

import com.company.Global;
import gameObject.GameObject;
import gameObject.Rect;

public class MapInfo {

    private final String name;
    private final int x;
    private final int y;
    private final int sizeX;
    private final int sizeY;

    private boolean isWalk;

    public MapInfo(String name, int x, int y, int sizeX, int sizeY) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.isWalk = false;
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
}
