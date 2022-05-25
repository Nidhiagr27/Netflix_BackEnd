package com.project.controller;

import com.project.accessor.models.UsersDTO;
import com.project.controller.models.CreateUserInput;
import com.project.exceptions.InvalidDataException;
import com.project.exceptions.UserNotFoundException;
import com.project.security.Roles;
import com.project.service.AuthService;
import com.project.service.UserService;
import com.project.security.SecurityConstants;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthService authService;

    @Secured({Roles.User,Roles.Customer})
    @GetMapping("/passwordResetLink")
    public ResponseEntity<Boolean> sendResetPasswordLink(@RequestParam("email") String email){
        try{
            userService.sendResetPasswordLink(email);
            return ResponseEntity.status(HttpStatus.OK).body(true);
        }
        catch(UserNotFoundException ex){
            ex.getMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
        }
    }


    @Secured({Roles.User,Roles.Customer})
    @PutMapping("/password")
    public ResponseEntity<Boolean> updatePassword(@RequestParam("userID")
                                                   String userID, @RequestParam("newPassword") String newPassword){
        System.out.println("Inside updatePassword");
        try {
             userService.updatePassword(userID, newPassword);
            return ResponseEntity.status(HttpStatus.OK).body(true);
        }
        catch(UserNotFoundException ex){
            System.out.println(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);

        }
    }

    @PostMapping("/user")
    public ResponseEntity<String> addNewUser(@RequestBody CreateUserInput createUserInput){
         String name= createUserInput.getName();
         String email= createUserInput.getEmail();
         String password= createUserInput.getPassword();
         String phone= createUserInput.getPhone();
         try {
             userService.addNewUser(name, email, password, phone);
             return ResponseEntity.status(HttpStatus.OK).body("User created successfully!");
         }
         catch(InvalidDataException ex){
             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
         }
         catch(Exception ex){
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
         }
    }
}
