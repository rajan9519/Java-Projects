package battleship;
import java.util.Scanner;
public class Game {
    Player player1;
    Player player2;
    Scanner scanner = new Scanner(System.in);

    public Game() {
        player1 = new Player();
        player2 = new Player();
    }

    public void startGame() {
        boolean toggle = true; // player1's turn
        System.out.println("Player 1, place your ships on the game field");
        player1.start();
        System.out.println("Press Enter and pass the move to another player");
        scanner.nextLine();
        System.out.println("Player 2, place your ships on the game field");
        player2.start();

        System.out.println("The game starts!");
        while (!player1.gameOver() && !player2.gameOver()) {
            System.out.println("Press Enter and pass the move to another player");
            scanner.nextLine();
            if (toggle) {
                System.out.println("Player 1, it's your turn:");
                player2.printFogOfWar();
                System.out.println("---------------------");
                player1.printGrid();
                player2.firstShot();
            }
            else {
                System.out.println("Player 2, it's your turn:");
                player1.printFogOfWar();
                System.out.println("---------------------");
                player2.printGrid();
                player1.firstShot();
            }
            toggle = !toggle;
        }

        if (toggle) {
            System.out.println("Player 2: You sank the last ship. You won. Congratulations!");
        } else {
            System.out.println("Player 2: You sank the last ship. You won. Congratulations!");
        }
    }
}
