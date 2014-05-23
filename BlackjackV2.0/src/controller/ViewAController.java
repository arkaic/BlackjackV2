package controller;

import model.GameModel;
import view.BlackjackView;

public class ViewAController implements GameController{

    private BlackjackView view;
    private GameModel model;

    public ViewAController() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void update() {
        // TODO maybe, maybe not
    }

    @Override
    public void dealFirstHands() {
        model.dealFirstHands();
    }

    @Override
    public void changeInitialBet(int seatNum, int amount) {
        model.changeInitialBet(seatNum, amount);
    }

    @Override
    public void setModel(GameModel model) {
        this.model = model;
    }

    @Override
    public void setView(BlackjackView view) {
        this.view = view;
    }
}
