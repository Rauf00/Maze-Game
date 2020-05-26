package ca.mazegame.model;
import java.util.Random;

/**
 * Cat class stores coordinates of the cat.
 * It also supports a method that moves the cat
 * pseudo-randomly
 */
public class Cat {
    private int x;
    private int y;

    public Cat(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int[] getCoordinate() {
        int[] coordinate = {x, y};
        return coordinate;
    }

    public void setCoordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    char generateRandomDirection(char prevMovement){
        Random random = new Random();
        int randomDirection = random.nextInt(3);
        char randomDirectionChar = ' ';
        if(prevMovement == 'W'){
            if(randomDirection == 0){
                randomDirectionChar = 'W';
            }
            else if(randomDirection == 1){
                randomDirectionChar = 'A';
            }
            else if (randomDirection == 2){
                randomDirectionChar = 'D';
            }
        }
        else if(prevMovement == 'S'){
            if(randomDirection == 0){
                randomDirectionChar = 'S';
            }
            else if(randomDirection == 1){
                randomDirectionChar = 'A';
            }
            else if (randomDirection == 2){
                randomDirectionChar = 'D';
            }
        }
        else if(prevMovement == 'A'){
            if(randomDirection == 0){
                randomDirectionChar = 'W';
            }
            else if(randomDirection == 1){
                randomDirectionChar = 'S';
            }
            else if (randomDirection == 2){
                randomDirectionChar = 'A';
            }
        }
        else if(prevMovement == 'D'){
            if(randomDirection == 0){
                randomDirectionChar = 'W';
            }
            else if(randomDirection == 1){
                randomDirectionChar = 'S';
            }
            else if (randomDirection == 2){
                randomDirectionChar = 'D';
            }
        }
        return randomDirectionChar;
    }

    public boolean backtrackIsAllowed(Maze maze, int[] coordinate, char prevMovement){
        int[] coordinateUp = {coordinate[0] - 1, coordinate[1]};
        int[] coordinateDown = {coordinate[0] + 1, coordinate[1]};
        int[] coordinateLeft = {coordinate[0], coordinate[1] - 1};
        int[] coordinateRight = {coordinate[0], coordinate[1] + 1};
        if(prevMovement == 'W'){
            // up, left, right are walls
            if(maze.cellIsWall(coordinateUp) && maze.cellIsWall(coordinateLeft) && maze.cellIsWall(coordinateRight)){
                return true;
            }
            else{
                return false;
            }
        }
        else if(prevMovement == 'S'){
            // down, left, right are walls
            if(maze.cellIsWall(coordinateDown) && maze.cellIsWall(coordinateLeft) && maze.cellIsWall(coordinateRight)){
                return true;
            }
            else{
                return false;
            }
        }
        else if(prevMovement == 'A'){
            // up, left, right are walls
            if(maze.cellIsWall(coordinateUp) && maze.cellIsWall(coordinateDown) && maze.cellIsWall(coordinateLeft)){
                return true;
            }
            else{
                return false;
            }
        }
        else if(prevMovement == 'D'){
            // up, left, right are walls
            if(maze.cellIsWall(coordinateUp) && maze.cellIsWall(coordinateDown) && maze.cellIsWall(coordinateRight)){
                return true;
            }
            else{
                return false;
            }
        }
        return false;
    }

    public int[] updateCoordinatesByDirection(char direction) {
        int[] coordinate = getCoordinate();
        int newCoordinateX = 0;
        int newCoordinateY = 0;
        if (direction == 'W' || direction == 'w') {
            newCoordinateX = coordinate[0] - 1;
            newCoordinateY = coordinate[1];
        }
        else if (direction == 'S' || direction == 'a') {
            newCoordinateX = coordinate[0] + 1;
            newCoordinateY = coordinate[1];
        }
        else if (direction == 'A' || direction == 'a') {
            newCoordinateX = coordinate[0];
            newCoordinateY = coordinate[1] - 1;
        }
        else if (direction == 'D' || direction == 'd') {
            newCoordinateX = coordinate[0];
            newCoordinateY = coordinate[1] + 1;
        }
        int[] newCoordinate = {newCoordinateX, newCoordinateY};
        return  newCoordinate;
    }

    public char getOppositeDirection(char direction){
        int[] coordinate = getCoordinate();
        char oppositeDirection = ' ';
        if(direction == 'W' || direction == 'w'){
            oppositeDirection = 'S';
            setCoordinate(coordinate[0] + 1, coordinate[1]);
            return oppositeDirection;
        }
        else if(direction == 'S' || direction == 's'){
            oppositeDirection = 'W';
            setCoordinate(coordinate[0] - 1, coordinate[1]);
            return oppositeDirection;
        }
        else if(direction == 'A' || direction == 'a'){
            oppositeDirection = 'D';
            setCoordinate(coordinate[0], coordinate[1] + 1);
            return oppositeDirection;
        }
        else if(direction == 'D' || direction == 'd'){
            oppositeDirection = 'A';
            setCoordinate(coordinate[0], coordinate[1] - 1);
            return oppositeDirection;
        }
        return oppositeDirection;
    }

    public char move(Maze maze, char direction, char prevMovement) {
        int[] coordinate = getCoordinate();
        if (direction == 'W' || direction == 'w') {
            int[] newCoordinate = updateCoordinatesByDirection('A');
            int newCoordinateX = newCoordinate[0];
            int newCoordinateY = newCoordinate[1];
            if(backtrackIsAllowed(maze, coordinate, prevMovement)){
                direction = getOppositeDirection(prevMovement);
                return direction;

            }
            while (maze.getMazeArray()[newCoordinateY][newCoordinateX] == 1) {
                direction = generateRandomDirection(prevMovement);
                newCoordinate = updateCoordinatesByDirection(direction);
                newCoordinateX = newCoordinate[0];
                newCoordinateY = newCoordinate[1];
            }
            setCoordinate(newCoordinateX, newCoordinateY);
        } else if (direction == 'S' || direction == 's') {
            int[] newCoordinate = updateCoordinatesByDirection('D');
            int newCoordinateX = newCoordinate[0];
            int newCoordinateY = newCoordinate[1];
            if(backtrackIsAllowed(maze, coordinate, prevMovement)){
                direction = getOppositeDirection(prevMovement);
                return direction;
            }
            while (maze.getMazeArray()[newCoordinateY][newCoordinateX] == 1) {
                direction = generateRandomDirection(prevMovement);
                newCoordinate = updateCoordinatesByDirection(direction);
                newCoordinateX = newCoordinate[0];
                newCoordinateY = newCoordinate[1];
            }
            setCoordinate(newCoordinateX, newCoordinateY);
        } else if (direction == 'A' || direction == 'a') {
            int[] newCoordinate = updateCoordinatesByDirection('W');
            int newCoordinateX = newCoordinate[0];
            int newCoordinateY = newCoordinate[1];
            if(backtrackIsAllowed(maze, coordinate, prevMovement)){
                direction = getOppositeDirection(prevMovement);
                return direction;
            }
            while (maze.getMazeArray()[newCoordinateY][newCoordinateX] == 1) {
                direction = generateRandomDirection(prevMovement);
                newCoordinate = updateCoordinatesByDirection(direction);
                newCoordinateX = newCoordinate[0];
                newCoordinateY = newCoordinate[1];
            }
            setCoordinate(newCoordinateX, newCoordinateY);
        } else if (direction == 'D' || direction == 'd') {
            int[] newCoordinate = updateCoordinatesByDirection('S');
            int newCoordinateX = newCoordinate[0];
            int newCoordinateY = newCoordinate[1];
            if(backtrackIsAllowed(maze, coordinate, prevMovement)){
                direction = getOppositeDirection(prevMovement);
                return direction;
            }
            while (maze.getMazeArray()[newCoordinateY][newCoordinateX] == 1) {
                direction = generateRandomDirection(prevMovement);
                newCoordinate = updateCoordinatesByDirection(direction);
                newCoordinateX = newCoordinate[0];
                newCoordinateY = newCoordinate[1];
            }
            setCoordinate(newCoordinateX, newCoordinateY);
        }
        return direction;
    }
}
