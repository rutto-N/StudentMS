package com.student.actions;

import com.student.ejb.CourseEjbI;
import com.student.models.Course;
import com.student.utils.CustomException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "Course", urlPatterns = {"/course/new","/course-delete","/courses"})

public class CourseAction extends BaseServlet {
    @EJB
    CourseEjbI courseEJB;

    Course course=new Course();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action=req.getServletPath();
        switch (action){
            case "/courses":
                transform(course, req.getParameterMap());
                handleResponse(resp, courseEJB.list(course, 0, 0).getList());
                break;
            case "/course-delete":
                transform(course, req.getParameterMap());
                try {
                    courseEJB.delete(course.getId());
                    messageResponse(resp,true,"Course Removed Successfully");
                } catch (CustomException e) {
                    e.getMessage();
                }

                break;

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url=req.getServletPath();

        switch (url){
            case "/course/new":

                try {
                    transform(course, req.getParameterMap());
                    courseEJB.save(course);
                    messageResponse(resp,true,"Course Added Successfully");


                }catch (Exception ex){
                    messageResponse(resp, false, ex.getMessage());

                }


                break;


        }
    }
}
