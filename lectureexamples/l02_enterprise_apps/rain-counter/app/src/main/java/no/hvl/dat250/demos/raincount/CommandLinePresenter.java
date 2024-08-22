package no.hvl.dat250.demos.raincount;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class CommandLinePresenter implements Presenter {


    public void presentResult(long hours, OffsetDateTime start) {
        String ts = start.toInstant().atZone(ZoneId.of("Europe/Oslo")).format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));

        if (hours < 0) {
            hours *= -1;
            System.out.println("From " + ts + " it will remain dry for " + hours + " more hours! hopefully..." );
        } else {
            System.out.println("From " + ts + " it will take " + hours + " before it stops raining..." );

        }
    }

}
