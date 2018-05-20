package battlecity.game;

import battlecity.gui.Viewer;
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

    public abstract void paint(Viewer v);

}
