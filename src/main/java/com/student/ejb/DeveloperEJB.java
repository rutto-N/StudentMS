package com.student.ejb;

import com.student.dao.DeveloperDao;
import com.student.models.Course;
import com.student.models.Developer;
import com.student.utils.CustomException;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class DeveloperEJB {

    @Inject
    DeveloperDao developerDao;
    public Developer save(Developer developer) throws CustomException {
        if (developer == null)
            throw new CustomException("Invalid developer name!!");

        developer = developerDao.create(developer);

        return developer;
    }


}
