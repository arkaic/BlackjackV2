package model;

public class Monetary {

    private int bankroll = 0;
    private Bet houseBankroll;
    
    public Monetary(int amount) {
        bankroll = amount;
    }
    
    public int getBankroll() {
        return bankroll;
    }
    
    protected void pay(Hand hand) {
        
    }
    
    protected void payBlackjack(Hand hand) {
        
    }
    
    protected void push(Hand hand) {
        
    }

    protected void surrender(Hand hand) {
        
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
