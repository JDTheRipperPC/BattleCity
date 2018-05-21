package battlecity.gui;

import battlecity.socket.ClientSocket;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

/**
 *
 * @author xGod
 */
public class Viewer extends Canvas implements Runnable{

     private Dimension dim;
     private Scene sc;

     private ArrayList<ClientSocket> clients;
     
    public Viewer(Dimension dim) {
        this.dim = dim;
        initProperties();
        initObjects();
    }
    
    private void initProperties(){
        setSize(dim);
        setMinimumSize(dim);
        setMaximumSize(dim);
        setPreferredSize(dim);
        setBackground(Color.black);
    }
    
    private void initObjects(){
        sc = new Scene();
        clients = new ArrayList<>();
    }

    public ArrayList<ClientSocket> getClients() {
        return clients;
    }

    public void setClients(ArrayList<ClientSocket> clients) {
        this.clients = clients;
    }

    public Scene getSc() {
        return sc;
    }

    public void setSc(Scene sc) {
        this.sc = sc;
    }
    
    @Override
    public void run() {
        
    }
    
    
    
}
