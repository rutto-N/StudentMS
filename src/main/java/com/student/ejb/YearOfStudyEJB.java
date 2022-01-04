package com.student.ejb;

import com.student.dao.ModelListWrapper;
import com.student.dao.YearOfStudyDao;
import com.student.interfaces.YearOfStudyDaoI;
import com.student.models.YearOfStudy;
import com.student.testdao.GenericDao;
import com.student.utils.CustomException;
import com.student.utils.ResultWrapper;
import com.student.utils.MessageResponse;
import org.apache.commons.beanutils.BeanUtils;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
@Stateless
public class YearOfStudyEJB {


    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    YearOfStudyDao yearDao;

    public MessageResponse newYearOfStudy(Map<String, String[]> params) {

        MessageResponse messageResponse=null;
        YearOfStudy yearOfStudy = new YearOfStudy();
        try {
            BeanUtils.populate(yearOfStudy,params);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println(yearOfStudy);

        if (yearOfStudy != null) {
            System.out.println(yearOfStudy.getName());

            YearOfStudy newYear= (YearOfStudy) new GenericDao().create(yearOfStudy);

            if (newYear!=null) {
                messageResponse = new MessageResponse(true,
                        "Group Added Successfully");

            } else {
                messageResponse = new MessageResponse(false,
                        "Group with the name provided exists.. Try again");

            }
            messageResponse.setObject(yearOfStudy);


        }
        return messageResponse;


    }

    public ResultWrapper yearsOfStudy(){
        ResultWrapper resultWrapper=new ResultWrapper();
        resultWrapper.setList(entityManager.createQuery("SELECT y FROM YearOfStudy y", YearOfStudy.class).getResultList());

        return resultWrapper;
    }

    public ModelListWrapper<YearOfStudy> list(YearOfStudy filter, int start, int limit){
        return yearDao.list(filter, start, limit);
    }


    public YearOfStudy save(YearOfStudy year) throws CustomException {
        if (year == null)
            throw new CustomException("Invalid!!");
        if (year!=null)
            System.out.println(year.getName()+"Year of study");
            year = yearDao.create(year);
        return year;
    }
}
