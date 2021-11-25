package com.student.dao;

import com.student.interfaces.DepartmentDaoI;
import com.student.models.Department;
import com.student.utils.CustomException;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class DepartmentDao implements DepartmentDaoI {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Department save(Department department){
        return entityManager.merge(department);
    }

    @Override
    public ModelListWrapper<Department> list(Department filter, int start, int limit) {
        ModelListWrapper<Department> results = new ModelListWrapper<Department>();

        if (filter!=null) {


            String hql = "SELECT d FROM Department d WHERE d.id is not null";

            Query query = entityManager.createQuery(hql, Department.class);

            if (start > 0)
                query.setFirstResult(start);

            if (limit > 0)
                query.setMaxResults(limit);


            List<Department> departments = query.getResultList();

            results.setList(departments);
            results.setCount(departments.size());
        }
        return results;
    }

    @Override
    public Department getDepartmentById(int id) {
        return entityManager.find(Department.class,id);
    }

    @Override
    public Department getDepartmentByName(String name) {
        CriteriaBuilder criteria=entityManager.getCriteriaBuilder();
        CriteriaQuery<Department> query= criteria.createQuery(Department.class);
        Root<Department> res = query.from(Department.class);
        query.where(criteria.equal(res.get("name"),name));
        try{
            return entityManager.createQuery(query).getSingleResult();

        }catch (NoResultException e){
            return null;
        }
    }
    @Override
    public Department delete(Department department) throws CustomException {
        entityManager.remove(entityManager.find(Department.class,department.getId()));
        return department;
    }


}
