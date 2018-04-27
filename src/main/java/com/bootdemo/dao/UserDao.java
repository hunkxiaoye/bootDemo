package com.bootdemo.dao;


import com.bootdemo.model.User;

public interface UserDao {
    User findByUsername(String username);
    User checklogin(String username, String pwd);
}
