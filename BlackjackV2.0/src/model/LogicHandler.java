package model;

/*
 * Handles the logic concerning hand totals, decisions on whether or not
 * player/dealer busts, and comparisons between player and dealer. 
 */
public class LogicHandler {
    
    private Hand player;
    private Hand dealer;
    
//    protected boolean isBust(Hand hand) {
//        return (hand.getFinalTotal() > 21);
//    }
//    
//    protected boolean isHard21(Hand hand) {
//        return (hand.getHardTotal() == 21);
//    }
//    
//    protected boolean isAtLeast17(Hand hand) {
//        return (hand.getFinalTotal() >= 17);
//    }

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
