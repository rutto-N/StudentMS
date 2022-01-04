package com.student.testdao;

import com.student.dao.ModelListWrapper;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;


public class GenericDao<T> {
    @PersistenceContext
    private EntityManager em;

    private Class<T> tClass;


    public T create(T t) {
        return em.merge(t);
    }

    public ModelListWrapper<T> list(T filter, int start, int limit){
        ModelListWrapper<T> results = new ModelListWrapper<T>();

        if (filter!=null){

            String hql ="FROM "+tClass+"c WHERE c.id is not null";

            Query query = em.createQuery(hql, tClass.getClass());

            if (start > 0)
                query.setFirstResult(start);

            if (limit > 0)
                query.setMaxResults(limit);

            List<T> list = query.getResultList();

            results.setList(list);
            results.setCount(list.size());
        }
        return results;
    }

    public T findById(int id) {

        return em.find(tClass,id);
    }

    public T findByName(String name) {

        CriteriaBuilder criteria=em.getCriteriaBuilder();
        CriteriaQuery<T> query= criteria.createQuery(tClass);
        Root<T> res = query.from(tClass);
        query.where(criteria.equal(res.get("name"),name));

        try {
            return em.createQuery(query).getSingleResult();

        }catch (NoResultException e){
            return null;
        }
    }

    public void delete(int id) {
        em.remove(findById(id));
    }


}
