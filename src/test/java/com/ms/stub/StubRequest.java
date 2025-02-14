package com.ms.stub;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@Getter
@RequiredArgsConstructor
public class StubRequest {
    private final String body;
    private final Map<String, String> headers;
    private final String url;
}

