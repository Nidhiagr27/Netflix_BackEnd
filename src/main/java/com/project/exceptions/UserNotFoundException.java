package com.project.exceptions;


import lombok.Data;

@Data
public class UserNotFoundException extends RuntimeException{
    private String email;
    private String userID;

    public UserNotFoundException(String email){
        this.email=email;
    }



    @Override
    public String getMessage(){
        return "User not found with email "+ email;
    }

}
