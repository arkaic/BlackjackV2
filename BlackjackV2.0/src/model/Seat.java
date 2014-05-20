package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Seat {

    private int seatNumber;
    private List<Hand> hands;
    private Bet initialBet; //before it is put into Hand
    
    public Seat(int num) {
        seatNumber = num;
        hands = new ArrayList<Hand>();
        initialBet = new Bet(0);
    }

//    protected void 
    
    protected void changeBet(int amount) {
        //initialBet.changeAmount = amount;
    }
    
    protected Iterator<Hand> getHandIterator() {
        return hands.iterator();
    }
}
