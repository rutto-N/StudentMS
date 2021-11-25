package com.student.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "units")
@JsonIgnoreProperties(value = {"course","lecturer"})
public class Unit  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "code",nullable = false)
    private String code;

    @ManyToOne(fetch = FetchType.EAGER)
    private Course course;

    @ManyToOne(fetch = FetchType.EAGER)
    private Semester semester;

    @ManyToOne(fetch = FetchType.EAGER)
    private YearOfStudy yearOfStudy;

    @Transient
    @Getter(onMethod_ = @JsonIgnore)
    private int courseId;

    @Transient
    @Getter(onMethod_ = @JsonIgnore)
    private int unitId;

    @Transient
    @Getter(onMethod_ = @JsonIgnore)
    private int semesterId;

    @Transient
    @Getter(onMethod_ = @JsonIgnore)
    private int yearOfStudyId;

    @ManyToOne
    private Lecturer lecturer;




}
