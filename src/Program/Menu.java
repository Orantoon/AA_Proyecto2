package Program;

import java.util.Scanner;

public class Menu {
    private Scanner scanner = new Scanner(System.in);

    public Menu(){
        System.out.println("1. Start");
        System.out.println("2. Exit");
        System.out.print("Enter an option: ");
        int opt = scanner.nextInt();

        if (opt == 1) {
            System.out.println("You chose option 1!");
        } else {
            System.out.println("Not 1");
            return;
        }

        System.out.println("Outside XD");
    }


    public static void main(String[] args) {
        new Menu();
    }
}
