package no.hvl.dat250.springrest;

import java.util.Random;

public class RecommendationDTO {

    private boolean shouldUseUmbrella;
    private double confidence;

    private String location;

    public RecommendationDTO() {

    }

    public RecommendationDTO(boolean shouldUseUmbrella, double confidence, String location) {
        this.shouldUseUmbrella = shouldUseUmbrella;
        this.confidence = confidence;
        this.location = location;
    }

    public boolean isShouldUseUmbrella() {
        return shouldUseUmbrella;
    }

    public void setShouldUseUmbrella(boolean shouldUseUmbrella) {
        this.shouldUseUmbrella = shouldUseUmbrella;
    }

    public double getConfidence() {
        return confidence;
    }

    public void setConfidence(double confidence) {
        this.confidence = confidence;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    public static RecommendationDTO createRandom(String location) {
        Random r = new Random();
        return new RecommendationDTO(r.nextBoolean(), r.nextDouble(), location);
    }
}
