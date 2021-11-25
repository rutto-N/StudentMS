package com.student.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.List;
@Entity
@Getter
@Setter
@Table(name = "scores")

public class Score extends BaseEntity{


    @OneToOne
    private Unit unit;

    private double marks;

    @OneToMany(mappedBy = "score",fetch = FetchType.EAGER)
    private List<Enrolment> enrolments;

    @OneToOne
    private Student student;

    @OneToOne
    private AcademicYear academicYear;

    private String grade;

    @Formula("(unit_id)")
    private int unitId;

    @Formula("(select u.name from units u where u.id=unit_id )")
    private String unitName;

}
