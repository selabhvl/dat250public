package no.hvl.dat250.rest;

import java.util.Objects;

public class Suggestion {

    public  enum Gadget {
        SUNGLASSES,
        UMBRELLA,
        NOTHING
    }

    private Gadget gadget;

    private int hoursGuaranteed;

    public Suggestion(Gadget gadget, int hoursGuaranteed) {
        this.gadget = gadget;
        this.hoursGuaranteed = hoursGuaranteed;
    }

    public Suggestion() {
    }

    public Gadget getGadget() {
        return gadget;
    }

    public void setGadget(Gadget gadget) {
        this.gadget = gadget;
    }

    public int getHoursGuaranteed() {
        return hoursGuaranteed;
    }

    public void setHoursGuaranteed(int hoursGuaranteed) {
        this.hoursGuaranteed = hoursGuaranteed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Suggestion that = (Suggestion) o;
        return hoursGuaranteed == that.hoursGuaranteed && gadget == that.gadget;
    }

    @Override
    public int hashCode() {
        return Objects.hash(gadget, hoursGuaranteed);
    }
}
