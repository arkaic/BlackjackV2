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
    public Hand(int n, String playingEntity) {
        this(playingEntity);
        temp = n;
    }

    public String toString() {
        return "[Hand " + temp + "]";
    }
    
    protected boolean isDealer() {
        return isDealer;
    }
    
    protected boolean isEmpty() {
        return cards.isEmpty();
    }
}
