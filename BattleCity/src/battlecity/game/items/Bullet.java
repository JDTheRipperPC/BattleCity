package battlecity.game.items;

import battlecity.custominterface.ItemInterface;
import battlecity.game.Item;
import battlecity.gui.Viewer;
import java.awt.image.BufferedImage;

/**
 *
 * @author xGod
 */
public class Bullet extends Item  implements ItemInterface{

    public Bullet(int axisX, int axisY, int life, BufferedImage imagenPath, Orientation o, Viewer vw) {
        super(axisX, axisY, life, imagenPath, o);
        
        super.setViewer(vw);

        float x = o.getAxisX();
        float y = o.getAxisY();

        this.setSpeedX(x);
        this.setSpeedY(y);

    }
    
    
    //-------------------- Custom Speed --------------------------------------->

    @Override
    public synchronized void setSpeedX(float x) {
        super.setSpeedX(x * 4);
    }

    @Override
    public synchronized void setSpeedY(float y) {
        super.setSpeedY(y * 4);
    }
    
    
    //------------------------------------------------------------------------->
    
    public void evaluate(Viewer.AllowedAction a ){
        switch(a){
            case EXPLODE:
                this.explode();
                break;
            case MOVE:
                this.move();
                break;
            case SLOWDOWN:
                //nothing for bullet
                this.move();
                break;
            case TAKEDMG:
                super.setLife(super.getLife()-1);
                break;
        }
    }
    
    
    //---------------------------- Interface ---------------------------------->

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void move() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void explode() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void doNoise() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void colide() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
