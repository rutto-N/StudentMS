package com.student.dao;

import com.student.interfaces.UnitDaoI;
import com.student.models.Lecturer;
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

public class UnitDao implements UnitDaoI {
    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    YearDao yearDao;

    @Inject
    SemesterDao semesterDao;

    @Override
    public Unit save(Unit unit) {
        return entityManager.merge(unit);
    }

    @Override
    public ModelListWrapper<Unit> list(Unit filter, int start, int limit) {

        ModelListWrapper<Unit> results = new ModelListWrapper<Unit>();
        if (filter != null) {


        String hql = "SELECT u FROM Unit u WHERE u.id is not null";

        Query query = entityManager.createQuery(hql, Unit.class);

        if (start > 0)
            query.setFirstResult(start);

        if (limit > 0)
            query.setMaxResults(limit);

        List<Unit> items = query.getResultList();

        results.setList(items);
        }

        return results;
    }

    @Override
    public Unit getUnitById(int id) {

        return entityManager.find(Unit.class,id);
    }

    @Override
    public ModelListWrapper<Unit> unitsByCourseAndYearOfStudy(int yearOfStudy, int course,int semester) {

        CriteriaBuilder criteria=entityManager.getCriteriaBuilder();
        CriteriaQuery<Unit> unit= criteria.createQuery(Unit.class);
        Root<Unit> res = unit.from(Unit.class);
        List<Predicate> predicates = new ArrayList<Predicate>();
        if (yearOfStudy!=0){
            predicates.add(
                    criteria.equal(res.get("yearOfStudy"),yearOfStudy));
        }
        if (course!=0){
            predicates.add(
                    criteria.equal(res.get("course"),course));

        }
        if (semester!=0){
            predicates.add(
                    criteria.equal(res.get("semester"),semester));

        }
        unit.select(res)
                .where(predicates.toArray(new Predicate[]{}));

        List<Unit> units =entityManager.createQuery(unit).getResultList();
        ModelListWrapper<Unit> results = new ModelListWrapper<Unit>();
        results.setList(units);
        results.setCount(units.size());

        return results;
    }

    @Override
    public ModelListWrapper<Unit> unitsByLecturerId(Lecturer lecturer,int start,int limit) {

        ModelListWrapper<Unit> results = new ModelListWrapper<>();
        if (lecturer != null) {
            CriteriaBuilder criteria = entityManager.getCriteriaBuilder();
            CriteriaQuery<Unit> unit = criteria.createQuery(Unit.class);
            Root<Unit> res = unit.from(Unit.class);
            List<Predicate> predicates = new ArrayList<Predicate>();

            if (lecturer != null) {
                predicates.add(
                        criteria.equal(res.get("lecturer"), lecturer));

            }
            unit.select(res)
                    .where(predicates.toArray(new Predicate[]{}));

            List<Unit> units = entityManager.createQuery(unit).getResultList();
            results = new ModelListWrapper<Unit>();
            results.setList(units);
            results.setCount(units.size());
        }


        return results;
    }

    @Override
    public ModelListWrapper<Unit> unitsByActiveAcademicYearAndSemester(Unit unit,int start,int limit) {
        CriteriaBuilder criteria=entityManager.getCriteriaBuilder();
        CriteriaQuery<Unit> query= criteria.createQuery(Unit.class);
        Root<Unit> res = query.from(Unit.class);
        List<Predicate> predicates = new ArrayList<Predicate>();
        int year=yearDao.activeAcademicYear().getId();
        int semester=semesterDao.activeSemester().getId();
        System.out.println(year+"========="+semester);

        if (semester!=0){
            predicates.add(
                    criteria.equal(res.get("semester"),semester));

        }

        query.select(res)
                .where(predicates.toArray(new Predicate[]{}));
        System.out.println(query);

        List<Unit> units =entityManager.createQuery(query).getResultList();
        ModelListWrapper<Unit> results = new ModelListWrapper<Unit>();
        results.setList(units);
        results.setCount(units.size());

        return results;
    }

    @Override
    public int update(Unit unit)  {


        System.out.println(unit+"===================");

        String hql = "UPDATE Unit set lecturer = :lecturer "  +
                "WHERE id = :id";
        Query query = entityManager.createQuery(hql);
        query.setParameter("lecturer", unit.getLecturer());
        query.setParameter("id", unit.getId());

        entityManager.merge(unit);
        entityManager.persist(unit);

        int result = query.executeUpdate();
        return result;

    }

    @Override
    public Unit getUnitByCode(String code) {
        CriteriaBuilder criteria=entityManager.getCriteriaBuilder();
        CriteriaQuery<Unit> query= criteria.createQuery(Unit.class);
        Root<Unit> res = query.from(Unit.class);
        query.where(criteria.equal(res.get("code"),code));
//        return entityManager.createQuery(query).getSingleResult();
        try {
            return entityManager.createQuery(query).getSingleResult();

        }catch (NoResultException e){
            return null;

        }
    }

    @Override
    public Unit delete(Unit unit) throws CustomException {
        return null;
    }
}
