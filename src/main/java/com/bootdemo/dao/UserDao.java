package com.bootdemo.dao;


import com.bootdemo.model.User;
import org.springframework.cache.annotation.Cacheable;


public interface UserDao {
    @Cacheable(value="findAllUser",key="1")
    User findByUsername(String username);
    User checklogin(String username, String pwd);
}
