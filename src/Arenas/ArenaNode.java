package Arenas;

public class ArenaNode {
    public int id;
    public ArenaNode back;
    public ArenaNode next;
    public float percentage;

    public ArenaNode(int _id){
        id = _id;
        back = null;
        next = null;
        percentage = (float) 0.0;
    }

    public void print(){
        System.out.println(id + "-" + percentage + "% <---> ");
    }
}
