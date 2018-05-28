package battlecity.game.tile;

import battlecity.game.Tile;
import battlecity.gui.Viewer;
import battlecity.util.BufferedImageLoader;
import java.awt.Graphics;

/**
 *
 * @author xGod
 */
public class Brick extends Tile {

    private int life;

    public Brick() {
        super.setBi(BufferedImageLoader
                .getInstance()
                .getBufferMap()
                .get("tile_brick")
        );
        life = 3;
    }

    @Override
    public void paint(Graphics g) {

        if (g == null) {
            System.err.println("Graphics are null");
            return;
        }
        g.drawImage(super.getBi(),
                super.getCoordinateX(),
                super.getCoordinateY(),
                null);
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public void getDmg() {
        this.life--;
    }

}
