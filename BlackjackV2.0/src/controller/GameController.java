package controller;

import view.BlackjackView;
import model.GameModel;

public interface GameController {
    public void update();
    public void dealFirstHands();
    public void setInitialBet(int seatNum, int amount);
    public void setModel(GameModel model);
    public void setView(BlackjackView view);
}
