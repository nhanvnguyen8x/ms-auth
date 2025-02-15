package com.ms.stub;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public final class StubDataBuilder {
    private Integer port;
    private String templateFolder;
    private List<StubRoute> routes = new ArrayList<>();

    private boolean https = true;

    private StubDataBuilder() {

    }

    public static StubDataBuilder aStubData() {
        return new StubDataBuilder();
    }

    public StubDataBuilder withPort(Integer port) {
        this.port = port;
        return this;
    }

    public StubDataBuilder withTemplateFolder(String templateFolder) {
        this.templateFolder = templateFolder;
        return this;
    }

    public StubDataBuilder withRoutes(StubRoute... routes) {
        this.routes.addAll(List.of(routes));
        return this;
    }

    public StubDataBuilder withHttps(boolean https) {
        this.https = https;
        return this;
    }

    public StubData build() {
        return new StubData(port, templateFolder, routes, https);
    }
}
