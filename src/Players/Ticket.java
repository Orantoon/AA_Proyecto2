package Players;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Ticket {
    public int[] date = new int[3];
    public int[] time = new int[3];

    public Ticket(int weekDate){
        //newDate(weekDate); //Lo hace realmente

        //Para revision ////////////////////////////////////////////////////////////////////////////////////////////////
        date = new int[]{29,6,2021};
        time[0] = 17;   // 05 : 25 : 30 pm
        time[1] = 25;
        time[2] = 30;

    }

    public void newDate(int weekDate){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);

        while (calendar.get(Calendar.DAY_OF_WEEK) != 1){    // First day of next week
            calendar.add(Calendar.DATE, 1);
        }
        calendar.add(Calendar.DATE, weekDate - 1);  // Date of next week

        date[0] = calendar.get(Calendar.DATE);
        date[1] = calendar.get(Calendar.MONTH) + 1;
        date[2] = calendar.get(Calendar.YEAR);
    }

    public String weekDate() throws ParseException {
        String sdate = date[1]+"/"+(date[1])+"/"+date[2];
        Date ndate = new SimpleDateFormat("dd/M/yyyy").parse(sdate);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(ndate);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

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

    public String getDate(){
        return date[0] + "/" + date[1] + "/" + date[2] + " " + time[0] + ":" + time[1] + ":" + time[2];
    }

    public void print(){
        System.out.println("=== Ticket ===");
        System.out.println("Date: " + date[0] + "/" + date[1] + "/" + date[2]);
        System.out.println("Time: " + time[0] + ":" + time[1] + ":" + time[2]);
        System.out.println();
    }
}