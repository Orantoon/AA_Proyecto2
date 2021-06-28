package ProbabilisticA;

import Arenas.ArenaNode;
import Graphs.NodeList;
import Players.Player;
import Utils.RandomNum;
import java.text.ParseException;

public class PlayerToNode {
    public NodeList nodeList;

    public PlayerToNode(NodeList nodeList){
        this.nodeList = nodeList;
    }

    public void mainProcess(Player player) throws ParseException {
        cleanArenas();
        cleanPlayer(player);

        RandomNum random = new RandomNum();
        int total = sumAllNodes(player);
        int value = random.getRandom(100);
        //System.out.println("Random = " + value);
        int sum = 0;
        setPercentagesP(player, total);

        ArenaNode tmp = nodeList.firstNode;
        while(tmp != null){
            sum += tmp.percentage;
            if (value <= sum){
                addPlayer(player,tmp.id);
                return;
            }
            tmp = tmp.nextN;
        }
    }

    public void addPlayer(Player player, int id){
        ArenaNode tmp = nodeList.firstNode;
        while(tmp != null){
            if (tmp.id == id){
                tmp.playerList.add(player);
                tmp.total += 1;
                player.arena = tmp;
                break;
            }
            tmp = tmp.nextN;
        }
    }

    public void setPercentagesP(Player player, int total) throws ParseException {
        ArenaNode tmp = nodeList.firstNode;
        while(tmp != null){
            tmp.percentage = (float) (compPlayerNode(tmp,player) * 100) / total;
            //System.out.println("Node " + tmp.id + " -> " + tmp.percentage);
            tmp = tmp.nextN;
        }
        //System.out.println();
    }

    public int sumAllNodes(Player player) throws ParseException {
        int res = 0;
        ArenaNode tmp = nodeList.firstNode;
        while(tmp != null){
            res += compPlayerNode(tmp, player);
            tmp = tmp.nextN;
        } return res;
    }

    public int compPlayerNode(ArenaNode arena, Player player) throws ParseException {
        int res = 0;
        if (!arena.ticketDay.equals(player.ticket.weekDate()))   // Same date is the most important data
            return 0;
        if (arena.timeZone.equals(player.timeZone))
            res += 3;
        if (arena.fightSize == player.limitFightSize())
            res += 9;
        if (arena.betPrice == player.limitBetPrice())
            res += 6;
        if (arena.experience.equals(player.getExperience()))
            res += 4;

        if (res == 0)   // A minimum value
            return 1;

        if (res - arena.total < 0 || arena.total >= 10) // Max space = 10 players per arena
            return 0;
        return res - arena.total;
    }

    public void cleanPlayer(Player player){
        ArenaNode tmp = nodeList.firstNode;
        while(tmp != null){
            if (tmp.playerList.contains(player)){
                player.arena = null;
                tmp.playerList.remove(player);
                return;
            }
            tmp = tmp.nextN;
        }
    }

    public void cleanArenas(){  // Leaves all arenas in 0 percentage
        ArenaNode tmp = nodeList.firstNode;
        while(tmp != null){
            tmp.percentage = (float) 0.0;
            tmp = tmp.nextN;
        }
    }
}