package controller;

import view.BlackjackView;
import model.GameModel;

public interface GameController {
    public void update();
    public void dealFirstHands();
    public void setInitialBet(int seatNum, int amount);
    public void setModel(GameModel model);
    public void setView(BlackjackView view);
    public void shuffle();
    public void hit();
    public void stay();
    public void surrender();
    public void split();
    public void doubleDown();
}
