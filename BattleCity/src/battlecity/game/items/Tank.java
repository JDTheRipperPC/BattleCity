package battlecity.game.items;

import battlecity.game.Item;
import battlecity.gui.Viewer;
import battlecity.socket.ClientSocket;
import battlecity.util.BufferedImageLoader;
import battlecity.util.BufferedImageUtil;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author xGod
 */
public class Tank extends Item {

    private ClientSocket cs;
    private float deceleration;
    private float aceleration;
    private int maxspeed;
    private String[] bulletPath = new String[]{"bala_amarilla", "bala_milka", "bala_roja", "bala_verde"};

    public Tank(BufferedImage imagenPath, ClientSocket cs) {
        super(imagenPath);
        aceleration = 1f;
        deceleration = 0.5f;
        maxspeed = 2; //times that will repaint at speed of 1 =/
        this.cs = cs;
    }

    // overrides --------------
    @Override
    public void run() {
        super.setLastUpdateTime(System.nanoTime());
        while (true) {
            try {
                Thread.sleep(1000 / 120);
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

        Bullet b = new Bullet(bllet, super.getOrientation(), super.getViewer(), super.getId());
        b.setImagenPath(BufferedImageLoader.getInstance().getBufferMap().get(bulletPath[super.getId()]));
        b.setAxisX(super.getAxisX() + super.getOrientation().getAxisX());
        b.setNewX(super.getAxisX() + super.getOrientation().getAxisX());
        b.setAxisY(super.getAxisY() + super.getOrientation().getAxisY());
        b.setNewY(super.getAxisY() + super.getOrientation().getAxisY());
        b.setOrientation(super.getOrientation());

        this.getViewer().getSc().getBullets().add(b);
        new Thread(b).start();

    }

    //------------------------------------------------------------------------->
    @Override
    public void evaluate(Viewer.AllowedAction a) {
        switch (a) {
            case EXPLODE:
                this.explode();
                break;

            case MOVE:
                this.move();
                break;

            case TAKEDMG:
                super.setLife(super.getLife() - 1);
                break;
            case BLOCKED:
                this.colide();
                break;
        }
    }

    //---------------------------- Interface ---------------------------------->
    @Override
    public void move() {

        float elapsedSeconds;
        long elapsedNanos;
        long now;

        now = System.nanoTime();
        elapsedNanos = now - super.getLastUpdateTime();
        super.setLastUpdateTime(now);
        elapsedSeconds = ((float) (elapsedNanos)) / 1000000000.0f;
        if (elapsedSeconds < 1) {
            elapsedSeconds = 1;
        }
        super.setNewX(super.getAxisX() + (int) (elapsedSeconds * super.getSpeedX()));
        super.setNewY(super.getAxisY() + (int) (elapsedSeconds * super.getSpeedY()));
        if (super.getViewer().itemCanDo(this).equals(Viewer.AllowedAction.MOVE)) {

            this.updatePosition(elapsedSeconds);
        }
    }

    @Override
    public synchronized void explode() {
        super.setLife(super.getLife() - 1);
        System.out.println("I got shot nigga =/");
        System.out.println(super.getLife());
    }

    @Override
    public void colide() {
        super.setSpeedX(0);
        super.setSpeedY(0);
        super.setNewX(super.getAxisX());
        super.setNewY(super.getAxisY());
    }

    @Override
    public synchronized void takeDmg() {

        super.setLife(super.getLife() - 1);
    }

    private synchronized void updatePosition(float elapsedSeconds) {

        super.setAxisX(super.getNewX());
        super.setAxisY(super.getNewY());

        if (Math.abs(this.aceleration) >= Math.abs(this.maxspeed)) {
            if (super.getSpeedX() > 0) {
                this.setSpeedX(super.getSpeedX() - this.deceleration);
            }
            if (super.getSpeedY() > 0) {
                this.setSpeedY(super.getSpeedY() - this.deceleration);
            }
            if (super.getSpeedX() < 0) {
                this.setSpeedX(super.getSpeedX() + this.deceleration);
            }
            if (super.getSpeedY() < 0) {
                this.setSpeedY(super.getSpeedY() + this.deceleration);
            }
        } else {
            if (super.getSpeedX() > 0) {
                super.setSpeedX(this.aceleration += super.getOrientation().getAxisX());

            }
            if (super.getSpeedX() < 0) {
                super.setSpeedX(this.aceleration += super.getOrientation().getAxisX());

            }
            if (super.getSpeedY() < 0) {
                super.setSpeedY(this.aceleration += super.getOrientation().getAxisY());

            }
            if (super.getSpeedY() > 0) {
                super.setSpeedY(this.aceleration += super.getOrientation().getAxisY());

            }

        }

    }

    public void goUp() {
        if (super.getOrientation() != Orientation.NORTH) {
            int oldDegrees = super.getOrientation().getDegrees();
            super.setOrientation(Orientation.NORTH);
            super.setImagenPath(BufferedImageUtil.rotate(super.getImagenPath(), oldDegrees - super.getOrientation().getDegrees()));
        } else {
            this.aceleration = super.getOrientation().getAxisY();
            super.setSpeedY(this.aceleration);
        }
    }

    public void goDown() {
        if (super.getOrientation() != Orientation.SOUTH) {
            int oldDegrees = super.getOrientation().getDegrees();
            super.setOrientation(Orientation.SOUTH);
            super.setImagenPath(BufferedImageUtil.rotate(super.getImagenPath(), oldDegrees - super.getOrientation().getDegrees()));
        } else {
            this.aceleration = super.getOrientation().getAxisY();
            super.setSpeedY(this.aceleration);
        }
    }

    public void goRight() {
        if (super.getOrientation() != Orientation.WEST) {
            int oldDegrees = super.getOrientation().getDegrees();
            super.setOrientation(Orientation.WEST);
            super.setImagenPath(BufferedImageUtil.rotate(super.getImagenPath(), oldDegrees - super.getOrientation().getDegrees()));
        } else {
            this.aceleration = super.getOrientation().getAxisX();
            super.setSpeedX(this.aceleration);
        }
    }

    public void goLeft() {

        if (super.getOrientation() != Orientation.EAST) {
            int oldDegrees = super.getOrientation().getDegrees();
            super.setOrientation(Orientation.EAST);
            super.setImagenPath(BufferedImageUtil.rotate(super.getImagenPath(), oldDegrees - super.getOrientation().getDegrees()));
        } else {
            this.aceleration = super.getOrientation().getAxisX();
            super.setSpeedX(this.aceleration);
        }
        System.out.println(this);
    }

    public ClientSocket getClientSocket() {
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

    @Override
    public void paint(Graphics g) {
        g.drawImage(getImagenPath(), super.getAxisX(), super.getAxisY(), null);
    }

    @Override
    public String toString() {
        return " Coordenadas: " + super.getAxisX() + ", " + super.getAxisY() + "\n Nuevas C " + super.getNewX() + ", " + super.getNewY() + "\n Orientacion" + super.getOrientation();
    }

    public int getTank_number() {
        return super.getId();
    }

    public void setTank_number(int tank_number) {
        super.setId(tank_number);
    }

}
