package battlecity.socket;

import battlecity.gui.Viewer;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import jdk.nashorn.internal.objects.NativeRegExp;

/**
 *
 * @author xGod
 */
public class GameServerSocket extends Thread {

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
            while (true) { // FIXME: change loop condition.
                System.out.println("waiting for clients...");
                client = ss.accept();
                System.out.println("client accepted!");
                in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                if (in.readLine().equals("$Player") && viewer.getClients().size() < 4) {
                    System.out.println("msg ok");
                    ClientSocket cs = new ClientSocket(client, viewer);
                    viewer.getClients().add(cs);
                    cs.getTank().setLife(3);
                    cs.start();
                } else {
                    System.err.println("Message not '$Player'");
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
                    bw.write("msg Cant connect");
                    bw.newLine();
                    bw.flush();
                }
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

}
