package battlecity.socket;

import battlecity.gui.Viewer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import jdk.nashorn.internal.objects.NativeRegExp;

/**
 *
 * @author xGod
 */
public class GameServerSocket extends Thread{
    
    private int port;
    private Viewer viewer;
    
    private ServerSocket ss;
    private Socket client;
    
    private BufferedReader in;

    public GameServerSocket(int port, Viewer viewer) {
        this.port = port;
        this.viewer = viewer;
    }

    @Override
    public void run() {
        try {
            ss = new java.net.ServerSocket(port);
            System.out.println("GameServerSocker created");
            while(true){ // FIXME: change loop condition.
                System.out.println("waiting for clients...");
                client = ss.accept();
                System.out.println("client accepted!");
                in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                if (in.readLine().equals("$Player")){
                    System.out.println("msg ok");
                    viewer.getClients().add(new ClientSocket(client));
                }else{
                    System.err.println("Message not '$Player'");
                }
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
}