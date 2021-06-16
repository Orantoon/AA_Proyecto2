package Arenas;

public class ArenaNode {
    // Characteristics
    public int id;
    public String ticketDay;
    public String timeZone;
    public int fightSize;
    public int amount;
    public String experience;
    public int total;

    // NodeList
    public ArenaNode nextN;

    // Graph
    public ArenaNode back;
    public ArenaNode next;
    public float percentage;

    public ArenaNode(int id, String ticketDay, String timeZone, int fightSize, int amount, String experience){
        this.id = id;
        this.ticketDay = ticketDay;
        this.timeZone = timeZone;
        this.fightSize = fightSize;
        this.amount = amount;
        this.experience = experience;
        this.total = 0;

        back = null;
        next = null;
        percentage = (float) 0.0;

        nextN = null;
    }
}
