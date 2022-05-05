package com.project;

import com.project.accessor.models.UsersDTO;
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

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam ("email") String email,@RequestParam ("password") String password){
        UsersDTO usersDTO=userService.getUserByEmail(email);
        if(usersDTO != null && usersDTO.getPassword().equals(password)){
            Date expirationDate= new Date(System.currentTimeMillis()+ SecurityConstants.EXPIRATION_TIME);
            String token= Jwts.builder()
                    .setSubject(email)
                    .setAudience(usersDTO.getRole().toString())
                    .setExpiration(expirationDate)
                    .signWith(SignatureAlgorithm.HS512,SecurityConstants.SECRET.getBytes())
                    .compact();
           if(authService.storeToken(usersDTO.getUserId(), token)){
               return ResponseEntity.status(HttpStatus.OK).body(token);
           }
           else{
               return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unable to store the token");
           }

        }

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");

    }
}
