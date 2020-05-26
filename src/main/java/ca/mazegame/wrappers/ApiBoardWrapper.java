package ca.mazegame.wrappers;

import ca.mazegame.model.Game;

import java.util.List;

/**
 * Wrapper class for the board
 * to connect the model with UI
 */
public class ApiBoardWrapper {
    public int boardWidth;
    public int boardHeight;
    public ApiLocationWrapper mouseLocation;
    public ApiLocationWrapper cheeseLocation;
    public List<ApiLocationWrapper> catLocations;
    public boolean[][] hasWalls;
    public boolean[][] isVisible;

    // MAY NEED TO CHANGE PARAMETERS HERE TO SUITE YOUR PROJECT
    public static ApiBoardWrapper makeFromGame(Game game) {
        ApiBoardWrapper wrapper = new ApiBoardWrapper();
        wrapper.boardWidth = game.getMaze().getWidth();
        wrapper.boardHeight = game.getMaze().getHeight();
        wrapper.mouseLocation = ApiLocationWrapper.makeFromCellLocation(game.getMouse().getCoordinate());
        wrapper.cheeseLocation = ApiLocationWrapper.makeFromCellLocation(game.getCheese().getCoordinate());
        wrapper.catLocations = ApiLocationWrapper.makeFromCellLocations(game.getCats());
        // Populate this object!
        wrapper.hasWalls = game.getMaze().hasWalls();
        wrapper.isVisible = game.getVisibleCells();

        return wrapper;
    }
}