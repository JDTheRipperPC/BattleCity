package battlecity;

import battlecity.gui.Viewer;
import battlecity.socket.GameServerSocket;
import battlecity.util.AudioPlayer;
import battlecity.util.BufferedImageLoader;
import battlecity.util.BufferedImageUtil;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;
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
    private JLabel logoLabel, menuLabel, menuLabel2;

    private Viewer viewer;

    private JLabel[] players;
    private JLabel[] maps;

    private int point, maxPoint;

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
        initMaps();
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
        
        menuLabel2 = new JLabel();
        menuLabel2.setText("Press <Left> or <Right> kay arrow to change the map!");
        menuLabel2.setForeground(Color.red);
        menuLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        sl.putConstraint(SpringLayout.NORTH, menuLabel2, 325, SpringLayout.NORTH, menuPanel);
        sl.putConstraint(SpringLayout.WEST, menuLabel2, 0, SpringLayout.WEST, menuPanel);
        sl.putConstraint(SpringLayout.EAST, menuLabel2, 0, SpringLayout.EAST, menuPanel);
        menuPanel.add(menuLabel2, sl);
    }

    private void initViewer() {
        Dimension viewDimension = new Dimension(576, 606);
        viewer = new Viewer(viewDimension);
        sl.putConstraint(SpringLayout.NORTH, viewer, 0, SpringLayout.NORTH, mainPanel);
        sl.putConstraint(SpringLayout.WEST, viewer, 0, SpringLayout.WEST, mainPanel);
        mainPanel.add(viewer, sl);
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

    private void initMaps() {
        Properties prop = new Properties();
        try {
            InputStream is = new FileInputStream("res/conf/maps.properties");
            prop.load(is);
        } catch (IOException ex) {
            System.err.println("Error, unable to find maps file");
            return;
        }
        maps = new JLabel[prop.size()];
        maxPoint = prop.size() - 1;
        Enumeration<?> e = prop.propertyNames();
        int i = 0;
        while (e.hasMoreElements()) {
            
            String key = (String) e.nextElement();
            String value = prop.getProperty(key);
            
            System.out.println(value);
            
            maps[i] = new JLabel();
            maps[i].setText(value);
            maps[i].setForeground(Color.white);
            maps[i].setHorizontalAlignment(SwingConstants.CENTER);
            maps[i].setVisible(false);
            BufferedImage bi = BufferedImageLoader.getInstance().getBufferMap().get("map" + i + "_preview");
            maps[i].setIcon(new ImageIcon(BufferedImageUtil.resize(bi, 50, 50)));
            sl.putConstraint(SpringLayout.SOUTH, maps[i], -20, SpringLayout.SOUTH, menuPanel);
            sl.putConstraint(SpringLayout.WEST, maps[i], 0, SpringLayout.WEST, menuPanel);
            sl.putConstraint(SpringLayout.EAST, maps[i], 0, SpringLayout.EAST, menuPanel);
            menuPanel.add(maps[i], sl);
            i++;
        }
        point = 0;
        maps[point].setVisible(true);
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
                        viewer.getClients().get(i - 1).sendMessage("msgPlayer" + i);
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

    public void resetMenu() {
        //viewer.getClients().clear();
        viewer.getSc().getItems().clear(); // Fix after change it by tank and bullet
        viewer.getSc().getTiles().clear();
        viewer.getSc().load(maps[point].getText());
        initMenuRefresh();
        mainPanel.setVisible(false);
        menuPanel.setVisible(true);
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
                viewer.getSc().load(maps[point].getText());
                menuPanel.setVisible(false);
                mainPanel.setVisible(true);
                new Thread(viewer).start();
            }
        }
        if(e.getKeyCode() == 39 && point < maxPoint){
            maps[point].setVisible(false);
            point++;
            maps[point].setVisible(true);
        }
        if(e.getKeyCode() == 37 && point > 0){
            maps[point].setVisible(false);
            point--;
            maps[point].setVisible(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Do nothing
    }

}
