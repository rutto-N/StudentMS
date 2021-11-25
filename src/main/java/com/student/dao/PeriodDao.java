package com.student.dao;

import com.student.interfaces.PeriodDaoI;
import com.student.models.Course;
import com.student.utils.Period;
import com.student.models.Student;
import com.student.models.YearOfStudy;
import com.student.utils.CustomException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class PeriodDao implements PeriodDaoI {

    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public Period save(Period session) throws CustomException {
        return entityManager.merge(session);
    }

    @Override
    public ModelListWrapper<Period> list(Period filter, int start, int limit) {
        ModelListWrapper<Period> results = new ModelListWrapper<Period>();

        String hql = "SELECT s FROM Period s WHERE s.id is not null";

        Query query = entityManager.createQuery(hql, Period.class);

        if (start > 0)
            query.setFirstResult(start);

        if (limit > 0)
            query.setMaxResults(limit);

        List<Period> semesters = query.getResultList();

        results.setList(semesters);
        return results;
    }

    @Override
    public ModelListWrapper<Period> findByCourseAndYearOfStudy(int course, int year) {

        ModelListWrapper<Period> results = new ModelListWrapper<Period>();

        String hql = "SELECT s FROM Period s WHERE s.course="+course+" and s.yearOfStudy="+year+" group by s.sessionId";

        Query query = entityManager.createQuery(hql, Period.class);
        List<Period> sessions=query.getResultList();


        results.setList(sessions);
        return results;
    }

    @Override
    public ModelListWrapper<Period> findByNameCourseAndYearOfStudy(String name, int course, int year) {

        ModelListWrapper<Period> results = new ModelListWrapper<Period>();

        String hql = "SELECT s FROM Period s WHERE  s.sessionId='"+name+"' and s.course="+course+" and s.yearOfStudy="+year+
                " group by s.sessionId";

        Query query = entityManager.createQuery(hql, Period.class);
        List<Period> sessions=query.getResultList();


        results.setList(sessions);
        return results;
    }



    @Override
    public Period getSessionById(int id) {
        return entityManager.find(Period.class,id);
    }


    public List<Student> getStudentsByCourseAndYearOfStudy(Course course, YearOfStudy yearOfStudy){

        List<Student> students=new ArrayList<>();
        String hql="SELECT s FROM Student s JOIN Period  p on s.course=p."+course+" AND s.yearOfStudy=p."+yearOfStudy;

        Query query=entityManager.createQuery(hql);
        students=query.getResultList();

        return students;
    }
}
