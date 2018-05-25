package battlecity.game.items;

import battlecity.game.Item;
import battlecity.gui.Viewer;
import battlecity.socket.ClientSocket;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author xGod
 */
public class Tank extends Item {

    private ClientSocket cs;

    public Tank(BufferedImage imagenPath, ClientSocket cs) {
        super(imagenPath);
        this.cs = cs;
    }

    // overrides --------------
    @Override
    public void run() {
        super.setLastUpdateTime(System.nanoTime());
        while (true) {
            try {
                Thread.sleep(1000 / 140);
            } catch (InterruptedException ex) {
            }
            this.evaluate(super.getViewer().itemCanDo(this));

        }

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
     * Shooting generates bullets. Not sync because it will only be accessible
     * by one thread
     */
    public void shoot() {
        BufferedImage bllet = null;
        //Bullet b = new Bullet(this.getAxisX(), this.getAxisY(), 1, bllet, super.getOrientation(), this.getViewer());
        //new Thread(b).start();
    }

    //------------------------------------------------------------------------->
    private void evaluate(Viewer.AllowedAction a) {
        switch (a) {
            case EXPLODE:
                this.explode();
                break;
            case MOVE:
                this.updateAll();
                break;
            case SLOWDOWN:
                //reduce speed 
                if (super.getSpeedX() > 0) {
                    float f = (float) (super.getSpeedX() * Math.random() * (0.75 - 0.25));
                    this.setSpeedX(f);
                } else {
                    float f = (float) (super.getSpeedY() * Math.random() * (0.75 - 0.25));
                    this.setSpeedY(f);
                }
                break;
            case TAKEDMG:
                super.setLife(super.getLife() - 1);
                break;
            case BLOCKED:
                super.setSpeedX(0);
                super.setSpeedY(0);
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

    @Override
    public void takeDmg() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void updateAll() {
        float elapsedSeconds;
        long elapsedNanos;
        long now;

        if (super.getLife() == 0) {
            return;
        }
        now = System.nanoTime();
        elapsedNanos = now - super.getLastUpdateTime();
        super.setLastUpdateTime(now);
        elapsedSeconds = ((float) (elapsedNanos)) / 1000000000.0f;

        super.setNewX(super.getAxisX() + (int) (elapsedSeconds * super.getSpeedX()));
        super.setNewY(super.getAxisY() + (int) (elapsedSeconds * super.getSpeedY()));

        this.updatePosition(elapsedSeconds);

    }

    private synchronized void updatePosition(float elapsedSeconds) {

        super.setAxisX(super.getNewX());
        super.setAxisY(super.getNewY());
        super.setSpeedX(0);
        super.setSpeedY(0);
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(getImagenPath(), getAxisX(), getAxisY(), null);
    }

}
