package com.project.controller.models;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateUserInput {
    private String name;
    private String email;
    private String password;
    private String phone;

}
