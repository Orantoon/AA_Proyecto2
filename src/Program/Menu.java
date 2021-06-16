package Program;

import Players.Player;

import java.util.Scanner;

public class Menu {
    private Scanner scanner = new Scanner(System.in);

    public Menu(){
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
