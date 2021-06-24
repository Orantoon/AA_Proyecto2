package Graphs;

import Arenas.ArenaNode;
import Players.Player;
import ProbabilisticA.NodeToNode;
import ProbabilisticA.PlayerToNode;
import java.text.ParseException;

public class Graph {
    public NodeList nodeList;
    public PlayerToNode playerToNode;
    public NodeToNode nodeToNode;

    public Graph(){
        nodeList = new NodeList();
        playerToNode = new PlayerToNode(nodeList);
        nodeToNode = new NodeToNode(nodeList);

        nodeToNode.mainProcess();   // Connect Nodes
    }

    public void playerToNode(Player player) throws ParseException {
        playerToNode.mainProcess(player);
    }

    public void print(){
        ArenaNode node = nodeList.firstNode;
        while(node != null){
            node.print();
            node = node.nextN;
        }
    }
}
