package ProbabilisticA;

import Arenas.ArenaNode;
import Graphs.NodeList;
import Graphs.UsedNodes;
import Utils.RandomNum;

public class NodeToNode {
    public NodeList nodeList;
    public UsedNodes usedNodes;

    public NodeToNode(NodeList nodeList){
            this.nodeList = nodeList;
            usedNodes = new UsedNodes();
        }

        public void mainProcess(){
            ArenaNode tmp = nodeList.firstNode;
            while(tmp != null){
                mainLoop(tmp);
                tmp = tmp.nextN;
            }
        }

        public void mainLoop(ArenaNode node){
            cleanArenas();
            RandomNum random = new RandomNum();
            int nodeAmount = random.getRandom(nodeList.nodeAmount() - 1);
            usedNodes.addNode(node);

            while (nodeAmount > 0){
                doNode(node, random);
                nodeAmount -= 1;
            }
        }

        public void doNode(ArenaNode node, RandomNum random){
            int value = random.getRandom(100);
            int sum = 0;
            int total = sumAllNodes(node);
            setPercentagesA(node, total);

            ArenaNode tmp = nodeList.firstNode;
            while(tmp != null){
                sum += tmp.percentage;
                if (value <= sum){
                    connectNodes(tmp,node);
                    usedNodes.addNode(tmp);
                    return;
                }
                tmp = tmp.nextN;
            }
        }

        public int sumAllNodes(ArenaNode node){
            int res = 0;
            ArenaNode tmp = nodeList.firstNode;
            while(tmp != null){
                res += compNodeNode(tmp, node);
                tmp = tmp.nextN;
            } return res;
        }

        public void setPercentagesA(ArenaNode node, int total){
            ArenaNode tmp = nodeList.firstNode;
            //System.out.println();
            //System.out.println(node.id);
            while(tmp != null){
                tmp.percentage = (float) (compNodeNode(tmp,node) * 100) / total;
                //System.out.println(tmp.percentage);
                tmp = tmp.nextN;
            }
        }

        public void connectNodes(ArenaNode arena, ArenaNode node){
            node.adjacent.add(arena);
        }

        public int compNodeNode(ArenaNode arena, ArenaNode node){
            int res = 0;

            if (!usedNodes.isInList(arena)){   // Same node, 0% chance of getting it
                if (arena.ticketDay.equals(node.ticketDay))
                    res += 7;
                if (arena.timeZone.equals(node.timeZone))
                    res += 3;
                if (arena.fightSize == node.fightSize)
                    res += 6;
                if (arena.betPrice == node.betPrice)
                    res += 6;
                if (arena.experience.equals(node.experience))
                    res += 4;
                if (arena.total == node.total)
                    res += 5;
            }
            return res;
        }

        public void cleanArenas(){  // Leaves all arenas in 0 percentage and cleans the UsedNodes List
            ArenaNode tmp = nodeList.firstNode;
            while(tmp != null){
                tmp.percentage = (float) 0.0;
                tmp.nextU = null;
                tmp = tmp.nextN;
            }
            usedNodes = new UsedNodes();
        }
    }