package com.github.knakielski;

import static com.github.knakielski.client.factory.ClientType.MOCK;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import com.github.knakielski.client.factory.SkyScannerClient;
import com.github.knakielski.client.factory.SkyScannerClientFactory;
import com.github.knakielski.jsonparser.SkyScannerResponseProcessor;
import io.javalin.http.Context;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

final class TestMainController {

    @Test
    void handleGet() throws Exception {
        //given
        Context ctx = mock();

        SkyScannerClient client = SkyScannerClientFactory.create(MOCK);
        SkyScannerResponseProcessor processor = spy(SkyScannerResponseProcessor.create());
        CommissionService commissionService = spy();
        MainController mainController = new MainController(client, processor, commissionService);

        //when
        mainController.handleGet().handle(ctx);

        //then
        verify(ctx).json(BigDecimal.valueOf(4416_99, 2));
    }

}
