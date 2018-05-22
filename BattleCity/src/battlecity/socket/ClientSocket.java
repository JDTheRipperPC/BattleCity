package battlecity.socket;

import battlecity.game.items.Tank;
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

    public ClientSocket(Socket client) throws IOException {
        this.client = client;
        br = new BufferedReader(new InputStreamReader(client.getInputStream()));
        bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
        bw.write("Hola papa");
        bw.newLine();
        bw.flush();
        System.out.println("Nuevo ClientSocket");
    }

    @Override
    public void run() {
        String msg;
        while (tank.getLife() > 0) { // FIXME: add condition GAME RUNNING
            try {
                msg = br.readLine();
                evaluateMessage(msg);
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }
    
    private void evaluateMessage(String msg){
        switch(msg){
            case "up":
                break;
            case "down":
                break;
            case "left":
                break;
            case "right":
                break;
            case "shot":
                break;
        }
    }
    
    public void sendMessage(String msg){
        try {
            bw.write(msg);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

}
