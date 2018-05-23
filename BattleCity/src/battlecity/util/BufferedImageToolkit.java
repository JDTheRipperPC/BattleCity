package battlecity.util;

import battlecity.game.Item;
import battlecity.game.Tile;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;

/**
 *
 * @author xGod
 */
public class BufferedImageToolkit {

    public static BufferedImage paintBuffer(int x, int y, Item[] items, Tile[] tiles) {
        BufferedImage bi2 = new BufferedImage(x, y, BufferedImage.TYPE_4BYTE_ABGR);
        byte[] dataRaster = new byte[x * y * 4];
        // TODO estrategia de solapamiento de imágenes mediante un único BufferedImage
        return bi2;
    }

}
