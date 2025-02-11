package com.ms.rest.base.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ApiError {
    private String errorId;
    private String message = "Error";

    public static ApiError success(String message) {
        ApiError response = new ApiError();
        response.setMessage(message);

        return response;
    }
}
