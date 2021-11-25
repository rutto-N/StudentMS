package com.student.dao;

import com.student.models.Lecturer;
import com.student.utils.CustomException;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class LecturerDao {

    @PersistenceContext
    private EntityManager entityManager;


    public Lecturer save(Lecturer lecturer) throws CustomException {

        return entityManager.merge(lecturer);

    }

    public ModelListWrapper<Lecturer> list(Lecturer filter, int start, int limit) {
        ModelListWrapper<Lecturer> results = new ModelListWrapper<Lecturer>();
        if (filter != null) {


        String hql = "SELECT e FROM Lecturer e WHERE e.id is not null";

        Query query = entityManager.createQuery(hql, Lecturer.class);

        if (start > 0)
            query.setFirstResult(start);

        if (limit > 0)
            query.setMaxResults(limit);

        List<Lecturer> lecturers = query.getResultList();

        results.setList(lecturers);
        results.setCount(lecturers.size());
        }

        return results;
    }

    public Lecturer getLecturerByLecturerId(int id){
        return entityManager.find(Lecturer.class,id);


    }

    public Lecturer getLecturerByEmail(String email) {
        CriteriaBuilder criteria=entityManager.getCriteriaBuilder();
        CriteriaQuery<Lecturer> query= criteria.createQuery(Lecturer.class);
        Root<Lecturer> res = query.from(Lecturer.class);
        query.where(criteria.equal(res.get("email"),email));
        try{
            return entityManager.createQuery(query).getSingleResult();

        }catch (NoResultException e){
            return null;
        }


    }


    public Lecturer delete(Lecturer lecturer) {
        entityManager.remove(entityManager.find(Lecturer.class,lecturer.getId()));
        return lecturer;
    }
}
