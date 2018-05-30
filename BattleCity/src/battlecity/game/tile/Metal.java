package battlecity.game.tile;

import battlecity.game.Tile;
import battlecity.util.BufferedImageLoader;

/**
 *
 * @author xGod
 */
public class Metal extends Tile {

    public Metal() {
        super.setBi(BufferedImageLoader
                .getInstance()
                .getBufferMap()
                .get("tile_metal")
        );
    }

}
