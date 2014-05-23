package model;

import java.util.List;

import view.BlackjackView;
import model.objects.*;

public interface GameModel {
    
    public List<Card> getDeck();
    public Bet getBet();
    public Hand getHand();
    public Hand getCurrentHand();
    public Seat getCurrentSeat();
    public Seat getSeat(int seatNum);
    public Bet getBankroll();
    public Hand getDealerHand();
    public void setView(BlackjackView view);
    //TODO should probably change this to an interface
    
    public void shuffle();
    public void dealFirstHands();
    public void hit();
    public void doubleDown();
    public void split();
    public void surrender();
    public void stay();
    public void takeInsurance();
    public void changeInitialBet(int seatNum, int amount);
    
}
