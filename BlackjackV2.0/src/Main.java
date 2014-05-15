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
        JFrame blackjackView = new BlackjackView(); 
        blackjackView.pack();
        blackjackView.setVisible(true);
    }

}
