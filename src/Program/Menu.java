package Program;

import Graphs.NodeList;
import Players.Player;
import java.util.Scanner;

public class Menu {

    public Menu(){
        NodeList nodeList = new NodeList();
        nodeList.initialize();

        Scanner scanner = new Scanner(System.in);
        System.out.println("1. New Match");
        System.out.println("2. Exit");
        System.out.print("Enter an option: ");

        int opt = scanner.nextInt();

        // Opcion de crear mas pantallas

        if (opt == 1) {
            Player player = new Player();
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

        System.out.println("Thanks for playing!");
    }

    public static void main(String[] args) {
        new Menu();
    }
}
