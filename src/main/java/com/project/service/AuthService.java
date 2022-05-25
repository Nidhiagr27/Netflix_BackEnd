package com.project.service;

import com.project.accessor.AuthAccessor;
import com.project.accessor.UserAccessor;
import com.project.accessor.models.AuthDTO;
import com.project.accessor.models.UsersDTO;
import com.project.exceptions.DependencyFailureException;
import com.project.exceptions.InvalidCredentialsException;
import com.project.security.SecurityConstants;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class AuthService {

    @Autowired
    private AuthAccessor authAccessor;

    @Autowired
    private UserAccessor userAccessor;

    /**
     * @param email:Email of user who wants to login
     * @param password:password of user who wants to login
     * @return : JWT token if email & password is correct
     * **/

    public String login(final String email,final String password){
        UsersDTO usersDTO=userAccessor.getUserByEmail(email);

            if (usersDTO != null && usersDTO.getPassword().equals(password)) {
                String token = Jwts.builder()
                        .setSubject(email)
                        .setAudience(usersDTO.getRole().name())
                        .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
                        .signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET)
                        .compact();
                authAccessor.storeToken(usersDTO.getUserId(), token);
                return token;
            }
            throw new InvalidCredentialsException("Either email or password is incorrect!");
    }

   /* public AuthDTO findByToken(String token) {
        return authAccessor.findByToken(token);
    }


    public boolean storeToken(String userId, String token){
        return authAccessor.storeToken(userId,token);
    }*/

}
