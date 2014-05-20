package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *  Manages seats and seat order during play.q
 */
public class SeatManager{

    private List<Seat> seats = new ArrayList<Seat>();
//    private List<Seat> seatsInPlay = new ArrayList<Seat>();
    private Seat currentSeat;
    private Hand currentHand;
    private Iterator<Seat> seatIterator;
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
    
    protected void setCurrentSeatAndHand() {
        if (seatIterator == null) {
            seatIterator = seats.iterator();
            currentSeat = seatIterator.next();
            handIterator = currentSeat.getHandIterator();
            currentHand = handIterator.next();
        }
    }
    private void setCurrentHand() {
        
    }
}
