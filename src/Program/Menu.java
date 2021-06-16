package Program;

import java.util.Random; ///////////////////////////////////////////////////////////////////////////////////////////////
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

    public static char extractBits(char genotype, int pos, int k){
        return (char) (((1 << k) - 1) & (genotype >> pos));
    }


    public static void main(String[] args) {
        Random r = new Random();
        int point = r.nextInt(4) + 2;

        char a = 56;
        char b = 231;

        System.out.println("Point : " + point);
        char a2 = extractBits(a, 0, point);
        char b2 = extractBits(b, point, 8-point);

        System.out.println("a2 " + (int) a2);
        System.out.println("b2 " + (int) b2);

        char c = b2;
        c <<= point;
        c |= a2;

        System.out.println("C: " + (int) c);

        // Mutation
        int bitMutation = -1;
        double percentage = r.nextDouble();

        if (percentage <= 0.1){
            bitMutation = r.nextInt(8);
            c &= ~(1 << (bitMutation));
        }

        System.out.println("C: " + (int) c + " Mutation: " + bitMutation + " Percentage " + percentage);


        //new Menu();
    }
}
