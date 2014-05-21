package model;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    private List<Card> cards = new ArrayList<Card>();
    public int temp;
    private boolean isDealer;
    
    public Hand(int n, boolean isDealer) {
        temp = n;
        this.isDealer = isDealer;
    }

    public String toString() {
        return "[Hand " + temp + "]";
    }
    
    protected boolean isDealer() {
        return isDealer;
    }
}
