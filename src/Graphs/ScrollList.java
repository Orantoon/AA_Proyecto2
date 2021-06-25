package Graphs;

import Arenas.ArenaNode;

import java.util.Vector;

public class ScrollList {
    public ArenaNode firstNode;
    public ArenaNode currentNode;
    public NodeList nodeList;
    public Vector<ArenaNode> usedNodes;

    public ScrollList(ArenaNode node, NodeList nodeList){
        usedNodes = new Vector<ArenaNode>();
        this.nodeList = nodeList;
        createList(node);
    }

    public void createList(ArenaNode node){
        firstNode = node;
        currentNode = firstNode;
        usedNodes.add(firstNode);

        addNodes();
    }

    public void addNodes(){
        ArenaNode current = firstNode;
        ArenaNode nNode;

        while (true){
            nNode = chooseArena(current);
            if (nNode == null){
                current.next = firstNode;
                firstNode.back = current;
                return;
            }else{
                current.next = nNode;
                nNode.back = current;
                current = current.next;
                usedNodes.add(current);
            }
        }
    }

    public ArenaNode chooseArena(ArenaNode current){
        ArenaNode node = null;
        int value = 0;
        ArenaNode tmp = nodeList.firstNode;
        while (tmp != null){
            if (usedNodes.contains(tmp) || tmp.id == firstNode.id){
                tmp = tmp.nextN;
                continue;
            }
            if (node == null){
                node = tmp;
                value = getValue(current, tmp);
            }
            else{
                if (getValue(current, tmp)>=value){
                    value = getValue(current, tmp);
                    node = tmp;
                }
            }
            tmp = tmp.nextN;
        }

        return node;
    }

    public int getValue(ArenaNode current, ArenaNode node){
        int res = 0;

        if (current.ticketDay.equals(node.ticketDay))
            res += 7;
        if (current.timeZone.equals(node.timeZone))
            res += 3;
        if (current.fightSize == node.fightSize)
            res += 6;
        if (current.betPrice == node.betPrice)
            res += 6;
        if (current.experience.equals(node.experience))
            res += 4;
        if (current.total == node.total)
            res += 5;

        return res;
    }

    public void next(){
        currentNode = currentNode.next;
    }

    public void back(){
        currentNode = currentNode.back;
    }

    public void print(){
        ArenaNode tmp = currentNode;

        System.out.println("=== Scroll List ===");
        System.out.println();
        System.out.println("-- Node " + tmp.id);
        System.out.println("Next: " + tmp.next.id);
        System.out.println("Back: " + tmp.back.id);
        tmp = tmp.next;

        while (tmp != currentNode){
            System.out.println();
            System.out.println("-- Node " + tmp.id);
            System.out.println("Next: " + tmp.next.id);
            System.out.println("Back: " + tmp.back.id);
            tmp = tmp.next;
        }
        System.out.println();
    }
}
