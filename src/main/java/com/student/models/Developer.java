package com.student.models;


import com.student.enums.UserType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name="developers")
public class Developer extends BaseEntity implements Serializable {

    @Column(name = "password",nullable = false)
    private String password;

    @Column(name = "username",nullable = false,unique = true)
    private String username;

    @Column(name = "userType",nullable = false)
    @Enumerated(EnumType.STRING)
    private UserType userType;

    @Column
    private String token;


}
