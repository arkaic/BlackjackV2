package model;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    private List<Card> cards = new ArrayList<Card>();
    private Bet bet          = new Bet(0);
    private Bet doubleBet    = new Bet(0);
    private boolean isDealer;
    public int temp;
    
    public Hand(int n, boolean isDealer) {
        temp = n;
        this.isDealer = isDealer;
    }
    public Hand(Card card, boolean isDealer) {
        cards.add(card);
        this.isDealer = isDealer;
    }

    public String toString() {
        return "[Hand " + temp + "]";
    }
    
    protected boolean isDealer() {
        return isDealer;
    }
}
