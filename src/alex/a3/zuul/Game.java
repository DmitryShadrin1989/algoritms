package alex.a3.zuul;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 *  This class is the main class of the "World of Zuul" application.
 *  "World of Zuul" is a very simple, text based adventure game.  Users
 *  can walk around some scenery. That's all. It should really be extended
 *  to make it more interesting!
 *
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 *
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 *
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class Game {
    private final Parser parser;
    private Room currentRoom;
    private final List<Room> rooms;
    private final Stack<Room> visitedRooms;

    /**
     * Create the game and initialise its internal map.
     */
    public Game()
    {
        this.rooms = new ArrayList<>();
        createRooms();
        this.parser = new Parser();
        this.visitedRooms = new Stack<>();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        // create the rooms
        Room outside = new Room("outside the main entrance of the university");
        Room theater = new Room("in a lecture theater");
        Room pub = new Room("in the campus pub");
        Room lab = new Room("in a computing lab");
        Room office = new Room("in the computing admin office");
        Room attic = new Room("in the attic, our teachers meditate here:)");
        Room cellar = new Room("in the cellar, they keep bad students here");
        Room magicRoom = new Room("in the magic transport room. Now you will move in...", true);

        // initialise room exits
        outside.setOutput(Direction.EAST, theater);
        outside.setOutput(Direction.SOUTH, lab);
        outside.setOutput(Direction.WEST, pub);
        theater.setOutput(Direction.WEST, outside);
        theater.setOutput(Direction.NORTH, magicRoom);
        pub.setOutput(Direction.EAST, outside);
        lab.setOutput(Direction.NORTH, outside);
        lab.setOutput(Direction.EAST, office);
        office.setOutput(Direction.WEST, lab);
        office.setOutput(Direction.UP, attic);
        office.setOutput(Direction.DOWN, cellar);
        attic.setOutput(Direction.DOWN, office);
        cellar.setOutput(Direction.UP, office);

        currentRoom = outside;  // start game outside
        Collections.addAll(rooms, outside, theater, pub, lab, office, attic, cellar, magicRoom);
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play()
    {
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        currentRoom.inspectRoom();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) {
            case HELP -> printHelp();
            case GO -> goRoom(command.getDirection());
            case BACK -> wantToQuit = goBack();
            case LOOK -> currentRoom.inspectRoom();
            case QUIT -> wantToQuit = quit(command);
        }
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the
     * command words.
     */
    private void printHelp()
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println("   go quit help");
    }

    /**
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Direction direction)
    {
        if(direction == null) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        Room nextRoom = currentRoom.getNextRoom(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            visitedRooms.push(currentRoom);
            currentRoom = nextRoom;
            currentRoom.inspectRoom();
            goMagic();
        }
    }

    private boolean goBack() {
        if (visitedRooms.isEmpty()) {
            return true;
        } else {
            currentRoom = visitedRooms.pop();
            if (currentRoom.isMagicRoom()) {
                currentRoom = visitedRooms.pop();
            }
            currentRoom.inspectRoom();
            return false;
        }
    }

    private void goMagic() {
        if (currentRoom.isMagicRoom()) {
            int randomIndex = (int) (Math.random() * rooms.size());
            currentRoom = rooms.get(randomIndex);
            currentRoom.inspectRoom();
        }
    }

    /**
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command)
    {
        if(command.hasDirection()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}
