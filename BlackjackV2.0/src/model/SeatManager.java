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
    }
    /*Put in six seats in the list */
    private void initSeats() {
        for (int i = 1; i <= 6; i++) {
            seats.add(new Seat(i));
        }
    }
    
    protected void createEmptyHandsForSeatsWithInitialBets() {
        for (int i = 1; i <= 6; i++) {
            if (getSeat(i).hasInitialBet()) {
                Hand emptyHand = new Hand("player");
                emptyHand.setBetAmount(getSeat(i).getInitialBet().getAmount());
                getSeat(i).addHand(emptyHand);
            }
        }
    }
    
    /*Stacks up seats in play whose initial bets are more than $0.
     * Adds an empty hand to each of these seats and sets the corresponding
     * bets onto them
     */
    protected void createPlayOrder() {
        if (!seatsInPlay.isEmpty())
            seatsInPlay.clear();
        
        //Iterating backwards
        for (int i = seats.size(); i > 0; i--) {
            Seat seat = seats.get(i - 1);
            if (seat.hasHand()) {
                seatsInPlay.push(seat);
            }
        }
        
        //Set current hand
        changeCurrentHand();
    }

    //Returns null if action rounds haven't started yet
    protected Hand getCurrentHand() {
        return currentHand;
    }
    
    protected void clearCurrentHand() {
        getCurrentSeat().clearCurrentHand();
        changeCurrentHand();
        
    }
          
    /*If there are seats left, top seat is current seat, a*/
    private void changeCurrentHand() {
        if (!seatsInPlay.isEmpty()) {
            Seat currentSeat = getCurrentSeat();
            currentSeat.changeCurrentHand(); //Changes to either next hand or null
            
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
    
    /*Gets Seat on top of stack*/
    protected Seat getCurrentSeat() {
        if (!seatsInPlay.isEmpty())
            return seatsInPlay.peek();
        else
            return null;
    }
    
    protected void setBet(int seatNum, int amount) {
        getSeat(seatNum).setInitialBet(amount);
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
