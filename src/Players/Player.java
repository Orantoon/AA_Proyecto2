package Players;

import Arenas.ArenaNode;
import Files.FileRead;
import Utils.RandomNum;

import java.io.FileNotFoundException;
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
        fightSize = -1;
        betPrice = -1;
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

    public void readIns(){
        inscription = inscription.toLowerCase();  // Everything to Lower Case
        inscription = inscription.replaceAll("\\$","");   // No $ Symbol
        inscription += " "; // Extra space at the end to read the last word

        // No Accent Marks
        inscription = inscription.replaceAll("á","a");
        inscription = inscription.replaceAll("é","e");
        inscription = inscription.replaceAll("í","i");
        inscription = inscription.replaceAll("ó","o");
        inscription = inscription.replaceAll("ú","u");


        int fIndex = inscription.indexOf(' ');
        String str;

        float num1 = 0;
        float num2 = 0;
        boolean n1Red = false;
        boolean n2Red = false;
        boolean bpRed = false;
        boolean fsRed = false;

        while (fIndex != -1){   // No more spaces
            str = inscription.substring(0,fIndex);  // Gets the string until the next space (index)

            try {   // Number
                if (!n1Red){
                    num1 = Float.parseFloat(str);
                    n1Red = true;
                }else{
                    num2 = Float.parseFloat(str);
                    n2Red = true;
                }
            }catch (Exception e){   // Word
                if (!bpRed && checkKeyword(str) == 1){    // Bet Price
                    if (!n2Red){
                        betPrice = num1;
                    }else{
                        betPrice = betRange(num1, num2);
                    }
                    num1 = 0;
                    num2 = 0;
                    n1Red = false;
                    n2Red = false;
                    bpRed = true;
                }else if (!fsRed && checkKeyword(str) == 2){  // Fight Size
                    if (!n2Red){
                        fightSize = (int) num1;
                    }else{
                        fightSize = (int) (num1 + num2);
                    }
                    num1 = 0;
                    num2 = 0;
                    n1Red = false;
                    n2Red = false;
                    fsRed = true;
                }
            }

            inscription = inscription.substring(fIndex+1);  // Deletes the string processed
            fIndex = inscription.indexOf(' ');  // Looks for the next index

            if (!bpRed && inscription.indexOf(' ') == -1){    // If there are still numbers left and no keyword, those will be part of betPrice
                if (!n2Red){
                    betPrice = num1;
                }else{
                    betPrice = betRange(num1, num2);
                }
            }
        }

    }

    /*
    0 = No Important Word
    1 = Bet Price
    2 = Fight Size
     */
    public int checkKeyword(String keyword){
        switch (keyword){
            // Bet Price
            case "dolar": return 1;
            case "dolares": return 1;
            case "credito": return 1;
            case "creditos": return 1;

            // Fight Size
            case "pulpo": return 2;
            case "pulpos": return 2;
            case "jugadores": return 2;
            case "jugador": return 2;
        }

        return 0;
    }

    public float betRange(float num1, float num2){
        RandomNum randomNum = new RandomNum();
        float n1;
        float n2;
        if (num1 > num2){
            n1 = num1;
            n2 = num2;
        }else{
            n2 = num1;
            n1 = num2;
        }
        int value = randomNum.getRandom((int)(n1-n2));
        value += n2;

        if (value <= 10)
            return 10;
        if (value <= 25)
            return 25;
        if (value <= 50)
            return 50;
        if (value <= 100)
            return 100;
        if (value <= 500)
            return 500;
        return 1000;
    }

    public void randNickname() throws FileNotFoundException {
        FileRead fileRead = new FileRead();
        RandomNum randomNum = new RandomNum();

        // while (true){
        int value = randomNum.getRandom(fileRead.lineCount("nicknames")-1);
        String res = fileRead.readLine("nicknames",value);
        res += "-";
        value = randomNum.getRandom(fileRead.lineCount("nicknames")-1);
        res += fileRead.readLine("nicknames",value);

        // SI res NO EXISTE CONTINUE, SI ES NUEVO BREAK, VALIDAR AQUI =================
        // }

        nickname = res;
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