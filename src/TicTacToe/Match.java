package TicTacToe;

import java.util.Scanner;

public class Match {
    private static void IncrementPlayerWins(boolean isX, Player player1, Player player2) {
        if (isX) {
            player1.incrementMatchesWon();
            System.out.println(player1.getName() + " - " + player1.getMatchesWon());
        } else {
            player2.incrementMatchesWon();
            System.out.println(player2.getName() + " - " + player2.getMatchesWon());
        }
    }

    public static void Initialize(Board board, boolean isX, Scanner scanner, Player player1, Player player2){
        while (true) {
            board.printBoard();
            System.out.println("Turno do jogador: " + (isX ? 'X' : 'O'));
            int position = scanner.nextInt();

            if (board.makeMove(position, isX)) {
                System.out.println("Movimento invalido");
                continue;
            }

            if (board.checkWin(isX)) {
                board.printBoard();
                System.out.println((isX ? 'X' : 'O') + " ganhou!");
                IncrementPlayerWins(isX, player1, player2);
                break;
            }

            if (board.checkTie()) {
                board.printBoard();
                System.out.println("Empate");
                break;
            }

            isX = !isX;
        }
    }
}
