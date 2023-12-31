package com.codeup.adlister.dao;

import com.codeup.adlister.models.User;

import java.util.List;

/////User Interface/////

public interface Users {
    User findByUsername(String username);
    User findByEmail(String email);
    Long insert(User user);
    void update(User user);
    void delete(User user);

    User findById(long id);

}
