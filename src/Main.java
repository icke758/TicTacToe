import TicTacToe.Board;
import TicTacToe.Match;
import TicTacToe.Player;
import Utils.Menu;

import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Board board = new Board();

        Map<String, String> names = Menu.PlayerNames();

        System.out.println("Player 1 = X");
        System.out.println("Player 2 = O");

        Player player1 = new Player(names.get("Player1"), 0, 'X');
        Player player2 = new Player(names.get("Player2"), 0, 'O');

        boolean isX = (player1.getTeam() == 'X');
        while (true) {
            Match.Initialize(board, isX, scanner, player1, player2);

            System.out.println("Voce gostaria de jogar novamente? s/n");
            String res = scanner.next();
            if("X".equals(res)) { //using this way is safer
                board.reset();
            } else {
                break;
            }
        }
        scanner.close();
    }
}