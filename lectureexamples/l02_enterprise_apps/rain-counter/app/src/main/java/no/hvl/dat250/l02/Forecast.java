package no.hvl.dat250.l02;

import java.time.OffsetDateTime;

public class Forecast {

    private OffsetDateTime ts;

    private Double rain;


    public Forecast() {
    }

    public OffsetDateTime getTs() {
        return ts;
    }

    public void setTs(OffsetDateTime ts) {
        this.ts = ts;
    }

    public Double getRain() {
        return rain;
    }

    public void setRain(Double rain) {
        this.rain = rain;
    }
}
