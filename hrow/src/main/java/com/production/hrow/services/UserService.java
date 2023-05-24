package com.production.hrow.services;

import com.production.hrow.models.User;

public interface UserService {
    public User createUser(User user);
    public User deleteUser(Long id, String username);
    public User updateUser(User user, String username, Long id);
    public User getUser(String username);
}
