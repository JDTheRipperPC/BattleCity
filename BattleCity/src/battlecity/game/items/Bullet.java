package battlecity.game.items;

import battlecity.game.Item;
import java.awt.image.BufferedImage;

/**
 *
 * @author xGod
 */
public class Bullet extends Item{

    public Bullet(int axisX, int axisY, int life, BufferedImage imagenPath) {
        super(axisX, axisY, life, imagenPath);
    }
    
    

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
