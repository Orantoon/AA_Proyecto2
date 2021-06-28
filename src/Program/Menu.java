package Program;

import Graphs.Graph;
import Graphs.NodeList;
import Graphs.ScrollList;
import Players.Player;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Scanner;
import Players.Ticket;
import Utils.Clock;
import Utils.FileRead;

import java.text.ParseException;

public class Menu {

    public Menu() throws ParseException, FileNotFoundException {
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
            player.ticket = new Ticket(3);
            player.inscription = "quiero jugar en peleas de 15 pulpos y apostar más de $20.00 hasta un máximo de $100.00";
            //player.ticket.print();
            //graph.playerToNode(player);

            //player.readIns();
            //player.randNickname();
            //player.print();

            //Clock clock = new Clock();
            //System.out.println(Arrays.toString(clock.timeLeft(player.ticket.date, player.ticket.time)));

            //ScrollList scrollList = new ScrollList(player.arena, nodeList);
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

    public static void main(String[] args) throws ParseException, IOException {
        new Menu();
    }
}
