package maploader;

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


    public boolean isWalk() {
        return isWalk;
    }

    public void setWalk(boolean walk) {
        isWalk = walk;
    }
}
