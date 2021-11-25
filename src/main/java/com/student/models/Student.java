package com.student.models;



import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Formula;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "Students")
public class Student extends Person {

    @Column(name="studentRegNo",nullable = false)
    private String studentRegNo;

    @ManyToOne
    private Course course;

    @Transient
    @Getter(onMethod_ = @JsonIgnore)
    private int courseId;

    @ManyToOne(optional = false)
    private YearOfStudy yearOfStudy;

    @Transient
    @Getter(onMethod_ = @JsonIgnore)
    private int yearOfStudyId=1;

    @Formula("(yearOfStudy_id)")
    private int yearId;

    @Formula("(select u.name from yearsofstudy u where u.id=yearOfStudy_id )")
    private String year;


    @Formula("(select u.name from courses u where u.id=course_id )")
    private String courseName;

    @PrePersist
    public void update(){
        this.setYearOfStudyId(1);
    }



}
