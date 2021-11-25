package com.student.interfaces;

import com.student.dao.ModelListWrapper;
import com.student.models.Lecturer;
import com.student.models.Unit;
import com.student.utils.CustomException;

import javax.transaction.SystemException;

public interface UnitDaoI {
    Unit save(Unit unit) throws CustomException;

    ModelListWrapper<Unit> list(Unit filter, int start, int limit);

    Unit getUnitById(int id);
    ModelListWrapper<Unit> unitsByCourseAndYearOfStudy(int yearOfStudy,int course,int semester);
    ModelListWrapper<Unit> unitsByActiveAcademicYearAndSemester(Unit unit,int start,int limit);
    int update(Unit unit) throws SystemException;
    ModelListWrapper<Unit> unitsByLecturerId(Lecturer lecturer, int start, int limit);

    Unit getUnitByCode(String code);

    Unit delete(Unit unit) throws CustomException;


}
