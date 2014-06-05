package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Seat {

    private int seatNumber;
    private List<Hand> hands = new ArrayList<Hand>();
    private Bet initialBet  = new Bet(0);
    private Hand currentHand;
    private Iterator<Hand> handIterator;
    
    public Seat(int num) {
        seatNumber = num;
    }
    
    protected void setInitialBet(int amount) {
        if (amount > 0) {
            initialBet.setAmount(amount); 
        } else {
            initialBet.setAmount(0);
        }
    }
    
    /*Should return null if this Seat is empty of hands*/
    protected Hand getCurrentHand() {
        return currentHand;
    }
    
    protected void setCurrentHand(Hand hand) {
        currentHand = hand;
    }
    
    protected void changeCurrentHand() {
        if (handIterator().hasNext()) {
            currentHand = handIterator().next();
        } else {
            currentHand = null;
        }
    }
    
    protected void clearCurrentHand() {
        handIterator().remove();
        //Iterator would still be in the same position of the element that it
        //removed, so iterator.hasNext() will still work if there's another
        //element.
    }
    
    /*Clears hands list*/
    protected void clearHands() {
        hands.clear();
    }
    
    protected void clearIterator() {
        handIterator = null;
    }
    
    /*Initializes handIterator if it hasn't been already*/
    private Iterator<Hand> handIterator() {
        if (handIterator == null) {
            handIterator = hands.iterator();
        }    
        
        return handIterator;
    }
    
    protected Hand getHand(int n) {
        return hands.get(n);
    }
    
    protected List<Hand> getHands() {
        return hands;
    }
    
    /*Checks if Seat has any hands, or cards in those hands.
     * Will return false if there is an empty hand.
     * Assumes that an empty hand will not be accompanied by non-empty
     * hands.
     */
    protected boolean hasHands() {
        if (hands.isEmpty()) {
            return false;
        } else {
            for (Hand hand : hands) {
                if (hand.isEmpty())
                    return false;
            }
            return true;
        }
    }
    
    protected void addHand(Hand hand) {
        hands.add(hand);
    }
    
    /*
     * A separate hand-adding implementation because when splitting, there 
     * needs to be a reset of the seat's iterator
     * 1. Take second card of current hand and put it in new hand.
     * 2. Insert new hand after the current hand in hands
     * 3. Renew the handIterator member
     * 4. Iterate the handIterator until it matches currentHand.
     */
    protected void splitHand(Hand newHand) {
        newHand.setBetAmount(getCurrentHand().getBetAmount());
        newHand.addCard(getCurrentHand().removeCard());
        
        for (int i = 0; i < hands.size(); i++) {
            if (hands.get(i) == currentHand) {
                hands.add(i + 1, newHand);
                break;
            }
        }
        
        handIterator = hands.iterator(); 
        
        while (true) {
            if (handIterator.next() == currentHand)
                break;
        }
    }
    
    protected Bet getInitialBet() {
        return initialBet;
    }
    
    public int getSeatNumber() {
        return seatNumber;
    }
    
    protected boolean hasInitialBet() {
        return (initialBet.getAmount() > 0);
    }

    public String toString() {
        if (this.hasHands()) {
            String str = "<html>";
            for (Hand hand : hands) {
                str += hand.toString() + "<br>";
            }
            return str += "</html>";
        } else {
            return "";
        }
    }
}
