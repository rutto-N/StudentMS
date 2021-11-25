package com.student.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.student.enums.Status;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "semesters")
@JsonIgnoreProperties(value = {"units","enrolments"})
public class Semester implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name",nullable = false)
    private String name;

    @OneToMany(mappedBy = "semester")
    private List<Unit> units;

    @OneToMany(mappedBy = "semester")
    private List<Enrolment> enrolments;

//    @ManyToOne
//    private AcademicYear academicYear;

    @ManyToOne
    private YearOfStudy yearOfStudy;
    @Enumerated(EnumType.STRING)
    private Status status=Status.INACTIVE;







}
