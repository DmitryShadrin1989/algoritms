package alex.a3.zuul;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game.
 *
 * A "Room" represents one location in the scenery of the game.  It is
 * connected to other rooms via exits.  The exits are labelled north,
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 *
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class Room {
    private final String description;
    private final Map<Direction, Room> outputs;
    private boolean isMagic = false;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) {
        this.description = description;
        this.outputs = new HashMap<>();
    }

    public Room(String description, boolean isMagic) {
        this.description = description;
        this.outputs = new HashMap<>();
        this.isMagic = isMagic;
    }

    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param north The north exit.
     * @param east The east east.
     * @param south The south exit.
     * @param west The west exit.
     */

    public void setOutput(Direction direction, Room room) {
        this.outputs.put(direction, room);
    }

    public boolean isMagicRoom() {
        return isMagic;
    }

    public Room getNextRoom(Direction direction) {
        return this.outputs.get(direction);
    }

    public List<Direction> getOutputs() {
        return this.outputs.keySet().stream().toList();
    }

    public void printOutputs() {
        getOutputs().forEach(e -> System.out.print(e.getNameDirection()+" "));
    }

    public void inspectRoom() {
        System.out.println("You are " + getDescription());
        System.out.print("Exits: ");
        printOutputs();
        System.out.println();
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }
}
