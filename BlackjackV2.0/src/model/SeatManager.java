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
    private Stack<Seat> seatsInPlay = new Stack<>();
//    private Seat currentSeat;
    private Hand currentHand;
    private Iterator<Hand> handIterator;
    
    public SeatManager() {
        initSeats();
    }
    /**
     * Put in six seats in the list 
     */
    private void initSeats() {
        for (int i = 1; i <= 6; i++) {
            seats.add(new Seat(i));
        }
    }
    
    //call from model.beginPlay()
    protected void createSeatPlayOrder() {
        if (!seatsInPlay.isEmpty()) {
            seatsInPlay.clear();
        }
        
        for (Seat seat : seats) {
            if (seat.hasHand()) {
                seatsInPlay.push(seat);
            }
        }
    }
    
    protected void changeCurrentHand() {
        if (!seatsInPlay.isEmpty()) {
            Seat currentSeat = getCurrentSeat();
            if (currentSeat.hasNextHand()) {
                currentSeat.changeCurrentHand();
            } else {
                seatsInPlay.pop();
                changeCurrentHand();
            }
        }
    }
    
    //TODO returns null if there are no more hands
    protected Hand getCurrentHand() {
        if (!seatsInPlay.isEmpty()) {
            return getCurrentSeat().getCurrentHand();
        } else {
            return null;
        }
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
}
