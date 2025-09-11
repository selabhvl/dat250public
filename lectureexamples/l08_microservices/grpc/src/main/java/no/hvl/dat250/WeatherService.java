package no.hvl.dat250;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;

public class WeatherService extends WeatherServiceGrpc.WeatherServiceImplBase {

    @Override
    public void getLocations(Weather.GetAllLocationsRequest request, StreamObserver<Weather.LocationsResponse> responseObserver) {
        responseObserver.onNext(Weather.LocationsResponse.newBuilder()
                .addLocations(
                        Weather.Location.newBuilder()
                                .setName("Oslo")
                                .setLatitude(58.12)
                                .setLongitude(6.1)
                                .build()
                ).build());

        responseObserver.onCompleted();
    }

    @Override
    public void getLocationWeather(Weather.GetLocationWeatherRequest request, StreamObserver<Weather.LocationWeather> responseObserver) {
        String loc = request.getLocation();
        if (loc.equals("Oslo")) {
            responseObserver.onNext(
                    Weather.LocationWeather.newBuilder()
                            .setName(loc)
                            .setPrecipitation(0.8)
                            .setTemperature(24.3)
                    .build()
            );
            responseObserver.onCompleted();
        } else {
            responseObserver.onError(new Exception("Location " + loc + "is unknown"));
        }
    }

    public static void startServer(int port) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(port).addService(new WeatherService()).build();
        server.start();
        System.out.println("Server listening on port 8083");
        server.awaitTermination();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        startServer(8083);

    }
}
