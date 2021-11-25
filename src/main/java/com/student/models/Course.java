package com.student.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "courses")
@JsonIgnoreProperties(value= {"departmentId","units","students"})
public class Course implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    private Department department;

    @Getter(onMethod_ = @JsonIgnore)
    @Formula("(department_id)")
    private int departmentId;//get values


    @Formula("(select c.name from departments c where c.id=department_id)")
    private String departmentName;


    @OneToMany(mappedBy = "course")
    private List<Student> students;

    @OneToMany(mappedBy = "course",fetch = FetchType.EAGER)
    private List<Unit> units;


}
