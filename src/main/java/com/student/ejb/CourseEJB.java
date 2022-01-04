package com.student.ejb;

import com.student.dao.CourseDao;
import com.student.dao.ModelListWrapper;
import com.student.interfaces.CourseDaoI;
import com.student.interfaces.DepartmentDaoI;
import com.student.models.Course;
import com.student.utils.CustomException;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class CourseEJB implements CourseEjbI {
    @Inject
    CourseDao courseDao;

    @Inject
    DepartmentDaoI departmentDao;


    @Override
    public Course save(Course course) throws CustomException {
        if (course == null)
            throw new CustomException("Invalid course name!!");
        if (course.getDepartmentId()>0)
            course.setDepartment(departmentDao.getDepartmentById(course.getDepartmentId()));
        if (courseDao.getCourseByName(course.getName())!=null){
            throw new CustomException("Course exists");
        }
        course = courseDao.create(course);

        return course;
    }

    @Override
    public Course delete(int id) throws CustomException {
        return courseDao.delete(courseDao.getCourseById(id));
    }

    @Override
    public ModelListWrapper<Course> list(Course filter, int start, int limit){
        return courseDao.list(filter, start, limit);
    }




}
