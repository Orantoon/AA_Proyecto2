package Graphs;

import Arenas.ArenaNode;

public class NodeList {   // All Nodes
    public ArenaNode firstNode;

    public NodeList(){
        initialize();
    }

    public void initialize(){
        firstNode = new ArenaNode(1, "Lunes", "EST", 10, 10, "Principiante");
        ArenaNode tmp = firstNode;
        tmp.nextN = new ArenaNode(2, "Jueves", "EST", 2, 50, "Avanzado");
        tmp = tmp.nextN;
        tmp.nextN = new ArenaNode(3, "Lunes", "AST", 4, 1000, "Brutal");
        tmp = tmp.nextN;
        tmp.nextN = new ArenaNode(4, "Sabado", "CST", 15, 100, "Medio");
        tmp = tmp.nextN;
        tmp.nextN = new ArenaNode(5, "Martes", "CST", 2, 25, "Principiante");
        tmp = tmp.nextN;
        tmp.nextN = new ArenaNode(6, "Domingo", "AST", 10, 50, "Brutal");
        tmp = tmp.nextN;
        tmp.nextN = new ArenaNode(7, "Viernes", "EST", 3, 1000, "Medio");
        tmp = tmp.nextN;
        tmp.nextN = new ArenaNode(8, "Miercoles", "CST", 5, 500, "Principiante");
        tmp = tmp.nextN;
        tmp.nextN = new ArenaNode(9, "Lunes", "EST", 5, 100, "Avanzado");
        tmp = tmp.nextN;
        tmp.nextN = new ArenaNode(10, "Domingo", "AST", 3, 25, "Brutal");
    }

    public void cleanNodes(){
        ArenaNode node = firstNode;
        while (node != null){
            node.clean();
            node = node.nextN;
        }
    }

    public int nodeAmount(){
        int res = 0;
        ArenaNode tmp = firstNode;
        while(tmp != null){
            res += 1;
            tmp = tmp.nextN;
        }
        return res;
    }

    public void addNode(ArenaNode node){
        if (firstNode == null)
            firstNode = node;
        else{
            ArenaNode tmp = firstNode;
            while(tmp.nextN != null){
                tmp = tmp.nextN;
            }
            tmp.nextN = node;
        }
    }

    public boolean isInList(ArenaNode node){
        ArenaNode tmp = firstNode;
        while(tmp != null){
            if (tmp.id == node.id)
                return true;
            tmp = tmp.nextN;
        }
        return false;
    }

    public void print(){
        System.out.println("=== Node List ===");
        System.out.println();
        ArenaNode tmp = firstNode;
        while(tmp != null){
            System.out.println(tmp.id);
            tmp = tmp.nextN;
        } System.out.println();
    }
}