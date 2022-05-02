package com.project;

import com.project.exceptions.UserNotFoundException;
import com.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

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
}
