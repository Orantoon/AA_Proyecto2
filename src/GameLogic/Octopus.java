package GameLogic;

public class Octopus {
    private int id;
    private int[] attributes; //Life, attack, speed/turns
    private int[] position;
    private int time;

    public Octopus(int energy, int id){ //energy from 20 to 100 //ids must start from 1 to X
        this.id = id;
        int plays = 3 - (energy-20)/40;
        attributes = new int[]{energy, 100/energy, plays};
    }

    public void decreaseLife(int att, int[][] matrix, int time){
        attributes[0] -= att;

        if (attributes[0] <= 0){ //validate if Octopus is dead
            matrix[position[0]][position[1]] = 0;

            this.time = time; //Global variable
        }
    }

    //Attack & Move -- Choosing is GA


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

// genetic representation of a solution

// function to generate new solutions (generations)

// fitness function

// selection function

// crossover function

// mutation function


/*
Check In: Nivel de energia a jugar (20 a 100)
20  -> + daño al atacar, + rapido, - efectiva la defensa
100 -> - daño al atacar, - rapido, + efectiva la defensa

Cada pulpo va analizando y creando un patrón o
estrategia de pelea combinando
desplazamientos en la arena, ataques y defensas

Pulpo que llega a 0 de energia muere y se guarda el tiempo que duro

Conforme el nivel de energia cambia la estrategia y comportamiento



 */