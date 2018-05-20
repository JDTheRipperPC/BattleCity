package battlecity.gui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;

/**
 *
 * @author xGod
 */
public class Viewer extends Canvas implements Runnable{

     private Dimension dim;
     private Scene sc;

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
