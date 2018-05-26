package battlecity.game;

import battlecity.custominterface.ItemInterface;
import battlecity.gui.Viewer;
import battlecity.socket.ClientSocket;
import java.awt.image.BufferedImage;

/**
 *
 * @author xGod
 */
public abstract class Item implements Runnable, ItemInterface {

    private int axisX;
    private int axisY;
    private int newX;
    private int newY;
    private int life;
    private float speedX;
    private float speedY;
    private volatile long lastUpdateTime;

    private Orientation orientation;
    private Viewer vw;
    private BufferedImage imagenPath;

    public Item(BufferedImage imagenPath) {
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
    public synchronized int getAxisX() {
        return axisX;
    }

    public synchronized int getAxisY() {
        return axisY;
    }

    public synchronized int getNewX() {
        return this.newX;
    }

    public synchronized int getNewY() {
        return this.newY;
    }

    public synchronized int getLife() {
        return life;
    }

    public synchronized float getSpeedX() {
        return speedX;
    }

    public synchronized float getSpeedY() {
        return speedY;
    }

    public synchronized BufferedImage getImagenPath() {
        return imagenPath;
    }

    public synchronized Orientation getOrientation() {
        return this.orientation;
    }

    public synchronized Viewer getViewer() {
        return this.vw;
    }
    public synchronized long getLastUpdateTime(){
        return this.lastUpdateTime;
    }

    public synchronized void setAxisX(int axisX) {
        this.axisX = axisX;
    }

    public synchronized void setAxisY(int axisY) {
        this.axisY = axisY;
    }

    public synchronized void setLife(int life) {
        this.life = life;
    }

    public synchronized void setSpeedX(float speed) {
        this.speedX = speed;
    }

    public synchronized void setSpeedY(float speed) {
        this.speedY = speed;
    }

    public synchronized void setImagenPath(BufferedImage imagenPath) {
        this.imagenPath = imagenPath;
    }

    public synchronized void setViewer(Viewer vw) {
        this.vw = vw;
    }

    public synchronized void setOrientation(Orientation o) {
        this.orientation = o;
    }
    
    public synchronized void setLastUpdateTime(long l){
        this.lastUpdateTime=l;
    }

    public synchronized void setNewX(int i){
        this.newX = i;
    }

    public synchronized void setNewY(int i){
        this.newY = i;
    }

    //---------------------- Publics -------------------------------------------    
}
