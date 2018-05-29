package battlecity.game;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author xGod
 */
public abstract class Tile {

    private BufferedImage bi;
    private int coordinateX, coordinateY;

    public BufferedImage getBi() {
        return bi;
    }

    public void setBi(BufferedImage bi) {
        this.bi = bi;
    }

    public int getCoordinateX() {
        return coordinateX;
    }

    public void setCoordinateX(int coordinateX) {
        this.coordinateX = coordinateX;
    }

    public int getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateY(int coordinateY) {
        this.coordinateY = coordinateY;
    }

    public void paint(Graphics g) {
        g.drawImage(bi, coordinateX, coordinateY, null);
    }

    @Override
    public String toString() {
        return "" + this.coordinateX/32 + " " + this.coordinateY/32;
    }
   
}
