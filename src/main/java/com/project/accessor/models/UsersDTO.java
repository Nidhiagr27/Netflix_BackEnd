package com.project.accessor.models;

import lombok.Data;

@Data
public class UsersDTO {
    private String userID;
    private String name;
    private String email;
    private String password;
    private String phone;
    private UserAccountState state; //active,deactivated, suspended

   }
