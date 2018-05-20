package battlecity.gui;

import battlecity.game.Item;
import battlecity.game.Tile;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

/**
 *
 * @author xGod
 */
public class Viewer extends Canvas implements Runnable {

    private Dimension dim;
    private Scene sc;

    public static enum AllowedAction {
        MOVE, TAKEDMG, SLOWDOWN, EXPLODE;
    }

    public Viewer(Dimension dim) {
        this.dim = dim;
        initProperties();
        initObjects();
    }

    private void initProperties() {
        setSize(dim);
        setMinimumSize(dim);
        setMaximumSize(dim);
        setPreferredSize(dim);
        setBackground(Color.black);
    }

    private void initObjects(){
        sc = new Scene();
    }

    public Scene getSc() {
        return sc;
    }

    public void setSc(Scene sc) {
        this.sc = sc;
    }

    @Override
    public void run() {

    }

    public AllowedAction itemCanDo(Item i) {
        return null;

    }

    //---------------------------COLLISIONS------------------------------------>
    public boolean checkCollision(Item i) {
        
        //following if's check if collides with walls
        if ((i.getAxisX() + i.getSpeedX()) > this.dim.width) {
            return true;
        }
        if ((i.getAxisY() + i.getSpeedY()) > this.dim.height) {
            return true;
        }
        //following if's check if collides with items or tiles
        //if(this.checkCollisionWithItems(i, ai)){
        //return true;}
        //if(this.checkCollisionWithTiles(i,t)){
        //return true;}
        
        return false;
    }

    private boolean checkCollisionWithTiles(Item i, ArrayList<Tile> t) {
        for (Tile x : t) {
            for (int j = 0; j <= 32; j++) {
                if ((x.getCoordinateX() + j == i.getAxisX() + i.getSpeedX()
                        || x.getCoordinateY() + j == i.getAxisY() + i.getSpeedY())
                        && !x.getClass().getSimpleName().equals("Grass")
                        || !x.getClass().getSimpleName().equals("Water")) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkCollisionWithItems(Item i, ArrayList<Item> ai) {
        for (Item x : ai) {
            if (x != i) {
                for (int j = 0; j <= 32; j++) {
                    if ((x.getAxisX() + j == i.getAxisX() + i.getSpeedX()
                            || x.getAxisY() + j == i.getAxisY() + i.getSpeedY())
                            && !i.getClass().getSimpleName().equals("Bullet")) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
