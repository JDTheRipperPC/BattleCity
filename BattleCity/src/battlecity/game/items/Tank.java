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
public class Tank extends Item {

    private ClientSocket cs;
    private int maxSpeed;
    private int acceleration;
    private int deceleration;
    private int repainting;

    public Tank(BufferedImage imagenPath, ClientSocket cs) {
        super(imagenPath);
        maxSpeed = 3;
        acceleration = 1;
        deceleration = 1;
        repainting = 0;
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
    public synchronized void setSpeedX(int speedX) {
        super.setSpeedY(0);
        super.setSpeedX(speedX);
    }

    @Override
    public synchronized void setSpeedY(int speedY) {
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
                this.move();
                break;
            case TAKEDMG:
                super.setLife(super.getLife() - 1);
                if (super.getLife() == 0) {
                    this.explode();
                }
                break;
            case BLOCKED:
                this.colide();
                break;
        }
    }

    //---------------------------- Interface ---------------------------------->
    @Override
    public void move() {
        // TODO : add aceleration
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

    @Override
    public void explode() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void colide() {
        super.setSpeedX(0);
        super.setSpeedY(0);
    }

    @Override
    public synchronized void takeDmg() {

        super.setLife(super.getLife() - 1);
    }

    private synchronized void updatePosition(float elapsedSeconds) {

        super.setAxisX(super.getNewX());
        super.setAxisY(super.getNewY());
        this.repainting++;
        if (this.repainting > 2) {
            super.setSpeedX(super.getSpeedX() - this.deceleration);
            super.setSpeedY(super.getSpeedY() - this.deceleration);
        }
        if (super.getSpeedX() == 0 && super.getSpeedY() == 0) {
            this.repainting = 0;
        }

    }
    
    
    public ClientSocket getClientSocket(){
        return this.cs;
    }

    //---------------------------NOTIFICATIONS--------------------------------->
    public void youLose() {
        this.cs.youLose();
    }

    public void youWin() {
        this.cs.youWin();
    }

    public void youTakeDmg() {
        this.cs.youTakeDmg();
    }

}
