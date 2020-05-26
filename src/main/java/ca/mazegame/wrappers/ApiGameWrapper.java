package ca.mazegame.wrappers;

import ca.mazegame.model.Game;

/**
 * Wrapper class for the game
 * to connect the model with UI
 */
public class ApiGameWrapper {
    public int gameNumber;
    public boolean isGameWon;
    public boolean isGameLost;
    public int numCheeseFound;
    public int numCheeseGoal;

    // MAY NEED TO CHANGE PARAMETERS HERE TO SUITE YOUR PROJECT
    public static ApiGameWrapper makeFromGame(Game game, int id) {
        ApiGameWrapper wrapper = new ApiGameWrapper();
        wrapper.gameNumber = id;
        wrapper.isGameLost = game.isGameOver();
        wrapper.isGameWon = game.isVictory();
        wrapper.numCheeseFound = game.getNumCheeseCollected();
        wrapper.numCheeseGoal = game.getTotalNumCheese();
        // Populate this object!

        return wrapper;
    }
}