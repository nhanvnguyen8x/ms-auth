package com.ms.stub;


import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.http.RequestListener;

import java.util.Map;

@Getter
public class StubApplication {

    private WireMockServer wireMockServer;

    private Map<String, Object> context;

    private RequestListener requestListener;

}
