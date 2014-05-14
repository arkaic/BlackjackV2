import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.*;

import net.miginfocom.swing.MigLayout;
import view.BlackjackView;


public class Main {

    public Main() {
        // TODO Auto-generated constructor stub
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGui();   
            }
        });
    }

    protected static void createAndShowGui() {
        JPanel panelNorth = new JPanel();
        JPanel panelSouth = new JPanel();
        JPanel panelEast  = new JPanel();
        JPanel panelCenter = new JPanel();
        JButton centerButton = new JButton("center");
        JButton hitButton = new JButton("Hit");
        JButton doubleButton = new JButton("Double Down");
        JButton splitButton = new JButton("Split");
        JButton surrenderButton = new JButton("Surrender");
        JButton stayButton = new JButton("Stay");
        JButton eastB = new JButton("East");
        JButton northB = new JButton("North");
        JButton westB = new JButton("West");
        
        panelCenter.add(centerButton);
        panelSouth.add(hitButton);
        panelSouth.add(doubleButton);
        panelSouth.add(splitButton);
        panelSouth.add(surrenderButton);
        panelSouth.add(stayButton);
        panelNorth.add(northB);
        panelEast.add(eastB);
        
//        panelSouth.setBackground(Color.BLUE);
        panelSouth.setBorder(BorderFactory.createRaisedBevelBorder());
        panelEast.setBorder(BorderFactory.createRaisedBevelBorder());
        panelNorth.setBorder(BorderFactory.createRaisedBevelBorder());
        
        JFrame blackjackView = new BlackjackView(); 
        blackjackView.setLayout(new MigLayout());

        blackjackView.add(panelNorth, "dock north");
        blackjackView.add(panelCenter, "height 500!, width 500!");
        blackjackView.add(panelSouth, "dock south");
        blackjackView.add(panelEast,   "dock east, width 100!");
        
        blackjackView.pack();
        blackjackView.setVisible(true);
    }

}
