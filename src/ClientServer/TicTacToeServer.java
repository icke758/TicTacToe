package ClientServer;

import TicTacToe.Board;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TicTacToeServer {
    private static final int PORT = 12345;

    public static void main(String[] args){
        Board board = new Board();
        boolean isX = true;

        try(ServerSocket serverSocket = new ServerSocket(PORT)){
            System.out.println("Aguardando o cliente...");
            Socket clientSocket = serverSocket.accept();
            System.out.println("Cliente conectado!");

            try (ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
                 ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream())) {
                while (true){
                    out.writeObject(board);
                    out.writeBoolean(isX);
                    out.flush();

                    if (board.checkWin(isX)) {
                        out.writeObject("X ganha!");
                        break;
                    } else if (board.checkTie()) {
                        out.writeObject("Empate!");
                        break;
                    }

                    int clientMove = in.readInt();
                    if(!board.makeMove(clientMove, !isX)){
                        out.writeObject("Movimento invalido");
                    } else {
                        isX = !isX;
                    }

                    if (board.checkWin(!isX)) {
                        out.writeObject("O wins!");
                        break;
                    } else if (board.checkTie()) {
                        out.writeObject("It's a tie!");
                        break;
                    }
                }
                out.writeObject(board);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
