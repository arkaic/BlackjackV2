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
        if (handIterator().hasNext()) {
            currentHand = handIterator().next();
        } else {
            currentHand = null;
        }
        //TODO notify view to update
    }
    
    /*Initializes handIterator if it hasn't been already*/
    private Iterator<Hand> handIterator() {
        if (handIterator == null) {
            handIterator = hands.iterator();
        }    
        
        return handIterator;
    }
    
    protected Hand getCurrentHand() {
        return currentHand;
    }
    
    protected void clearCurrentHand() {
        handIterator().remove();
    }
    
    protected boolean hasHand() {
        if (hands.isEmpty()) 
            return false;
        else 
            return true;
    }
    
    protected void addHand(Hand hand) {
        hands.add(hand);
    }
    
    /*
     * A separate hand-adding implementation because when splitting, there 
     * needs to be a reset of the seat's iterator
     */
    protected void addSplitHand(Hand newHand) {
        //reset iterator and then 'next' it till it's back to current hand
        //TODO take second card of current hand and put it in new hand
        for (int i = 0; i < hands.size(); i++) {
            if (hands.get(i) == currentHand) {
                hands.add(i + 1, newHand);
                break;
            }
        }
        
        handIterator = hands.iterator();
        
        //TODO test
        Hand iteratingHand = null;
        while (true) {
            iteratingHand = handIterator.next();
            if (iteratingHand == currentHand)
                break;
        }
    }
    
    //TODO possibly temporary
    protected Hand getHand(int n) {
        return hands.get(n);
    }
    
    public String toString() {
        return "Seat " + seatNumber;
    }
    
//    protected boolean hasBet() {
//        if (initialBet.getAmount() == 0)
//            return false;
//        else
//            return true;
//    }
}
