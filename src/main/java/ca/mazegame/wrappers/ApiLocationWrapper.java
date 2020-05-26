package ca.mazegame.wrappers;

import ca.mazegame.model.Cat;

import java.util.ArrayList;
import java.util.List;

/**
 * Wrapper class for the location
 * to connect the model with UI
 */
public class ApiLocationWrapper {
    public int x;
    public int y;

    // MAY NEED TO CHANGE PARAMETERS HERE TO SUITE YOUR PROJECT
    public static ApiLocationWrapper makeFromCellLocation(int[] coordinates) {
        ApiLocationWrapper location = new ApiLocationWrapper();
        location.x = coordinates[0];
        location.y = coordinates[1];
        return location;
    }
    // MAY NEED TO CHANGE PARAMETERS HERE TO SUITE YOUR PROJECT
    public static List<ApiLocationWrapper> makeFromCellLocations(List<Cat> cats) {
        List<ApiLocationWrapper> locations = new ArrayList<>();
        ApiLocationWrapper catRightTopLocation = makeFromCellLocation(cats.get(0).getCoordinate());
        ApiLocationWrapper catRightBottomLocation = makeFromCellLocation(cats.get(1).getCoordinate());
        ApiLocationWrapper catLeftBottomLocation = makeFromCellLocation(cats.get(2).getCoordinate());
        locations.add(catRightTopLocation);
        locations.add(catRightBottomLocation);
        locations.add(catLeftBottomLocation);
        return locations;
    }
}