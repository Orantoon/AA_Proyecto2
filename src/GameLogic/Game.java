package GameLogic;

import GeneticA.GeneticAlgorithm;
import UI.UI;

import java.util.Vector;
import java.util.concurrent.TimeUnit;

public class Game {

    public Game(Vector<Octopus> octopuses, int id) throws InterruptedException {
        Map map = new Map(octopuses);
        UI ui = new UI(map, id);
        int turns, alive;

        do {
            alive = map.getAlive();

            for (Octopus o: map.getOctopuses()){
                if (o.getLife() <= 0) //Dead octopus
                    continue;

                turns = o.getPlays();

                while (turns != 0){
                    //System.out.println(o.getId());

                    ui.updateFrame(map, id);

                    TimeUnit.MICROSECONDS.sleep(50000);

                    GeneticAlgorithm GA = new GeneticAlgorithm(o, map);
                    char gen = GA.getBestSolution();
                    o.choose(gen, map, 0); //change TIME //////////////////////////////////////////////////////////

                    turns--;
                }

            }

        } while (alive > 1);

        for (Octopus o : octopuses){
            if (o.getLife() > 0)
                System.out.println("Winner " + o.getId());
        }


        // Game loop -- Turns -> Multiple G.A.
        // Actions

        // End Game

        // TEXT
        // Visual Part -- FULL MAP
        // Visual Part -- POV Octopus

    }

    public static void main(String[] args) throws InterruptedException {
        Vector<Octopus> os = new Vector<>();
        os.add(new Octopus(20, 1));
        os.add(new Octopus(100, 2));
        os.add(new Octopus(20, 3));
        new Game(os, 3);
    }


}
