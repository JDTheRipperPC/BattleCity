package battlecity.gui;

import battlecity.game.Item;
import battlecity.game.Tile;
import java.util.ArrayList;

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

    public void load(){
        // TODO
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
}
