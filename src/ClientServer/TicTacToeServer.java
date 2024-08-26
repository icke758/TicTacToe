package ClientServer;

import TicTacToe.Board;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TicTacToeServer {
    private static final int PORT = 12345;

    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server is listening on port " + PORT);

            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected");

            ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());

            System.out.println("Client connected.");

            Scanner scanner = new Scanner(System.in);
            Board board = new Board();
            boolean isX = true; // X starts the game

            while (true) {
                out.writeObject(board);
                out.writeBoolean(isX);
                out.flush();
                // Server player's turn (X)
                if (isX) {
                    // Here you'd implement the logic for the server to make a move (or wait for user input)
                    // For testing, let's assume server plays the first available move
                    for (int i = 0; i < 9; i++) {
                        if (board.makeMove(i, true)) {
                            break;
                        }
                    }
                }

                // Check for win or tie after server's move
                if (board.checkWin(isX)) {
                    out.writeObject("Server wins!");
                    break;
                } else if (board.checkTie()) {
                    out.writeObject("It's a tie!");
                    break;
                } else {
                    out.writeObject(null);
                }
                out.flush();

                if (!isX) {
                    int clientMove = in.readInt();
                    board.makeMove(clientMove, false);
                }

                isX = !isX;
            }

            out.close();
            in.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
