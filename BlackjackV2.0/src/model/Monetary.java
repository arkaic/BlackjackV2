package model;

public class Monetary {

    private int bankroll = 0;
    private int insurance = 0;
    private Bet houseBankroll;
    
    public Monetary(int amount) {
        bankroll = amount;
    }
    
    public int getBankroll() {
        return bankroll;
    }
    
    public int getInsurance() {
        return insurance;
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
    
    protected void payInsurance(Bet insurance) {
        
    }
    
    protected void addToBankroll(int amount) {
        bankroll += amount;
    }
    
    protected void subtractFromBankroll(int amount) {
        bankroll -= amount;
    }
}
