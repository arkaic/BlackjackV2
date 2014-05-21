package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.objects.*;

public class BjGameModel implements GameModel{

    private SeatManager seatManager;
    private List<Card> deck;
    private Hand dealerHand;
    
    public BjGameModel() {
        seatManager = new SeatManager();

    }

    public void test() {
      
      //for each seat, add three hands for each
      for (int i = 1; i <= 6; i++) {
          for (int j = 1; j <= 3; j++) {
              seatManager.getSeat(i).addHand(new Hand(j, false)); 
          }
      }
//      for (int i = 1; i <= 6; i++) {
//          for (int j = 0; j < 3; j++) {
//              System.out.print(seatManager.getSeat(i) + ": ");
//              System.out.println(seatManager.getSeat(i).getHand(j));
//          }
//      }
      
      seatManager.createSeatPlayOrder();
      System.out.println(toStringCurrents());
      int c = 0;
      while (c < 19) {
          seatManager.changeCurrentHand();
          System.out.println(toStringCurrents());
          c++;
      }
    }
    
    @Override
    public List<Card> getDeck() {
        // TODO Auto-generated method stub
        return null;
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
    public Seat getSeat() {
        // TODO Auto-generated method stub
        return null;
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
        // TODO Auto-generated method stub
        
    }

    @Override
    public void dealFirstHands() {
        // TODO Auto-generated method stub
        // TODO calls a beginPlay() method to begin play
    }
    
    private void beginPlay() {
        //TODO implement, should be called after dealFirst phase
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
    public void changeBets() {
        // TODO Auto-generated method stub
        
    }

    private void notifyController() {
        //tell controller something has changed. controller should call
        //update 
        //controller.update()x
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
}
