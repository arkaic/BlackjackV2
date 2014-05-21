package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.objects.*;

public class BjGameModel implements GameModel{

    private SeatManager seatManager;
    private List<Card> deck;
    
    public BjGameModel() {
        seatManager = new SeatManager();
    }

    public void test() {
      List<Integer> list = new ArrayList<>();
      for (int i = 0; i < 3; i++) {
          list.add(new Integer(i));
      }
      Iterator<Integer> it = list.iterator();
      System.out.println(it.next());
      System.out.println(it.next());
      System.out.println(it.next());
      System.out.println(it.next());
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
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Seat getCurrentSeat() {
        // TODO Auto-generated method stub
        return null;
    }
}
