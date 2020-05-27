package ca.mazegame.model;

/**
 * Mouse class stores coordinates of the mouse.
 * It also supports a method that move the mouse
 * in a corresponding direction.
 */
public class Mouse {
    private int x;
    private int y;
    private boolean hitWall;

    public Mouse(int x, int y) {
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


    public boolean isHitWall() {
        return hitWall;
    }

    public String move(Maze maze, char direction) {

        int[] coordinate = getCoordinate();
        if (direction == 'W' || direction == 'w'){
            int newCoordinateX = coordinate[0];
            int newCoordinateY = coordinate[1] - 1;
            if (maze.getMazeArray()[newCoordinateY][newCoordinateX] == 1){
                return "BUMPED_TO_WALL";
            }
            setCoordinate(newCoordinateX, newCoordinateY);
        }
        else if (direction == 'S' || direction == 's'){
            int newCoordinateX = coordinate[0] ;
            int newCoordinateY = coordinate[1] + 1;
            if (maze.getMazeArray()[newCoordinateY][newCoordinateX] == 1){
                return "BUMPED_TO_WALL";
            }
            setCoordinate(newCoordinateX, newCoordinateY);
        }
        else if (direction == 'A' || direction == 'a'){
            int newCoordinateX = coordinate[0] - 1;
            int newCoordinateY = coordinate[1];
            if (maze.getMazeArray()[newCoordinateY][newCoordinateX] == 1){
                return "BUMPED_TO_WALL";
            }
            setCoordinate(newCoordinateX, newCoordinateY);
        }
        else if (direction == 'D' || direction == 'd'){
            int newCoordinateX = coordinate[0] + 1;
            int newCoordinateY = coordinate[1];
            if (maze.getMazeArray()[newCoordinateY][newCoordinateX] == 1){
                return "BUMPED_TO_WALL";
            }
            setCoordinate(newCoordinateX, newCoordinateY);
        }
        return "";
    }

    @Override
    public String toString() {
        return "Mouse{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

}
