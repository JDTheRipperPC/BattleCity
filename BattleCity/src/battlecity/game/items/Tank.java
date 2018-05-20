package battlecity.game.items;

import battlecity.custominterface.ItemInterface;
import battlecity.game.Item;
import battlecity.gui.Viewer;
import battlecity.socket.ClientSocket;
import java.awt.image.BufferedImage;

/**
 *
 * @author xGod
 */
public class Tank extends Item implements ItemInterface{

    private ClientSocket cs;

    public Tank(int axisX, int axisY, int life, BufferedImage imagenPath, Orientation o, ClientSocket cs) {
        super(axisX, axisY, life, imagenPath, o);
        this.cs = cs;
    }
    
    // overrides --------------
    @Override
    public void run() {

    }

    @Override
    public synchronized void setSpeedX(float speedX) {
        super.setSpeedY(0);
        super.setSpeedX(speedX);
    }

    @Override
    public synchronized void setSpeedY(float speedY) {
        super.setSpeedX(0);
        super.setSpeedY(speedY);
    }

    // this class methods :
    
    /**
     * Shooting generates bullets.
     * Not sync because it will only be accessible by one thread
     */
    public void shoot(){
        BufferedImage bllet = null;
        Bullet b = new Bullet(this.getAxisX(), this.getAxisY(), 1, bllet, super.getOrientation(), this.getViewer());
        new Thread(b).start();
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
                //reduce speed 
                if(super.getSpeedX()>0){
                    
                    float f = (float) (super.getSpeedX()*Math.random() * (0.75 - 0.25));
                    this.setSpeedX(f);
                }else{
                    
                }
                break;
            case TAKEDMG:
                super.setLife(super.getLife()-1);
                break;
        }
    }
    
    //---------------------------- Interface ---------------------------------->
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
