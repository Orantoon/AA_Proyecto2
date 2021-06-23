package Players;

public class Player {
    public String nickname;
    public float credits;
    public String inscription;
    public Ticket ticket;

    public String timeZone;
    public int fightSize;
    public int betPrice;
    public int matchesPlayed;

    public Player next;

    public Player(){
        nickname = null;
        credits = (float) 1000.00;
        inscription = null;
        ticket = null;

        timeZone = null;
        fightSize = 0;
        betPrice = 0;
        matchesPlayed = 0;

        next = null;
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

    /*
    public void readIns(){

    }   // [FightSize, Amount]
     */
}
