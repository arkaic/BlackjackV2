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
    public void addToBankroll(int amount);
    public void subtractFromBankroll(int amount);
    public void displayMessage(String msg);
    public void waitForInsurance();
    public void setInsurance(int amount);
    public int getMaxInsurance();
    public void updateViewComponentsForNewRound();
}
