package com.student.dao;

import com.student.interfaces.EnrolmentDaoI;
import com.student.models.Enrolment;
import com.student.models.Lecturer;
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

public class EnrolmentDao implements EnrolmentDaoI {

    @Inject
    LecturerDao lecturerDao;

    @Inject
    YearDao yearDao;

    @Inject
    SemesterDao semesterDao;


    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public Enrolment save(Enrolment enrolment) throws CustomException {

        return entityManager.merge(enrolment);
    }

    @Override
    public Enrolment delete(Enrolment enrolment) throws CustomException {
        return null;
    }

    public ModelListWrapper<Enrolment> list(Enrolment filter, int start, int limit) {
        ModelListWrapper<Enrolment> results = new ModelListWrapper<>();

        if (filter!=null) {

            String hql = "SELECT e FROM Enrolment e WHERE e.id is not null";

            Query query = entityManager.createQuery(hql, Enrolment.class);

            if (start > 0)
                query.setFirstResult(start);

            if (limit > 0)
                query.setMaxResults(limit);

            List<Enrolment> enrolments = query.getResultList();

            results.setList(enrolments);
            results.setCount(enrolments.size());
        }
        return results;
    }
    public ModelListWrapper<Enrolment> enrolmentsGroupedByStudents(Enrolment filter, int start, int limit) {
        ModelListWrapper<Enrolment> results = new ModelListWrapper<>();

        if (filter!=null) {

            String hql = "SELECT e FROM Enrolment e WHERE e.id is not null group by e.student";

            Query query = entityManager.createQuery(hql, Enrolment.class);

            if (start > 0)
                query.setFirstResult(start);

            if (limit > 0)
                query.setMaxResults(limit);

            List<Enrolment> enrolments = query.getResultList();

            results.setList(enrolments);
            results.setCount(enrolments.size());
        }
        return results;
    }

    public Enrolment checkEnrolled(Unit unit, Student student){



        String hql="select e from Enrolment  e where e.unit=:unit and e.student=:student ";


        Query query1=entityManager.createQuery(hql,Enrolment.class);

        query1.setParameter("unit",unit);
        query1.setParameter("student",student);
        try {
            return (Enrolment)query1.getSingleResult();

        }catch (NoResultException e){
            return null;

        }
    }

    public boolean ifExists(Enrolment enrolment ){
        return entityManager.contains(enrolment);

    }

    public ModelListWrapper<Enrolment> getStudentEnrolledPerUnit(Unit unit,int start,int limit) {
        ModelListWrapper<Enrolment>  results = new ModelListWrapper<Enrolment>();
        if (unit != null) {
            CriteriaBuilder criteria = entityManager.getCriteriaBuilder();
            CriteriaQuery<Enrolment> query = criteria.createQuery(Enrolment.class);
            Root<Enrolment> res = query.from(Enrolment.class);
            List<Predicate> predicates = new ArrayList<Predicate>();
            if (unit.getCourse() != null) {
                predicates.add(
                        criteria.equal(res.get("course"), unit.getCourse().getId()));
            }
            if (unit.getId() != 0) {
                predicates.add(
                        criteria.equal(res.get("unit"), unit.getId()));

            }
            if (unit.getSemester() != null) {
                predicates.add(
                        criteria.equal(res.get("semester"), unit.getSemester().getId()));

            }

            query.select(res)
                    .where(predicates.toArray(new Predicate[]{}));

            Query query1 = entityManager.createQuery(query);
            if (start > 0)
                query1.setFirstResult(start);

            if (limit > 0)
                query1.setMaxResults(limit);

            List<Enrolment> enrolments = query1.getResultList();

            results.setList(enrolments);
            results.setCount(enrolments.size());
        }
        return results;

    }

    public ModelListWrapper<Enrolment> getUnitsEnrolledPerStudent(Student student,int start,int limit) {
        ModelListWrapper<Enrolment>  results = new ModelListWrapper<Enrolment>();
        if (student != null) {
            CriteriaBuilder criteria = entityManager.getCriteriaBuilder();
            CriteriaQuery<Enrolment> query = criteria.createQuery(Enrolment.class);
            Root<Enrolment> res = query.from(Enrolment.class);
            List<Predicate> predicates = new ArrayList<Predicate>();

            if (student.getId() !=0) {
                predicates.add(
                        criteria.equal(res.get("student"), student.getId()));
                predicates.add(
                        criteria.equal(res.get("academicYear"), yearDao.activeAcademicYear()));
                predicates.add(
                        criteria.equal(res.get("semester"), semesterDao.activeSemester()));

            }

            query.select(res)
                    .where(predicates.toArray(new Predicate[]{}));

            Query query1 = entityManager.createQuery(query);
            if (start > 0)
                query1.setFirstResult(start);

            if (limit > 0)
                query1.setMaxResults(limit);

            List<Enrolment> enrolments = query1.getResultList();

            results.setList(enrolments);
            results.setCount(enrolments.size());
        }
        return results;

    }
    public ModelListWrapper<Enrolment> getEnrolmentsPerLecturer(Lecturer lecturer){
//        SELECT * FROM `units` INNER join enrolments on units.id=enrolments.unit_id where units.lecturer_id=173


        String query="Select e from Enrolment e  inner join Unit u on u.id=e.id where u.lecturer="+lecturer.getId();
//        CriteriaBuilder criteria=entityManager.getCriteriaBuilder();
//        CriteriaQuery<Enrolment> query= criteria.createQuery(Enrolment.class);
//        Root<Enrolment> res = query.from(Enrolment.class);
//        Join<Enrolment,Unit> enrol= res.join("unit",JoinType.INNER);
//        enrol.on(criteria.equal(enrol.get("id"),lecturer.getUnits()));
////        res.join(Unit.class,criteria.equal(res.get("unit",unitDao.find())));
//        List<Predicate> predicates = new ArrayList<Predicate>();
//        if (lecturer!=null){
//            predicates.add(
//                    criteria.equal(res.get("lecturer"),lecturer.getId()));
//        }
//        query.select(res)
//                .where(predicates.toArray(new Predicate[]{}));

        List<Enrolment> enrolments =entityManager.createQuery(query).getResultList();
        ModelListWrapper<Enrolment> results = new ModelListWrapper<Enrolment>();
        results.setList(enrolments);
        results.setCount(enrolments.size());
        return results;

    }

    public Enrolment checkEnrolment(Enrolment enrolment) {
        CriteriaBuilder criteria=entityManager.getCriteriaBuilder();
        CriteriaQuery<Enrolment> query= criteria.createQuery(Enrolment.class);
        Root<Enrolment> res = query.from(Enrolment.class);
//        query.where(
//                criteria.equal(res.get("unit"),enrolment.getUnit()));
//
//                criteria.and(res.get("student"),enrolment.getStudent());
        return entityManager.createQuery(query).getSingleResult();
    }




}
