package no.hvl.dat250.javalinrest;

import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.Random;

public class App {

    public final static String HTML_CONTENT = "<html>.....";

    public static void main(String[] args) {
        Javalin javalinServer = Javalin.create( javalinConfig -> {
            javalinConfig.staticFiles.add(staticFileConfig ->
                    staticFileConfig.directory = "/static"
            );
        });

        javalinServer.get("/", ctx -> ctx.result("it works"));
        javalinServer.get("/recommendation/{location}", App::handleLocation);

        javalinServer.start(7072);
    }

    private static void handleLocation(Context context) {
        String location = context.pathParam("location");
        String name = context.queryParam("name");
        System.out.println(name + " was asking");

        context.json(RecommendationDTO.createRandom(location));
    }


}
