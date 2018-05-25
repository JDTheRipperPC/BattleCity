package battlecity.game.tile;

import battlecity.game.Tile;
import battlecity.util.BufferedImageLoader;

/**
 *
 * @author xGod
 */
public class Brick extends Tile {

    public Brick() {
        super.setBi(BufferedImageLoader
                .getInstance()
                .getBufferMap()
                .get("tile_brick")
        );
    }

}
