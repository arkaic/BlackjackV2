package model;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    private List<Card> cards = new ArrayList<Card>();
    private Bet bet          = new Bet(0);
    private Bet doubleBet    = new Bet(0);
    private boolean hiddenHoleCard = true;
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
    
    public int getBetAmount() {
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
    
    protected Card getCard(int n) {
        return cards.get(n);
    }
    
    protected List<Card> getCards() {
        return cards;
    }
    
    protected int size() {
        return cards.size();
    }
    
    /*Removes and returns last card of hand*/
    protected Card removeLastCard() {
        return cards.remove(cards.size() - 1);
    }
    
    protected void clearCards() {
        cards.clear();
    }
    
    protected int getFinalTotal() {
        if (getSoftTotal() <= 21) {
            return getSoftTotal();
        } else {
            return getHardTotal();
        }
    }
    
    private int getSoftTotal() {
        int softTotal = 0;
        boolean handHasAce = false;
        for (Card card : cards) {
            if (card.isAce()) {
                if (handHasAce) {
                    softTotal += card.getHardNumber();
                } else {
                    softTotal += card.getSoftNumber();
                    handHasAce = true;
                }
            } else {
                softTotal += card.getSoftNumber();
            }
        }
        
        return softTotal;
    }
    
    protected int getHardTotal() {
        int hardTotal = 0;
        for (Card card : cards) {
            hardTotal += card.getHardNumber();
        }
        
        return hardTotal;
    }
    
    protected boolean isBlackjack() {   
        return (isTwoCards() && getFinalTotal() == 21);
    }
    
    protected boolean isHard21() {
        return (getHardTotal() == 21);
    }
    
    protected boolean isBust() {
        return (getFinalTotal() > 21);
    }
    
    /*If hand is made up of two aces*/
    protected boolean isAces() {
        return (cards.size() == 2 && cards.get(0).isAce() && 
                cards.get(1).isAce());
    }
    
    protected boolean isAtLeast17() {
        return (getFinalTotal() >= 17);
    }
    
    protected boolean isDealer() {
        return isDealer;
    }
    
    protected boolean isOneCard() {
        return (cards.size() == 1);
    }
    
    public boolean isTwoCards() {
        return (cards.size() == 2);
    }
    
    public boolean isAPair() {
        return (cards.size() == 2 && 
                (cards.get(0).getHardNumber() == cards.get(1).getHardNumber()));
    }
    
    protected boolean isEmpty() {
        return cards.isEmpty();
    }
    
    protected void setHoleCardVisible(boolean bool) {
        hiddenHoleCard = !bool;
    }

    public String toString() {
        String str = "";
        if (isDealer) {
            if (hiddenHoleCard) {
                if (!cards.isEmpty())
                    str += "[" + cards.get(0) + ", N]";
            } else {
                str += cards.toString();
            }
            
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
