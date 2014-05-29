package model;

/*
 * Handles the logic concerning hand totals, decisions on whether or not
 * player/dealer busts, and comparisons between player and dealer. 
 */
public class LogicHandler {
    
    private Hand player;
    private Hand dealer;

    protected void decideOnHandTotal(Hand hand) {
        if (hand.isBust()) {
            
        } else if (hand.isHard21()) {
            
        } else {
            
        }
    }
    
    protected void setDealerHand(Hand hand) {
        dealer = hand;
    }
    
    protected void setPlayerHand(Hand hand) {
        player = hand;
    }
}
