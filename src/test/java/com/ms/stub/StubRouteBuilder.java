package com.ms.stub;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

public class StubRouteBuilder {
    private String scenario;
    private String url;
    private String template;
    private StubRoute.Method method;
    private HttpStatus httpStatus;
    private StubRoute.MatchType matchType;
    private Integer delayTime;

    public static StubRouteBuilder aStubRoute() {
        return new StubRouteBuilder();
    }

    public StubRouteBuilder withScenario(String scenario) {
        this.scenario = scenario;
        return this;
    }

    public StubRouteBuilder withUrl(String url) {
        this.url = url;
        return this;
    }

    public StubRouteBuilder withTemplate(String template) {
        this.template = template;
        return this;
    }

    public StubRouteBuilder withMethod(StubRoute.Method method) {
        this.method = method;
        return this;
    }

    public StubRouteBuilder withHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
        return this;
    }

    public StubRouteBuilder withMatchType(StubRoute.MatchType matchType) {
        this.matchType = matchType;
        return this;
    }

    public StubRouteBuilder withDelayTime(Integer delayTime) {
        this.delayTime = delayTime;
        return this;
    }

    public StubRoute build() {
        if (scenario != null && url != null && template != null && method != null) {
            return new StubRoute(scenario, url, template, method, httpStatus, matchType, delayTime);
        }
        return null;
    }
}
