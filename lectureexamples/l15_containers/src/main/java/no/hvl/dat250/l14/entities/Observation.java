package no.hvl.dat250.l14.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

@Entity
public class Observation {

    public enum ObservationType {
        TEMPERATURE,
        PRECIPITATION,
        WIND
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JsonIgnore
    private Location location;

    private Instant timestamp;

    @Column(name = "measurement_value")
    private double value;

    private String unit;

    @Column(name = "observation_type")
    @Enumerated(EnumType.STRING)
    private ObservationType type;

    public Observation() {
    }

    public Observation(Location location, Instant timestamp, double value, String unit, ObservationType type) {
        this.location = location;
        this.timestamp = timestamp;
        this.value = value;
        this.unit = unit;
        this.type = type;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
