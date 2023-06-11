package alex.a3.zuul;

public enum Direction {
    NORTH("north"),
    SOUTH("south"),
    EAST("east"),
    WEST("west"),
    UP("up"),
    DOWN("down");

    private final String nameDirection;

    Direction(String nameDirection) {
        this.nameDirection = nameDirection;
    }

    public String getNameDirection() {
        return nameDirection;
    }
}
