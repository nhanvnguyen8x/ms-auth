package com.ms.auth.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ApiResponse {
    private Object data;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ApiError error;

    public static ApiResponse success(Object data) {
        ApiResponse response = new ApiResponse();
        response.setData(data);

        return response;
    }

    public static ApiResponse failure(ApiError error) {
        ApiResponse response = new ApiResponse();
        response.setError(error);
        return response;
    }
}
