package model;

/**
 * ACTUAL NUMBERS: J    K   Q   A
 *                 11   12  13  1
 * @author henry
 *
 */
public class Card {
    
    private int  hardNumber;
    private int  softNumber;
    private int  actualNumber; 
    private char suit; //D,H,S,C; N for null
    
    public Card(int number, char suit) {
        assignCardNumber(number);
        this.suit = suit;
    }
    public Card(char character, char suit) {
        switch (character) {
        case 'A': assignCardNumber(1);
                  break;
        case 'J': assignCardNumber(11);
                  break;
        case 'Q': assignCardNumber(12);
                  break;
        case 'K': assignCardNumber(13);
                  break;
        }
        
        this.suit = suit;
    }
    
    private void assignCardNumber(int number) {
        actualNumber = number;
        if (number > 10) {
            hardNumber = 10;
            softNumber = 10;
        } else {
            hardNumber = number;
            if (number == 1)
                softNumber = 1;
            else
                softNumber = number;
        }        
    }

    /*Returns number ("1", etc) or "A/J/Q/K"*/
    public String toString() {
        String s = "";
        if (actualNumber == 1) {
            s += 'A';
        } else if (actualNumber == 11) {
            s += 'J';
        } else if (actualNumber == 12) {
            s += 'Q';
        } else if (actualNumber == 13) {
            s += 'K';
        } else {
            s += actualNumber;
        }
        return s;
    }
}
