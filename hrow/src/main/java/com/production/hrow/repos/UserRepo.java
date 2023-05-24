package com.production.hrow.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.production.hrow.models.User;


@Component
public interface UserRepo extends JpaRepository<User,Long> {
    
    public User findByUsername(String username);
    public User findByEmail(String email);

}
