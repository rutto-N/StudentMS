package com.student.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.student.enums.Status;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "yearsofstudy")
@JsonIgnoreProperties(value = {"unit","students"})
public class YearOfStudy  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column
    @Enumerated(EnumType.STRING)
    private Status status=Status.ACTIVE;



    @OneToMany(mappedBy = "yearOfStudy",fetch = FetchType.LAZY)
    private List<Unit> unit;

    @OneToMany(mappedBy = "yearOfStudy")
    private List<Student> students;


}
