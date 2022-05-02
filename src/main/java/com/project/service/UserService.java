package com.project.service;

import com.project.accessor.UserAccessor;
import com.project.accessor.models.UsersDTO;
import com.project.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService {

    @Autowired
    private UserAccessor userAccessor;

    public void sendResetPasswordLink(String email){
        UsersDTO usersDTO= userAccessor.getUserByEmail(email);
        if(usersDTO==null){
            throw new UserNotFoundException(email,null);
        }
        //write code for generating link and sending mail
    }

    public boolean updatePassword(String userID, String newPassword){
        UsersDTO usersDTO=userAccessor.getUserByUserID(userID);
        if(usersDTO==null){
            throw new UserNotFoundException(null,userID);
        }
        return userAccessor.updatePassword(userID,newPassword);
    }

}
