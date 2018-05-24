package battlecity.gui;

import battlecity.socket.ClientSocket;
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
    private boolean game;

    public static enum AllowedAction {
        MOVE, TAKEDMG, SLOWDOWN, EXPLODE, BLOCKED;
    }

    private ArrayList<ClientSocket> clients;

    public Viewer(Dimension dim) {

        this.game = false;
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

    private void initObjects() {
        sc = new Scene();
        clients = new ArrayList<>();
    }

    public ArrayList<ClientSocket> getClients() {
        return clients;
    }

    public boolean isGame() {
        return this.game;
    }

    public void setGame(boolean game) {
        this.game = game;
    }

    public void setClients(ArrayList<ClientSocket> clients) {
        this.clients = clients;
    }

    public Scene getSc() {
        return sc;
    }

    public void setSc(Scene sc) {
        this.sc = sc;
    }

    @Override
    public void run() {
        this.game = true;
        while (game) {

        }

    }

    public AllowedAction itemCanDo(Item i) {
        if (checkCollision(i)) {
            return AllowedAction.BLOCKED;
        }

        return AllowedAction.MOVE;

    }

    public boolean checkSlow(Item i) {
        i.getAxisX();
        i.getAxisY();
        return true;
    }

    //---------------------------NOTIFICATIONS--------------------------------->

    public void youLose(Tank t){
        t.youLose();
    }

    public void youWin(Tank t){
        t.youWin();
    }

    public void youTakeDmg(Tank t){
        t.youTakeDmg();
    }

    //---------------------------COLLISIONS------------------------------------>
    public boolean checkCollision(Item i) {

        //following if's check if collides with walls
        if (i.getNewX() > this.dim.width) {
            return true;
        }
        if (i.getNewY() > this.dim.height) {
            return true;
        }
        //following if's check if collides with items or tiles
        if (this.checkCollisionWithItems(i, this.sc.getItems())) {
            return true;
        }
        if (this.checkCollisionWithTiles(i, this.sc.getTiles())) {
            return true;
        }
        return false;
    }

    private boolean checkCollisionWithTiles(Item i, ArrayList<Tile> t) {
        for (Tile x : t) {
            for (int j = 0; j <= 32; j++) {
                if ((x.getCoordinateX() + j == i.getNewX()
                        || x.getCoordinateY() + j == i.getNewY())
                        && !x.getClass().getSimpleName().equals("Grass")
                        || !x.getClass().getSimpleName().equals("Water")) {

                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkCollisionWithItems(Item i, ArrayList<Item> allItems) {
        for (Item x : allItems) {
            if (x != i && !x.getClass().getSimpleName().equals("Bullet")) {
                for (int j = 0; j <= 32; j++) {
                    if ((x.getAxisX() + j == i.getNewX()
                            || x.getAxisY() + j == i.getNewY())) {
                        x.takeDmg();
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
