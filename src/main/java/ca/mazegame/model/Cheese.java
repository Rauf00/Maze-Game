package ca.mazegame.model;
import java.util.Arrays;
import java.util.Random;

/**
 * Cheese class stores coordinates of the cheese.
 * It also supports a method that moves the cheese
 * into random maze cell, once the previous is collected
 */
public class Cheese{
    private int x;
    private int y;

    public Cheese(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int[] getCoordinate(){
        int[] coordinate = {x, y};
        return coordinate;
    }

    public void setCoordinate(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void move(Maze maze) {
        Random random = new Random();
        // rand between 1 and 13
        int randomXCoordinate = random.nextInt(maze.getHeight() - 2) + 1;
        // rand between 1 and 18
        int randomYCoordinate = random.nextInt(maze.getWidth() - 2) + 1;
        // if the cell with generated random coordinates is a wall,
        // than generate random coordinates again until the cell is not a wall
        while(maze.getMazeArray()[randomXCoordinate][randomYCoordinate] == 1){
            randomXCoordinate = random.nextInt(maze.getHeight() - 2) + 1;
            randomYCoordinate = random.nextInt(maze.getWidth() - 2) + 1;
        }
        setCoordinate(randomYCoordinate, randomXCoordinate);
    }
}
