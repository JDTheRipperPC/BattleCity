package battlecity.game.items;

import battlecity.game.Item;
import battlecity.gui.Viewer;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author xGod
 */
public class Bullet extends Item {

    private int x, y;
    private int aceleration;
    private int maxspeed;

    public Bullet(BufferedImage imagenPath, Orientation o, Viewer vw) {
        super(imagenPath);
        super.setViewer(vw);
        super.setLife(1);

        this.x = o.getAxisX();
        this.y = o.getAxisY();

        this.aceleration = 5;
        this.maxspeed = 5;

        this.setSpeedX(this.x*4);
        this.setSpeedY(this.y*4);

    }

    @Override
    public void run() {
        super.setLastUpdateTime(System.nanoTime());
        while (super.getLife() > 0) {
            try {
                Thread.sleep(1000 / 60);
            } catch (InterruptedException ex) {

            }
            this.evaluate(super.getViewer().itemCanDo(this));

        }
    }

    //-------------------- Custom Speed --------------------------------------->
    @Override
    public synchronized void setSpeedX(int x) {
        super.setSpeedX(x);
    }

    @Override
    public synchronized void setSpeedY(int y) {
        super.setSpeedY(y);
    }

    //------------------------------------------------------------------------->
    @Override
    public void evaluate(Viewer.AllowedAction a) {
        switch (a) {
            case MOVE:
                this.move();
                break;
            default:
                this.explode();
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
    public void explode() {
        new Thread(() -> {
            Media m = new Media(new File("res/audio/explosion.wav").toURI().toString());
            MediaPlayer mp = new MediaPlayer(m);
            mp.play();
        }).start();
        this.getViewer().getSc().getItems().remove(this);
    }

    @Override
    public void colide() {
        super.setLife(0);
    }

    @Override
    public void takeDmg() {
        super.setLife(0);
    }

    private synchronized void updatePosition(float elapsedSeconds) {
        super.setAxisX(super.getNewX());
        super.setAxisY(super.getNewY());

        if (Math.abs(this.aceleration) == Math.abs(this.maxspeed)) {
            if (super.getSpeedX() > 0) {
                this.setSpeedX(super.getSpeedX());
            }
            if (super.getSpeedY() > 0) {
                this.setSpeedY(super.getSpeedY());
            }
            if (super.getSpeedX() < 0) {
                this.setSpeedX(super.getSpeedX());
            }
            if (super.getSpeedY() < 0) {
                this.setSpeedY(super.getSpeedY());
            }
        } else {
            if (super.getSpeedX() > 0) {
                this.aceleration += super.getOrientation().getAxisX();

            }
            if (super.getSpeedX() < 0) {
                this.aceleration += super.getOrientation().getAxisX();

            }
            if (super.getSpeedY() < 0) {
                this.aceleration += super.getOrientation().getAxisY();

            }
            if (super.getSpeedY() > 0) {
                this.aceleration += super.getOrientation().getAxisY();

            }

        }
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(getImagenPath(), getAxisX(), getAxisY(), null);
    }

}
