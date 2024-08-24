package Utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Menu {
    public static Map<String, String> PlayerNames(){
        Scanner scanner = new Scanner(System.in);
        Map<String, String> playerNames = new HashMap<>();

        System.out.println("Digite o nome do primeiro player: ");
        String player1Name = scanner.next();
        System.out.println("Digite o nome do Segundo player: ");
        String player2Name = scanner.next();

        playerNames.put("Player1", player1Name);
        playerNames.put("Player2", player2Name);

        return playerNames;
    }
}
