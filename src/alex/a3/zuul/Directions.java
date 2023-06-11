package alex.a3.zuul;

import java.util.HashMap;
import java.util.Map;

public class Directions {
    private final Map<String, Direction> validDirections;

    public Directions() {
        this.validDirections = new HashMap<>();
        validDirections.put("north", Direction.NORTH);
        validDirections.put("south", Direction.SOUTH);
        validDirections.put("east", Direction.EAST);
        validDirections.put("west", Direction.WEST);
        validDirections.put("up", Direction.UP);
        validDirections.put("down", Direction.DOWN);
    }

    public Direction getDirection(String word) {
        return validDirections.get(word);
    }
}
