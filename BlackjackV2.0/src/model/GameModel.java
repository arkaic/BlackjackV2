package model;

import java.util.List;

public interface GameModel {
    
    public List<Card> getDeck();
    public Bet getBet();
    public Hand getHand();
    public Seat getSeat();
    public Bet getBankroll();
    public Hand getDealerHand();
    
    public void shuffle();
    public void dealFirstHands();
    public void hit();
    public void stay();
    public void surrender();
    public void split();
    public void doubleDown();
    public void takeInsurance();
    public void changeBets();
    
}
