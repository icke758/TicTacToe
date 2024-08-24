package TicTacToe;

public class Player {
    private String name;
    private int matchesWon;
    private char team;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMatchesWon() {
        return matchesWon;
    }

    public void setMatchesWon(int matchesWon) {
        this.matchesWon = matchesWon;
    }

    public void incrementMatchesWon(){
        this.matchesWon++;
    }

    public char getTeam() {
        return team;
    }

    public void setTeam(char team) {
        this.team = team;
    }

    public Player(String nome, int matchesWon, char team) {
        this.name = nome;
        this.matchesWon = matchesWon;
        this.team = team;
    }
}
