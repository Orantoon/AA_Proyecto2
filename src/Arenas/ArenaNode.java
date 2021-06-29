package Arenas;

import GameLogic.Octopus;
import Players.Player;

import java.util.HashMap;
import java.util.Vector;

public class ArenaNode {
    // Characteristics
    public Vector<Player> playerList;

    public int id;
    public String ticketDay;
    public String timeZone;
    public int fightSize;
    public int betPrice;
    public String experience;
    public int total;
    public int totalPrice = 0;
    public HashMap<String, Integer> usersCharge = new HashMap<>();
    public int readyPlayers = 0;

    // NodeList
    public ArenaNode nextN; // Node List
    public ArenaNode nextU; // Used Nodes

    // Graph
    public Vector<ArenaNode> adjacent;
    public float percentage;

    // Scroll
    public ArenaNode back;
    public ArenaNode next;

    //Octopuses
    public Vector<Octopus> Octopi;

    public ArenaNode(int id, String ticketDay, String timeZone, int fightSize, int betPrice, String experience){
        playerList = new Vector<Player>();
        Octopi = new Vector<>();

        this.id = id;
        this.ticketDay = ticketDay;
        this.timeZone = timeZone;
        this.fightSize = fightSize;
        this.betPrice = betPrice;
        this.experience = experience;
        this.total = 0;

        nextN = null;
        nextU = null;

        adjacent = new Vector<ArenaNode>();
        percentage = (float) 0.0;

        back = null;
        next = null;
    }

    public int getReady(){
        int res = 0;
        for (int i = 0; i < playerList.size(); i++){
            if (playerList.get(i).checkInReady)
                res += 1;
        }
        return res;
    }

    public void clean(){
        playerList = new Vector<Player>();

        total = 0;

        adjacent = new Vector<ArenaNode>();
        percentage = (float) 0.0;

        back = null;
        next = null;
    }

    public String weekDate(int num){
        return switch (num) {
            case 1 -> "Domingo";
            case 2 -> "Lunes";
            case 3 -> "Martes";
            case 4 -> "Miercoles";
            case 5 -> "Jueves";
            case 6 -> "Viernes";
            case 7 -> "Sabado";
            default -> "ERROR";
        };
    }

    public void print(){
        System.out.println("=================");
        System.out.println("Nodo " + id);
        System.out.println("Nodos Adyacentes:");
        for (int i = 0; i < adjacent.size(); i++)
            System.out.print(adjacent.get(i).id + " ");
        System.out.println();
        System.out.println("Users In:");
        for (int i = 0; i < playerList.size(); i++){
            System.out.print(playerList.get(i).nickname);
            System.out.println();
        }
        System.out.println();
    }
}
