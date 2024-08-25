package ClientServer;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import TicTacToe.Board;


public class TicTacToeClient {
    private static final String SERVER_ADDRESS = "127.0.0.1"; // Change to server's IP
    private static final int PORT = 12345;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_ADDRESS, PORT);
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())) {

            Scanner scanner = new Scanner(System.in);
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
