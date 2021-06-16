package Graphs;

import ProbabilisticA.NodeToNode;
import ProbabilisticA.PlayerToNode;

public class Graph {
    public NodeList nodeList;
    public PlayerToNode playerToNode;
    public NodeToNode nodeToNode;

    public Graph(){
        nodeList = new NodeList();
        playerToNode = new PlayerToNode();
        nodeToNode = new NodeToNode();
    }
}
