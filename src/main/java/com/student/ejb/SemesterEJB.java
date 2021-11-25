package com.student.ejb;

import com.student.dao.ModelListWrapper;
import com.student.interfaces.SemesterDaoI;
import com.student.models.Semester;
import com.student.utils.CustomException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class SemesterEJB {

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    SemesterDaoI semesterDao;

    public Semester save(Semester semester) throws CustomException {
        if (semester == null)
            throw new CustomException("Invalid course name!!");

        if (semesterDao.getSemesterByName(semester.getName())!=null){
            throw new CustomException("Semester already exists");
        }

        semester = semesterDao.save(semester);

        return semester;
    }


    public ModelListWrapper<Semester> list(Semester filter, int start, int limit) {
        return semesterDao.list(filter, start, limit);
    }


    public Semester delete(int id) throws CustomException {
        return semesterDao.delete(semesterDao.getSemesterById(id));
    }

    public Semester semesterPromotion(){
        return null;
    }
}
