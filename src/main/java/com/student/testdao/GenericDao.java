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


public class GenericDao implements GenericDaoI {

    @PersistenceContext
    private EntityManager em;

    private Object object;

    public GenericDao() {
        this.object=new Object();

    }


    @Override
    public Object save(Object object) {
        return em.merge(object);
    }

    @Override
    public ModelListWrapper<Object> list(Object filter, int start, int limit){
        ModelListWrapper<Object> results = new ModelListWrapper<Object>();

        if (filter!=null){

            String hql ="";
//                    "SELECT c FROM Object c WHERE c.id is not null";

            Query query = em.createQuery(hql, Object.class);

            if (start > 0)
                query.setFirstResult(start);

            if (limit > 0)
                query.setMaxResults(limit);

            List<Object> list = query.getResultList();

            results.setList(list);
            results.setCount(list.size());
        }
        return results;
    }

    @Override
    public Object findById(int id) {

        return em.find(Object.class,id);
    }

    @Override
    public Object findByName(String name) {

        CriteriaBuilder criteria=em.getCriteriaBuilder();
        CriteriaQuery<Object> query= criteria.createQuery(Object.class);
        Root<Object> res = query.from(Object.class);
        query.where(criteria.equal(res.get("name"),name));

        try {
            return em.createQuery(query).getSingleResult();

        }catch (NoResultException e){
            return null;
        }
    }

    @Override
    public Object delete(int id) {
        em.remove(findById(id));
        return object ;
    }


}
