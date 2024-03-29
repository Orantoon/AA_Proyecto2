package GameLogic;

import GeneticA.GeneticAlgorithm;
import UI.GameUI;

import java.awt.*;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

public class Game {

    private GameUI gameUi;
    private Vector<Octopus> octopuses;

    public Game(Vector<Octopus> octopuses, int id) throws InterruptedException, IOException, FontFormatException {
        this.octopuses = octopuses;
        Map map = new Map(octopuses);
        gameUi = new GameUI();
        int turns, alive;

        long starttime = System.nanoTime();

        do {
            alive = map.getAlive();

            for (Octopus o: map.getOctopuses()){
                if (o.getLife() <= 0) //Dead octopus
                    continue;

                turns = o.getPlays();

                while (turns != 0){
                    gameUi.updateFrame(map, id);

                    TimeUnit.MICROSECONDS.sleep(50000);

                    GeneticAlgorithm GA = new GeneticAlgorithm(o, map);
                    char gen = GA.getBestSolution();
                    o.choose(gen, map, starttime); //change TIME //////////////////////////////////////////////////////////

                    turns--;
                }

            }

        } while (alive > 1);

        for (Octopus o : octopuses){
            if (o.getLife() > 0)
                o.setTime(starttime);
            System.out.println("Id: " + o.getId() + " Time: " + o.getTime());
        }

        octopuses.sort(compareByTime);

    }

    Comparator<Octopus> compareByTime = new Comparator<Octopus>() {
        @Override
        public int compare(Octopus o1, Octopus o2) {
            if (o1.getTime() == o2.getTime())
                return 0;
            else if (o1.getTime() > o2.getTime())
                return -1;
            return 1;
        }
    };

    public Vector<Octopus> getOctopuses(){ return octopuses; }

    public GameUI getGameUi(){return gameUi;}

    public static void main(String[] args) throws InterruptedException, IOException, FontFormatException {
        Vector<Octopus> os = new Vector<>();
        os.add(new Octopus(50, 1, "JACK", 10));
        os.add(new Octopus(20, 2, "JOSEPH", 10));
        os.add(new Octopus(60, 3, "JONAS", 10));
        /*
        os.add(new Octopus(40, 4));
        os.add(new Octopus(20, 5));
        os.add(new Octopus(100, 6));
        os.add(new Octopus(30, 7));
        os.add(new Octopus(40, 8));
        os.add(new Octopus(30, 9));
        os.add(new Octopus(40, 10));*/
        new Game(os, 2);
    }


}
