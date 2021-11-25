package com.student.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.student.enums.Status;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="academicyears")
@JsonIgnoreProperties(value = {"enrolments","score","yearId"})
public class AcademicYear extends BaseEntity {

    @Column(name = "startDate")
    private String startDate;

    @Column(name = "endDate")
    private String endDate;

    @Column(name = "year")
    private String year;

    @Enumerated(EnumType.STRING)
    private Status status=Status.INACTIVE;


    @OneToMany(mappedBy = "academicYear")
    private List<Enrolment> enrolments;

    @OneToOne(mappedBy = "academicYear")
    private Score score;

    @Transient
    private int yearId;





}
