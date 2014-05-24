package model;

public class Bet {

    private int amount;
    
    public Bet(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }
    
    protected void setAmount(int newAmount) {
        this.amount = newAmount;
    }
    
    @Override
    public String toString() {
        return "$" + amount;
    }
}
