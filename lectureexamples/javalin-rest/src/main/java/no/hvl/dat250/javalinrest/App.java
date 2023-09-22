package no.hvl.dat250.javalinrest;

import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.Random;

public class App {

    public final static String HTML_CONTENT = "<html>.....";

    public static void main(String[] args) {
        Javalin javalinServer = Javalin.create();

        javalinServer.get("/", ctx -> ctx.result("it works"));
        javalinServer.get("/recommendations/bergen", App::handleBergenWeather);


        javalinServer.start(7072);
    }

    private static void handleBergenWeather(Context context) {
        context.json(RecommendationDTO.createRandom("bergen"));

    }
}
