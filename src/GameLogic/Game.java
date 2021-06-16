package GameLogic;

import java.util.Arrays;
import java.util.List;

public class Game {
    private List<Octopus> octopi;

    public Game(Octopus[] octopuses){
        Map map = new Map(octopuses);
        octopi = Arrays.asList(octopuses);

        // Game loop -- Turns -> Multiple G.A.
        // Actions

        // End Game

        // TEXT
        // Visual Part -- FULL MAP
        // Visual Part -- POV Octopus


    }
}
