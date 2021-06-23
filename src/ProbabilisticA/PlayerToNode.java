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
        RandomNum random = new RandomNum();
        int total = sumAllNodes(player);
        int value = random.getRandom(100);
        int sum = 0;
        setPercentages(player, total);

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
                tmp.playerList.addPlayer(player);
                tmp.total += 1;
                break;
            }
            tmp = tmp.nextN;
        }
    }

    public void setPercentages(Player player, int total) throws ParseException {
        ArenaNode tmp = nodeList.firstNode;
        while(tmp != null){
            tmp.percentage = (float) (compPlayerNode(tmp,player) * 100) / total;
            tmp = tmp.nextN;
        }
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
        if (arena.ticketDay.equals(player.ticket.weekDate()))
            res += 7;
        if (arena.timeZone.equals(player.timeZone))
            res += 3;
        if (arena.fightSize == player.fightSize)
            res += 6;
        if (arena.betPrice == player.betPrice)
            res += 6;
        if (arena.experience.equals(player.getExperience()))
            res += 4;
        if (res - arena.total < 0)
            return 0;
        return res - arena.total;
    }

    public void cleanArenas(){  // Leaves all arenas in 0 percentage
        ArenaNode tmp = nodeList.firstNode;
        while(tmp != null){
            tmp.percentage = (float) 0.0;
            tmp = tmp.nextN;
        }
    }
}