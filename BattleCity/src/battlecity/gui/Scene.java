package battlecity.gui;

import battlecity.game.Item;
import battlecity.game.Tile;
import battlecity.game.items.Bullet;
import battlecity.game.items.Tank;
import battlecity.game.tile.Brick;
import battlecity.game.tile.Grass;
import battlecity.game.tile.Metal;
import battlecity.game.tile.Water;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.awt.Point;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author xGod
 */
public class Scene {

    private ArrayList<Bullet> bullets;
    private ArrayList<Tank> tanks;
    private ArrayList<Tile> tiles;

    private Point[] tankPoint;

    public Scene() {
        bullets = new ArrayList<>();
        tanks = new ArrayList<>();
        tiles = new ArrayList<>();
        tankPoint = new Point[4];
    }

    public void load() {
        try (Reader reader = new InputStreamReader(new FileInputStream("res/scene/map1.json"))) {
            Gson gson = new GsonBuilder().create();
            JsonScene sc = gson.fromJson(reader, JsonScene.class);
            System.out.println(sc);
            for (int x = 0; x < sc.size_width; x++) {
                for (int y = 0; y < sc.size_height; y++) {
                    System.out.print(sc.tiles[x][y] + "");
                    switch (sc.tiles[x][y]) {
                        case "B":
                            Brick b = new Brick();
                            b.setCoordinateX(x * 32);
                            b.setCoordinateY(y * 32);
                            tiles.add(b);
                            break;
                        case "G":
                            Grass g = new Grass();
                            g.setCoordinateX(x * 32);
                            g.setCoordinateY(y * 32);
                            tiles.add(g);
                            break;
                        case "M":
                            Metal m = new Metal();
                            m.setCoordinateX(x * 32);
                            m.setCoordinateY(y * 32);
                            tiles.add(m);
                            break;
                        case "W":
                            Water w = new Water();
                            w.setCoordinateX(x * 32);
                            w.setCoordinateY(y * 32);
                            tiles.add(w);
                            break;
                    }
                }
                System.out.println("");
            }
            tankPoint[0] = new Point(sc.t1x, sc.t1y);
            tankPoint[1] = new Point(sc.t2x, sc.t2y);
            tankPoint[2] = new Point(sc.t3x, sc.t3y);
            tankPoint[3] = new Point(sc.t4x, sc.t4y);
            System.out.println("Map loaded...");
        } catch (IOException ex) {
            Logger.getLogger(Scene.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public synchronized ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public void setBullets(ArrayList<Bullet> bullets) {
        this.bullets = bullets;
    }

    public synchronized ArrayList<Tank> getTanks() {
        return tanks;
    }

    public void setTanks(ArrayList<Tank> tanks) {
        this.tanks = tanks;
    }

    

    public synchronized ArrayList<Tile> getTiles() {
        return tiles;
    }

    public void setTiles(ArrayList<Tile> tiles) {
        this.tiles = tiles;
    }

    public Point[] getTankPoint() {
        return tankPoint;
    }

    class JsonScene {

        private int size_width;
        private int size_height;
        private String tiles[][];
        private int t1x, t1y, t2x, t2y, t3x, t3y, t4x, t4y;

        @Override
        public String toString() {
            return "Size: {" + size_width + ", " + size_height + "}\n"
                    + "Tank 1: {" + t1x + ", " + t1y + "}\n"
                    + "Tank 2: {" + t2x + ", " + t2y + "}\n"
                    + "Tank 3: {" + t3x + ", " + t3y + "}\n"
                    + "Tank 4: {" + t4x + ", " + t4y + "}";
        }

    }

}
