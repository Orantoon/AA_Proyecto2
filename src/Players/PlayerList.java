package Players;

public class PlayerList {
    public Player firstNode;

    public PlayerList(){
        firstNode = null;
    }

    public void addPlayer(Player player){
        Player tmp = firstNode;
        if (tmp == null){
            firstNode = player;
            return;
        }

        while(tmp.next != null)
            tmp = tmp.next;

        tmp.next = player;
    }
}