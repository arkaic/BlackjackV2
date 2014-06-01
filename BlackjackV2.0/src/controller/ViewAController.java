package controller;

import javax.swing.JOptionPane;

import model.GameModel;
import view.BlackjackView;

public class ViewAController implements GameController{

    private BlackjackView view;
    private GameModel model;

    @Override
    public void update() {
        // TODO maybe, maybe not
    }

    @Override
    public void dealFirstHands() {
        model.dealFirstHands();
    }

    @Override
    public void setInitialBet(int seatNum, int amount) {
        model.setInitialBet(seatNum, amount);
    }

    @Override
    public void setModel(GameModel model) {
        this.model = model;
    }

    @Override
    public void setView(BlackjackView view) {
        this.view = view;
    }

    @Override
    public void shuffle() {
        model.shuffle();
    }

    @Override
    public void hit() {
        model.hit();
    }

    @Override
    public void stay() {
        model.stay();
    }

    @Override
    public void surrender() {
        model.surrender();
    }

    @Override
    public void split() {
        model.split();
    }

    @Override
    public void doubleDown() {
        model.doubleDown();
    }

    @Override
    public void addToBankroll(int amount) {
        model.addToBankroll(amount);
    }

    @Override
    public void subtractFromBankroll(int amount) {
        model.subtractFromBankroll(amount);
    }

    @Override
    public void displayMessage(String msg) {
        JOptionPane.showMessageDialog(view, msg);
    }

    /* The model calls this method
     * It starts a new thread which runs a while loop that waits for insurance
     * to be made, due to the fact that it is another spinner on the same window
     * that just waits for input. Once the code inside the while loop reads that
     * insurance has been decided, the while loop will break, allowing the 
     * program to continue. This new thread is needed because pausing the main
     * thread will lock up the GUI.
     */
    @Override
    public void waitForInsurance() {
        class MyRunnable implements Runnable {
            public void run() {
                while (true) {
                    try {
                        if (view.isInsuranceDecided())
                            break;
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                return;
            }
        }
        MyRunnable ru = new MyRunnable();
        Thread th = new Thread(ru);
        th.start();
    }

    @Override
    public void setInsurance(int amount) {
        model.setInsurance(amount);
    }
    
    @Override
    public int getMaxInsurance() {
        return model.getMaxInsurance();
    }

    
    @Override
    public void updateViewComponentsForNewRound() {
        view.updateComponentsForNewRound();
    }
}
