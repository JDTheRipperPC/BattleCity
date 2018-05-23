package battlecity;

import battlecity.game.items.Tank;
import battlecity.gui.Viewer;
import battlecity.socket.GameServerSocket;
import battlecity.util.AudioPlayer;
import battlecity.util.BufferedImageLoader;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import sun.audio.AudioStream;

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

    public Main() {
        initProperties();
        initComponents();
        initServer();
    }

    private void initProperties() {
        setTitle("BattleCity");
        dim = new Dimension(800, 600);
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
        Dimension viewDimension = new Dimension(600, 600);
        viewer = new Viewer(viewDimension);
        sl.putConstraint(SpringLayout.NORTH, viewer, 0, SpringLayout.NORTH, mainPanel);
        sl.putConstraint(SpringLayout.WEST, viewer, 0, SpringLayout.WEST, mainPanel);

        mainPanel.add(viewer, sl);
    }

    private void initServer(){
        GameServerSocket gss = new GameServerSocket(2020, viewer);
        gss.start();
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
        new Thread(player).start();
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
