package Graphs;

import Arenas.ArenaNode;

public class UsedNodes {
    public ArenaNode firstNode;

    public UsedNodes(){}

    public int nodeAmount(){
        int res = 0;
        ArenaNode tmp = firstNode;
        while(tmp != null){
            res += 1;
            tmp = tmp.nextU;
        }
        return res;
    }

    public void addNode(ArenaNode node){
        if (firstNode == null)
            firstNode = node;
        else{
            ArenaNode tmp = firstNode;
            while(tmp.nextU != null){
                tmp = tmp.nextU;
            }
            tmp.nextU = node;
        }
    }

    public boolean isInList(ArenaNode node){
        ArenaNode tmp = firstNode;
        while(tmp != null){
            if (tmp.id == node.id)
                return true;
            tmp = tmp.nextU;
        }
        return false;
    }

    public void print(){
        System.out.println("=== Used Nodes ===");
        System.out.println();
        ArenaNode tmp = firstNode;
        while(tmp != null){
            System.out.println(tmp.id);
            tmp = tmp.nextU;
        } System.out.println();
    }
}