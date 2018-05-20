package battlecity.game.tile;

import battlecity.game.Tile;
import battlecity.gui.Viewer;
import battlecity.util.BufferedImageLoader;
import java.awt.Graphics;

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
