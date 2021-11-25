package com.student.interfaces;

import com.student.dao.ModelListWrapper;
import com.student.models.Student;
import com.student.models.YearOfStudy;
import com.student.utils.CustomException;

public interface StudentDaoI {
    Student save(Student student) throws CustomException;

    ModelListWrapper<Student> list(Student filter, int start, int limit);

    Student getStudentById(int id);

    Student getStudentByEmail(String name);
    ModelListWrapper<Student> studentsByCourseAndYearOfStudy(int course, String year,int start,int limit);
    public int updateYearOfStudy(int studentId, YearOfStudy yearOfStudy);

    Student delete(Student student) throws CustomException;


}
