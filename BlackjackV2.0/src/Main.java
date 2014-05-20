import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
//                createAndShowGui();   
                test();
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
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            list.add(new Integer(i));
        }
        Iterator<Integer> it = list.iterator();
        System.out.println(it.next());
        System.out.println(it.next());
        it = list.iterator();
        System.out.println(it.next());
    }

}
