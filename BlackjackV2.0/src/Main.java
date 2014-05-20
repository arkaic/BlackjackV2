import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.*;

import controller.GameController;
import controller.ViewAController;
import model.BjGameModel;
import model.GameModel;
import net.miginfocom.swing.MigLayout;
import view.BlackjackView;


public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGui();   
            }
        });
    }

    protected static void createAndShowGui() { 
        GameModel model = new BjGameModel();
        GameController controller = new ViewAController();
        JFrame blackjackView = new BlackjackView(model, controller);
        blackjackView.pack();
        blackjackView.setVisible(true);
    }

}
