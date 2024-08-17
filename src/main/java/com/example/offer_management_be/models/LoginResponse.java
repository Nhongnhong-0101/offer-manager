package com.example.offer_management_be.models;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
public class LoginResponse {
    @Getter
    private String token;

    private long expiresIn;

}
