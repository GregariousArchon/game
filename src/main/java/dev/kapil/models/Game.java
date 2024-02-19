package dev.kapil.models;

import dev.kapil.strategies.MovementStrategy;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Game {
    private Board board;
    private Queue<Player> players;
    private List<Dice> dices;

    private MovementStrategy movementStrategy;

    private GameStatus gameStatus;

    private int currRank;

    public static Builder getBuilder() {
        return new Builder();
    }

    private Game(int dimension, List<Player> players, List<Snake> snakes, List<Ladder> ladders, MovementStrategy movementStrategy) {
        board = new Board(dimension, snakes, ladders);
        this.movementStrategy = movementStrategy;
        this.players = new LinkedList<>(players);
        this.currRank = 1;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }


    public Player getPlayer() {
        return players.poll();
    }

    public String getConfigKey(String key) throws IOException {
        Properties prop = new Properties();
        String propFileName = "test.properties";
        InputStream inputSecretStream = getClass().getClassLoader().getResourceAsStream(propFileName);
        if (inputSecretStream != null) {
            prop.load(inputSecretStream);
        } else {
            throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
        }

        return prop.getProperty(key);
//        String jsonArray2= prop.getProperty("jsonArray2");
    }

    public void makeMove() {
        StringBuilder str = new StringBuilder();
        final int[] s = {dices.size()};
        int diceNumber = movementStrategy.getEndPosition(s);
        if(players.size()>1) {
            Player player = players.poll();
            str.append(player.getPlayerName()).append(" rolled a ").append(diceNumber);;
            int endPosition  = player.getCurrPosition() + diceNumber;
            if(endPosition==100) {
                player.setCurrPosition(endPosition);
                player.setRank(currRank);
                currRank++;
            } else if(endPosition<100) {
                String s1 = "";
                if(board.getSnakes().containsKey(endPosition)) {
                    s1 = " and bitten by snake at " + endPosition + " and moved from " + endPosition + " to " + board.getSnakes().get(endPosition);
                } else {
                    if(board.getLadders().containsKey(endPosition)) {
                        s1 = " and climbed the ladder at " + endPosition + " and moved from " + endPosition + " to " + board.getLadders().get(endPosition);
                    } else {
                        s1 = " and moved from " + player.getCurrPosition() + " to " + endPosition;
                    }

                }
                player.setCurrPosition(endPosition);
                players.offer(player);
            }
        } else if(players.size()==1){
            Player player = players.poll();
            player.setRank(currRank);
            this.setGameStatus(GameStatus.ENDED);
        }
    }
    public static class Builder {

        int dimension;
        private Board board;
        private List<Player> players;
        private List<Snake> snakes;
        private List<Ladder> ladders;
        private List<Dice> dices;

        private MovementStrategy movementStrategy;

        private Builder() {};

        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = new ArrayList<>(players);
            return this;
        }

        public Builder setSnakes(List<Snake> snakes) {
            this.snakes = new ArrayList<>(snakes);
            return this;
        }

        public Builder setLadders(List<Ladder> ladders) {
            this.ladders = new ArrayList<>(ladders);
            return this;
        }

        public Builder setMovementStrategy(MovementStrategy movementStrategy) {
            this.movementStrategy = movementStrategy;
            return this;
        }

        public Builder setDice(int dice) {
            dices = new ArrayList<>();
            for(int i = 0; i < dice; i++) {
                dices.add(new Dice());
            }
            return this;
        }

        public Game build() {
            return new Game(dimension, players, snakes, ladders, movementStrategy);
        }
    }
}
