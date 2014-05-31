package model;

public class Monetary {

    private int bankroll = 0;
    private int insurance = 0;
//    private Bet houseBankroll;
    
    public Monetary(int amount) {
        bankroll = amount;
    }
    
    protected int getBankroll() {
        return bankroll;
    }
    
    protected int getInsurance() {
        return insurance;
    }
    
    protected void setInsurance(int amount) {
        insurance = amount;
    }
    
    
    protected void pay(Hand hand) {
        int bet = hand.getBetAmount();
        int doubleBet = hand.getDoubleBetAmount();
        int payout = bet + doubleBet;
        bankroll += bet + doubleBet + payout;
    }
    
    protected void payBlackjack(Hand hand) {
        int bet = hand.getBetAmount();
        int payout = (int) (bet * 1.5);
        bankroll += bet + payout;
    }
    
    protected void push(Hand hand) {
        int bet = hand.getBetAmount();
        int doubleBet = hand.getDoubleBetAmount();
        bankroll += bet + doubleBet;
    }

    protected void surrender(Hand hand) {
        int bet = hand.getBetAmount();
        int payout = (int) (bet / 2);
        bankroll += payout;
    }
    
    protected void take(Hand hand) {
        
    }
    
    protected void payInsurance() {
        int payout = insurance * 2;
        bankroll += insurance + payout;
        insurance = 0;
    }
    
    protected void addToBankroll(int amount) {
        bankroll += amount;
    }
    
    protected void subtractFromBankroll(int amount) {
        bankroll -= amount;
    }
}
