package no.hvl.dat250.demos.raincount;

import java.time.OffsetDateTime;

public interface Presenter {

    void presentResult(long hours, OffsetDateTime start);
}
