package com.bootdemo.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class User implements Serializable {
    private int id;
    private String userName;
    private String pwd;
    private int isAdmin;
}
