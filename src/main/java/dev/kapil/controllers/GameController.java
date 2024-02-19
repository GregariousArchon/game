package dev.kapil.controllers;

import dev.kapil.models.*;
import dev.kapil.strategies.MovementStrategy;

import java.util.List;

public class GameController {

    public Game createGame(int dimension, List<Snake> snakes, List<Ladder> ladders, List<Player> players, MovementStrategy movementStrategy, int dice) {
        return Game.getBuilder().setDimension(dimension).setPlayers(players).setSnakes(snakes).setLadders(ladders).setMovementStrategy(movementStrategy).setDice(dice).build();
    }

    public GameStatus getGameStatus(Game game) {
        return game.getGameStatus();
    }

    public void makeMove(Game game) {
        game.makeMove();
    }

    public Player getCurrentPlayer(Game game) {
        return game.getPlayer();
    }
}
