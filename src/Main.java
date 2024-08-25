import ClientServer.TicTacToeClient;
import ClientServer.TicTacToeServer;
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

        System.out.println("Choose game mode:");
        System.out.println("1. Local Play");
        System.out.println("2. Host LAN Game");
        System.out.println("3. Join LAN Game");
        int gameMode = scanner.nextInt();

        Player player1 = null;
        Player player2 = null;

        switch (gameMode) {
            case 1: // Local Play
                Map<String, String> names = Menu.PlayerNames();
                player1 = new Player(names.get("Player1"), 0, 'X');
                player2 = new Player(names.get("Player2"), 0, 'O');
                initializeLocalMatch(player1, player2, board, scanner);
                break;
            case 2:
                System.out.println("Starting server...");
                new Thread(() -> TicTacToeServer.main(new String[]{})).start();
                System.out.println("Aguardando acesso do cliente...");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            case 3:
                TicTacToeClient.main(new String[]{});
                break;
            default:
                System.out.println("Valor invalido");
                break;
        }
    }

    private static void initializeLocalMatch(Player player1, Player player2, Board board, Scanner scanner){
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