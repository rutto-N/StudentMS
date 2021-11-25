package com.student.interfaces;

import com.student.dao.ModelListWrapper;
import com.student.models.Course;
import com.student.utils.Period;
import com.student.models.Student;
import com.student.models.YearOfStudy;
import com.student.utils.CustomException;

import java.util.List;

public interface PeriodDaoI {
    Period save(Period session) throws CustomException;

    ModelListWrapper<Period> list(Period filter, int start, int limit);
    ModelListWrapper<Period> findByCourseAndYearOfStudy(int course, int year );

    Period getSessionById(int id);

    List<Student> getStudentsByCourseAndYearOfStudy(Course course, YearOfStudy yearOfStudy);

    ModelListWrapper<Period> findByNameCourseAndYearOfStudy(String name, int course, int year);



}
