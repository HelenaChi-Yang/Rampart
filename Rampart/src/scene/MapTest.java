package scene;

import com.company.CommandSolver;
import com.company.Global;
import com.company.Path;
import controllers.SceneController;
import maploader.MapInfo;
import maploader.MapLoader;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

import static com.company.Global.*;

public class MapTest extends Scene {

    private Image path1;
    private Image pathUnder;
    private Image pathUp;
    private Image pathRight;
    private Image pathLeft;
    private Image cornerRightBottom;
    private Image cornerLeftBottom;
    private Image cornerRigthTop;
    private Image cornerLeftTop;

    private Image corner1;
    private Image corner2;
    private Image corner3;
    private Image corner4;
    private Image building;


    private ArrayList<MapInfo> mapInfo;  //存入一個boolean

    public MapTest() throws IOException {
        mapInfo = new MapLoader("/maploader/map3/genMap.bmp", "/maploader/map3/genMap.txt").combineInfo();

        for (MapInfo tmp : mapInfo) {
            switch (tmp.getName()) {
                case "path1":
                    path1= SceneController.getInstance().imageController().tryGetImage(new Path().img().road().path1());
                    break;
                case "path2":
                    pathUnder = SceneController.getInstance().imageController().tryGetImage(new Path().img().road().path2());
                    break;
                case "path3":
                    pathUp = SceneController.getInstance().imageController().tryGetImage(new Path().img().road().path3());
                    break;
                case "path4":
                    pathRight = SceneController.getInstance().imageController().tryGetImage(new Path().img().road().path4());
                    break;
                case "path5":
                    pathLeft = SceneController.getInstance().imageController().tryGetImage(new Path().img().road().path5());
                    break;
                case "path3(1)":
                    cornerRightBottom = SceneController.getInstance().imageController().tryGetImage(new Path().img().road().path3_1());
                    break;
                case "path3(2)":
                    cornerLeftBottom = SceneController.getInstance().imageController().tryGetImage(new Path().img().road().path3_2());
                    break;
                case "path3(3)":
                    cornerRigthTop = SceneController.getInstance().imageController().tryGetImage(new Path().img().road().path3_3());
                    break;
                case "path3(4)":
                    cornerLeftTop = SceneController.getInstance().imageController().tryGetImage(new Path().img().road().path3_4());
                    break;
                case "corner1":
                    corner1 = SceneController.getInstance().imageController().tryGetImage(new Path().img().road().corner1());
                    break;
                case "corner2":
                    corner2 = SceneController.getInstance().imageController().tryGetImage(new Path().img().road().corner2());
                    break;
                case "corner3":
                    corner3 = SceneController.getInstance().imageController().tryGetImage(new Path().img().road().corner3());
                    break;
                case "corner4":
                    corner4 = SceneController.getInstance().imageController().tryGetImage(new Path().img().road().corner4());
                    break;
                case "building":
                default:
                    building = SceneController.getInstance().imageController().tryGetImage(new Path().img().road().building());
                    break;
            }
        }
    }

    @Override
    public void sceneBegin() {

    }

    @Override
    public void sceneEnd() {

    }

    @Override
    public void paint(Graphics g) {
        for (MapInfo tmp : mapInfo) {
            switch (tmp.getName()) {
                case "path1":
                    g.drawImage(path1, tmp.getX()*MAP_PIXEL, tmp.getY()*MAP_PIXEL, tmp.getSizeX()*MAP_PIXEL, tmp.getSizeY()*MAP_PIXEL, null);
                    break;
                case "path2":
                    g.drawImage(pathUnder, tmp.getX()*MAP_PIXEL, tmp.getY()*MAP_PIXEL, tmp.getSizeX()*MAP_PIXEL, tmp.getSizeY()*MAP_PIXEL, null);
                    break;
                case "path3":
                    g.drawImage(pathUp, tmp.getX()*MAP_PIXEL, tmp.getY()*MAP_PIXEL, tmp.getSizeX()*MAP_PIXEL, tmp.getSizeY()*MAP_PIXEL, null);
                    break;
                case "path4":
                    g.drawImage(pathRight, tmp.getX()*MAP_PIXEL, tmp.getY()*MAP_PIXEL, tmp.getSizeX()*MAP_PIXEL, tmp.getSizeY()*MAP_PIXEL, null);
                    break;
                case "path5":
                    g.drawImage(pathLeft, tmp.getX()*MAP_PIXEL, tmp.getY()*MAP_PIXEL, tmp.getSizeX()*MAP_PIXEL, tmp.getSizeY()*MAP_PIXEL, null);
                    break;
                case "path3(1)":
                    g.drawImage(cornerRightBottom, tmp.getX()*MAP_PIXEL, tmp.getY()*MAP_PIXEL, tmp.getSizeX()*MAP_PIXEL, tmp.getSizeY()*MAP_PIXEL, null);
                    break;
                case "path3(2)":
                    g.drawImage(cornerLeftBottom, tmp.getX()*MAP_PIXEL, tmp.getY()*MAP_PIXEL, tmp.getSizeX()*MAP_PIXEL, tmp.getSizeY()*MAP_PIXEL, null);
                    break;
                case "path3(3)":
                    g.drawImage(cornerRigthTop, tmp.getX()*MAP_PIXEL, tmp.getY()*MAP_PIXEL, tmp.getSizeX()*MAP_PIXEL, tmp.getSizeY()*MAP_PIXEL, null);
                    break;
                case "path3(4)":
                    g.drawImage(cornerLeftTop, tmp.getX()*MAP_PIXEL, tmp.getY()*MAP_PIXEL, tmp.getSizeX()*MAP_PIXEL, tmp.getSizeY()*MAP_PIXEL, null);
                    break;
                case "corner1":
                    g.drawImage(corner1, tmp.getX()*MAP_PIXEL, tmp.getY()*MAP_PIXEL, tmp.getSizeX()*MAP_PIXEL, tmp.getSizeY()*MAP_PIXEL, null);
                    break;
                case "corner2":
                    g.drawImage(corner2, tmp.getX()*MAP_PIXEL, tmp.getY()*MAP_PIXEL, tmp.getSizeX()*MAP_PIXEL, tmp.getSizeY()*MAP_PIXEL, null);
                    break;
                case "corner3":
                    g.drawImage(corner3, tmp.getX()*MAP_PIXEL, tmp.getY()*MAP_PIXEL, tmp.getSizeX()*MAP_PIXEL, tmp.getSizeY()*MAP_PIXEL, null);
                    break;
                case "corner4":
                    g.drawImage(corner4, tmp.getX()*MAP_PIXEL, tmp.getY()*MAP_PIXEL, tmp.getSizeX()*MAP_PIXEL, tmp.getSizeY()*MAP_PIXEL, null);
                    break;
                case "building":
                default:
                    g.drawImage(building, tmp.getX()*MAP_PIXEL, tmp.getY()*MAP_PIXEL, tmp.getSizeX()*MAP_PIXEL, tmp.getSizeY()*MAP_PIXEL, null);
                    break;
            }
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

    /**找出發點(數值要調整)*/
    public MapInfo firstGrid(){
        for (MapInfo tmp : mapInfo) {
            if(tmp.getX() == 6 && tmp.getY()== 0){
                return tmp;
            }
        }
        return null;
    }

    public ArrayList<MapInfo> getMapInfo() {
        return mapInfo;
    }

    public void setMapInfo(ArrayList<MapInfo> mapInfo) {
        this.mapInfo = mapInfo;
    }
}
