// TODO: Implement the Shoe class in this file
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

public class Shoe extends CardCollection {

    public Shoe(int numDecks) {
        super();
        if (numDecks != 6 && numDecks != 8) {
            throw new CardException("Number of decks must be 6 or 8");
        }
        initializeShoe(numDecks);
    }

    private void initializeShoe(int numDecks) {
        for (int i = 0; i < numDecks; i++) {
            for (Card.Suit suit : Card.Suit.values()) {
                for (Card.Rank rank : Card.Rank.values()) {
                    add(new BaccaratCard(rank, suit));
                }
            }
        }
    }

    // Override size method to return the number of cards in the shoe
    @Override
    public int size() {
        return super.size();
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public BaccaratCard deal() {
        if (isEmpty()) {
            throw new CardException("Attempting to deal from an empty shoe");
        }
        return (BaccaratCard) cards.remove(0);
    }

}
