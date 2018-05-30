package battlecity.game.tile;

import battlecity.game.Tile;
import battlecity.util.BufferedImageLoader;

/**
 *
 * @author xGod
 */
public class Grass extends Tile {

    public Grass() {
        super.setBi(BufferedImageLoader
                .getInstance()
                .getBufferMap()
                .get("tile_grass")
        );
    }
}
