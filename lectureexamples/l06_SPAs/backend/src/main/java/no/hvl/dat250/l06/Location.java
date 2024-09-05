package no.hvl.dat250.l06;


public class Location {

    private String name;

    private Double latitude;

    private Double longitude;

    private Double precipitation;

    private Double temperature;


    public Location() {
    }

    public Location(String name, Double latitude, Double longitude, Double precipitation, Double temperature) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.precipitation = precipitation;
        this.temperature = temperature;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(Double precipitation) {
        this.precipitation = precipitation;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Location) {
            return this.name.equals(((Location) obj).name);
        }
        return false;
    }
}
