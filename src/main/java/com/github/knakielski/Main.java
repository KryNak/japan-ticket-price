package com.github.knakielski;

import lombok.SneakyThrows;

public class Main {

    @SneakyThrows
    public static void main(String[] args) {
        Scheduler.attach();
        AppServer.runJavalin();
    }

}
