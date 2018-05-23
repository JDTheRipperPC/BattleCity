package battlecity.game.tile;

import battlecity.game.Tile;
import battlecity.gui.Viewer;
import battlecity.util.BufferedImageLoader;
import java.awt.Graphics;
import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

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

    @Override
    public void paint(Viewer v) {
        Graphics g = v.getGraphics();
        if (g == null) {
            System.err.println("Graphics are null");
            return;
        }
        g.drawImage(super.getBi(),
                super.getCoordinateX(),
                super.getCoordinateY(),
                null);
    }

}
