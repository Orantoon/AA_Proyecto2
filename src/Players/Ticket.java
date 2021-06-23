package Players;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Ticket {
    public int[] date = new int[3];

    public Ticket(int day, int month, int year){
        date[0] = day;
        date[1] = month;
        date[2] = year;
    }

    public String weekDate() throws ParseException {
        String sdate = date[0]+"/"+date[1]+"/"+date[2];
        Date ndate = new SimpleDateFormat("dd/M/yyyy").parse(sdate);
        Calendar c = Calendar.getInstance();
        c.setTime(ndate);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);

        return switch (dayOfWeek) {
            case 1 -> "Domingo";
            case 2 -> "Lunes";
            case 3 -> "Martes";
            case 4 -> "Miercoles";
            case 5 -> "Jueves";
            case 6 -> "Viernes";
            case 7 -> "Sabado";
            default -> "ERROR";
        };

    }
}