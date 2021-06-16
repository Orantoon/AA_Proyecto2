package Players;

import java.time.LocalTime;

public class Player {
    public String nickname;
    public float credits;
    public Ticket ticket;

    public String timeZone;
    public String inscription;
    public int matchesPlayed;

    public Player(){
        nickname = " ";
        credits = (float) 1000.00;
        ticket = null;

        timeZone = null;
        inscription = " ";
        matchesPlayed = 0;
    }

    /*
    public int[] readIns(){

    }   // [FightSize, Amount]
     */
}
