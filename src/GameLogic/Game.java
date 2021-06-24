package GameLogic;

import GeneticA.GeneticAlgorithm;
import UI.GameUI;

import java.awt.*;
import java.io.IOException;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

public class Game {

    public Game(Vector<Octopus> octopuses, int id) throws InterruptedException, IOException, FontFormatException {
        Map map = new Map(octopuses);
        GameUI gameUi = new GameUI();
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

    }

    public static void main(String[] args) throws InterruptedException, IOException, FontFormatException {
        Vector<Octopus> os = new Vector<>();
        os.add(new Octopus(50, 1));
        os.add(new Octopus(20, 2));
        os.add(new Octopus(60, 3));/*
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
