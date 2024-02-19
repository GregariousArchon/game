package dev.kapil;

import dev.kapil.controllers.GameController;
import dev.kapil.models.*;
import dev.kapil.strategies.MovementStrategy;
import dev.kapil.strategies.MovementStrategyFactory;
import dev.kapil.strategies.SumMovementStrategy;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

            Properties prop = new Properties();
            String propFileName = "test.properties";
            InputStream inputSecretStream = Main.class.getClassLoader().getResourceAsStream(propFileName);
            if (inputSecretStream != null) {
                prop.load(inputSecretStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }
            int dimension = Integer.parseInt(prop.getProperty("dimension"));

            Scanner scan = new Scanner(System.in);
            Entities entities = Entities.getInstance();
            GameController gameController = new GameController();
            Game game;

            int noOfSnakes = Integer.parseInt(prop.getProperty("noOfSnakes"));
            List<Snake> snakes = new ArrayList<>();
            while(noOfSnakes>0){
                int startPosition = scan.nextInt();
                int endPosition = scan.nextInt();
                Snake snake = new Snake(startPosition, endPosition);
                snakes.add(snake);
                entities.setSnake(startPosition, endPosition);
                noOfSnakes--;
            }
            int noOfLadders = Integer.parseInt(prop.getProperty("noOfLadders"));
            List<Ladder> ladders = new ArrayList<>();
            while(noOfLadders>0){
                int startPosition = scan.nextInt();
                int endPosition = scan.nextInt();
                Ladder ladder = new Ladder(startPosition, endPosition);
                ladders.add(ladder);
                entities.setLadder(startPosition, endPosition);
                noOfLadders--;
            }
            int noOfPlayers = Integer.parseInt(prop.getProperty("noOfPlayers"));
            int i=0;
            List<Player> players = new ArrayList<>();
            while(noOfPlayers>0){
                String playerName = scan.next();
                int startPos = scan.nextInt();
                Player player = new Player(playerName, startPos);
                players.add(player);
                entities.setPlayer(i++, playerName);
                noOfPlayers--;
            }
            int dice = Integer.parseInt(prop.getProperty("dice"));
            MovementStrategy movementStrategy = MovementStrategyFactory.getMovementStrategy(prop.getProperty("movementStrategy"));
            game = gameController.createGame(dimension, snakes, ladders, players, movementStrategy, dice);
            while(gameController.getGameStatus(game).equals(GameStatus.IN_PROGRESS)) {
                gameController.makeMove(game);
            }

    }
}