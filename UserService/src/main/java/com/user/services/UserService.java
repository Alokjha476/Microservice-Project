package com.user.services;

import com.user.entities.User;

import java.util.List;

public interface UserService {

    User saveUser(User user);

    List<User> getAllUser();

    User getUser(String userId);

    void deleteUser();

    User updateUser(String userId, User user);



}

