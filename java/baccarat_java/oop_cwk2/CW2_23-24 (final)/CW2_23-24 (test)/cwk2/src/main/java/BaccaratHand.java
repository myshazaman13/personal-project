// TODO: Implement the BaccaratHand class in the file
import java.util.Collections;

public class BaccaratHand extends CardCollection {

    
    public BaccaratHand() {
        super();
    }

    // to get the points value of the hand in Baccarat
    public int value() {
        int sum = 0;
        for (Card card : cards) {
            sum += card.value();
        }
        return sum % 10; 
    }

    public boolean isNatural() {
        return size() == 2 && (value() == 8 || value() == 9);
    }

    // Override toString method to return two-character representations of each card
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Card card : cards) {
            sb.append(card.toString()).append(" ");
        }
        return sb.toString().trim(); 
    }

    // Override add method to add a BaccaratCard object
    @Override
    public void add(Card card) {
        super.add(card);
    }

    // Override size method to return the number of cards in the hand
    @Override
    public int size() {
        return super.size();
    }

}
