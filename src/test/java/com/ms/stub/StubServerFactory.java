package com.ms.stub;


import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.stubbing.StubMapping;
import com.github.tomakehurst.wiremock.matching.UrlPattern;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.request;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;
import static com.github.tomakehurst.wiremock.stubbing.Scenario.STARTED;


public final class StubServerFactory {
    private static final String CONTENT = "Content-Type";
    private static final String JSON = "application/json";

    private StubServerFactory() {

    }

    public static StubServer createStubServer(StubData stubData) {
        Map<String, Object> context = new HashMap<>();
        RequestListener requestListener = new RequestListener();

        return new StubServer(createServer(context, stubData, requestListener), context, requestListener);
    }

    private static WireMockServer createServer(Map<String, Object> context, StubData stubData, RequestListener requestListener) {
        WireMockServer wireMockServer = new WireMockServer();
        createMappings(stubData).forEach(wireMockServer::addStubMapping);
        wireMockServer.start();

        wireMockServer.addMockServiceRequestListener(requestListener);
        return wireMockServer;
    }

    private static List<StubMapping> createMappings(StubData stubData) {
        List<StubMapping> stubMappings = new ArrayList<>();

        stubData.getRoutes().forEach(stubRoute -> {
            stubMappings.add(
                    request(stubRoute.getMethod().toString(), createUrlPattern(stubRoute))
                            .inScenario(stubRoute.getScenario())
                            .whenScenarioStateIs(stubRoute.getHttpStatus() == HttpStatus.OK ? STARTED : "NONE")
                            .willReturn(aResponse()
                                    .withStatus(stubRoute.getHttpStatus().value())
                                    .withHeader(CONTENT, JSON)
                                    .withBodyFile(stubRoute.getTemplate())
                                    .withFixedDelay(stubRoute.getDelayTime()))
                            .build()
                    );
        });

        return stubMappings;
    }

    private static UrlPattern createUrlPattern(StubRoute stubRoute) throws IllegalStateException {
        return stubRoute.getMatchType() == StubRoute.MatchType.EXACT ?
                urlEqualTo(stubRoute.getUrl()) :
                urlMatching(stubRoute.getUrl());
    }
}
