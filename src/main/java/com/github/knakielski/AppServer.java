package com.github.knakielski;

import io.javalin.Javalin;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AppServer {

    private final MainController controller;

    public void runJavalin() {
        Javalin.create()
               .get("/fetch-price", controller.handleGet())
               .get("/healthcheck", ctx -> ctx.json("OK"))
               .start(8080);
    }

}
