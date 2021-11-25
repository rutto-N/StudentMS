package com.student.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.student.enums.Gender;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;


@Getter
@Setter
@MappedSuperclass
public class Person extends BaseEntity {

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(unique = true)
    @Email
    private String email;

    @Column(unique = true,nullable = false)
    private String phoneNumber;

    @Transient
    @Getter(onMethod_ = @JsonIgnore)
    private String genderStr;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(nullable = false)
    private String dob;


}
