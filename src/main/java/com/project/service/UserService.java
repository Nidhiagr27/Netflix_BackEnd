package com.project.service;

import com.project.accessor.UserAccessor;
import com.project.accessor.models.UserAccountState;
import com.project.accessor.models.UserRole;
import com.project.accessor.models.UsersDTO;
import com.project.exceptions.InvalidDataException;
import com.project.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

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


    //this method is for login
    public UsersDTO getUserByEmail(String email) {
       return userAccessor.getUserByEmail(email);
    }

    public void addNewUser(final String name,final String email,final String password,final String phone){
        if(phone.length()!=10){
            throw new InvalidDataException("phoneNo. "+phone+" is invalid!");
        }
        if (password.length()<6){
                throw new InvalidDataException("Password is too simple!");
        }
        if(name.length()<5){
            throw new InvalidDataException("Name is not correct!");
        }
        String emailRegex="^[a-zA-Z0-9_+&*-]+(?:\\."+
                          "[a-zA-Z0-9_+&*-]+)*@"+
                          "(?:[a-zA-Z0-9-]+\\.)+[a-z"+
                         "A-Z]{2,7}$";
        Pattern pat=Pattern.compile(emailRegex);
        if(!pat.matcher(email).matches()){
            throw new InvalidDataException("Email is not correct!");
        }
        UsersDTO usersDTO=userAccessor.getUserByEmail(email);
        if(usersDTO!=null){
            throw new InvalidDataException("User with given email already exists!");
        }
        usersDTO=userAccessor.getUserByPhone(phone);
        if(usersDTO!=null){
            throw new InvalidDataException("User with given phone No. already exists!");
        }
        userAccessor.addNewUser(name,email,password,phone, UserAccountState.ACTIVE, UserRole.ROLE_USER);
    }
}
