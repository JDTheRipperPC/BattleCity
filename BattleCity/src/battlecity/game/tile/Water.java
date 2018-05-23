package battlecity.game.tile;

import battlecity.game.Tile;
import battlecity.gui.Viewer;
import battlecity.util.BufferedImageLoader;
import java.awt.Graphics;

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

    @Override
    public void paint(Viewer v) {
        Graphics g = v.getGraphics();
        if (g == null) {
            return;
        }
        g.drawImage(super.getBi(),
                super.getCoordinateX(),
                super.getCoordinateY(),
                null);
    }

}
