package com.bootdemo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private int id;
    private String userName;
    private String pwd;
    private int isAdmin;
}
