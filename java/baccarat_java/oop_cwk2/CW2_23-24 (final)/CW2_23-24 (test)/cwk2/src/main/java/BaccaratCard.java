// TODO: Implement the BaccaratCard class in this file
public class BaccaratCard extends Card {
    // Constructor
    public BaccaratCard(Rank rank, Suit suit) {
        super(rank, suit);
    }

    public int value() {
        int rankValue = getRank().ordinal();
        if (rankValue < 9) {
            return rankValue + 1; 
        } else {
            return 0; 
        }
    }

    // Override toString method to return a two-character string representation
    @Override
    public String toString() {
        return String.format("%c%s", getRank().getSymbol(), getSuit().getSymbol());
    }

    // Override equals method to compare BaccaratCard objects
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof BaccaratCard))
            return false;
        BaccaratCard other = (BaccaratCard) obj;
        return this.getRank() == other.getRank() && this.getSuit() == other.getSuit();
    }

    // Override compareTo method to compare BaccaratCard objects
    @Override
    public int compareTo(Card o) {
        int suitComparison = this.getSuit().compareTo(o.getSuit());
        if (suitComparison != 0) {
            return suitComparison;
        } else {
            return this.getRank().compareTo(o.getRank());
        }
    }
}