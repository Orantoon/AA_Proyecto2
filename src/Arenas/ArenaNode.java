package Arenas;

import Players.PlayerList;

import java.util.Vector;

public class ArenaNode {
    // Characteristics
    public PlayerList playerList;

    public int id;
    public String ticketDay;
    public String timeZone;
    public int fightSize;
    public int betPrice;
    public String experience;
    public int total;

    // NodeList
    public ArenaNode nextN;

    // Graph
    public Vector<ArenaNode> adjacent;
    public float percentage;

    // Scroll
    public ArenaNode back;
    public ArenaNode next;

    public ArenaNode(int id, String ticketDay, String timeZone, int fightSize, int betPrice, String experience){
        playerList = new PlayerList();

        this.id = id;
        this.ticketDay = ticketDay;
        this.timeZone = timeZone;
        this.fightSize = fightSize;
        this.betPrice = betPrice;
        this.experience = experience;
        this.total = 0;

        nextN = null;

        adjacent = new Vector<ArenaNode>();
        percentage = (float) 0.0;

        back = null;
        next = null;
    }

    public void clean(){
        playerList = new PlayerList();

        total = 0;

        adjacent = new Vector<ArenaNode>();
        percentage = (float) 0.0;

        back = null;
        next = null;
    }
}
