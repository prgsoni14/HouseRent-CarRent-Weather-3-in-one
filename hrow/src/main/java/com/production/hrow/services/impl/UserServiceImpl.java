package com.production.hrow.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.production.hrow.models.User;
import com.production.hrow.repos.UserRepo;
import com.production.hrow.services.UserService;


@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepo userRepo;

    @Override
    public User createUser(User user) {

        User response = new User();
        if(userRepo.findByUsername(user.getUsername()) != null)
        {
            response.setName("username already exists");
            return response;
        }
        if(userRepo.findByEmail(user.getEmail())!=null)
        {
            response.setName("Email already exists");
            return response;
        }

        return userRepo.save(user);
    }

    @Override
    public User deleteUser(Long id, String username) {
        User response = new User();
        User old_user = userRepo.findById(id).orElse(null);
        if(old_user == null || !old_user.getUsername().equals(username))
        {
            response.setName("Invalid Request");
        }
        else
        {
            userRepo.deleteById(id);
            response.setName("Successful");
        }

        return response;
    }

    @Override
    public User updateUser(User user, String username, Long id) {
        User response = new User();
        User old_user = userRepo.findById(id).orElse(null);
        if(id!= user.getId() || old_user == null || !old_user.getUsername().equals(username))
        {
            response.setName("Invalid Request");
            return response;
        }
        else if(!user.getRole().equals(old_user.getRole()))
        {
            response.setName("Role change not allowed");
            return response;
        }
        else
        {
            user.setProfilePic(old_user.getProfilePic());
            return userRepo.save(user);
        }
    }

    @Override
    public User getUser(String username) {
        return userRepo.findByUsername(username);
    }
    
}
