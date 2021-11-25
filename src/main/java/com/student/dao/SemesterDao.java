package com.student.dao;

import com.student.enums.Status;
import com.student.interfaces.SemesterDaoI;
import com.student.models.Semester;
import com.student.utils.CustomException;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class SemesterDao implements SemesterDaoI {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Semester save(Semester semester) throws CustomException {
        return entityManager.merge(semester);
    }

    @Override
    public ModelListWrapper<Semester> list(Semester filter, int start, int limit) {
        ModelListWrapper<Semester> results = new ModelListWrapper<Semester>();

        if (filter != null) {


        String hql = "SELECT s FROM Semester s WHERE s.id is not null";

        Query query = entityManager.createQuery(hql, Semester.class);

        if (start > 0)
            query.setFirstResult(start);

        if (limit > 0)
            query.setMaxResults(limit);

        List<Semester> semesters = query.getResultList();

        results.setList(semesters);
        results.setCount(semesters.size());
        }

        return results;
    }

    @Override
    public Semester getSemesterById(int id) {
        return entityManager.find(Semester.class,id);
    }

    @Override
    public Semester activeSemester(){
        CriteriaBuilder criteria=entityManager.getCriteriaBuilder();
        CriteriaQuery<Semester> query= criteria.createQuery(Semester.class);
        Root<Semester> res = query.from(Semester.class);
        query.where(criteria.equal(res.get("status"), Status.ACTIVE));
        return entityManager.createQuery(query).getSingleResult();
    }

    @Override
    public Semester getSemesterByName(String name) {
        CriteriaBuilder criteria=entityManager.getCriteriaBuilder();
        CriteriaQuery<Semester> query= criteria.createQuery(Semester.class);
        Root<Semester> res = query.from(Semester.class);
        query.where(criteria.equal(res.get("name"),name));
        try {
            return entityManager.createQuery(query).getSingleResult();

        }catch (NoResultException e){
            return null;

        }

    }

    @Override
    public Semester delete(Semester semester) throws CustomException {
        entityManager.remove(entityManager.find(Semester.class,semester.getId()));
        return semester;
    }

    public void openSemester(Semester semester){
        //set all as inactive
        String hql="UPDATE Semester SET status=:status";
        Query query=entityManager.createQuery(hql);
        query.setParameter("status",Status.INACTIVE);
        query.executeUpdate();

        //set the selected one as active
        hql="UPDATE Semester s SET s.status=:status where s.id=:id";
        query=entityManager.createQuery(hql);
        query.setParameter("status",Status.ACTIVE);


        System.out.println(getSemesterByName("1"));

        query.setParameter("id",semester.getId());
        query.executeUpdate();

    }

}
