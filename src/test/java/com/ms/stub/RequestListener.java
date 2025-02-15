package com.ms.stub;

import com.github.tomakehurst.wiremock.http.HttpHeaders;
import com.github.tomakehurst.wiremock.http.Request;
import com.github.tomakehurst.wiremock.http.Response;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class RequestListener implements com.github.tomakehurst.wiremock.http.RequestListener {
    private final List<StubRequest> requests = new ArrayList<>();

    @Override
    public void requestReceived(Request request, Response response) {
        requests.add(new StubRequest(request.getBodyAsString(), getHeaderMap(request.getHeaders()), request.getUrl()));
    }

    void reset() {
        requests.clear();
    }

    private Map<String, String> getHeaderMap(HttpHeaders httpHeaders) {
        Map<String, String> headerMap = new HashMap<>();
        httpHeaders.keys().forEach(key -> headerMap.put(key, httpHeaders.getHeader(key).firstValue()));
        return headerMap;
    }
}
