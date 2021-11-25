package com.student.models;

import com.student.enums.UserType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
@Entity
@Getter
@Setter
@Table(name="users")
public class User extends BaseEntity {


    @Column(name = "password",nullable = false)
    private String password;

    @Column(name = "username",nullable = false,unique = true)
    private String username;

    @Column(name = "userType",nullable = false)
    @Enumerated(EnumType.STRING)
    private UserType userType;

    @Transient
    private String userTypeStr;



}
