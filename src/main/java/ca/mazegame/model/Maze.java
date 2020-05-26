package ca.mazegame.model;import java.util.*;
import java.awt.Point;

/**
 * Maze array consisting of walls.
 * Randomly generated using Binary Tree maze generating algorithm.
 */
public class Maze {
    private int width = 20;
    private int height = 15;
    public int[][] maze;

    Maze(int height, int width){
        this.width = width;
        this.height = height;
        this.maze = new int[height][width];

    }

    public boolean cellIsWall(int[] coordinate){
        if(maze[coordinate[1]][coordinate[0]] == 1){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean[][] hasWalls(){
        boolean[][] wallsArray = new boolean[height][width];
        for(int i = 0; i < height; i++){
            for (int j = 0; j < width; j++){
                if (maze[i][j] == 1){
                    wallsArray[i][j] = true;
                }
                else{
                    wallsArray[i][j] = false;
                }
            }
        }
        return wallsArray;
    }

    /**
     * MAZE GENERATING ALGORITHM: Using Binary Tree Algorithm
     * */
    public void generateMaze(){
        Random rand = new Random();
        // put the walls in
        for(int row=0; row<height; row++){
            for(int col=0; col<width; col++){
                if(row==0 || row==height-1 || col==0 || col==width-1){
                    maze[row][col]=1;
                }else{
                    if(col==1 || row==1){
                        maze[row][col]=0;
                    }else{
                        int num = rand.nextInt(2);
                        if(num==0){
                            maze[row][col]=1;
                            maze[row][col-1]=0;
                        }else{
                            maze[row][col]=1;
                            maze[row-1][col]=0;
                        }
                    }
                }
            }
        }
        maze[1][1] = maze[1][width-2] = maze[height-2][1] = maze[height-2][width-2] = 0;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return "Maze{" +
                "maze=" + Arrays.toString(maze) +
                '}';
    }

    public int[][] getMazeArray() {
        return maze;
    }
}
