package com.student.ejb;

import com.student.dao.ModelListWrapper;
import com.student.models.Course;
import com.student.utils.CustomException;

public interface CourseEjbI {
    Course save(Course course) throws CustomException;
    Course delete(int id) throws CustomException;

    ModelListWrapper<Course> list(Course filter, int start, int limit);


}
