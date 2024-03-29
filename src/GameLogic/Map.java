package GameLogic;

import java.util.Random;
import java.util.Vector;

public class Map {
    private final int[][] matrix;
    private Vector<Octopus> Octopuses;
    private final Random r = new Random();
    private int alive;

    //Constructor
    public Map(Vector<Octopus> Os){
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
        alive = Os.size();

        //assign random positions to the octopuses
        for (Octopus o: Octopuses)
            addOctopus(o);
    }


    //Add Octopus to matrix
    public void addOctopus(Octopus o){ //Octopus o
        int row = r.nextInt(8), col = r.nextInt(8);

        if (matrix[row][col] != 0) //Used space
            addOctopus(o);
        else {
            matrix[row][col] = o.getId();
            o.setPosition(row, col);
        }
    }


    //Get Octopus by it's id
    public Octopus getOctopus(int id){
        for (Octopus o : Octopuses){
            if (o.getId() == id)
                return o;
        } return null;
    }


    //Getters
    public int[][] getMatrix() { return matrix; }
    public void setMatrixSpace(int row, int col, int id) { matrix[row][col] = id; }
    public Vector<Octopus> getOctopuses() { return Octopuses;}
    public int getAlive() { return alive; }
    public void deadOctopus() { alive--; }
}
