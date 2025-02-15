package com.ms.stub;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class StubData {
    private Integer port;
    private String templateFolder;
    private List<StubRoute> routes;
    private boolean https;

}
