package practice.leetcode.ttt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class PlayTTT {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    TicTacToe game;

    public void startGame() {
        System.out.println("Enter player name");
        Player player1 = new Player(getUserInput(), new HumanMove(), Player.Symbol.X);

        Player player2 = new Player("A.I.", new SimpleMoveStrategy(), Player.Symbol.ZERO);
        System.out.println("\nHuman player " + player1.getName() +
                " vs Computer Player " + player2.getName() + ":");

        game = new TicTacToe(new Player[] {player1, player2});

        System.out.println("Welcome to Tic-Tac-Toe.");
        System.out.println("");
        System.out.println("Please make your move selection by entering "
                + "a number 1-9 corresponding to the movement "
                + "key on the right.\n");
        System.out.println(game.toString());

        int playFirst = new Random().nextInt(2);
        game.setPlayFirst(playFirst);
        System.out.println("It has been randomly decided that " + (playFirst == 0 ? player1.getName() : player2.getName()) + " will play first.");

        TTTResult result = game.playGame();

        System.out.println("Game Over");
        System.out.println(game);
        System.out.println(result);
    }

    protected static String getUserInput() {
        String input = "";
        try {
            input = reader.readLine();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return input;
    }

    public static void main(String[] args) {
        PlayTTT ttt = new PlayTTT();
        ttt.startGame();
    }
}
