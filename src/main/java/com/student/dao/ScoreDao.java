package com.student.dao;

import com.student.models.Score;
import com.student.models.Student;
import com.student.models.Unit;
import com.student.utils.CustomException;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class ScoreDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    YearDao yearDao;
    public Score save(Score score) throws CustomException {

        return entityManager.merge(score);
    }
    public ModelListWrapper<Score> list(Score filter, int start, int limit) {
        ModelListWrapper<Score> results = new ModelListWrapper<>();

        if (filter!=null) {

            String hql = "SELECT e FROM Score e WHERE e.id is not null";

            Query query = entityManager.createQuery(hql, Score.class);

            if (start > 0)
                query.setFirstResult(start);

            if (limit > 0)
                query.setMaxResults(limit);

            List<Score> scores = query.getResultList();

            results.setList(scores);
            results.setCount(scores.size());
        }
        return results;
    }

    public Score getScorePerStudent(Student student, Unit unit){
        CriteriaBuilder criteria=entityManager.getCriteriaBuilder();
        CriteriaQuery<Score> query= criteria.createQuery(Score.class);
        Root<Score> res = query.from(Score.class);
        List<Predicate> predicates = new ArrayList<Predicate>();
        if (unit.getId() != 0 ) {
            predicates.add(
                    criteria.equal(res.get("unit"), unit.getId()));
        }
        if (student!=null) {
            predicates.add(
                    criteria.equal(res.get("student"),student));

        }
        query.select(res)
                .where(predicates.toArray(new Predicate[]{}));

        return entityManager.createQuery(query).getSingleResult();

    }

    public ModelListWrapper<Score> getScoresPerStudent(Student student, int start, int limit) {
        ModelListWrapper<Score>  results = new ModelListWrapper<Score>();
        if (student != null) {
            CriteriaBuilder criteria = entityManager.getCriteriaBuilder();
            CriteriaQuery<Score> query = criteria.createQuery(Score.class);
            Root<Score> res = query.from(Score.class);
            List<Predicate> predicates = new ArrayList<Predicate>();

            if (student.getId() !=0) {
                predicates.add(
                        criteria.equal(res.get("student"), student.getId()));
                predicates.add(
                        criteria.equal(res.get("academicYear"), yearDao.activeAcademicYear()));


            }

            query.select(res)
                    .where(predicates.toArray(new Predicate[]{}));

            Query query1 = entityManager.createQuery(query);
            if (start > 0)
                query1.setFirstResult(start);

            if (limit > 0)
                query1.setMaxResults(limit);

            List<Score> list = query1.getResultList();

            results.setList(list);
            results.setCount(list.size());
        }
        return results;

    }

    public Score checkIfExists(Unit unit, Student student){



        String hql="select s from Score  s where s.unit=:unit and s.student=:student ";


        Query query1=entityManager.createQuery(hql,Score.class);

        query1.setParameter("unit",unit);
        query1.setParameter("student",student);
        try {
            return (Score)query1.getSingleResult();

        }catch (NoResultException e){
            return null;

        }
    }

}
