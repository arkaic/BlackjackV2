package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import controller.GameController;
import model.Bet;
import model.GameModel;
import net.miginfocom.swing.MigLayout;

public class BlackjackView extends JFrame {

    private static final long serialVersionUID = 1L;
    
    private GameModel model;
    private GameController controller;
    private HashMap<JSpinner, Bet> spinnerToPrevBetMap = 
            new HashMap<JSpinner, Bet>();
    private List<JSpinner> spinners = new ArrayList<>();
    private List<JPanel> panelAreas = new ArrayList<>();
    
//    private boolean isInsuranceDecided = false;
    
    
    
    public BlackjackView(GameModel model, GameController controller) {
        this.model = model;
        this.controller = controller;
        buildUI();
//        addTestLabel(); //To show current Hand
        attachListenersToComponents();
//        updateSpinnerMaxes();

        spinners.add(seat1Spinner);
        spinners.add(seat2Spinner);
        spinners.add(seat3Spinner);
        spinners.add(seat4Spinner);
        spinners.add(seat5Spinner);
        spinners.add(seat6Spinner);
        
        spinnerToPrevBetMap.put(seat1Spinner, new Bet(0));
        spinnerToPrevBetMap.put(seat2Spinner, new Bet(0));
        spinnerToPrevBetMap.put(seat3Spinner, new Bet(0));
        spinnerToPrevBetMap.put(seat4Spinner, new Bet(0));
        spinnerToPrevBetMap.put(seat5Spinner, new Bet(0));
        spinnerToPrevBetMap.put(seat6Spinner, new Bet(0));
        spinnerToPrevBetMap.put(insuranceSpinner, new Bet(0));

        panelAreas.add(seat1Area);
        panelAreas.add(seat2Area);
        panelAreas.add(seat3Area);
        panelAreas.add(seat4Area);
        panelAreas.add(seat5Area);
        panelAreas.add(seat6Area);
        
        updateDisplays();
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
        
        addFundsButton.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                JDialog dialog = new JDialog((JFrame)addFundsButton.
                    getTopLevelAncestor(), "Add more funds...");
                final JSpinner spinner = new JSpinner();
                SpinnerNumberModel spinModel = new SpinnerNumberModel();
                spinModel.setMinimum(0);
                spinner.setModel(spinModel);
                
                final JButton okButton = new JButton("OK");
                okButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        int amount = (Integer)spinner.getValue();
                        model.addToBankroll(amount);
                        
                        Window dialogBox = (Window)okButton.getTopLevelAncestor();
                        updateDisplays();
                        dialogBox.setVisible(false);
                        dialogBox.dispose();
                    }
                });
                
                JPanel panel = new JPanel();
                panel.add(spinner);
                panel.add(okButton);
                dialog.setContentPane(panel);
                dialog.setLocationRelativeTo(null);
                dialog.pack();
                dialog.setVisible(true);
            }
        });
        /*******************SPINNERS*********************/
        seat1Spinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent arg0) {
                changeSpinnerState(seat1Spinner);
                int bet = ((Integer) seat1Spinner.getValue()).intValue();
                controller.setInitialBet(1, bet);
            }
        });
        seat2Spinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent arg0) {
                changeSpinnerState(seat2Spinner);
                int bet = ((Integer) seat2Spinner.getValue()).intValue();
                controller.setInitialBet(2, bet);
            }
        });
        seat3Spinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent arg0) {
                changeSpinnerState(seat3Spinner);
                int bet = ((Integer) seat3Spinner.getValue()).intValue();
                controller.setInitialBet(3, bet);          
            }
        });        
        seat4Spinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent arg0) {
                changeSpinnerState(seat4Spinner);
                int bet = ((Integer) seat4Spinner.getValue()).intValue();
                controller.setInitialBet(4, bet);
            }
        });
        seat5Spinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent arg0) {
                changeSpinnerState(seat5Spinner);
                int bet = ((Integer) seat5Spinner.getValue()).intValue();
                controller.setInitialBet(5, bet);
            }
        });
        seat6Spinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent arg0) {
                changeSpinnerState(seat6Spinner);
                int bet = ((Integer) seat6Spinner.getValue()).intValue();
                controller.setInitialBet(6, bet);
            }
        });
        
        insuranceSpinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                int current = ((Integer) insuranceSpinner.getValue()).intValue();
                int previous = spinnerToPrevBetMap.get(insuranceSpinner).getAmount();
                
                if (current != previous) {
                    controller.addToBankroll(previous - current);
                    spinnerToPrevBetMap.get(insuranceSpinner).setAmount(current);
                }
                
                SpinnerNumberModel spinnerModel = 
                        (SpinnerNumberModel)insuranceSpinner.getModel();
                int maxInsurance = controller.getMaxInsurance();
                int spinnerMax = Math.min(maxInsurance, current + 
                        model.getBankroll());
                //Choose the smaller of two values to use as max for insurance
                spinnerModel.setMaximum(spinnerMax);
                controller.setInsurance(current);
            }
        });
        insuranceOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.checkForBlackjack();
            }
        });
    }
    
//    public boolean isInsuranceDecided() {
//        return isInsuranceDecided;
//    }
    
    private void changeSpinnerState(JSpinner spinner) {
        int currentAmount = ((Integer) spinner.getValue()).intValue();
        int previousAmount = spinnerToPrevBetMap.get(spinner).getAmount();
        
        if (currentAmount != previousAmount) {
            controller.addToBankroll(previousAmount - currentAmount);
            spinnerToPrevBetMap.get(spinner).setAmount(currentAmount);
        }
        
        updateSpinnerMaxes();
    }
    private void updateSpinnerMaxes() {
        int current1 = ((Integer) seat1Spinner.getValue());
        int current2 = ((Integer) seat2Spinner.getValue());
        int current3 = ((Integer) seat3Spinner.getValue());
        int current4 = ((Integer) seat4Spinner.getValue());
        int current5 = ((Integer) seat5Spinner.getValue());
        int current6 = ((Integer) seat6Spinner.getValue());
        SpinnerNumberModel model1 = (SpinnerNumberModel) seat1Spinner.getModel();
        SpinnerNumberModel model2 = (SpinnerNumberModel) seat2Spinner.getModel();
        SpinnerNumberModel model3 = (SpinnerNumberModel) seat3Spinner.getModel();
        SpinnerNumberModel model4 = (SpinnerNumberModel) seat4Spinner.getModel();
        SpinnerNumberModel model5 = (SpinnerNumberModel) seat5Spinner.getModel();
        SpinnerNumberModel model6 = (SpinnerNumberModel) seat6Spinner.getModel();
        model1.setMaximum(current1 + model.getBankroll());
        model2.setMaximum(current2 + model.getBankroll());
        model3.setMaximum(current3 + model.getBankroll());
        model4.setMaximum(current4 + model.getBankroll());
        model5.setMaximum(current5 + model.getBankroll());
        model6.setMaximum(current6 + model.getBankroll());
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

    
    private void addTestLabel() {
        testLabel = new JLabel();
        topLeftSubPanel.add(testLabel, "cell 0 3");
    }

    public void updateDisplays() {
        seat1Label.setText(model.getSeat(1).toString());
        seat2Label.setText(model.getSeat(2).toString());
        seat3Label.setText(model.getSeat(3).toString());
        seat4Label.setText(model.getSeat(4).toString());
        seat5Label.setText(model.getSeat(5).toString());
        seat6Label.setText(model.getSeat(6).toString());
        insuranceLabel.setText("$" + model.getInsurance());
        bankrollLabel.setText("$" + model.getBankroll());
        dealerLabel.setText("Dealer hand: " + model.getDealerHand().toString());
//        deckDebugDisplay.setText(model.getDeck().toString());
//        discardDebugDisplay.setText(model.getDiscards().toString());

        //Testing purposes only: displays string of current hand on a label 
        //below the dealer's
//        if (model.getCurrentHand() != null) {
//            testLabel.setText(model.getCurrentHand().toString());
//        }
        
        int currentSeatNumber = 7;
        if (model.getCurrentSeat() != null) {
            currentSeatNumber = model.getCurrentSeat().getSeatNumber();
        }
        lowerPanelArea(currentSeatNumber);
        
        if (model.getState().equalsIgnoreCase("Start")) {
            setSpinnersEnabled(true);
            if (areSpinnersEmpty()) 
                dealHandsButton.setEnabled(false);
            else 
                dealHandsButton.setEnabled(true);
            
            shuffleButton.setEnabled(true);
            addFundsButton.setEnabled(true);
        } else {
            setSpinnersEnabled(false);
            dealHandsButton.setEnabled(false);
            shuffleButton.setEnabled(false);
        }
        
        if (model.getState().equalsIgnoreCase("Play")) {
            hitButton.setEnabled(true);
            doubleButton.setEnabled(
                    model.getBankroll() > 0 
                    &&
                    model.getCurrentHand().isTwoCards());
            splitButton.setEnabled(
                    model.getBankroll() >= model.getCurrentHand().getBetAmount() 
                    &&
                    model.getCurrentHand().isAPair());
            surrenderButton.setEnabled(model.getCurrentHand().isTwoCards());
            stayButton.setEnabled(true);
        } else {
            hitButton.setEnabled(false);
            doubleButton.setEnabled(false);
            splitButton.setEnabled(false);
            surrenderButton.setEnabled(false);
            stayButton.setEnabled(false);
        }
        
        if (model.getState().equalsIgnoreCase("Insurance")) {
            insuranceSpinner.setEnabled(true);
            insuranceOk.setEnabled(true);
        } else {
            insuranceSpinner.setEnabled(false);
            insuranceOk.setEnabled(false);
        }
        
        this.pack();
    }
    
    /**
     * This private routine is to give a pressed look to the area for the seat
     * that has the current hand, while raising up every other. 
     * @param seatNum
     */
    private void lowerPanelArea(int seatNum) {
        int index = seatNum - 1;
        
        for (int i = 0; i < panelAreas.size(); i++) {
            if (i == index) {
                panelAreas.get(i).setBorder(BorderFactory.
                        createLoweredBevelBorder());
            } else {
                panelAreas.get(i).setBorder(BorderFactory.
                        createRaisedBevelBorder());
            }
        }
    }

    
    /**
     * Further updates to displays of the View, such as the bet spinners when
     * the previous initial bets for them were larger than the current bankroll.
     * On a new round, the spinners reappear with their previous bets inserted.
     * But let's say there is one spinner too many that would draw from the 
     * bankroll. In this case, the leftmost spinners get priority.
     */
    public void updateComponentsForNewRound() {
        spinnerToPrevBetMap.get(insuranceSpinner).setAmount(0);
        ((SpinnerNumberModel)(insuranceSpinner.getModel())).setValue(0);;
        
        for (JSpinner spinner : spinners) {
            int spinnerAmount = (int) spinner.getValue();
            if (spinnerAmount <= model.getBankroll()) {
                model.subtractFromBankroll(spinnerAmount);
                ((SpinnerNumberModel)(spinner.getModel()))
                    .setValue(spinnerAmount);
            } else {
                model.subtractFromBankroll(model.getBankroll());
                ((SpinnerNumberModel)(spinner.getModel()))
                    .setValue(model.getBankroll());
            }
        }
    }
   
    private boolean areSpinnersEmpty() {
        boolean bool = true;
        for (JSpinner spinner : spinners) {
            if ((Integer)spinner.getValue() > 0)
                bool = false;
        }
        return bool;
    }
    
    /**
     * Creates all components for the Window
     */
    private void buildUI() {
        topPanel            = new JPanel();
        centerPanel         = new JPanel();
        bottomPanel         = new JPanel();
        topRightSubPanel    = new JPanel();
        topLeftSubPanel     = new JPanel();
//        deckDebugDisplay    = new JLabel("debug here");
//        discardDebugDisplay = new JLabel("discards here");
        seat1Area           = new JPanel();
        seat2Area           = new JPanel();
        seat3Area           = new JPanel();
        seat4Area           = new JPanel();
        seat5Area           = new JPanel();
        seat6Area           = new JPanel();
        insuranceArea       = new JPanel();
        seat1Label          = new JLabel("seat1");
        seat2Label          = new JLabel("seat2");
        seat3Label          = new JLabel("seat3");
        seat4Label          = new JLabel("seat4");
        seat5Label          = new JLabel("seat5");
        seat6Label          = new JLabel("seat6");
        bankrollLabel       = new JLabel("bankroll");
        insuranceLabel      = new JLabel("insurance here");
        dealerLabel         = new JLabel("dealer here");
        seat1Spinner        = new JSpinner();
        seat2Spinner        = new JSpinner();
        seat3Spinner        = new JSpinner();
        seat4Spinner        = new JSpinner();
        seat5Spinner        = new JSpinner();
        seat6Spinner        = new JSpinner();
        insuranceSpinner    = new JSpinner();
        hitButton           = new JButton("Hit");
        doubleButton        = new JButton("Double Down");
        splitButton         = new JButton("Split");
        surrenderButton     = new JButton("Surrender");
        stayButton          = new JButton("Stay");
        dealHandsButton     = new JButton("Deal Hands");
        shuffleButton       = new JButton("Shuffle");
        addFundsButton      = new JButton("Add Funds");
        insuranceOk         = new JButton("OK");
        
        topPanel.setLayout(new MigLayout("", "[grow]", "[]"));
//        topRightSubPanel.setLayout(new BoxLayout(topRightSubPanel, BoxLayout.Y_AXIS));
        topRightSubPanel.setLayout(new MigLayout());
        topRightSubPanel.add(dealHandsButton);
        topRightSubPanel.add(shuffleButton, "cell 0 1");
        topRightSubPanel.add(addFundsButton, "cell 0 2");
        topLeftSubPanel.setLayout(new MigLayout());
        topLeftSubPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
//        topLeftSubPanel.add(deckDebugDisplay, "cell 0 0");
//        topLeftSubPanel.add(discardDebugDisplay, "cell 0 1");
        topLeftSubPanel.add(dealerLabel, "cell 0 2");
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
        seat1Spinner.setModel(new SpinnerNumberModel(0, 0, 
                model.getBankroll(), 5));
        seat2Spinner.setMinimumSize(new Dimension(60, 10));
        seat2Spinner.setModel(new SpinnerNumberModel(0, 0, 
                model.getBankroll(), 5));
        seat3Spinner.setMinimumSize(new Dimension(60, 10));
        seat3Spinner.setModel(new SpinnerNumberModel(0, 0, 
                model.getBankroll(), 5));
        seat4Spinner.setMinimumSize(new Dimension(60, 10));
        seat4Spinner.setModel(new SpinnerNumberModel(0, 0, 
                model.getBankroll(), 5));
        seat5Spinner.setMinimumSize(new Dimension(60, 10));
        seat5Spinner.setModel(new SpinnerNumberModel(0, 0, 
                model.getBankroll(), 5));
        seat6Spinner.setMinimumSize(new Dimension(60, 10));
        seat6Spinner.setModel(new SpinnerNumberModel(0, 0, 
                model.getBankroll(), 5));
        seat1Area.add(seat1Label);
        seat1Area.add(seat1Spinner, "cell 0 1");
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
        insuranceSpinner.setMinimumSize(new Dimension(60, 10));
        insuranceSpinner.setModel(new SpinnerNumberModel(0, 0, model.getBankroll(), 1));
        insuranceArea.add(insuranceSpinner, "cell 0 1");
        insuranceArea.add(insuranceOk, "cell 1 1");
        
        
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
        this.add(bankrollLabel, "dock south");
        this.add(bottomPanel, "dock South");
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
    private JLabel bankrollLabel;
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
    private JButton addFundsButton;
    private JLabel deckDebugDisplay;
    private JLabel discardDebugDisplay;
    private JSpinner insuranceSpinner;
    private JButton insuranceOk;
    
    private JLabel testLabel;
}
