package com.student.interfaces;

import com.student.dao.ModelListWrapper;
import com.student.models.Course;
import com.student.utils.CustomException;

public interface CourseDaoI {
    Course save(Course course);
    Course delete(Course course) throws CustomException;


    ModelListWrapper<Course> list(Course filter, int start, int limit);

    Course getCourseById(int id);

    Course getCourseByName(String name);



}
