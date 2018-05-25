package battlecity;

import battlecity.gui.Viewer;
import battlecity.socket.GameServerSocket;
import battlecity.util.AudioPlayer;
import battlecity.util.BufferedImageLoader;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.JFXPanel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

/**
 *
 * @author xGod
 */
public class Main extends JFrame implements KeyListener {

    private Dimension dim;

    private SpringLayout sl;

    private JPanel mainPanel, menuPanel;
    private JLabel logoLabel, menuLabel;

    private Viewer viewer;

    private JLabel[] players;

    public Main() {
        initProperties();
        initComponents();
        initServer();
        initMenuRefresh();
    }

    private void initProperties() {
        setTitle("BattleCity");
        dim = new Dimension(800, 605);
        setSize(dim);
        setMinimumSize(dim);
        setMaximumSize(dim);
        setPreferredSize(dim);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        addKeyListener(this);
    }

    private void initComponents() {
        sl = new SpringLayout();
        initMainPanel();
        initMenuPanel();
        initViewer();
        initPlayers();
    }

    private void initMainPanel() {
        mainPanel = new JPanel(sl);
        mainPanel.setSize(dim);
        mainPanel.setMinimumSize(dim);
        mainPanel.setMaximumSize(dim);
        mainPanel.setPreferredSize(dim);
        mainPanel.setBackground(Color.darkGray);
        add(mainPanel);
        mainPanel.setVisible(false);
    }

    private void initMenuPanel() {
        menuPanel = new JPanel(sl);
        menuPanel.setSize(dim);
        menuPanel.setMinimumSize(dim);
        menuPanel.setMaximumSize(dim);
        menuPanel.setPreferredSize(dim);
        menuPanel.setBackground(Color.black);
        add(menuPanel);
        initLogoLabel();
        initMenuLabel();
    }

    private void initLogoLabel() {
        logoLabel = new JLabel();
        ImageIcon ii = new ImageIcon(BufferedImageLoader.getInstance().getBufferMap().get("logo"));
        logoLabel.setIcon(ii);
        sl.putConstraint(SpringLayout.NORTH, logoLabel, 25, SpringLayout.NORTH, menuPanel);
        sl.putConstraint(SpringLayout.WEST, logoLabel, 0, SpringLayout.WEST, menuPanel);
        sl.putConstraint(SpringLayout.EAST, logoLabel, 0, SpringLayout.EAST, menuPanel);
        menuPanel.add(logoLabel, sl);
    }

    private void initMenuLabel() {
        menuLabel = new JLabel();
        menuLabel.setText("Press <Enter> to start the game!");
        menuLabel.setForeground(Color.red);
        menuLabel.setHorizontalAlignment(SwingConstants.CENTER);
        sl.putConstraint(SpringLayout.NORTH, menuLabel, 300, SpringLayout.NORTH, menuPanel);
        sl.putConstraint(SpringLayout.WEST, menuLabel, 0, SpringLayout.WEST, menuPanel);
        sl.putConstraint(SpringLayout.EAST, menuLabel, 0, SpringLayout.EAST, menuPanel);
        menuPanel.add(menuLabel, sl);
    }

    private void initViewer() {
        Dimension viewDimension = new Dimension(576, 606);
        viewer = new Viewer(viewDimension);
        sl.putConstraint(SpringLayout.NORTH, viewer, 0, SpringLayout.NORTH, mainPanel);
        sl.putConstraint(SpringLayout.WEST, viewer, 0, SpringLayout.WEST, mainPanel);

        mainPanel.add(viewer, sl);

        /**/
        viewer.getSc().load();
    }

    private void initPlayers() {
        players = new JLabel[4];
        Dimension playerDim = new Dimension(100, 100);
        //
        players[0] = new JLabel();
        players[0].setText("Player 1");
        players[0].setIcon(new ImageIcon(BufferedImageLoader.getInstance().getBufferMap().get(viewer.getTankImgPath()[0])));
        players[0].setSize(playerDim);
        sl.putConstraint(SpringLayout.NORTH, players[0], 400, SpringLayout.NORTH, menuPanel);
        sl.putConstraint(SpringLayout.WEST, players[0], 50, SpringLayout.NORTH, menuPanel);
        menuPanel.add(players[0], sl);
        players[0].setVisible(false);
        //
        players[1] = new JLabel();
        players[1].setText("Player 2");
        players[1].setIcon(new ImageIcon(BufferedImageLoader.getInstance().getBufferMap().get(viewer.getTankImgPath()[1])));
        players[1].setSize(playerDim);
        sl.putConstraint(SpringLayout.NORTH, players[1], 400, SpringLayout.NORTH, menuPanel);
        sl.putConstraint(SpringLayout.WEST, players[1], 175, SpringLayout.NORTH, menuPanel);
        menuPanel.add(players[1], sl);
        players[1].setVisible(false);
        //
        players[2] = new JLabel();
        players[2].setText("Player 3");
        players[2].setIcon(new ImageIcon(BufferedImageLoader.getInstance().getBufferMap().get(viewer.getTankImgPath()[2])));
        players[2].setSize(playerDim);
        sl.putConstraint(SpringLayout.NORTH, players[2], 400, SpringLayout.NORTH, menuPanel);
        sl.putConstraint(SpringLayout.WEST, players[2], 300, SpringLayout.NORTH, menuPanel);
        menuPanel.add(players[2], sl);
        players[2].setVisible(false);
        //
        players[3] = new JLabel();
        players[3].setText("Player 4");
        players[3].setIcon(new ImageIcon(BufferedImageLoader.getInstance().getBufferMap().get(viewer.getTankImgPath()[3])));
        players[3].setSize(playerDim);
        sl.putConstraint(SpringLayout.NORTH, players[3], 400, SpringLayout.NORTH, menuPanel);
        sl.putConstraint(SpringLayout.WEST, players[3], 425, SpringLayout.NORTH, menuPanel);
        menuPanel.add(players[3], sl);
        players[3].setVisible(false);
    }

    private void initServer() {
        GameServerSocket gss = new GameServerSocket(2020, viewer);
        gss.start();
    }

    private void initMenuRefresh() {
        new Thread(() -> {
            while (!viewer.isGame()) {
                for (int i = 0; i < viewer.getClients().size(); i++) {
                    try {
                        viewer.getClients().get(i).getClient().sendUrgentData(1);
                    } catch (IOException ex) {
                        players[viewer.getClients().size() - 1].setVisible(false);
                        viewer.getClients().remove(i);
                    }
                }
                for (int i = 1; i <= players.length; i++) {
                    if (viewer.getClients().size() >= i) {
                        // Send message to all clients with the player asigned
                        viewer.getClients().get(i-1).sendMessage("msgPlayer" + i);
                        players[i - 1].setVisible(true);
                    }
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                }
            }
        }).start();
    }

    static { // Needed to play sound in background (eg. explosions)
        JFXPanel fxPanel = new JFXPanel();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BufferedImageLoader.getInstance().loadBufferFromProperties("res/conf/baldosas.properties");
        BufferedImageLoader.getInstance().loadBufferFromProperties("res/conf/misc.properties");
        new Main().setVisible(true);
        //
        //
        AudioPlayer player = new AudioPlayer(new File("res/audio/background.wav"));
        //new Thread(player).start();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Do nothing
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("Key PRESSED: " + e.getKeyCode());
        if (e.getKeyCode() == 10) { // KeyCode (10) == <Enter>
            if (viewer.getClients().size() < 2) {
                System.err.println("Faltan jugadores");
            } else {
                menuPanel.setVisible(false);
                mainPanel.setVisible(true);
                new Thread(viewer).start();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Do nothing
    }

}
