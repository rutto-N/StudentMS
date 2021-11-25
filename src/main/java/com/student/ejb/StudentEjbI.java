package com.student.ejb;

import com.student.dao.ModelListWrapper;
import com.student.models.Student;
import com.student.utils.CustomException;
import com.student.utils.ResultWrapper;

public interface StudentEjbI {
    Student save(Student student) throws CustomException;

    ResultWrapper allStudents();

    void sendMail(Student student);
    ModelListWrapper<Student> list(Student filter, int start, int limit);
    Student delete(int id) throws CustomException;
}
