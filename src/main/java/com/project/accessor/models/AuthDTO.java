package com.project.accessor.models;

import lombok.*;

@Builder
@Getter
public class AuthDTO {
    private String authId;
    private String token;
    private String userId;
}
