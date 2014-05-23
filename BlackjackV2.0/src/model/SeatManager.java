package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 *  Manages seats and seat order during play.q
 */
public class SeatManager{

    private List<Seat> seats = new ArrayList<Seat>();
    public Stack<Seat> seatsInPlay = new Stack<>();
    private Hand currentHand;
    private Hand dealerHand;
    
    public SeatManager() {
        initSeats();
        dealerHand = new Hand(0, true);//TODO delete dummy statement
    }
    
    /*Put in six seats in the list */
    private void initSeats() {
        for (int i = 1; i <= 6; i++) {
            seats.add(new Seat(i));
        }
    }
    
    /*Stacks up seats in play*/
    protected void createSeatPlayOrder() {
        if (!seatsInPlay.isEmpty()) {
            seatsInPlay.clear();
        }
        
        for (int i = seats.size(); i > 0; i--) {
            seatsInPlay.push(seats.get(i - 1));
        }
    }
    
    protected void changeCurrentHand() {
        if (!seatsInPlay.isEmpty()) {
            Seat currentSeat = getCurrentSeat();
            currentSeat.changeCurrentHand();
            
            if (currentSeat.getCurrentHand() != null) {
                currentHand = currentSeat.getCurrentHand();    
            } else {
                seatsInPlay.pop();
                changeCurrentHand();
            }
        } else {
            currentHand = dealerHand;
        }
    }
    
    //TODO returns null if action rounds haven't started yet
    protected Hand getCurrentHand() {
        return currentHand;
    }
    
    protected void clearCurrentHand() {
        getCurrentSeat().clearCurrentHand();
    }
    
    protected Seat getCurrentSeat() {
        if (!seatsInPlay.isEmpty())
            return seatsInPlay.peek();
        else
            return null;
    }
    
    protected Seat getSeat(int n) {
        return seats.get(n - 1);
    }
    
    protected void setDealerHand(Hand hand) {
        dealerHand = hand;
    }
    
    protected void dealCard(Seat seat) {
        
    }
}
