package Utils;


import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Clock {
    public Clock (){}

    public String timeLeft (int[] date, int[] ntime) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime time = LocalDateTime.of(date[2],date[1],date[0],ntime[0],ntime[1],ntime[2]);


        Long[] res = new Long[]
                {ChronoUnit.YEARS.between(now,time),
                ChronoUnit.MONTHS.between(now,time),
                ChronoUnit.DAYS.between(now,time),
                ChronoUnit.HOURS.between(now,time),
                ChronoUnit.MINUTES.between(now,time),
                ChronoUnit.SECONDS.between(now,time)};

        if (res[0] != 0)
            return res[0] + " YEARS";
        if (res[1] != 0)
            return res[1] + " MONTHS";
        if (res[2] != 0)
            return res[2] + " DAYS";
        if (res[3] != 0)
            return res[3] + " HOURS";
        if (res[4] != 0)
            return res[4] + " MINUTES";
        return res[5] + " SECONDS";
    }

    public boolean ready (int[] date, int[] ntime) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime time = LocalDateTime.of(date[2],date[1],date[0],ntime[0],ntime[1],ntime[2]);

        return ChronoUnit.YEARS.between(now, time) == 0 &&
                ChronoUnit.MONTHS.between(now, time) == 0 &&
                ChronoUnit.DAYS.between(now, time) == 0 &&
                ChronoUnit.HOURS.between(now, time) == 0 &&
                ChronoUnit.MINUTES.between(now, time) == 0 &&
                ChronoUnit.SECONDS.between(now, time) == 0;
    }
}
