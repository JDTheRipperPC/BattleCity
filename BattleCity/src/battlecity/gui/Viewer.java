package battlecity.gui;

import battlecity.socket.ClientSocket;
import battlecity.game.Item;
import battlecity.game.Tile;
import battlecity.util.BufferedImageLoader;
import battlecity.game.items.Tank;
import battlecity.game.tile.Brick;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author xGod
 */
public class Viewer extends Canvas implements Runnable {

    private Dimension dim;
    private Scene sc;
    private boolean game;

    private String[] tankImgPath = new String[]{"tanque_amarillo", "tanque_milka", "tanque_rojo", "tanque_verde"};

    public static enum AllowedAction {
        MOVE, TAKEDMG, EXPLODE, BLOCKED;
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

    public synchronized ArrayList<ClientSocket> getClients() {
        return clients;
    }

    public synchronized void removeClient(ClientSocket c) {
        this.clients.remove(c);
        if (this.checkWinner()) {
            this.clients.get(0).youWin();
        }
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

    public String[] getTankImgPath() {
        return tankImgPath;
    }

    public void setTankImgPath(String[] tankImgPath) {
        this.tankImgPath = tankImgPath;
    }

    @Override
    public void run() {
        this.game = true;
        System.out.println("---");
        for (int i = 0; i < clients.size(); i++) {
            sc.getItems().get(i).setAxisX(sc.getTankPoint()[i].x * 32);
            sc.getItems().get(i).setAxisY(sc.getTankPoint()[i].y * 32);
            sc.getItems().get(i).setImagenPath(BufferedImageLoader.getInstance().getBufferMap().get(tankImgPath[i]));

            // setting tank properties
            sc.getItems().get(i).setViewer(this);
            sc.getItems().get(i).setNewX(sc.getTankPoint()[i].x * 32);
            sc.getItems().get(i).setNewY(sc.getTankPoint()[i].y * 32);
            sc.getItems().get(i).setOrientation(Item.Orientation.NORTH);
            //iniciamos su thread
            new Thread(sc.getItems().get(i)).start();
            System.out.println(sc.getItems().get(i));
        }
        createBufferStrategy(2);
        while (game && !checkWinner()) {
            paint();
            try {
                Thread.sleep(7);
            } catch (InterruptedException ex) {
            }
        }
        System.out.println("game end");
    }

    public synchronized void paint() {
        BufferStrategy bs;
        bs = getBufferStrategy();
        if (bs == null) {
            System.err.println("bs is null");
            return;
        }
        Graphics g = bs.getDrawGraphics();
        paintItems(g);
        paintTiles(g);
        bs.show();
        g.dispose();
    }

    private synchronized void paintItems(Graphics g) {
        if (sc.getItems() == null || sc.getItems().isEmpty()) {
            return;
        }
        sc.getItems().forEach((i) -> {
            i.paint(g);
        });
    }

    private synchronized void paintTiles(Graphics g) {
        if (sc.getTiles() == null || sc.getTiles().isEmpty()) {
            return;
        }
        sc.getTiles().forEach((t) -> {
            t.paint(g);
        });
    }

    @Override
    public void paintAll(Graphics g) {
        if (g == null) {
            System.err.println("Graphics null in paintAll");
            return;
        }
        BufferedImage bi = new BufferedImage(getSize().width, getSize().height, BufferedImage.TYPE_4BYTE_ABGR);
        for (Item i : sc.getItems()) {
            g.drawImage(i.getImagenPath(), i.getAxisX(), i.getAxisY(), null);
        }
        for (Tile t : sc.getTiles()) {
            if (t.getClass().getSimpleName().equals("Brick") || t.getClass().getSimpleName().equals("Grass")) {
                g.drawImage(t.getBi(), t.getCoordinateX(), t.getCoordinateY(), null);
            }
        }
    }

    public synchronized boolean checkWinner() {
        return this.clients.size() == 1;
    }

    public AllowedAction itemCanDo(Item i) {
        if (checkCollision(i)) {
            return AllowedAction.BLOCKED;
        }

        return AllowedAction.MOVE;

    }

    //---------------------------NOTIFICATIONS--------------------------------->
    public void youLose(Tank t) {
        t.youLose();
    }

    public void youWin(Tank t) {
        t.youWin();
    }

    public void youTakeDmg(Tank t) {
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
        if (this.checkCollisionWithItems(i)) {
            return true;
        }
        if (this.checkCollisionWithTiles(i)) {
            return true;
        }
        return false;
    }

    private boolean checkCollisionWithTiles(Item proxy_item) {

        for (Tile tile : this.sc.getTiles()) {

            for (int j = 0; j <= 32; j++) {
                if (proxy_item.getNewX() == tile.getCoordinateX() + j
                        && proxy_item.getNewY() == tile.getCoordinateY() + j) {
                    if(tile.getClass().getSimpleName().equals("Grass")){
                        return false;
                    }
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkCollisionWithItems(Item i) {
        for (Item proxy_item : this.sc.getItems()) {

        }
        return false;
    }

}
