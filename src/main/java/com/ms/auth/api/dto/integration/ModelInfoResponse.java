package com.ms.auth.api.dto.integration;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ModelInfoResponse {
    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "public_key")
    private String publicKey;

    @JsonProperty(value = "ethereum_address")
    private String ethereumAddress;

    @JsonProperty(value = "p2pPort")
    private String p2pPort;

    @JsonProperty(value = "rpcPort")
    private String rpcPort;

    @JsonProperty(value = "registered")
    private Boolean registered;

}
