package battlecity.gui;

import battlecity.game.Item;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;

/**
 *
 * @author xGod
 */
public class Viewer extends Canvas implements Runnable {

    private Dimension dim;
    private Scene sc;

    public static enum AllowedAction {
        MOVE, TAKEDMG, SLOWDOWN, EXPLODE;
    }

    public Viewer(Dimension dim) {
        this.dim = dim;
        initProperties();
    }

    private void initProperties() {
        setSize(dim);
        setMinimumSize(dim);
        setMaximumSize(dim);
        setPreferredSize(dim);
        setBackground(Color.black);
    }

    @Override
    public void run() {

    }

    public AllowedAction itemCanDo(Item i) {
        
        

        return null;

    }

}
