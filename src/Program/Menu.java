package Program;

import Graphs.Graph;
import Graphs.NodeList;
import Graphs.ScrollList;
import Players.Player;

import java.util.Calendar;
import java.util.Scanner;
import Players.Ticket;
import java.text.ParseException;

public class Menu {

    public Menu() throws ParseException {
        NodeList nodeList = new NodeList();
        Graph graph = new Graph();

        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Start");
        System.out.println("2. Exit");
        System.out.print("Enter an option: ");

        int opt = scanner.nextInt();

        // Opcion de crear mas pantallas

        if (opt == 1) {
            Player player = new Player();
            player.nickname = "Joe";
            player.ticket = new Ticket(3);
            //player.ticket.print();
            player.timeZone = "EST";
            player.fightSize = 3;
            player.betPrice = 1000;
            graph.playerToNode(player);

            ScrollList scrollList = new ScrollList(player.arena, nodeList);
            //scrollList.print();

            // Set Inscription
            // Set Random Nickname
            // Key System

            // Set Ticket
            // Scroll
            // Fight
            // Set Ticket
            // Menu

        } else {
            return;
        }


        // graph.print();

        System.out.println("Thanks for playing!");
    }

    public static void main(String[] args) throws ParseException {
        new Menu();
    }
}
