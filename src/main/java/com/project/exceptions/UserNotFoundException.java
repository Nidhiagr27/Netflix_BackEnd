package com.project.exceptions;


import lombok.Data;

@Data
public class UserNotFoundException extends RuntimeException{
    private String email;
    private String userID;

    public UserNotFoundException(String email, String userID){
        this.email=email;
        this.userID=userID;
    }




    @Override
    public String getMessage(){

        if(email!=null) {
            return "User not found with email " + email;
        }
        else{
            return "User not found!!";
        }
    }

}
