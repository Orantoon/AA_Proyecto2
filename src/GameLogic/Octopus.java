package GameLogic;

import java.util.Arrays;

public class Octopus {
    private final int id;
    private final int[] attributes; //Life, attack, speed/turns
    private int[] position;
    private int time;


    public Octopus(int energy, int id){ //energy from 20 to 100 //ids must start from 1 to X
        this.id = id;
        int plays = 3 - (energy-20)/40;
        attributes = new int[]{energy, 100/energy, plays};
    }


    public void decreaseLife(int att, Map map, int time){
        attributes[0] -= att;

        System.out.println("Damage");

        if (attributes[0] <= 0){ //validate if Octopus is dead
            map.setMatrixSpace(position[0], position[1], 0);
            map.deadOctopus();
            this.time = time; //Global variable
            System.out.println("Dead");
        }
    }


    /** Decreases the life of the enemy */
    public void attack(int row, int col, Map map, int time){

        //Validation of not a valid space
        if (row < 0 || col < 0 || row >= map.getMatrix().length || col >= map.getMatrix().length || (Arrays.equals(position, new int[]{row, col}))){
            System.out.println("Missed attack"); //Octopus Misses
            return;
        }

        System.out.println("Attack");

        Octopus o = map.getOctopus(map.getMatrix()[row][col]);

        if (o != null)
            o.decreaseLife(attributes[1], map, time);
    }


    /** Cleans old space and sets new space & current position of the Octopus */
    public void move(int row, int col, Map map){
        if (row < 0 || col < 0 || row >= map.getMatrix().length || col >= map.getMatrix().length){
            System.out.println("Missed move"); //Octopus Misses
            return;
        }

        System.out.println("Move");

        map.setMatrixSpace(position[0],position[1],0);
        map.setMatrixSpace(row,col,id);
        position = new int[]{row, col};
    }


    /** Selects the choice depending on the genotype */
    public void choose(char genotype, Map map, int time){

        int row = ((genotype >= 126) ? genotype-126 : genotype) / 42 - 1, col = (genotype%42)/14 - 1;
        row += this.position[0]; col += this.position[1];

        if (genotype >= 126)
            move(row, col, map);
        else
            attack(row, col, map, time);

    }


    //Getters & setters
    public void setPosition(int r, int c){
        position = new int[] {r,c};
    }

    public int[] getPosition(){ return position; }
    public int getId(){ return id; }
    public int getLife(){ return attributes[0]; }
    public int getPlays(){ return attributes[2]; }
    public int getTime() { return time; }
}

/* Pulpo que llega a 0 de energia muere y se guarda el tiempo que duro */