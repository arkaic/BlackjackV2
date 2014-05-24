package model;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    private List<Card> cards = new ArrayList<Card>();
    private Bet bet          = new Bet(0);
    private Bet doubleBet    = new Bet(0);
    private boolean isDealer;
    public int temp;
    
    public Hand(Card card, String playingEntity) {
        if (card != null)
            cards.add(card);
        
        if (playingEntity.equalsIgnoreCase("dealer") ||
                playingEntity.equalsIgnoreCase("computer"))
            isDealer = true;
        else if (playingEntity.equalsIgnoreCase("player"))
            isDealer = false;
    }
    public Hand(String playingEntity) {
        this(null, playingEntity);
    }
    
    protected int getBetAmount() {
        return bet.getAmount();
    }
    
    protected int getDoubleBetAmount() {
        return doubleBet.getAmount();
    }
    
    protected void setBetAmount(int a) {
        bet.setAmount(a);
    }
    
    protected void setDoubleBetAmount(int a) {
        doubleBet.setAmount(a);
    }
    
    protected void addCard(Card card) {
        cards.add(card);
    }
    
    protected void clearCards() {
        cards.clear();
    }
    
    protected boolean isDealer() {
        return isDealer;
    }
    
    protected boolean isEmpty() {
        return cards.isEmpty();
    }

    public String toString() {
        String str = "";
        if (isDealer) {
            str += cards.toString();
        } else {
            str += "[" + bet.toString() + "]"; 
            if (doubleBet.getAmount() > 0) {
                str += "[" + doubleBet.toString() + "]";
            }
            if (!cards.isEmpty()) {
                str += cards.toString();
            }
        }
        return str;
    }
}
