package com.student.ejb;

import com.student.interfaces.UserDaoI;
import com.student.models.User;
import com.student.services.LoginUserBeanI;
import com.student.utils.CustomException;
import com.student.utils.MessageResponse;
import com.student.utils.ResultWrapper;

import javax.ejb.Stateless;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class UserEJB implements UserEJBI {

    @Inject
    UserDaoI userDao;


    @Inject @Any
    private Instance<LoginUserBeanI> loginUserBeans;

    @PersistenceContext
    private EntityManager entityManager;


    User user=new User();
    private MessageResponse messageResponse;

    public User save(User user) throws CustomException {
        if (user == null) {
            throw new CustomException();
        }
        User db = userDao.findUserByUserName(user);
        if (db == null) {
            return entityManager.merge(user);
        }
        else {
            throw new CustomException();
        }
    }



    public User getUser(User user) {
        if (user.getUsername() != null && user.getPassword() != null) {
            return userDao.findUser(user);
        }
        return null;

    }

    @Override
    public MessageResponse login(User user) throws CustomException {
        User dbUser=getUser(user);
        try {
            messageResponse= loginUserBeans.select(dbUser.getUserType().getClazz()).get().checkUser(dbUser);

        } catch (Exception e) {
            throw new CustomException("Invalid login details");

        }
        return messageResponse;

    }

    @Override
    public ResultWrapper allUsers(){
        ResultWrapper resultWrapper=new ResultWrapper();
        resultWrapper.setList(userDao.getAll(user,0,0).getList());
        return resultWrapper;
    }


}
