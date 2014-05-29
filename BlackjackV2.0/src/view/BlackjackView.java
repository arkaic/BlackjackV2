package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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
                setSpinnersEnabled(false);
                controller.dealFirstHands();
            }
        });
        
        hitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                controller.hit();
            }
        });
        
        doubleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.doubleDown();
            }
        });
        
        splitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.split();
            }
        });
        
        surrenderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.surrender();
            }
        });
        
        stayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.stay();
            }
        });
        
        shuffleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.shuffle();
            }
        });
        
        seat1Spinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent arg0) {
                int bet = ((Integer) seat1Spinner.getValue()).intValue();
                controller.setInitialBet(1, bet);
            }
        });
        seat2Spinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent arg0) {
                int bet = ((Integer) seat2Spinner.getValue()).intValue();
                controller.setInitialBet(2, bet);
            }
        });
        seat3Spinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent arg0) {
                int bet = ((Integer) seat3Spinner.getValue()).intValue();
                controller.setInitialBet(3, bet);          
            }
        });        
        seat4Spinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent arg0) {
                int bet = ((Integer) seat4Spinner.getValue()).intValue();
                controller.setInitialBet(4, bet);
            }
        });
        seat5Spinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent arg0) {
                int bet = ((Integer) seat5Spinner.getValue()).intValue();
                controller.setInitialBet(5, bet);
            }
        });
        seat6Spinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent arg0) {
                int bet = ((Integer) seat6Spinner.getValue()).intValue();
                controller.setInitialBet(6, bet);
            }
        });
    
    }
    
    private void setSpinnersEnabled(boolean bool) {
        seat1Spinner.setEnabled(bool);
        seat1Spinner.setVisible(bool);
        seat2Spinner.setEnabled(bool);
        seat2Spinner.setVisible(bool);
        seat3Spinner.setEnabled(bool);
        seat3Spinner.setVisible(bool);
        seat4Spinner.setEnabled(bool);
        seat4Spinner.setVisible(bool);
        seat5Spinner.setEnabled(bool);
        seat5Spinner.setVisible(bool);
        seat6Spinner.setEnabled(bool);
        seat6Spinner.setVisible(bool);
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
        deckDebugDisplay = new JLabel("debug here");
        seat1Area        = new JPanel();
        seat2Area        = new JPanel();
        seat3Area        = new JPanel();
        seat4Area        = new JPanel();
        seat5Area        = new JPanel();
        seat6Area        = new JPanel();
        insuranceArea    = new JPanel();
        seat1Label       = new JLabel("seat1");
        seat2Label       = new JLabel("seat2");
        seat3Label       = new JLabel("seat3");
        seat4Label       = new JLabel("seat4");
        seat5Label       = new JLabel("seat5");
        seat6Label       = new JLabel("seat6");
        insuranceLabel   = new JLabel("insurance here");
        dealerLabel      = new JLabel("dealer here");
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
        topLeftSubPanel.setLayout(new MigLayout());
        topLeftSubPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        topLeftSubPanel.add(deckDebugDisplay, "cell 0 0");
        topLeftSubPanel.add(dealerLabel, "cell 0 1");
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
        seat1Spinner.setMinimumSize(new Dimension(60, 10));
        seat2Spinner.setMinimumSize(new Dimension(60, 10));
        seat3Spinner.setMinimumSize(new Dimension(60, 10));
        seat4Spinner.setMinimumSize(new Dimension(60, 10));
        seat5Spinner.setMinimumSize(new Dimension(60, 10));
        seat6Spinner.setMinimumSize(new Dimension(60, 10));
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

    public void updateDisplays() {
        /**
         * TODO: shall update
         * -debug deck
         * -dealer's hand
         * -spinner visibility and accessibility
         * -player's hands
         * -button visibility and accessibility
         */
        seat1Label.setText(model.getSeat(1).toString());
        seat2Label.setText(model.getSeat(2).toString());
        seat3Label.setText(model.getSeat(3).toString());
        seat4Label.setText(model.getSeat(4).toString());
        seat5Label.setText(model.getSeat(5).toString());
        seat6Label.setText(model.getSeat(6).toString());
        dealerLabel.setText(model.getDealerHand().toString());
        deckDebugDisplay.setText(model.getDeck().toString());
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
    private JLabel dealerLabel;
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
