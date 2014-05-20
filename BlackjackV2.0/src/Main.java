import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.*;

import model.BjGameModel;
import model.GameModel;
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
        GameModel model = new BjGameModel();
        JFrame blackjackView = new BlackjackView(model);
        blackjackView.pack();
        blackjackView.setVisible(true);
    }

}
