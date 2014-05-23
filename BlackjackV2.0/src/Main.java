import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import javax.swing.*;

import controller.GameController;
import controller.ViewAController;
import model.BjGameModel;
import model.GameModel;
import net.miginfocom.swing.MigLayout;
import view.BlackjackView;

import model.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGui();   
//                test();
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
    
    private static void test() {
        BjGameModel model = new BjGameModel();

        
        model.test();
    }

}
