package com.project.accessor.models;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Builder
@Getter
public class UsersDTO {
    private String userId;
    private String name;
    private String email;
    private String password;
    private String phone;
    private UserAccountState state; //active,deactivated, suspended
    private UserRole role; //role_user, role_customer

   }
