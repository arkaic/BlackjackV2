package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Seat {

    private int seatNumber;
    private List<Hand> hands = new ArrayList<Hand>();
    private Bet initialBet; //before it is put into Hand
    private Hand currentHand;
    private Iterator<Hand> handIterator;
    
    public Seat(int num) {
        seatNumber = num;
        initialBet = new Bet(0);
    }
    
    protected void changeBet(int amount) {
        //TODO implement
        //initialBet.changeAmount = amount; 
    }
    
    protected void changeCurrentHand() {
        if (hasNextHand()) {
            currentHand = getHandIterator().next();
        } else {
            currentHand = null;
        }
        //TODO notify view to update
    }
    
    protected Hand getCurrentHand() {
        return currentHand;
    }
    
    protected boolean hasNextHand() {
        return getHandIterator().hasNext();
    }
    
    private Iterator<Hand> getHandIterator() {
        if (handIterator == null) {
            handIterator = hands.iterator();
        }    
        
        return handIterator;
    }
    
    protected boolean hasHand() {
        if (hands.isEmpty()) 
            return false;
        else 
            return true;
    }
    
//    protected boolean hasBet() {
//        if (initialBet.getAmount() == 0)
//            return false;
//        else
//            return true;
//    }
}
