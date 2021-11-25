package com.student.dao;

import com.student.interfaces.YearOfStudyDaoI;
import com.student.models.YearOfStudy;
import com.student.utils.CustomException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class YearOfStudyDao implements YearOfStudyDaoI {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public YearOfStudy save(YearOfStudy yearOfStudy) throws CustomException {
        return entityManager.merge(yearOfStudy);
    }

    @Override
    public ModelListWrapper<YearOfStudy> list(YearOfStudy filter, int start, int limit) {
        ModelListWrapper<YearOfStudy> results = new ModelListWrapper<YearOfStudy>();

        String hql = "SELECT y FROM YearOfStudy y WHERE y.id is not null";

        Query query = entityManager.createQuery(hql, YearOfStudy.class);

        if (start > 0)
            query.setFirstResult(start);

        if (limit > 0)
            query.setMaxResults(limit);

        List<YearOfStudy> yearOfStudies = query.getResultList();

        results.setList(yearOfStudies);
        results.setCount(yearOfStudies.size());
        return results;
    }

    @Override
    public YearOfStudy getYearOfStudyById(int id) {
        return entityManager.find(YearOfStudy.class,id);
    }

    @Override
    public YearOfStudy getYearOfStudyByName(int name) {
        String hql = "SELECT y FROM YearOfStudy y WHERE y.name='"+name+"'";
        return entityManager.createQuery(hql, YearOfStudy.class).getSingleResult();
    }
}
