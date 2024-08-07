package com.github.knakielski;

import com.github.knakielski.client.factory.ClientType;
import com.github.knakielski.client.factory.SkyScannerClient;
import com.github.knakielski.client.factory.SkyScannerClientFactory;
import com.github.knakielski.jsonparser.SkyScannerResponseProcessor;
import lombok.SneakyThrows;

public class Main {

    @SneakyThrows
    public static void main(String[] args) {
        Scheduler.attach();

        SkyScannerClient client = SkyScannerClientFactory.create(ClientType.CACHE_CLIENT);
        SkyScannerResponseProcessor processor = SkyScannerResponseProcessor.create();
        MainController mainController = new MainController(client, processor);
        AppServer appServer = new AppServer(mainController);
        appServer.runJavalin();
    }

}
