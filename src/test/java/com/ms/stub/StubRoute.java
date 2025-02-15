package com.ms.stub;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class StubRoute {
    private final String scenario;
    private final String url;
    private final String template;
    private final Method method;
    private final HttpStatus httpStatus;
    private final MatchType matchType;
    private final Integer delayTime;

    public enum Method {
        GET, POST, PUT, DELETE
    }

    public enum MatchType {
        EXACT, REGEX
    }
}
