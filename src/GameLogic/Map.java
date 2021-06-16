package GameLogic;

import java.util.Random;

public class Map {
    private final int[][] matrix;
    private Octopus[] Octopuses;
    private final Random r = new Random();

    //Constructor
    public Map(Octopus[] Os){
        matrix = new int[][]{
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0}
        };

        Octopuses = Os;

        //assign random positions to the octopuses
        for (Octopus o: Octopuses)
            addOctopus(o);
    }

    //Add Octopus to matrix
    public void addOctopus(Octopus o){ //Octopus o
        int row = r.nextInt(8), col = r.nextInt(8);

        if (matrix[row][col] != 0) //Used space
            addOctopus(o);

        matrix[row][col] = o.getId();
        o.setPosition(row, col);
    }

}
