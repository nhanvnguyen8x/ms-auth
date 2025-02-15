package com.ms.stub;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.matching.RequestPattern;
import com.github.tomakehurst.wiremock.stubbing.StubMapping;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@AllArgsConstructor
@Getter
@Slf4j
public class StubServer {
    private static final String STARTED = "STARTED";
    private static final String NON_ACTIVE_STATE = "NON_ACTIVE_STATE";

    private WireMockServer wireMockServer;
    private Map<String, Object> context;
    private RequestListener requestListener;

    public void reset() {
        context.clear();
        requestListener.reset();
        setStatus(HttpStatus.OK);
    }

    public void setScenario(String scenario) {
        AtomicInteger counter = new AtomicInteger();
        List<StubMapping> stubMappings = new ArrayList<>();
        stubMappings.forEach(m -> {
            if (m.getScenarioName() != null && m.getScenarioName().equalsIgnoreCase(scenario)) {
                m.setRequiredScenarioState(STARTED);
                counter.incrementAndGet();
                printStubMapping("Scenario " + counter.get(), m);
            } else {
                m.setRequiredScenarioState(NON_ACTIVE_STATE);
            }
        });

    }

    public void printStubMapping(String callerName, StubMapping stubMapping) {
        RequestPattern requestPattern = stubMapping.getRequest();
        String url = requestPattern.getUrl();

        System.out.println(callerName + ": " + url + ": " + stubMapping.getScenarioName());
    }

    public void setStatus(HttpStatus status) {
        wireMockServer.getStubMappings().forEach(m -> {
            if (m.getResponse().getStatus() == status.value()) {
                m.setRequiredScenarioState(STARTED);
            } else {
                m.setRequiredScenarioState(NON_ACTIVE_STATE);
            }
        });
    }

    public void addToContext(String key, Object value) {
        context.put(key, value);
    }
    public StubRequest getFirstRequest() {
        return requestListener.getRequests().size() > 0 ? requestListener.getRequests().get(0) : null;
    }
}
