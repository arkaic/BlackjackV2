package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import controller.GameController;
import view.BlackjackView;
import model.objects.*;

public class BjGameModel implements GameModel{

    private SeatManager seatManager     = new SeatManager();
    private List<Card> deck             = new ArrayList<Card>();
    private List<Card> discards         = new ArrayList<Card>();
    private Hand dealerHand             = new Hand("dealer");
    private String gameState            = "Start";
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
        deck.add(0, new Card(9, 'D'));
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
        
        Card upCard = dealerHand.getCard(0);
        view.updateDisplays();
        
        if (upCard.isAce()) {
            askForEvenMoney();
            if (seatManager.areSeatsEmptyOfHands()) {
                initiateNewRound();
            } else {
                gameState = "Insurance";
                view.updateDisplays();
                controller.displayMessage("Option to make an insurance bet");
            }
        } else {
            checkForBlackjack();
        }
        
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
    public void checkForBlackjack() {
        if (dealerHand.isBlackjack()) {
            controller.displayMessage("Dealer has Blackjack");
            pushBlackjackHands();
            initiateNewRound();
        } else {
            if (dealerHand.getCard(0).isFace() || dealerHand.getCard(0).isAce()) 
                controller.displayMessage("Dealer does not have Blackjack");
            payBlackjackHands();
            if (seatManager.areSeatsEmptyOfHands()) {
                initiateNewRound();
            } else {
                seatManager.createPlayOrder();
                seatManager.changeCurrentHand();
                gameState = "Play";  
                view.updateDisplays();
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
                    controller.displayMessage("Seat " + seat.getSeatNumber()
                            + "'s Blackjack payed");
                    seat.clearHands();
                    view.updateDisplays();
                }
            }
        }
    }
    
    private void initiateNewRound() {
        gameState = "Start";
        controller.displayMessage("Starting new round...");
        if (!seatManager.areSeatsEmptyOfHands()) 
            seatManager.clearAllHands();
        seatManager.resetAllSeats();
        dealerHand.clearCards();
        view.updateDisplays();
        controller.updateViewComponentsForNewRound();
        view.updateDisplays();
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
        if (getCurrentHand().isAces()) {
            getCurrentSeat().splitHand(new Hand("player"));
            getCurrentHand().addCard(deck.remove(0));
            seatManager.changeCurrentHand();
            getCurrentHand().addCard(deck.remove(0));
            stay();
        } else {
            getCurrentSeat().splitHand(new Hand("player"));
            hit();
        }
    }

    /**
     * If stay is clicked, and the next hand has only one card, as a result of
     * splitting, an automatic hit() is called.
     * If it is the dealer hand, that means all player hands have been iterated
     * over and decided, so all that's left to do is to deal cards to the 
     * dealer.
     */
    @Override
    public void stay() {
        seatManager.changeCurrentHand();
        view.updateDisplays();
        if (getCurrentHand().isOneCard()) {
            hit();
        } else if (getCurrentHand().isDealer()) {
            controller.displayMessage("Showing dealer hand");
            playDealerHand();
        }
    }
    
    /**
     * Since I wanted each card to be dealt to the dealer at specific 1 second
     * intervals instead of all at once to enhance user understanding and 
     * perception, I needed to use a worker thread to avoid GUI hang ups. 
     * Thus, SwingWorker is brought in, an inner class used to act as the
     * worker thread that runs the dealing-to-dealer code concurrently with 
     * the GUI.
     */
    private void playDealerHand() {
        dealerHand.setHoleCardVisible(true);
        view.updateDisplays();
        controller.displayMessage("Dealer has a " + dealerHand.getFinalTotal());
        
        class DealerPlaysWorker extends SwingWorker<Void, Void> {
            
            @Override
            protected Void doInBackground() {
                dealCardsToDealer();
                compareDealerHandToPlayerHands();
                initiateNewRound();
                return null;
            }
            
            private void dealCardsToDealer() {
                while (dealerHand.getFinalTotal() < 17) {
                    dealerHand.addCard(deck.remove(0));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    view.updateDisplays();
                }
                controller.displayMessage("Dealer stays at " 
                        + dealerHand.getFinalTotal());
            }
            
            private void compareDealerHandToPlayerHands() {
                for (Seat seat : seatManager.getSeats()) {
                    if (seat.hasHands()) {
                        for (Hand hand : seat.getHands()) {
                            int handTotal = hand.getFinalTotal();
                            int dealerTotal = dealerHand.getFinalTotal();
                            if (handTotal > dealerTotal && handTotal <= 21) {
                                controller.displayMessage("Seat " +
                                        seat.getSeatNumber() + "'s hand wins");
                                monetary.pay(hand);
                            } else if (handTotal == dealerTotal) {
                                monetary.push(hand);
                                controller.displayMessage("Seat " +
                                        seat.getSeatNumber() + "'s hand pushes");
                            } else {
                                controller.displayMessage("Seat " + 
                                        seat.getSeatNumber() + "'s hand loses");
                            }
                            view.updateDisplays();
                        }
                    }
                }
            }
        }
        
        new DealerPlaysWorker().execute();
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
    @Override
    public String getState() {
        return gameState;
    }
}
