package battlecity.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

/**
 *
 * @author xGod
 */
public class Statistics extends JPanel implements Runnable {

    private Viewer viewer;
    private JLabel[] tanksInfo;
    private SpringLayout sl;

    public Statistics(int width, int height, Viewer viewer) {
        this.viewer = viewer;
        GridLayout layout = new GridLayout(0, 1);
        setLayout(layout);
        Dimension dim = new Dimension(width, height);
        setBackground(Color.black);
        setSize(dim);
        setMinimumSize(dim);
        setMaximumSize(dim);
        setPreferredSize(dim);
        sl = new SpringLayout();
        setLayout(sl);
        tanksInfo = new JLabel[4];
        tanksInfo[0] = new JLabel();
        tanksInfo[0].setHorizontalAlignment(SwingConstants.CENTER);
        tanksInfo[0].setVerticalAlignment(SwingConstants.CENTER);
        tanksInfo[0].setSize(224, 200);
        tanksInfo[1] = new JLabel();
        tanksInfo[1].setHorizontalAlignment(SwingConstants.CENTER);
        tanksInfo[1].setVerticalAlignment(SwingConstants.CENTER);
        tanksInfo[1].setSize(224, 200);
        tanksInfo[2] = new JLabel();
        tanksInfo[2].setHorizontalAlignment(SwingConstants.CENTER);
        tanksInfo[2].setVerticalAlignment(SwingConstants.CENTER);
        tanksInfo[2].setSize(224, 200);
        tanksInfo[3] = new JLabel();
        tanksInfo[3].setHorizontalAlignment(SwingConstants.CENTER);
        tanksInfo[3].setVerticalAlignment(SwingConstants.CENTER);
        tanksInfo[3].setSize(224, 200);
        sl.putConstraint(SpringLayout.NORTH, tanksInfo[0], 50, SpringLayout.NORTH, this);
        sl.putConstraint(SpringLayout.EAST, tanksInfo[0], -112, SpringLayout.EAST, this);
        sl.putConstraint(SpringLayout.NORTH, tanksInfo[1], 200, SpringLayout.NORTH, this);
        sl.putConstraint(SpringLayout.EAST, tanksInfo[1], -112, SpringLayout.EAST, this);
        sl.putConstraint(SpringLayout.NORTH, tanksInfo[2], 350, SpringLayout.NORTH, this);
        sl.putConstraint(SpringLayout.EAST, tanksInfo[2], -112, SpringLayout.EAST, this);
        sl.putConstraint(SpringLayout.NORTH, tanksInfo[3], 500, SpringLayout.NORTH, this);
        sl.putConstraint(SpringLayout.EAST, tanksInfo[3], -112, SpringLayout.EAST, this);
        add(tanksInfo[0], sl);
        add(tanksInfo[1], sl);
        add(tanksInfo[2], sl);
        add(tanksInfo[3], sl);
    }

    @Override
    public void run() {
        System.out.println("run stats");
        System.out.println(getWidth() + ", "+ getHeight());
        while (viewer.isGame()) {
            try {
                Thread.sleep(300);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            for (int i = tanksInfo.length; i > viewer.getClients().size(); i--) {
                tanksInfo[i-1].setVisible(false);
            }
            for (int i = 0; i < viewer.getClients().size(); i++) {
                tanksInfo[i].setVisible(true);
                tanksInfo[i].setIcon(new ImageIcon(viewer.getClients().get(i).getTank().getImagenPath()));
                tanksInfo[i].setText("Life: " + viewer.getClients().get(i).getTank().getLife());
                tanksInfo[i].setForeground(Color.red);
                tanksInfo[i].setBackground(Color.green);
            }
        }
    }

}
