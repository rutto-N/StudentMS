package com.student.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "departments")
@JsonIgnoreProperties(value= {"courses"})
public class Department implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;


    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "department",fetch = FetchType.EAGER)
    private List<Course> courses;





}
