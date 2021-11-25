package com.student.dao;

import com.student.interfaces.StudentDaoI;
import com.student.models.Student;
import com.student.models.YearOfStudy;
import com.student.utils.CustomException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class StudentDao  implements StudentDaoI {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Student save(Student student) throws CustomException {
        return entityManager.merge(student);
    }

    @Override
    public ModelListWrapper<Student> list(Student filter, int start, int limit) {
        ModelListWrapper<Student> results = new ModelListWrapper<Student>();

        String hql = "SELECT s FROM Student s WHERE s.id is not null";

        Query query = entityManager.createQuery(hql, Student.class);

        if (start > 0)
            query.setFirstResult(start);

        if (limit > 0)
            query.setMaxResults(limit);

        List<Student> students = query.getResultList();

        results.setList(students);
        results.setCount(students.size());

        return results;
    }

    @Override
    public Student getStudentById(int id) {
        return entityManager.find(Student.class,id);
    }

    @Override
    public Student getStudentByEmail(String email) {
        CriteriaBuilder criteria=entityManager.getCriteriaBuilder();
        CriteriaQuery<Student> student= criteria.createQuery(Student.class);
        Root<Student> res = student.from(Student.class);
        student.where(criteria.equal(res.get("email"),email));
        return entityManager.createQuery(student).getSingleResult();
    }

    @Override
    public ModelListWrapper<Student> studentsByCourseAndYearOfStudy(int course, String year,int start,int limit) {
        ModelListWrapper<Student> results = new ModelListWrapper<Student>();

        String hql = "SELECT s FROM Student s WHERE s.course="+course+" AND s.yearOfStudy='"+year+"'";

        System.out.println(hql);

        Query query = entityManager.createQuery(hql, Student.class);
        if (start > 0)
            query.setFirstResult(start);

        if (limit > 0)
            query.setMaxResults(limit);


        List<Student> students = query.getResultList();

        results.setList(students);
        results.setCount(students.size());
        return results;
    }


    public int updateYearOfStudy(int studentId, YearOfStudy yearOfStudy){
        String hql="update Student s SET s.yearOfStudy=:yearOfStudy where s.id=:id";
        Query query=entityManager.createQuery(hql);
        query.setParameter("yearOfStudy",yearOfStudy);
        query.setParameter("id",studentId);
        return query.executeUpdate();
    }

    @Override
    public Student delete(Student student) throws CustomException {
        entityManager.remove(entityManager.find(Student.class,student.getId()));
        return student;
    }
}
