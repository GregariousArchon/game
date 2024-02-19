package dev.kapil.models;

public class Player {
    private String playerName;
    private int startingPos;

    private int currPosition;

    private int rank;
    public Player(String playerName,int startingPos){
        this.startingPos = startingPos;
        this.playerName = playerName;
        this.currPosition = startingPos;
        this.rank = -1;
    }
    public String getPlayerName() {
        return playerName;
    }

    public int getCurrPosition() {
        return currPosition;
    }

    public void setCurrPosition(int pos) {
        this.currPosition = pos;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
