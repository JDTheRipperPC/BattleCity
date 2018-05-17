/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battlecity;

import battlecity.gui.Viewer;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

/**
 *
 * @author xGod
 */
public class Main extends JFrame{
private Dimension dim;
    
    private SpringLayout sl;
    
    private JPanel mainPanel;
    
    private Viewer viewer;

    public Main() {
        initProperties();
        initComponents();
    }
    
    private void initProperties(){
        setTitle("BattleCity");
        dim = new Dimension(800, 600);
        setSize(dim);
        setMinimumSize(dim);
        setMaximumSize(dim);
        setPreferredSize(dim);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    private void initComponents(){
        sl = new SpringLayout();
        initMainPanel();
        initViewer();
    }
    
    private void initMainPanel(){
        mainPanel = new JPanel(sl);
        mainPanel.setSize(dim);
        mainPanel.setMinimumSize(dim);
        mainPanel.setMaximumSize(dim);
        mainPanel.setPreferredSize(dim);
        mainPanel.setBackground(Color.darkGray);
        add(mainPanel);
    }
    
    private void initViewer(){
        Dimension viewDimension = new Dimension(600, 600);
        viewer = new Viewer(viewDimension);
        sl.putConstraint(SpringLayout.NORTH, viewer, 0, SpringLayout.NORTH, mainPanel);
        sl.putConstraint(SpringLayout.WEST, viewer, 0, SpringLayout.WEST, mainPanel);
        
        mainPanel.add(viewer, sl);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Main().setVisible(true);
    }

}
