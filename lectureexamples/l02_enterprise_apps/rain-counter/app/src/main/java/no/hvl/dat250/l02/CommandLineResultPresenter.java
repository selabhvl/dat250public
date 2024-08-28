package no.hvl.dat250.l02;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class CommandLineResultPresenter {


    public void presentResult(long hours, OffsetDateTime start) {
        String ts = start.toInstant().atZone(ZoneId.of("Europe/Oslo")).format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));

        if (hours < 0) {
            hours *= -1;
            System.out.println("From " + ts + " it will remain dry for " + hours + " more hours! hopefully..." );
        } else {
            System.out.println("From " + ts + " it will take " + hours + " more hours before it stops raining..." );

        }
    }

    public void presentException(OffsetDateTime start, HowLongAlgorithm.AlgorithmException e) {
        String ts = start.toInstant().atZone(ZoneId.of("Europe/Oslo")).format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));

        switch (e.type) {
            case NO_DATA:
            case NOT_ENOUGH_DATA:
                System.out.println("Cannot reliably calculate rain hours from " + ts + " because the given forecast data does not contain enough data after the given datetime");
                break;
            case TOO_FAR_ADVANCED:
                System.out.println("Cannot reliably calculate rain hours from " + ts + " because the given forecast data does is too far in the future");

        }
    }

}
