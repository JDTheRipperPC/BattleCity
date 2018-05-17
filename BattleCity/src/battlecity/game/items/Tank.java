package battlecity.game.items;

import battlecity.game.Item;
import battlecity.socket.ClientSocket;
import java.awt.image.BufferedImage;

/**
 *
 * @author xGod
 */
public class Tank extends Item {

    private ClientSocket cs;

    public Tank(int axisX, int axisY, int life, float speed, BufferedImage imagenPath, ClientSocket cs) {
        super(axisX, axisY, life, speed, imagenPath);
        this.cs = cs;
    }

    
   
    @Override
    public void run() {
        
    }

}
