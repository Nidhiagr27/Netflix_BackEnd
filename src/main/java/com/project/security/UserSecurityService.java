package com.project.security;

import com.project.accessor.models.UsersDTO;
import com.project.service.UserService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class UserSecurityService implements UserDetailsService {

    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsersDTO usersDTO = userService.getUserByEmail(username);

        if (usersDTO != null) {
            return new User(usersDTO.getEmail(), usersDTO.getPassword(),
                    Arrays.asList(new SimpleGrantedAuthority(usersDTO.getRole().name())));
        }
        else {
            throw new UsernameNotFoundException("User with email " + username + " not found");
        }
    }
}
