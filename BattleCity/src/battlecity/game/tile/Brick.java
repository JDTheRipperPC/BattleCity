package battlecity.game.tile;

import battlecity.game.Tile;
import battlecity.util.BufferedImageLoader;

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
