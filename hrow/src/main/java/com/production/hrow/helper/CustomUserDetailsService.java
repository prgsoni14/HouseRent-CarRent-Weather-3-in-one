package com.production.hrow.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.production.hrow.models.User;
import com.production.hrow.repos.UserRepo;


@Component
public class CustomUserDetailsService implements UserDetailsService{

    @Autowired
    UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepo.findByUsername(username);
        if(user == null)
        {
            throw new UsernameNotFoundException("Invalid Credentials");
        }
        
        return new CustomUserDetails(user);
    }
    
}
