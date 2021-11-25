package com.student.dao;


import com.student.models.User;
import com.student.utils.ResultWrapper;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;


public class UserDao implements com.student.interfaces.UserDaoI {
    @PersistenceContext
    private EntityManager entityManager;



    @Override
    public User add(User user) {

        return entityManager.merge(user);
    }

    @Override
    public User findUser(User user){
        return entityManager.createQuery("SELECT u FROM User u WHERE u.username='"+user.getUsername()+
                "' AND u.password='"+user.getPassword()+"'",User.class).getSingleResult();

    }
    @Override
    public User findUserByUserName(User user){

        try {
            user=entityManager.createQuery("SELECT u FROM User u WHERE u.username='"+user.getUsername()+"'",
                    User.class).getSingleResult();
            return user;

        }catch (NoResultException e){
            return null;
        }

    }
    @Override
    public ResultWrapper getAll(User filter, int start, int limit){

        ResultWrapper resultWrapper=new ResultWrapper();

        String hql = "SELECT u FROM User u WHERE u.id is not null";

        Query query = entityManager.createQuery(hql, User.class);

        if (start > 0)
            query.setFirstResult(start);

        if (limit > 0)
            query.setMaxResults(limit);

        List<User> users = query.getResultList();

        resultWrapper.setList(users);

        return resultWrapper;

    }


}
