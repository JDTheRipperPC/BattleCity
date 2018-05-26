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

    public Bullet(BufferedImage imagenPath, Orientation o, Viewer vw) {
        super(imagenPath);
        super.setViewer(vw);

        this.x = o.getAxisX();
        this.y = o.getAxisY();

        this.setSpeedX(this.x);
        this.setSpeedY(this.y);

    }

    @Override
    public void run() {
        super.setLastUpdateTime(System.nanoTime());
        while (super.getLife() == 0 && super.getViewer().isGame()) {
            try {
                Thread.sleep(1000 / 140);
            } catch (InterruptedException ex) {

            }
            this.evaluate(super.getViewer().itemCanDo(this));

        }
    }

    //-------------------- Custom Speed --------------------------------------->
    @Override
    public synchronized void setSpeedX(float x) {
        super.setSpeedX(x * 2);
    }

    @Override
    public synchronized void setSpeedY(float y) {
        super.setSpeedY(y * 2);
    }

    //------------------------------------------------------------------------->
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
        new Thread(() -> {
            Media m = new Media(new File("res/audio/explosion.wav").toURI().toString());
            MediaPlayer mp = new MediaPlayer(m);
            mp.play();
        }).start();
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
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(getImagenPath(), getAxisX(), getAxisY(), null);
    }

}
