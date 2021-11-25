package com.student.dao;

import com.student.enums.Status;
import com.student.models.AcademicYear;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;

public class YearDao  {
    @PersistenceContext
    private EntityManager entityManager;
    public AcademicYear save(AcademicYear academicYear){
        return entityManager.merge(academicYear);
    }

    public AcademicYear activeAcademicYear(){
        CriteriaBuilder criteria=entityManager.getCriteriaBuilder();
        CriteriaQuery<AcademicYear> year= criteria.createQuery(AcademicYear.class);
        Root<AcademicYear> res = year.from(AcademicYear.class);
        year.where(criteria.equal(res.get("status"), Status.ACTIVE));
        return entityManager.createQuery(year).getSingleResult();
    }
    public ModelListWrapper<AcademicYear> list(AcademicYear filter, int start, int limit) {

        ModelListWrapper<AcademicYear> results = new ModelListWrapper<AcademicYear>();
        if (filter != null) {


            String hql = "SELECT u FROM AcademicYear u WHERE u.id is not null";

            Query query = entityManager.createQuery(hql, AcademicYear.class);

            if (start > 0)
                query.setFirstResult(start);

            if (limit > 0)
                query.setMaxResults(limit);

            List<AcademicYear> list = query.getResultList();

            results.setList(list);
            results.setCount(list.size());
        }

        return results;
    }

    public AcademicYear openAcademicYear(AcademicYear academicYear){
        //set all as inactive
        String hql="UPDATE AcademicYear SET status=:status";
        Query query=entityManager.createQuery(hql);
        query.setParameter("status",Status.INACTIVE);
        query.executeUpdate();

        //set the selected one as active
        hql="UPDATE AcademicYear a SET a.status=:status, a.timeUpdated=:time where a.id=:id";
        query=entityManager.createQuery(hql);
        query.setParameter("status",Status.ACTIVE);
        query.setParameter("id",academicYear.getId());
        query.setParameter("time",new Date());
        query.executeUpdate();


        return academicYear;



    }
    public AcademicYear getById(int id) {
        return entityManager.find(AcademicYear.class,id);
    }



}
