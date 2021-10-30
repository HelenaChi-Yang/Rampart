package scene;

import com.company.CommandSolver;
import com.company.Global;
import com.company.Path;
import controllers.AudioResourceController;
import controllers.SceneController;
import gameObject.DefenseTower.DefenseTower;
import maploader.MapInfo;
import maploader.MapLoader;
import menu.Button;
import menu.Theme;
import menu.impl.MouseTriggerImpl;

import java.awt.*;
import java.awt.event.MouseEvent;
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

//    private PopupBuildScene popupBuildScene;
//    private Button mouseButton;

    private ArrayList<MapInfo> mapInfo;  //存入一個boolean
    private ArrayList<Button> buildingButtons;
    private ArrayList<PopupBuildScene> bulidingPops;
    private static ArrayList<int[]> buttonWork;


    public MapTest(String MapPath, String txtPath) throws IOException {
        mapInfo = new MapLoader(MapPath, txtPath).combineInfo();
        buildingButtons = new ArrayList<>();
        bulidingPops = new ArrayList<>();
        buttonWork = new ArrayList<>();

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
                    System.out.println((tmp.getX()*MAP_PIXEL + MAP_PIXEL) + "," + (tmp.getY()*MAP_PIXEL + MAP_PIXEL));
                    buttonWork.add(new int[]{tmp.getX() * MAP_PIXEL+ MAP_PIXEL, tmp.getY() * MAP_PIXEL+ MAP_PIXEL, 1});
                    //building = SceneController.getInstance().imageController().tryGetImage(new Path().img().road().building());
                    buildingButtons.add(new Button(tmp.getX()*MAP_PIXEL + MAP_PIXEL, tmp.getY()*MAP_PIXEL + MAP_PIXEL, Theme.get(38)));
                    bulidingPops.add(new PopupBuildScene(tmp.getX()*MAP_PIXEL + MAP_PIXEL, tmp.getY()*MAP_PIXEL + MAP_PIXEL, 112, 112));
                    break;
                default:
                    break;
            }
        }

        for (int i = 0; i < buildingButtons.size(); i++) {
            PopupBuildScene tmp = bulidingPops.get(i);
            buildingButtons.get(i).setClickedActionPerformed((int x, int y) -> {
                System.out.println(x + "," + y);
                AudioResourceController.getInstance().shot(new Path().sound().gameButton());
                System.out.println("成功啦");
                tmp.sceneBegin();
                tmp.show();
                tmp.setCancelable();
            });
        }





//        for (Button button: buildingButtons) {
//            button.setClickedActionPerformed((int x, int y) -> {
//                AudioResourceController.getInstance().shot(new Path().sound().gameButton());
//                System.out.println("成功啦");
//                popupTowerScene.sceneBegin();
//                popupTowerScene.show();
//                popupTowerScene.setCancelable();
//            });
//        }

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

        for (int i = 0; i < bulidingPops.size(); i++) {
            if (!bulidingPops.get(i).isShow()) {
                if (buttonWork.get(i)[2] == 1) {
                    buildingButtons.get(i).paint(g);
                }
            }
        }



        for (PopupBuildScene pop: bulidingPops) {
            if (pop.isShow()) {
                pop.paint(g);
            }
        }

    }

    public static void closeButton( int x, int y) {
        for (int i = 0; i < buttonWork.size(); i++) {
            if (buttonWork.get(i)[0] == x + 56 && buttonWork.get(i)[1] == y + 56) {
                buttonWork.get(i)[2] = 0;
                break;
            }
        }
    }

    public static void turnOnButton( int x, int y) {
        for (int i = 0; i < buttonWork.size(); i++) {
            if (buttonWork.get(i)[0] == x + 56 && buttonWork.get(i)[1] == y + 64) {
                buttonWork.get(i)[2] = 1;
                break;
            }
        }
    }

    @Override
    public void update() {

    }

    @Override
    public CommandSolver.MouseCommandListener mouseListener() {
        return (MouseEvent e, CommandSolver.MouseState state, long trigTime) -> {

//            for (Button button: buildingButtons) {
//                MouseTriggerImpl.mouseTrig(button, e, state);
//            }
            for (int i = 0; i < bulidingPops.size(); i++) {
                if (!bulidingPops.get(i).isShow()) {
                    if (buttonWork.get(i)[2] == 1) {
                        MouseTriggerImpl.mouseTrig(buildingButtons.get(i), e, state);
                    }
                } else {
                    bulidingPops.get(i).mouseListener().mouseTrig(e, state, trigTime);
                }
            }





        };
    }

    @Override
    public CommandSolver.KeyListener keyListener() {
        return null;
    }

    /**找出發點(數值要調整)*/
    public MapInfo specifyGrid(int x, int y){
        for (MapInfo tmp : mapInfo) {
            if(tmp.getX() == x && tmp.getY()== y){
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
