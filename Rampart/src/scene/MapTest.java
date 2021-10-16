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

    Image path1;
    Image pathUnder;
    Image pathUp;
    Image pathRight;
    Image pathLeft;
    Image cornerRightBottom;
    Image cornerLeftBottom;
    Image cornerRigthTop;
    Image cornerLeftTop;
    ArrayList<MapInfo> mapInfo;

    public MapTest() throws IOException {
        mapInfo = new MapLoader("genMap.bmp", "genMap.txt").combineInfo();

        for (MapInfo tmp : mapInfo) {
            switch (tmp.getName()) {
                case "path1":
                    path1= SceneController.getInstance().imageController().tryGetImage(new Path().img().road().path1());
                    break;
                case "pathUnder":
                    pathUnder = SceneController.getInstance().imageController().tryGetImage(new Path().img().road().path2());
                    break;
                case "pathUp":
                    pathUp = SceneController.getInstance().imageController().tryGetImage(new Path().img().road().path3());
                    break;
                case "pathRight":
                    pathRight = SceneController.getInstance().imageController().tryGetImage(new Path().img().road().path4());
                    break;
                case "pathLeft":
                    pathLeft = SceneController.getInstance().imageController().tryGetImage(new Path().img().road().path5());
                    break;
                case "cornerRightBottom":
                    cornerRightBottom = SceneController.getInstance().imageController().tryGetImage(new Path().img().road().path3_1());
                    break;
                case "cornerLeftBottom":
                    cornerLeftBottom = SceneController.getInstance().imageController().tryGetImage(new Path().img().road().path3_2());
                    System.out.println("cornerLeftBottom");
                    break;
                case "cornerRigthTop":
                    cornerRigthTop = SceneController.getInstance().imageController().tryGetImage(new Path().img().road().path3_3());
                    break;
                case "cornerLeftTop":
                default:
                    cornerLeftTop = SceneController.getInstance().imageController().tryGetImage(new Path().img().road().path3_4());
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
                    g.drawImage(path1, tmp.getX()*tmp.getSizeX()*MAP_PIXEL, tmp.getY()*tmp.getSizeY()*MAP_PIXEL, tmp.getSizeX()*MAP_PIXEL, tmp.getSizeY()*MAP_PIXEL, null);
                    break;
                case "pathUnder":
                    g.drawImage(pathUnder, tmp.getX()*tmp.getSizeX()*MAP_PIXEL, tmp.getY()*tmp.getSizeY()*MAP_PIXEL, tmp.getSizeX()*MAP_PIXEL, tmp.getSizeY()*MAP_PIXEL, null);
                    break;
                case "pathUp":
                    g.drawImage(pathUp, tmp.getX()*tmp.getSizeX()*MAP_PIXEL, tmp.getY()*tmp.getSizeY()*MAP_PIXEL, tmp.getSizeX()*MAP_PIXEL, tmp.getSizeY()*MAP_PIXEL, null);
                    break;
                case "pathRight":
                    g.drawImage(pathRight, tmp.getX()*tmp.getSizeX()*MAP_PIXEL, tmp.getY()*tmp.getSizeY()*MAP_PIXEL, tmp.getSizeX()*MAP_PIXEL, tmp.getSizeY()*MAP_PIXEL, null);
                    break;
                case "pathLeft":
                    g.drawImage(pathLeft, tmp.getX()*tmp.getSizeX()*MAP_PIXEL, tmp.getY()*tmp.getSizeY()*MAP_PIXEL, tmp.getSizeX()*MAP_PIXEL, tmp.getSizeY()*MAP_PIXEL, null);
                    break;
                case "cornerRightBottom":
                    g.drawImage(cornerRightBottom, tmp.getX()*tmp.getSizeX()*MAP_PIXEL, tmp.getY()*tmp.getSizeY()*MAP_PIXEL, tmp.getSizeX()*MAP_PIXEL, tmp.getSizeY()*MAP_PIXEL, null);
                    break;
                case "cornerLeftBottom":
                    g.drawImage(cornerLeftBottom, tmp.getX()*tmp.getSizeX()*MAP_PIXEL, tmp.getY()*tmp.getSizeY()*MAP_PIXEL, tmp.getSizeX()*MAP_PIXEL, tmp.getSizeY()*MAP_PIXEL, null);
                    break;
                case "cornerRigthTop":
                    g.drawImage(cornerRigthTop, tmp.getX()*tmp.getSizeX()*MAP_PIXEL, tmp.getY()*tmp.getSizeY()*MAP_PIXEL, tmp.getSizeX()*MAP_PIXEL, tmp.getSizeY()*MAP_PIXEL, null);
                    break;
                case "cornerLeftTop":
                default:
                    g.drawImage(cornerLeftTop, tmp.getX()*tmp.getSizeX()*MAP_PIXEL, tmp.getY()*tmp.getSizeY()*MAP_PIXEL, tmp.getSizeX()*MAP_PIXEL, tmp.getSizeY()*MAP_PIXEL, null);
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
}
