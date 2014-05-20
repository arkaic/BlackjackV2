package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;

import controller.GameController;
import model.GameModel;
import net.miginfocom.swing.MigLayout;

public class BlackjackView extends JFrame {

    private static final long serialVersionUID = 1L;
    
    private GameModel model;
    private GameController controller;
    
    public BlackjackView(GameModel model, GameController controller) {
        this.model = model;
        this.controller = controller;
        buildUI();
        attachListenersToComponents();
    }
    
    private void attachListenersToComponents() {
        
        dealHandsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dealHandsButton.setEnabled(false);
                shuffleButton.setEnabled(false);
                //TODO grey out or make the spinners disappear
                //TODO deal first routine called from controller
            }
        });
        
        hitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                /**
                 * TODO implement this:
                 * -Draw card
                 * -Conditional check if the current hand can take any more
                 *   action (Either a bust or a hard 21)
                 * -If it can't take more action, 
                 */
                
            }
        });
        
        doubleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO implement this
                
            }
        });
        
        splitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO implement this
                
            }
        });
        
        surrenderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO implement this
                
            }
        });
        
        stayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO implement this
                
            }
        });
        
        
        shuffleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO implement this
                
            }
        });
    }
    

    /**
     * Creates all components for the Window
     */
    private void buildUI() {
        topPanel         = new JPanel();
        centerPanel      = new JPanel();
        bottomPanel      = new JPanel();
        topRightSubPanel = new JPanel();
        topLeftSubPanel  = new JPanel();
        deckDebugDisplay = new JLabel();
        seat1Area        = new JPanel();
        seat2Area        = new JPanel();
        seat3Area        = new JPanel();
        seat4Area        = new JPanel();
        seat5Area        = new JPanel();
        seat6Area        = new JPanel();
        insuranceArea    = new JPanel();
        seat1Label       = new JLabel("<html>[$55+$55][A,A,10,A,A,A,A,A,A]</html>");
        seat2Label       = new JLabel("<html>[$55+$55][A,A,10,A,A,A,A,A,A]</html>");
        seat3Label       = new JLabel("<html>[$55+$55][A,A,10,A,A,A,A,A,A]</html>");
        seat4Label       = new JLabel("<html>[$55+$55][A,A,10,A,A,A,A,A,A]</html>");
        seat5Label       = new JLabel("<html>[$55+$55][A,A,10,A,A,A,A,A,A]<br>g<br>g<br>g<br></html>");
        seat6Label       = new JLabel("<html>[$55+$55][A,A,10,A,A,A,A,A,A]<br>g<br>g<br>g<br></html>");
        insuranceLabel   = new JLabel("insurance here");
        seat1Spinner     = new JSpinner();
        seat2Spinner     = new JSpinner();
        seat3Spinner     = new JSpinner();
        seat4Spinner     = new JSpinner();
        seat5Spinner     = new JSpinner();
        seat6Spinner     = new JSpinner();
        insuranceSpinner = new JSpinner();
        hitButton        = new JButton("Hit");
        doubleButton     = new JButton("Double Down");
        splitButton      = new JButton("Split");
        surrenderButton  = new JButton("Surrender");
        stayButton       = new JButton("Stay");
        dealHandsButton  = new JButton("Deal Hands");
        shuffleButton    = new JButton("Shuffle");
        
        topPanel.setLayout(new MigLayout("", "[grow]", "[]"));
//        topRightSubPanel.setLayout(new BoxLayout(topRightSubPanel, BoxLayout.Y_AXIS));
        topRightSubPanel.setLayout(new MigLayout());
        topRightSubPanel.add(dealHandsButton);
        topRightSubPanel.add(shuffleButton, "cell 0 1");
        topLeftSubPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        topLeftSubPanel.add(deckDebugDisplay);
        topPanel.add(topLeftSubPanel, "dock west, width 600!");
        topPanel.add(topRightSubPanel, "dock east");
        topPanel.setBorder(BorderFactory.createRaisedBevelBorder());
        
        seat1Area.setBorder(BorderFactory.createRaisedBevelBorder());
        seat1Area.setLayout(new MigLayout());
        seat2Area.setBorder(BorderFactory.createRaisedBevelBorder());
        seat2Area.setLayout(new MigLayout());
        seat3Area.setBorder(BorderFactory.createRaisedBevelBorder());
        seat3Area.setLayout(new MigLayout());
        seat4Area.setBorder(BorderFactory.createRaisedBevelBorder());
        seat4Area.setLayout(new MigLayout());
        seat5Area.setBorder(BorderFactory.createRaisedBevelBorder());
        seat5Area.setLayout(new MigLayout());
        seat6Area.setBorder(BorderFactory.createRaisedBevelBorder());
        seat6Area.setLayout(new MigLayout());
        insuranceArea.setBorder(BorderFactory.createEtchedBorder());
        insuranceArea.setLayout(new MigLayout());
        seat1Area.add(seat1Label);
        seat1Area.add(seat1Spinner, "dock south");
        seat2Area.add(seat2Label);
        seat2Area.add(seat2Spinner, "dock south");
        seat3Area.add(seat3Label);
        seat3Area.add(seat3Spinner, "dock south");
        seat4Area.add(seat4Label);
        seat4Area.add(seat4Spinner, "dock south");
        seat5Area.add(seat5Label);
        seat5Area.add(seat5Spinner, "dock south");
        seat6Area.add(seat6Label);
        seat6Area.add(seat6Spinner, "dock south");
        insuranceArea.add(insuranceLabel);
        insuranceArea.add(insuranceSpinner, "dock south");
        
        centerPanel.setLayout(new MigLayout("", "[][][]10[]", "[]20[]"));
        centerPanel.add(seat1Area);
        centerPanel.add(seat2Area, "cell 1 1");
        centerPanel.add(seat3Area, "cell 2 2");
        centerPanel.add(seat4Area, "cell 3 2");
        centerPanel.add(seat5Area, "cell 4 1");
        centerPanel.add(seat6Area, "cell 5 0");
        centerPanel.add(insuranceArea, "cell 2 0, span 2, width 300");
        centerPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        bottomPanel.add(hitButton);
        bottomPanel.add(doubleButton);
        bottomPanel.add(splitButton);
        bottomPanel.add(surrenderButton);
        bottomPanel.add(stayButton);
        bottomPanel.setBorder(BorderFactory.createRaisedBevelBorder());

        this.setLayout(new MigLayout());
        this.add(topPanel, "dock north");
        this.add(centerPanel);
        this.add(bottomPanel, "dock South");
    }

    private void render() {
        /**
         * TODO: shall update
         * -debug deck
         * -dealer's hand
         * -spinner visibility and accessibility
         * -player's hands
         * -button visibility and accessibility
         */
    }
    
    private JPanel topPanel;
    private JPanel centerPanel;
    private JPanel bottomPanel;
    private JPanel topRightSubPanel;
    private JPanel topLeftSubPanel;
    private JPanel seat1Area;
    private JPanel seat2Area;
    private JPanel seat3Area;
    private JPanel seat4Area;
    private JPanel seat5Area;
    private JPanel seat6Area;
    private JPanel insuranceArea;
    private JLabel seat1Label;
    private JLabel seat2Label;
    private JLabel seat3Label;
    private JLabel seat4Label;
    private JLabel seat5Label;
    private JLabel seat6Label;
    private JLabel insuranceLabel;
    private JSpinner seat1Spinner;
    private JSpinner seat2Spinner;
    private JSpinner seat3Spinner;
    private JSpinner seat4Spinner;
    private JSpinner seat5Spinner;
    private JSpinner seat6Spinner;
    private JButton hitButton;
    private JButton doubleButton;
    private JButton splitButton;
    private JButton surrenderButton;
    private JButton stayButton;
    private JButton dealHandsButton;
    private JButton shuffleButton;
    private JLabel deckDebugDisplay;
    private JSpinner insuranceSpinner;
}
