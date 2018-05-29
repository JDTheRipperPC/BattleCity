package battlecity.socket;

import battlecity.game.Item;
import battlecity.game.items.Tank;
import battlecity.gui.Viewer;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pacho
 */
public class ClientSocket extends Thread {

    private Tank tank;

    private Socket client;

    private BufferedReader br;
    private BufferedWriter bw;

    private Viewer viewer;

    public ClientSocket(Socket client, Viewer v) throws IOException {
        this.client = client;
        br = new BufferedReader(new InputStreamReader(client.getInputStream()));
        bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
        bw.write("Hola papa");
        bw.newLine();
        bw.flush();
        this.viewer = v;
        System.out.println("Nuevo ClientSocket");
        this.tank = new Tank(null, this);
        this.viewer.getSc().getItems().add(tank);

    }

    public Tank getTank() {
        return tank;
    }

    public void setTank(Tank tank) {
        this.tank = tank;
    }

    public Socket getClient() {
        return client;
    }

    public void setClient(Socket client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Life: " + tank.getLife();
    }

    @Override
    public void run() {
        String msg;
        System.out.println(this);
        while (tank.getLife() > 0) {
            try {
                msg = br.readLine();
                evaluateMessage(msg);
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }

    private void evaluateMessage(String msg) {
        switch (msg) {
            case "up":
                tank.goUp();
                break;
            case "down":
                tank.goDown();
                break;
            case "left":
                tank.goLeft();
                break;
            case "right":
                tank.goRight();
                break;
            case "shoot":
                this.tank.shoot();
                break;
        }
    }

    public void sendMessage(String msg) {
        try {
            bw.write(msg);
            bw.newLine();
            bw.flush();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void youLose() {
       this.sendMessage("lose");
    }

    public void youWin() {
        this.sendMessage("win");
    }

    public void youTakeDmg() {
        this.sendMessage("dmg");
    }
    
    private void goUp(){
        
    }
    
    private void goDown(){
        
    }
    
    private void goLeft(){
        
    }
    private void goRight(){
        
    }
    

}
