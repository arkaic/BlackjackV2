package controller;

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
}
