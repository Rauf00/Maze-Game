package ca.mazegame.model;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Main Game Logic. Gets all data from models and
 *  updates the state of the game after each user
 *  input.
 */
public class Game {
    private Boolean isGameOver;
    private Boolean isVictory;
    private int totalNumCheese = 5;
    private int numCheeseCollected;
    private Maze maze = new Maze(15, 20);
    private Mouse mouse = new Mouse(1, 1);;
    private Cheese cheese = new Cheese(20, 15);
    private Cat catRightTop = new Cat(1, 13);
    private Cat catRightBottom= new Cat(18, 13);
    private Cat catLeftBottom = new Cat(18, 1);
    private List<Cat> cats = new ArrayList<>();
    private char catRightTopPrevMovement = 'S';
    private char catRightBottomPrevMovement = 'W';
    private char catLeftBottomPrevMovement = 'W';
    Set<Point> visibleCells = new HashSet<>();

    public Game(){
        isGameOver = false;
        isVictory = false;
        maze.generateMaze();
        this.totalNumCheese = 5;
        this.numCheeseCollected = 0;
        cats.add(catRightTop);
        cats.add(catRightBottom);
        cats.add(catLeftBottom);
        // Initialize cheese
        Random random = new Random();
        // rand between 1 and 13
        int randomXCoordinate = random.nextInt(maze.getHeight() - 2) + 1;
        // rand between 1 and 18
        int randomYCoordinate = random.nextInt(maze.getWidth() - 2) + 1;
        while(maze.getMazeArray()[randomXCoordinate][randomYCoordinate] == 1){
            // rand between 1 and 13
            randomXCoordinate = random.nextInt(maze.getHeight() - 2) + 1;
            // rand between 1 and 18
            randomYCoordinate = random.nextInt(maze.getWidth() - 2) + 1;
        }
        cheese.setCoordinate(randomYCoordinate, randomXCoordinate);
        for(int i = 0; i < getMaze().getWidth(); i++){
            visibleCells.add(new Point(0, i));
        }
        for(int i = 0; i < getMaze().getWidth(); i++){
            visibleCells.add(new Point(getMaze().getHeight() - 1, i));
        }
        for(int i = 0; i < getMaze().getHeight(); i++){
            visibleCells.add(new Point(i, 0));
        }
        for(int i = 0; i < getMaze().getHeight(); i++){
            visibleCells.add(new Point(i, getMaze().getWidth() - 1));
        }
        int[] initialMouseCoordinates = {1,1};
        updateVisibleCells(maze, initialMouseCoordinates);
    }

    public List<Cat> getCats() {
        return cats;
    }

    public Cat getCatRightTop() {
        return catRightTop;
    }

    public Cat getCatRightBottom() {
        return catRightBottom;
    }

    public Cat getCatLeftBottom() {
        return catLeftBottom;
    }

    public Maze getMaze() {
        return maze;
    }

    public Mouse getMouse() {
        return mouse;
    }

    public Cheese getCheese() {
        return cheese;
    }

    public boolean isGameOver(){
        return this.isGameOver;
    }

    public boolean isVictory(){
        return this.isVictory;
    }

    public int getTotalNumCheese() {
        return totalNumCheese;
    }

    // for when the c key is pressed by user
    public void setTotalNumCheese(int totalNumCheese) {
        this.totalNumCheese = totalNumCheese;
    }

    public int getNumCheeseCollected() {
        return numCheeseCollected;
    }

    public char getCatRightTopPrevMovement() {
        return catRightTopPrevMovement;
    }

    public void setCatRightTopPrevMovement(char catRightTopPrevMovement) {
        this.catRightTopPrevMovement = catRightTopPrevMovement;
    }

    public char getCatRightBottomPrevMovement() {
        return catRightBottomPrevMovement;
    }

    public void setCatRightBottomPrevMovement(char catRightBottomPrevMovement) {
        this.catRightBottomPrevMovement = catRightBottomPrevMovement;
    }

    public char getCatLeftBottomPrevMovement() {
        return catLeftBottomPrevMovement;
    }

    public void setCatLeftBottomPrevMovement(char catLeftBottomPrevMovement) {
        this.catLeftBottomPrevMovement = catLeftBottomPrevMovement;
    }

    public boolean[][] getVisibleCells() {
        boolean[][] visibleArray = new boolean[maze.getHeight()][maze.getWidth()];
        for(int i = 0; i < maze.getHeight(); i++){
            for (int j = 0; j < maze.getWidth(); j++){
                visibleArray[i][j] = false;
            }
        }
        for (Point point : visibleCells){
            int coordinateX = point.x;
            int coordinateY = point.y;
            visibleArray[coordinateX][coordinateY] = true;
        }
        return visibleArray;
    }

    public void updateVisibleCells(Maze maze, int[] mouseCoordinates) {
        int mouseRow = mouseCoordinates[1];
        int mouseCol = mouseCoordinates[0];
        visibleCells.add((new Point(mouseRow, mouseCol)));
        // 8 directions around the mouse
        // North
        if(mouseRow > 1)
            visibleCells.add(new Point(mouseRow-1, mouseCol));
        // North East
        if(mouseCol < maze.getWidth()-1 && mouseRow > 1)
            visibleCells.add(new Point(mouseRow-1, mouseCol+1));
        // East
        if(mouseCol < maze.getWidth()-1)
            visibleCells.add(new Point(mouseRow, mouseCol+1));
        // South East
        if(mouseCol < maze.getWidth()-1 && mouseRow < maze.getHeight()-1)
            visibleCells.add(new Point(mouseRow+1, mouseCol+1));
        // South
        if(mouseRow < maze.getHeight()-1)
            visibleCells.add(new Point(mouseRow+1, mouseCol));
        // South West
        if(mouseRow < maze.getHeight()-1 && mouseCol > 1)
            visibleCells.add(new Point(mouseRow+1, mouseCol-1));
        // West
        if(mouseCol > 1)
            visibleCells.add(new Point(mouseRow, mouseCol-1));
        // North West
        if(mouseCol > 1 && mouseRow > 1)
            visibleCells.add(new Point(mouseRow-1, mouseCol-1));
    }

    public String moveMouse(String userInput){
        String bumpedToWall = "";
        if (userInput.equals("MOVE_UP")){
            bumpedToWall = getMouse().move(getMaze(), 'W');
        } else if (userInput.equals("MOVE_DOWN")){
            bumpedToWall = getMouse().move(getMaze(), 'S');
        } else if (userInput.equals("MOVE_LEFT")){
            bumpedToWall = getMouse().move(getMaze(), 'A');
        } else if (userInput.equals("MOVE_RIGHT")){
            bumpedToWall = getMouse().move(getMaze(), 'D');
        }
        updateVisibleCells(maze, mouse.getCoordinate());
        if(Arrays.equals(getMouse().getCoordinate(), getCats().get(0).getCoordinate())
                || Arrays.equals(getMouse().getCoordinate(), getCats().get(1).getCoordinate())
                || Arrays.equals(getMouse().getCoordinate(), getCats().get(2).getCoordinate())){
            isGameOver = true;
            isVictory = false;
            return bumpedToWall;
        }
        // Cheese movement
        if(Arrays.equals(getMouse().getCoordinate(), getCheese().getCoordinate())){
            numCheeseCollected++;
            cheese.move(maze);
        }
        // End the game if the player won
        if(numCheeseCollected == totalNumCheese){
            isGameOver = false;
            isVictory = true;
            return bumpedToWall;
        }
        return bumpedToWall;
    }

    public void moveCats(){
        // Cat movement
        char catRightTopNextMove = catRightTop.generateRandomDirection(catRightTopPrevMovement);
        catRightTopNextMove = catRightTop.move(maze, catRightTopNextMove, catRightTopPrevMovement);
        setCatRightTopPrevMovement(catRightTopNextMove);
        char catRightBottomNextMove = catRightBottom.generateRandomDirection(catRightBottomPrevMovement);
        catRightBottomNextMove = catRightBottom.move(maze, catRightBottomNextMove, catLeftBottomPrevMovement);
        setCatRightBottomPrevMovement(catRightBottomNextMove);
        char catLeftBottomNextMove = catLeftBottom.generateRandomDirection(catLeftBottomPrevMovement);
        catLeftBottomNextMove = catLeftBottom.move(maze, catLeftBottomNextMove, catLeftBottomPrevMovement);
        setCatLeftBottomPrevMovement(catLeftBottomNextMove);
        //End the game if the mouse is caught
        if(Arrays.equals(getMouse().getCoordinate(), getCats().get(0).getCoordinate())
                || Arrays.equals(getMouse().getCoordinate(), getCats().get(1).getCoordinate())
                || Arrays.equals(getMouse().getCoordinate(), getCats().get(2).getCoordinate())){
            isGameOver = true;
            isVictory = false;
            return;
        }
    }

    public void showAllMaze(){
        for(int i = 0; i < maze.getHeight(); i++){
            for(int j = 0; j < maze.getWidth(); j++){
                visibleCells.add(new Point(i,j));
            }
        }
    }
}
