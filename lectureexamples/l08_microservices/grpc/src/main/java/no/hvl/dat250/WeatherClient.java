package no.hvl.dat250;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class WeatherClient {

    WeatherServiceGrpc.WeatherServiceBlockingStub client;

    public WeatherClient(String address, int port) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(address, port).usePlaintext().build();
        this.client = WeatherServiceGrpc.newBlockingStub(channel);

    }

    public void getAllLocations() {
        Weather.LocationsResponse locations = this.client.getLocations(Weather.GetAllLocationsRequest.newBuilder().build());
        System.out.println("Locations:");
        for (Weather.Location l : locations.getLocationsList()) {
            System.out.printf("- %s (lat: %.2f long: %.2f)\n", l.getName(), l.getLatitude(), l.getLongitude());
        }
        System.out.println("");
    }

    public void getTemperature(String location) {
        Weather.LocationWeather locationWeather = this.client.getLocationWeather(Weather.GetLocationWeatherRequest.newBuilder().setLocation(location).build());
        System.out.printf("Checking weather in %s:\n", location);
        System.out.printf("%s temperature: %f, precipitation: %f\n", location, locationWeather.getTemperature(), locationWeather.getPrecipitation());
    }

    public static void main(String[] args) {
        WeatherClient c = new WeatherClient("localhost", 8083);
        c.getAllLocations();
        c.getTemperature("Oslo");
    }
}
