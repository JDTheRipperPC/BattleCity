package battlecity.game;

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
    private float speed;
    
    private BufferedImage imagenPath;

    public Item(int axisX, int axisY, int life, float speed, BufferedImage imagenPath) {
        this.axisX = axisX;
        this.axisY = axisY;
        this.life = life;
        this.speed = speed;
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

    public float getSpeed() {
        return speed;
    }

    public BufferedImage getImagenPath() {
        return imagenPath;
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

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void setImagenPath(BufferedImage imagenPath) {
        this.imagenPath = imagenPath;
    }

    
    //---------------------- Publics -------------------------------------------    


    

}
