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
}
