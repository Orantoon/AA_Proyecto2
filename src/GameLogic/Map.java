package GameLogic;

import java.util.Random;

public class Map {
    private int[][] matrix;
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

        //assign random positions to the octopuses
        for (Octopus o: Os)
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
