package arena;

public class ArenaNode {
    public ArenaNode back;
    public ArenaNode next;
    public float percentage;

    public ArenaNode(){
        back = null;
        next = null;
        percentage = (float) 0.0;
    }
}
