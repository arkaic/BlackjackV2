package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import view.BlackjackView;
import model.objects.*;

public class BjGameModel implements GameModel{

    private SeatManager seatManager;
    private List<Card> deck = new ArrayList<Card>();
    private List<Card> discards;
    private Hand dealerHand;
    private BlackjackView view;
    
    public BjGameModel() {
        seatManager = new SeatManager();
        generateDeck(6);
        //TODO add option to vary the amount of decks
    }
    private void generateDeck(int numberofDecks) {
        for (int i = 1; i <= numberofDecks; i++) {
            for (int j = 1; j <= 13; j++) {
                deck.add(new Card(j, 'D'));
                deck.add(new Card(j, 'H'));
                deck.add(new Card(j, 'C'));
                deck.add(new Card(j, 'S'));
            }
        }
    }

    public void test() {
      
        seatManager.createSeatPlayOrder();
        System.out.println(toStringCurrents());
        int c = 0;
        while (c < 1) {
            seatManager.changeCurrentHand();
            System.out.println(toStringCurrents());
            c++;
        }
        
        System.out.println("");
        
//        seatManager.getCurrentSeat().addSplitHand(new Hand(4, false));
//        for (int i = 1; i <= 6; i++) {
//            for (int j = 0; j < 3; j++) {
//                System.out.println(seatManager.getSeat(i).toString() + ": " +
//                        seatManager.getSeat(i).getHand(j));
//                if (j == 2) {
//                    while (seatManager.getSeat(i).hands.size() > (j+1)) {
//                        j++;
//                        System.out.println(seatManager.getSeat(i).toString() + 
//                                ": " +seatManager.getSeat(i).getHand(j));
//                    }
//                }
//            }
//        }
    }
    
    @Override
    public List<Card> getDeck() {
        return deck;
    }

    @Override
    public Bet getBet() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Hand getHand() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Seat getSeat(int seatNum) {
        return seatManager.getSeat(seatNum);
    }

    @Override
    public Bet getBankroll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Hand getDealerHand() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void shuffle() {
        //Transfers discards to deck
        if (!discards.isEmpty()) {
            for (Card card : discards) {
                deck.add(card);
            }
            discards.clear();
        }
        //TODO shuffle deck
    }

    @Override
    public void dealFirstHands() {
        /* -TODO Give seats with bets an empty hand, and set the bets on them
         * -TODO deal hands
         * -TODO 
         * -if upcard = ace || face:
         *   if ace:
         *     for all player blackjacks
         *       ask for even money
         *     if insurance: 
         *       display insurance spinner
         *       //max should be half of total bets
         *       
         *   check hole card
         *   
         *   if Blackjack:
         *     take all, push blackjacks
         *     pay insurances
         *     initiate new round
         *   else:
         *     pay player blackjacks
         *   
         *   
         */
        seatManager.createSeatPlayOrder();
    }

    @Override
    public void hit() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void stay() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void surrender() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void split() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void doubleDown() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void takeInsurance() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void changeInitialBet(int seatNum, int amount) {
        seatManager.changeBet(seatNum, amount);
        view.updateDisplays();
    }

    @Override
    public Hand getCurrentHand() {
        return seatManager.getCurrentHand();
    }

    @Override
    public Seat getCurrentSeat() {
        return seatManager.getCurrentSeat();
    }
    
    private String toStringCurrents() {
        if (getCurrentHand() == null || getCurrentSeat() == null) {
            return "no current hand or seat";
        } else {
            return getCurrentSeat().toString() + ": " + getCurrentHand().toString();
        }
    }

    @Override
    public void setView(BlackjackView view) {
        this.view = view;
    }
}
