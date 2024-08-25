package ClientServer;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import TicTacToe.Board;


public class TicTacToeClient {
    private static final int PORT = 12345;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the server IP address: ");
        String serverAddress = scanner.nextLine();

        try (Socket socket = new Socket(serverAddress, PORT);
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())) {

            scanner = new Scanner(System.in);
            while (true) {
                // Receive the board state and whose turn it is
                Board board = (Board) in.readObject();
                boolean isX = in.readBoolean();

                // Print the board
                board.printBoard();
                System.out.println("It's " + (isX ? "your" : "opponent's") + " turn!");

                // Wait for user's move
                if (!isX) {
                    System.out.print("Enter your move (0-8): ");
                    int move = scanner.nextInt();
                    out.writeInt(move);
                    out.flush();
                }

                // Check for game over message
                String result = (String) in.readObject();
                if (result != null) {
                    System.out.println(result);
                    break;
                }
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
