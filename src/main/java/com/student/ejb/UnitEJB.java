package com.student.ejb;

import com.student.dao.ModelListWrapper;
import com.student.interfaces.UnitDaoI;
import com.student.models.Lecturer;
import com.student.models.Unit;
import com.student.utils.CustomException;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class UnitEJB {

    @Inject
    UnitDaoI unitDao;

    public Unit save(Unit unit) throws CustomException {
        if (unit == null)
            throw new CustomException("Invalid course name!!");


        if (unitDao.getUnitByCode(unit.getCode())!=null){
            throw new CustomException("Unit exists");
        }

        unit = unitDao.save(unit);
        return unit;


    }
    public ModelListWrapper<Unit> list(Unit filter, int start, int limit){
        return unitDao.list(filter, start, limit);
    }

    public ModelListWrapper<Unit> allActiveUnits(Unit unit,int start, int limit) {
        return unitDao.unitsByActiveAcademicYearAndSemester(unit,start,limit);
    }
    public ModelListWrapper<Unit> lecturerUnits(Lecturer lecturer, int start, int limit) {
        return unitDao.unitsByLecturerId(lecturer,start,limit);
    }


}
