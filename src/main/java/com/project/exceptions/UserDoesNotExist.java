package com.project.exceptions;


import lombok.Data;

@Data
public class UserDoesNotExist extends RuntimeException {
    private String userID;

    public UserDoesNotExist(String userID){
        this.userID=userID;
    }

    @Override
    public String getMessage(){
        return "User does not exist";
    }
}
