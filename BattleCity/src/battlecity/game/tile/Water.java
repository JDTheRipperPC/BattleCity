package battlecity.game.tile;

import battlecity.game.Tile;
import battlecity.util.BufferedImageLoader;

/**
 *
 * @author xGod
 */
public class Water extends Tile {

    public Water() {
        super.setBi(BufferedImageLoader
                .getInstance()
                .getBufferMap()
                .get("tile_water")
        );
    }

}
