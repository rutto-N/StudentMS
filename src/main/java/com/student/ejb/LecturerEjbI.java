package com.student.ejb;

import com.student.dao.ModelListWrapper;
import com.student.models.Lecturer;
import com.student.utils.CustomException;

public interface LecturerEjbI {
    Lecturer save(Lecturer lecturer) throws CustomException;

    ModelListWrapper<Lecturer> list(Lecturer filter, int start, int limit);

    void update(String[] units, Lecturer lecturer);

    void sendMail(Lecturer lecturer);
    Lecturer delete(int id) throws CustomException;

}
