package com.student.utils;

import com.student.models.AcademicYear;
import com.student.models.Semester;
import com.student.models.Unit;
import com.student.models.YearOfStudy;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.io.Serializable;


//@Entity
@Getter
@Setter

@Table(name = "Sessions")
public class Period implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name")
    private String sessionId;

    @ManyToOne
    @JoinColumn
    private YearOfStudy yearOfStudy;

    @Formula("(yearOfStudy_id)")
    private int yearOfStudyId;

    @ManyToOne
    @JoinColumn
    private AcademicYear academicYear;

    @Formula("(academicYear_id)")
    private int academicYearId;

    @ManyToOne
    @JoinColumn
    private Semester semester;

    @Formula("(semester_id)")
    private int semesterId;

//    @ManyToOne
//    @JoinColumn
//    private Course course;

    @Formula("(course_id)")
    private int courseId;

    @ManyToOne
    @JoinColumn
    private Unit unit;

    @Formula("(unit_id)")
    private int unitId;

//    @ManyToMany(mappedBy = "units")
//    private List<Unit> units;


}
