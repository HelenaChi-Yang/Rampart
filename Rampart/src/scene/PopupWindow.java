package scene;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;

import com.company.CommandSolver;


public abstract class PopupWindow extends Scene {

    private boolean isShow;

    public boolean isCancelable() {
        return isCancelable;
    }

    protected boolean isCancelable;

    private int x;
    private int y;
    private int width;
    private int height;
    private int left;
    private int right;
    private int top;

    public int getLeft() {
        return left;
    }

    public int getRight() {
        return right;
    }

    public int getTop() {
        return top;
    }

    public int getBottom() {
        return bottom;
    }

    private int bottom;

    public PopupWindow(int x, int y, int width, int height) {
        this.x = this.left = x - width / 2;
        this.y = this.top = y - height /2;
        this.width = width;
        this.height = height;
        this.isShow = false;
        this.isCancelable = false;
        this.right = x + width /2;
        this.bottom = y + height/2;

    }

    public void setCancelable() {
        isCancelable = true;
    }

    public void show() {
        isShow = true;
    }

    public void hide() {
        isShow = false;
    }

    public boolean isShow() {
        return this.isShow;
    }

    @Override
    public abstract void sceneBegin();

    @Override
    public abstract void sceneEnd();

    public abstract void paintWindow(Graphics g);

    @Override
    public final void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform tf = g2d.getTransform();
        g2d.translate(x, y);
        paintWindow(g);
        g2d.setTransform(tf);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public abstract void update();

    @Override
    public final CommandSolver.MouseCommandListener mouseListener() {
        return (MouseEvent e, CommandSolver.MouseState state, long trigTime) -> {
            if (e == null) {
                return;
            }
            if (isCancelable) { //滑鼠點外面他會hide()
                isCancelableHide(e, state, trigTime);
            }
            e.translatePoint(-x, -y);
            mouseTrig(e, state, trigTime);
            if (isShow == false) {
                sceneEnd();
            }
        };
    }

    public void isCancelableHide(MouseEvent e, CommandSolver.MouseState state, long trigTime) {
        if (state == CommandSolver.MouseState.PRESSED) {
            if (e.getX() < x || e.getX() > x + width || e.getY() < y || e.getY() > y + height) {
                hide();
            }
        }
    }

    protected abstract void mouseTrig(MouseEvent e, CommandSolver.MouseState state, long trigTime);

}
