import java.util.Scanner;

public class Baccarat {
    public static void main(String[] args) {
        if (args.length > 0 && ("-i".equals(args[0]) || "--interactive".equals(args[0]))) {
            playInteractive();
        } else {
            playNonInteractive();
        }
    }

    private static void playInteractive() {
        Shoe shoe = new Shoe(6); 
        shoe.shuffle();

        int rounds_played = 0;
        int player_wins = 0;
        int banker_wins = 0;
        int ties = 0;

        Scanner scanner = new Scanner(System.in);
        String response = "y";

        while (shoe.size() >= 6 && response.startsWith("y")) {
            rounds_played++;
            System.out.println("\nRound " + rounds_played);

            BaccaratHand playerHand = new BaccaratHand();
            BaccaratHand bankerHand = new BaccaratHand();

            dealCardToPlayer(playerHand, shoe);
            dealCardToBanker(bankerHand, shoe);
            dealCardToPlayer(playerHand, shoe);
            dealCardToBanker(bankerHand, shoe);

            System.out.println("Player: " + playerHand);
            System.out.println("Banker: " + bankerHand);

            // Determine the winner of the round
            int player_value = playerHand.value();
            int banker_value = bankerHand.value();
            if (player_value > banker_value) {
                System.out.println("Player Win!");
                player_wins++;
            } else if (player_value < banker_value) {
                System.out.println("Banker Win!");
                banker_wins++;
            } else {
                System.out.println("Tie!");
                ties++;
            }

            System.out.print("\nAnother round? (y/n): ");
            response = scanner.nextLine().trim().toLowerCase();
        }

        System.out.println("\n" + rounds_played + " rounds played");
        System.out.println(player_wins + " player wins");
        System.out.println(banker_wins + " banker wins");
        System.out.println(ties + " ties");
    }

    private static void playNonInteractive() {
        Shoe shoe = new Shoe(6); 
        shoe.shuffle();
        int rounds_played = 0;
        int player_wins = 0;
        int banker_wins = 0;
        int ties = 0;

        while (shoe.size() >= 6) {
            rounds_played++;
            System.out.println("\nRound " + rounds_played);

            BaccaratHand playerHand = new BaccaratHand();
            BaccaratHand bankerHand = new BaccaratHand();

            dealCardToPlayer(playerHand, shoe);
            dealCardToBanker(bankerHand, shoe);
            dealCardToPlayer(playerHand, shoe);
            dealCardToBanker(bankerHand, shoe);


            int player_value = playerHand.value();
            int banker_value = bankerHand.value();

            System.out.println("Player: " + playerHand.toString() + " = " + player_value);
            System.out.println("Banker: " + bankerHand.toString() + " = " + banker_value);

            if (player_value > banker_value) {
                System.out.println("Player Win!");
                player_wins++;
            } else if (player_value < banker_value) {
                System.out.println("Banker Win!");
                banker_wins++;
            } else {
                System.out.println("Tie!");
                ties++;
            }
        }

        // Display game statistics
        System.out.println("\n" + rounds_played + " rounds played");
        System.out.println(player_wins + " player wins");
        System.out.println(banker_wins + " banker wins");
        System.out.println(ties + " ties");
    }

    private static void dealCardToPlayer(BaccaratHand hand, Shoe shoe) {
        BaccaratCard card = shoe.deal();
        hand.add(card);
    }

    private static void dealCardToBanker(BaccaratHand hand, Shoe shoe) {
        BaccaratCard card = shoe.deal();
        hand.add(card);
    }
}
