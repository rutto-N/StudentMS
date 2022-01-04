package com.student.dao;

import com.student.database.DbConn;
import com.student.interfaces.CourseDaoI;
import com.student.models.Course;
import com.student.testdao.GenericDao;
import com.student.utils.CustomException;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class CourseDao extends GenericDao<Course> implements CourseDaoI {

    @Inject
    DbConn dbConn;

    @PersistenceContext
    private EntityManager entityManager;


    public Course save(Course course) {
        return entityManager.merge(course);
    }

    @Override
    public Course delete(Course course) throws CustomException {
         entityManager.remove(entityManager.find(Course.class,course.getId()));
         return course;
    }

    public ModelListWrapper<Course> list(Course filter, int start, int limit) {
        ModelListWrapper<Course> results = new ModelListWrapper<Course>();

        if (filter!=null){

        String hql = "SELECT c FROM Course c WHERE c.id is not null";

        Query query = entityManager.createQuery(hql, Course.class);

        if (start > 0)
            query.setFirstResult(start);

        if (limit > 0)
            query.setMaxResults(limit);

        List<Course> courses = query.getResultList();

        results.setList(courses);
        results.setCount(courses.size());
        }
        return results;
    }

    @Override
    public Course getCourseById(int id) {
        return entityManager.find(Course.class,id);
    }

    @Override
    public Course getCourseByName(String name) {
        CriteriaBuilder criteria=entityManager.getCriteriaBuilder();
        CriteriaQuery<Course> query= criteria.createQuery(Course.class);
        Root<Course> res = query.from(Course.class);
        query.where(criteria.equal(res.get("name"),name));

        try {
            return entityManager.createQuery(query).getSingleResult();

        }catch (NoResultException e){
            return null;
        }
    }


}
