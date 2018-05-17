package battlecity.game;

import battlecity.gui.Viewer;
import battlecity.socket.ClientSocket;
import java.awt.image.BufferedImage;

/**
 *
 * @author xGod
 */
public abstract class Item implements Runnable {

    private int axisX;
    private int axisY;
    private int life;
    private float speedX;
    private float speedY;

    private Viewer vw;
    private BufferedImage imagenPath;

    public Item(int axisX, int axisY, int life, BufferedImage imagenPath) {
        this.axisX = axisX;
        this.axisY = axisY;
        this.life = life;
        this.imagenPath = imagenPath;
    }

    /**
     * Orientation class to help us rotate objects and know their direction
     */
    public static enum Orientation {

        NORTH(0, -1, 0), SOUTH(0, 1, 180), WEST(-1, 0, 270), EAST(1, 0, 90);
        private int axisX;
        private int axisY;
        private int degrees;

        /**
         * Creates orientation.
         *
         *
         * @param axisX
         * @param axisY
         * @param degrees
         */
        private Orientation(int axisX, int axisY, int degrees) {
            this.axisX = axisX;
            this.axisY = axisY;
            this.degrees = degrees;
        }

        public int getAxisX() {
            return axisX;
        }

        public void setAxisX(int axisX) {
            this.axisX = axisX;
        }

        public int getAxisY() {
            return axisY;
        }

        public void setAxisY(int axisY) {
            this.axisY = axisY;
        }

        public int getDegrees() {
            return degrees;
        }

        public void setDegrees(int degrees) {
            this.degrees = degrees;
        }
    }

    //---------------------- GETTERS AND SETTERS -------------------------------
    public int getAxisX() {
        return axisX;
    }

    public int getAxisY() {
        return axisY;
    }

    public int getLife() {
        return life;
    }

    public float getSpeedX() {
        return speedX;
    }

    public float getSpeedY() {
        return speedY;
    }

    public BufferedImage getImagenPath() {
        return imagenPath;
    }

    public Viewer getViewer() {
        return this.vw;
    }

    public void setAxisX(int axisX) {
        this.axisX = axisX;
    }

    public void setAxisY(int axisY) {
        this.axisY = axisY;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public void setSpeedX(float speed) {
        this.speedX = speed;
    }

    public void setSpeedY(float speed) {
        this.speedY = speed;
    }

    public void setImagenPath(BufferedImage imagenPath) {
        this.imagenPath = imagenPath;
    }

    public void setViewer(Viewer vw) {
        this.vw = vw;
    }

    //---------------------- Publics -------------------------------------------    
}
