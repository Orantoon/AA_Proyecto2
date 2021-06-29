package GameLogic;

import java.util.Arrays;

public class Octopus {
    private final int id;
    private final int[] attributes; //Life, attack, speed/turns
    private int[] position;
    private long time;
    private String user;
    private int bet;
    //private userid, nickname, USUARIO EN SI

    public Octopus(int energy, int id, String user, int bet){ //energy from 20 to 100 //ids must start from 1 to X
        this.id = id;
        int plays = 3 - (energy-20)/40;
        attributes = new int[]{energy, 100/energy, plays};
        this.user = user;
        this.bet = bet;
    }


    public void decreaseLife(int att, Map map, long starttime){
        attributes[0] -= att;

        System.out.println("Damage");

        if (attributes[0] <= 0){ //validate if Octopus is dead
            map.setMatrixSpace(position[0], position[1], 0);
            map.deadOctopus();
            setTime(starttime);
            System.out.println("Dead");
        }
    }


    /** Decreases the life of the enemy */
    public void attack(int row, int col, Map map, long starttime){

        //Validation of not a valid space
        if (row < 0 || col < 0 || row >= map.getMatrix().length || col >= map.getMatrix().length || (Arrays.equals(position, new int[]{row, col}))){
            System.out.println("Missed attack"); //Octopus Misses
            return;
        }

        System.out.println("Attack");

        Octopus o = map.getOctopus(map.getMatrix()[row][col]);

        if (o != null)
            o.decreaseLife(attributes[1], map, starttime);
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
    public void choose(char genotype, Map map, long starttime){

        int row = ((genotype >= 126) ? genotype-126 : genotype) / 42 - 1, col = (genotype%42)/14 - 1;
        row += this.position[0]; col += this.position[1];

        if (genotype >= 126)
            move(row, col, map);
        else
            attack(row, col, map, starttime);

    }


    //Getters & setters
    public void setPosition(int r, int c){
        position = new int[] {r,c};
    }

    public int[] getPosition(){ return position; }
    public int getId(){ return id; }
    public int getLife(){ return attributes[0]; }
    public int getAttack() {return  attributes[1]; }
    public int getPlays(){ return attributes[2]; }
    public long getTime() { return time; }
    public void setTime(long starttime){ time = System.nanoTime() - starttime; }
}

/* Pulpo que llega a 0 de energia muere y se guarda el tiempo que duro */