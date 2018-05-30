package battlecity.custominterface;

import java.awt.Graphics;

/**
 *
 * @author Pacho
 */
public interface ItemInterface {

    public void move();
    public void explode();  
    public void colide();
    public void takeDmg();
    public void paint(Graphics g);

}
