package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;

import view.BlackjackView;
import model.objects.*;

public class BjGameModel implements GameModel{

    private SeatManager seatManager     = new SeatManager();
    private List<Card> deck             = new ArrayList<Card>();
    private List<Card> discards         = new ArrayList<Card>();
    private Hand dealerHand             = new Hand("dealer");
    private Monetary monetary;
    private BlackjackView view;
    
    public BjGameModel() {
        int numberOfDecks = 6;
        int initialBankroll = 50;
        
        monetary = new Monetary(initialBankroll);
        generateDeck(numberOfDecks);
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
    public Hand getHand() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Seat getSeat(int seatNum) {
        return seatManager.getSeat(seatNum);
    }

    @Override
    public int getBankroll() {
        return monetary.getBankroll();
    }
    
    @Override
    public void addToBankroll(int amount) {
        monetary.addToBankroll(amount);
    }
    @Override
    public void subtractFromBankroll(int amount) {
        monetary.subtractFromBankroll(amount);
    }

    @Override
    public Hand getDealerHand() {
        return dealerHand;
    }

    @Override
    public void dealFirstHands() {
        seatManager.createEmptyHandsForSeatsWithInitialBets();
        passOutFirstCards();
        doAceOrFaceProcedure();
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
    
    private void doAceOrFaceProcedure() {
        Card upCard = dealerHand.getCard(0);
        if (upCard.isAce()) {
            for (Seat seat : seatManager.getSeats()) {
                if (seat.getHand(0).isBlackjack()) {
                    //TODO ask for even money
                    //     if any, pay it, clear the hand
                }
            }
            /* TODO -below-
             *   if insurance:
             *     display insurance spinner
             */
        }
        if (dealerHand.isBlackjack()) {
            /*  TODO -below-
             *  take all losing, push blackjacks
             *  clear hands
             *  pay insurances
             *  initiate new round
             */
        }
    }
    
    @Override
    public void hit() {
        getCurrentHand().addCard(deck.remove(0));
        view.updateDisplays();
        if (getCurrentHand().isBust()) {
            JOptionPane.showMessageDialog(view, "Player BUSTED!");
            //TODO take money
            seatManager.clearCurrentHand();
            stay();
        } else if (getCurrentHand().isHard21()) {
            JOptionPane.showMessageDialog(view, "Player will stay at hard 21");
            stay();
        } else {
            return;
        }
    }

    @Override
    public void surrender() {
        //TODO take half money
        seatManager.clearCurrentHand();
        view.updateDisplays();
        stay();
    }

    @Override
    public void doubleDown() {
        //TODO double money
        getCurrentHand().addCard(deck.remove(0));
        view.updateDisplays();
        if (getCurrentHand().isBust()) {
            //TODO take money
            seatManager.clearCurrentHand();
            view.updateDisplays();
        }
        stay();
    }

    @Override
    public void split() {
        getCurrentSeat().splitHand(new Hand("player"));
        if (getCurrentHand().isAces()) {
            getCurrentHand().addCard(deck.remove(0));
            seatManager.changeCurrentHand();
            getCurrentHand().addCard(deck.remove(0));
            stay();
        } else {
            hit();
        }
    }

    @Override
    public void stay() {
        seatManager.changeCurrentHand();
        view.updateDisplays();
        if (getCurrentHand().isOneCard()) {
            hit();
        } else if (getCurrentHand() == dealerHand) {
            dealerHand.setHoleCardVisible(true);
            view.updateDisplays();
            JOptionPane.showMessageDialog(view, "Showing dealer hand");
            //TODO final dealer procedure
        }
        view.updateDisplays();
    }

    @Override
    public void takeInsurance() {
        // TODO implement
    }

    @Override
    public void setInitialBet(int seatNum, int amount) {
        seatManager.setBet(seatNum, amount);
        view.updateDisplays();
    }

    public Hand getCurrentHand() {
        return seatManager.getCurrentHand();
    }

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
