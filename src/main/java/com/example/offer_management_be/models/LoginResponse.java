package com.example.offer_management_be.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.annotation.Nullable;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginResponse {
    @Getter
    private String token;

    private long expiresIn;

    private int statusCode = 1000;

    private String message;

    public  LoginResponse(String message){
        this.message = message;
    }
}
