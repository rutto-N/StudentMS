package com.student.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Formula;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "enrolments")

public class Enrolment extends BaseEntity {

    @Column(name = "enrolment_id")
    private String enrolmentId;

    @Transient
    @Getter(onMethod_ = @JsonIgnore)
    private int semesterId;

    @ManyToOne(fetch = FetchType.LAZY)
    private Semester semester;

    @ManyToOne(optional = false)
    private Course course;

    @ManyToOne(optional = false)
    private Unit unit;

    @ManyToOne(optional = false)
    private AcademicYear academicYear;

    @ManyToOne(optional = false)
    private Student student;

    @ManyToOne
    private Score score;

    @Transient
    @Getter(onMethod_ = @JsonIgnore)
    @Column(name = "unit_id")
    @Formula("(unit_id)")
    private int unitId;

    @Formula("(select u.name from units u where u.id=unit_id )")
    private String unitName;

    @Formula("(course_id)")
    private int courseId;

    @Formula("(select u.name from courses u where u.id=course_id )")
    private String courseName;



}
