package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;

import controller.GameController;
import view.BlackjackView;
import model.objects.*;

public class BjGameModel implements GameModel{

    private SeatManager seatManager     = new SeatManager();
    private List<Card> deck             = new ArrayList<Card>();
    private List<Card> discards         = new ArrayList<Card>();
    private Hand dealerHand             = new Hand("dealer");
    private Monetary monetary;
    private BlackjackView view;
    private GameController controller;
    
    public BjGameModel() {
        int numberOfDecks = 6;
        int initialBankroll = 50;
        
        monetary = new Monetary(initialBankroll);
        generateDeck(numberOfDecks);
        deck.add(0, new Card(10, 'D'));
        deck.add(0, new Card('A', 'D'));
        deck.add(0, new Card('A', 'D'));
        deck.add(0, new Card('A', 'D'));
        deck.add(0, new Card(10, 'D'));
        deck.add(0, new Card(10, 'D'));
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
        payBlackjackHands();
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
    
    /** 
     * If dealer has blackjack, dealFirstHands() will not continue; A new round
     * of play will be initiated, starting all over. The same also happens
     * when, if an ace is showing and all player hands have blackjacks AND they
     * all decide to take even money.
     */
    private void doAceOrFaceProcedure() {
        Card upCard = dealerHand.getCard(0);
        view.updateDisplays();
        
        if (upCard.isAce()) {
            askForEvenMoney();
            if (!seatManager.areSeatsEmptyOfHands()) {
                controller.waitForInsurance();
            }
        }
        
        if (seatManager.areSeatsEmptyOfHands()) {
            initiateNewRound();
        } else {
            if (dealerHand.isBlackjack()) {
                pushBlackjackHands();
                
                if (monetary.getInsurance() > 0) {
                    controller.displayMessage("Insurance payed");
                    monetary.payInsurance();
                }
                
                initiateNewRound();
            }
        }
    }
    
    private void askForEvenMoney() {
        for (Seat seat : seatManager.getSeats()) {
            if (seat.hasHands()) {
                if (seat.getHand(0).isBlackjack()) {
                    int option = JOptionPane.showConfirmDialog(view,
                            "Even money for seat " + seat.getSeatNumber() + "?",
                            "Blackjack hand has an option",
                            JOptionPane.YES_NO_OPTION, 
                            JOptionPane.QUESTION_MESSAGE);
                    if (option == JOptionPane.YES_OPTION) {
                        monetary.pay(seat.getHand(0));
                        seat.clearHands();
                    }
                    view.updateDisplays();
                }
            }
        }
    }

    private void pushBlackjackHands() {
        for (Seat seat : seatManager.getSeats()) {
            if (seat.hasHands()) {
                if (seat.getHand(0).isBlackjack()) {
                    monetary.push(seat.getHand(0));
                }
            }
        }
    }
    
    private void payBlackjackHands() {
        for (Seat seat : seatManager.getSeats()) {
            if (seat.hasHands()) {
                if (seat.getHand(0).isBlackjack()) {
                    monetary.payBlackjack(seat.getHand(0));
                }
            }
        }
    }
    
    private void initiateNewRound() {
        controller.displayMessage("Starting new round...");
        if (!seatManager.areSeatsEmptyOfHands()) 
            seatManager.clearAllHands();
        seatManager.resetAllSeats();
        view.updateDisplays();
        //TODO set spinners to the previous initial bets, subtracting from 
        //bankroll as needed, starting from left to right. 
        controller.updateViewComponentsForNewRound();
    }
    
    @Override
    public void hit() {
        getCurrentHand().addCard(deck.remove(0));
        view.updateDisplays();
        if (getCurrentHand().isBust()) {
            controller.displayMessage("Player BUSTED!");
            seatManager.clearCurrentHand();
            stay();
        } else if (getCurrentHand().isHard21()) {
            controller.displayMessage("Player will stay at hard 21");
            stay();
        } else {
            return;
        }
    }

    @Override
    public void surrender() {
        monetary.surrender(getCurrentHand()); //TODO test
        seatManager.clearCurrentHand();
        view.updateDisplays();
        stay();
    }

    @Override
    public void doubleDown() {
        monetary.pay(getCurrentHand());
        getCurrentHand().addCard(deck.remove(0));
        view.updateDisplays();
        if (getCurrentHand().isBust()) {
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
            controller.displayMessage("Showing dealer hand");
            //TODO final dealer procedure
        }
        view.updateDisplays();
    }

    @Override
    public void setInitialBet(int seatNum, int amount) {
        seatManager.setBet(seatNum, amount);
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
     
    @Override
    public void setController(GameController controller) {
        this.controller = controller;
    }
    
    @Override
    public void setInsurance(int amount) {
        monetary.setInsurance(amount);
        view.updateDisplays();
    }
    @Override
    public int getMaxInsurance() {
        int runningTotal = 0;
        for (Seat seat : seatManager.getSeats()) {
            if (seat.hasHands()) {
                runningTotal += seat.getHand(0).getBetAmount();
            }
        }
        return (int) (runningTotal / 2);
    }
    @Override
    public int getInsurance() {
        return monetary.getInsurance();
    }
}
