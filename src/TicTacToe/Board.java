package TicTacToe;

/**
 * Isso e uma implementaçao de um algoritimo super eficaz de verificaçao de vitoria, derrota ou empate no jogo da velha. Fonte: https://stackoverflow.com/questions/1056316/algorithm-for-determining-tic-tac-toe-game-over
 */
public class Board {
    // Bitboard values for each position on the Tic Tac Toe board
    private static final int A1 = 0x80080080;
    private static final int A2 = 0x40008000;
    private static final int A3 = 0x20000808;
    private static final int B1 = 0x08040000;
    private static final int B2 = 0x04004044;
    private static final int B3 = 0x02000400;
    private static final int C1 = 0x00820002;
    private static final int C2 = 0x00402000;
    private static final int C3 = 0x00200220;

    // Winning masks
    private static final int[] WIN_MASKS = {
            A1 | A2 | A3, // Row 1
            B1 | B2 | B3, // Row 2
            C1 | C2 | C3, // Row 3
            A1 | B1 | C1, // Column 1
            A2 | B2 | C2, // Column 2
            A3 | B3 | C3, // Column 3
            A1 | B2 | C3, // Diagonal 1
            A3 | B2 | C1  // Diagonal 2
    };

    // Player bitboards
    private int xBoard = 0;
    private int oBoard = 0;

    // Move function for X or O
    public boolean makeMove(int position, boolean isX) {
        int moveValue = getMoveValue(position);
        if ((xBoard & moveValue) != 0 || (oBoard & moveValue) != 0) {
            return false; // Invalid move, position already taken
        }
        if (isX) {
            xBoard |= moveValue;
        } else {
            oBoard |= moveValue;
        }
        return true;
    }

    // Get the move value for the given position
    private int getMoveValue(int position) {
        return switch (position) {
            case 0 -> A1;
            case 1 -> A2;
            case 2 -> A3;
            case 3 -> B1;
            case 4 -> B2;
            case 5 -> B3;
            case 6 -> C1;
            case 7 -> C2;
            case 8 -> C3;
            default -> throw new IllegalArgumentException("Invalid position");
        };
    }

    public void reset() {
        xBoard = 0; // Reset X's board
        oBoard = 0; // Reset O's board
    }

    // Check if there's a win
    public boolean checkWin(boolean isX) {
        int board = isX ? xBoard : oBoard;
        for (int mask : WIN_MASKS) {
            if ((board & mask) == mask) {
                return true;
            }
        }
        return false;
    }

    public boolean checkTie() {
        // Full board with no win means a tie
        int fullBoard = xBoard | oBoard;
        return (fullBoard & (A1 | A2 | A3 | B1 | B2 | B3 | C1 | C2 | C3)) == (A1 | A2 | A3 | B1 | B2 | B3 | C1 | C2 | C3);
    }

    // Print the board for debugging purposes
    public void printBoard() {
        char[] board = new char[9];
        for (int i = 0; i < 9; i++) {
            int moveValue = getMoveValue(i);
            if ((xBoard & moveValue) != 0) {
                board[i] = 'X';
            } else if ((oBoard & moveValue) != 0) {
                board[i] = 'O';
            } else {
                board[i] = '.';
            }
        }
        System.out.println(board[0] + " " + board[1] + " " + board[2]);
        System.out.println(board[3] + " " + board[4] + " " + board[5]);
        System.out.println(board[6] + " " + board[7] + " " + board[8]);
    }
}
