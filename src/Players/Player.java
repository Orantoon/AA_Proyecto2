package Players;

import Arenas.ArenaNode;
import Utils.RandomNum;

import java.text.ParseException;

public class Player {
    public String nickname;
    public float credits;
    public String inscription;
    public boolean checkInReady;
    public Ticket ticket;

    public String timeZone;
    public int fightSize;
    public float betPrice;
    public int matchesPlayed;

    public ArenaNode arena;

    public Player next;

    public Player(){
        nickname = null;
        credits = (float) 1000.00;
        inscription = null;
        checkInReady = false;
        ticket = null;

        timeZone = getTimeZone();
        fightSize = 0;
        betPrice = 0;
        matchesPlayed = 0;

        arena = null;
    }

    public String getTimeZone(){
        String tz = " ";
        RandomNum random = new RandomNum();
        int value = random.getRandom(3);

        switch (value){
            case 1:
                tz = "CST";
                break;
            case 2:
                tz = "EST";
                break;
            case 3:
                tz = "AST";
        }
        return tz;
    }

    public String getExperience(){
        if (matchesPlayed <= 10)
            return "Principiante";
        if (matchesPlayed <= 30)
            return "Medio";
        if (matchesPlayed <= 50)
            return "Avanzado";
        return "Brutal";
    }

    public int limitFightSize(){
        if (fightSize < 2)
            return 2;
        if (fightSize > 10)
            return 10;
        return fightSize;
    }

    public int limitBetPrice(){
        if (betPrice <= 10)
            return 10;
        if (betPrice <= 25)
            return 25;
        if (betPrice <= 50)
            return 50;
        if (betPrice <= 100)
            return 100;
        if (betPrice <= 500)
            return 500;
        return 1000;
    }

    public float betRange(float num1, float num2){
        float n1;
        float n2;
        if (num1 > num2){
            n1 = num1;
            n2 = num2;
        }else{
            n2 = num1;
            n1 = num2;
        }
        if (n1 < 10)
            return 10;
        if (n1 >= 10 && 10 >= n2)
            return 10;
        if (n1 >= 25 && 25 >= n2)
            return 25;
        if (n1 >= 50 && 50 >= n2)
            return 50;
        if (n1 >= 100 && 100 >= n2)
            return 100;
        if (n1 >= 500 && 500 >= n2)
            return 500;
        if (n1 >= 1000 && 1000 >= n2)
            return 1000;
        if (n2 > 1000)
            return 1000;

        System.out.println("?????");
        return 50;
    }

    public void readIns(){
        String keyword = "";
        String num1 = "";
        String num2 = "";
        //String max = "";
        //String min = "";

        int fs = 0;  // Fight Size
        float bp = 0;  // Bet Price
        char current;
        int code = 0;
        boolean dubs = false;

        for (int i = 0; i < inscription.length(); i++){
            current = inscription.charAt(i);

            // Number
            if (Character.isDigit(current)){    // If Digit, Saved
                num1 += current;
                continue;
            }
            if (current == '.' && Character.isDigit((inscription.charAt(i+1)))){    // If Dot and next is Digit, Saved
                dubs = true;
                num1 += current;
                continue;
            }

            if (dubs)
                code = 1;
            // Word
            switch (code){
                case 1: // Float.parseFloat(num1) + Float.parseFloat(num2)
                    dubs = false;
                    if (num1.equals(""))
                        break;
                    if (!num2.equals("")){
                        bp = betRange(Float.parseFloat(num1), Float.parseFloat(num2));
                        break;
                    }
                    if (bp != 0){
                        bp = betRange(Float.parseFloat(num1), bp);
                        dubs = true;
                        break;
                    }

                    bp =  Float.parseFloat(num1);
                    num1 = "";
                    num2 = "";
                    code = 0;
                    break;
                case 2:

                case 3:

                case 4:
                    if (num1.equals("") || fs != 0)
                        break;
                    if (!num2.equals("")){
                        fs = Integer.parseInt(num1) + Integer.parseInt(num2);
                        num1 = "";
                        num2 = "";
                        code = 0;
                        break;
                    }
                    fs = Integer.parseInt(num1);
                    num1 = "";
                    num2 = "";
                    code = 0;
            }

            if (current == ' '){
                code = checkKeyword(keyword);
                keyword = "";
                continue;
            }
            keyword += current;
        }

    }

    /*
    0 = No Important Word
    1 = Bet Price
    2 = Bet Price MIN
    3 = Bet Price MAX
    4 = Fight Size
     */
    public int checkKeyword(String keyword){
        keyword = keyword.toLowerCase();  // Everything to Lower Case
        keyword = keyword.replaceAll("\\s","");   // No Spaces
        // No Accent Marks
        keyword = keyword.replaceAll("á","a");
        keyword = keyword.replaceAll("é","e");
        keyword = keyword.replaceAll("í","i");
        keyword = keyword.replaceAll("ó","o");
        keyword = keyword.replaceAll("ú","u");

        switch (keyword){
            // Bet Price
            //case "$": return 1;
            case "dolar": return 1;
            case "dolares": return 1;
            case "credito": return 1;
            case "creditos": return 1;

            // Bet Price MIN
            //case "minimo": return 2;

            // Bet Price MAX
            //case "maximo": return 3;

            // Fight Size
            case "pulpo": return 4;
            case "pulpos": return 4;
            //case "pelea": return 4;
            //case "peleas": return 4;
        }

        return 0;
    }

    public void clean(){
        inscription = null;
        checkInReady = false;
        ticket = null;

        fightSize = 0;
        betPrice = 0;
        matchesPlayed = 0;

        arena = null;
    }

    public void print() throws ParseException {
        System.out.println();
        System.out.println("=== Player " + nickname);
        System.out.println("Ticket Date: " + ticket.weekDate());
        System.out.println("Time Zone: " + timeZone);
        System.out.println("Fight Size: " + fightSize);
        System.out.println("Bet Price: " + betPrice);
        System.out.println("Exp: " + getExperience());
    }
}