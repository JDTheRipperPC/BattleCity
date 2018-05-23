package battlecity.gui;

import battlecity.game.Item;
import battlecity.game.Tile;
import battlecity.game.tile.Metal;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

    private ArrayList<Item> items;
    private ArrayList<Tile> tiles;

    public Scene() {
        items = new ArrayList<>();
        tiles = new ArrayList<>();
    }

    public void load() {
        try (Reader reader = new InputStreamReader(new FileInputStream("res/scene/map1.json"))) {
            Gson gson = new GsonBuilder().create();
            JsonScene sc = gson.fromJson(reader, JsonScene.class);
            System.out.println(sc);
            for (String[] row : sc.tiles) {
                for (String tile : row) {
                    System.out.print(tile + "");
                    switch (tile) {
                        case "B":
                            break;
                        case "G":
                            break;
                        case "M":
                            break;
                        case "W":
                            break;
                    }
                }
                System.out.println();
            }
        } catch (IOException ex) {
            Logger.getLogger(Scene.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public ArrayList<Tile> getTiles() {
        return tiles;
    }

    public void setTiles(ArrayList<Tile> tiles) {
        this.tiles = tiles;
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
