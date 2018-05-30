package battlecity.util;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 *
 * @author xGod
 */
public class BufferedImageUtil {

    public static BufferedImage rotate(BufferedImage oldBuffer, int degrees) {
        BufferedImage newBuffer = new BufferedImage(oldBuffer.getWidth(), oldBuffer.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = newBuffer.createGraphics();
        g2d.rotate(Math.toRadians(degrees), oldBuffer.getWidth() / 2, oldBuffer.getHeight() / 2);
        g2d.drawImage(oldBuffer, 0, 0, null);
        g2d.dispose();
        return newBuffer;
    }

    public static BufferedImage resize(BufferedImage img, int newW, int newH) {
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, img.getType());
        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return dimg;
    }

}
