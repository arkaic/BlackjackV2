package model;

import java.util.List;

import controller.GameController;
import view.BlackjackView;
import model.objects.*;

public interface GameModel {
    
    public List<Card> getDeck();
    public Hand getHand();
//    public Hand getCurrentHand();
//    public Seat getCurrentSeat();
    public Seat getSeat(int seatNum);
    public Seat getCurrentSeat();
    public Hand getCurrentHand();
    public int  getBankroll();
    public void addToBankroll(int amount);
    public void subtractFromBankroll(int amount);
    public Hand getDealerHand();
    public void setView(BlackjackView view);
    //TODO should probably change this argument to an interface
    public void setController(GameController controller);
    
    public void shuffle();
    public void dealFirstHands();
    public void hit();
    public void doubleDown();
    public void split();
    public void surrender();
    public void stay();
    public void setInitialBet(int seatNum, int amount);
    
}
