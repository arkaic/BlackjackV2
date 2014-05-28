package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import view.BlackjackView;
import model.objects.*;

public class BjGameModel implements GameModel{

    private SeatManager seatManager;
    private List<Card> deck     = new ArrayList<Card>();
    private List<Card> discards = new ArrayList<Card>();
    private Hand dealerHand     = new Hand("dealer");
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
        
        //Builds a new deck by randomly picking cards out of the preceding deck
        List<Card> buildingDeck = new ArrayList<>();
        List<Card> workingDeck  = deck;
        int workingSize = workingDeck.size();
        Random rand = new Random();
        while (workingSize > 0) {
            int randomCardIndex = rand.nextInt((workingSize - 1 - 0) + 1) + 0;
            buildingDeck.add(workingDeck.get(randomCardIndex));
            workingDeck.remove(randomCardIndex);
            workingSize = workingDeck.size();
        }
        deck = buildingDeck;
        view.updateDisplays();
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
        return dealerHand;
    }

    @Override
    public void dealFirstHands() {
        seatManager.createEmptyHandsForSeatsWithInitialBets();
        passOutFirstCards();
        /* TODO -BELOW-
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
         */
        seatManager.createPlayOrder();
        seatManager.changeCurrentHand();
        view.updateDisplays();
    }

    private void passOutFirstCards() {
        for (int i = 1; i <= 2; i++) {
            for (int j = 1; j <= 7; j++) {
                if (j != 7) {
                    Seat seat = seatManager.getSeat(j);
                    if (seat.hasInitialBet()) {
                        seat.getHand(0).addCard(deck.remove(0));
                    }
                } else {
                    dealerHand.addCard(deck.remove(0));
                }
            }
        }
        seatManager.setDealerHand(dealerHand);
    }
    
    @Override
    public void hit() {
        getCurrentHand().addCard(deck.remove(0));
        /* TODO -BELOW-
         * -Check totals so that it don't bust
         * -If it busts, 
         *      seatManager.clearCurrentHand();
         *      seatManager.changeCurrentHand();
         * -If currentHand = dealerHand
         *      final dealer procedure
         */
        view.updateDisplays();
    }

    @Override
    public void stay() {
        seatManager.changeCurrentHand();
        /* TODO -BELOW-
         * -If currentHand = dealerHand
         *      final dealer procedure
         */
    }

    @Override
    public void surrender() {
        seatManager.clearCurrentHand();
        seatManager.changeCurrentHand();
        /* TODO -BELOW-
         * -If currentHand = dealerHand
         *      final dealer procedure
         */
    }

    @Override
    public void split() {
       /* TODO -BELOW-
        * -If split aces
        *      getCurrentHand().addCard(deck.remove(0));
        *      seatManager.changeCurrentHand();
        *      getCurrentHand().addCard(deck.remove(0));
        *      seatManager.changeCurrentHand()
        * -If currentHand = dealerHand
        *      final dealer procedure
        */      
    }

    @Override
    public void doubleDown() {
        getCurrentHand().addCard(deck.remove(0));
        /* TODO -BELOW-
         * -Check totals so that it don't bust
         */
        stay();
    }

    @Override
    public void takeInsurance() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setInitialBet(int seatNum, int amount) {
        seatManager.setBet(seatNum, amount);
        view.updateDisplays();
    }

    private Hand getCurrentHand() {
        return seatManager.getCurrentHand();
    }

    private Seat getCurrentSeat() {
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
