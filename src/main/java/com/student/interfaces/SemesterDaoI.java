package com.student.interfaces;

import com.student.dao.ModelListWrapper;
import com.student.models.Semester;
import com.student.utils.CustomException;

public interface SemesterDaoI {
    Semester save(Semester semester) throws CustomException;

    ModelListWrapper<Semester> list(Semester filter, int start, int limit);

    Semester getSemesterById(int id);
    Semester activeSemester();
    Semester getSemesterByName(String name);
    Semester delete(Semester semester) throws CustomException;
    void openSemester(Semester semester);
}
