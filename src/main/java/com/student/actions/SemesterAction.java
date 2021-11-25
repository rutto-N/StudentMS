package com.student.actions;

import com.student.ejb.SemesterEJB;
import com.student.models.Semester;
import com.student.utils.CustomException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Semester", urlPatterns = {"/semester/new","/semester-delete","/semesters"})

public class SemesterAction extends BaseServlet {

    Semester semester=new Semester();


    @EJB
    SemesterEJB semesterEJB;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action=req.getServletPath();
        switch (action){
            case "/semesters":
                transform(semester, req.getParameterMap());
                handleResponse(resp, semesterEJB.list(semester, 0, 0).getList());
                break;
            case "/semester-delete":
                transform(semester, req.getParameterMap());
                try {
                    semesterEJB.delete(semester.getId());
                    messageResponse(resp,true,"Semester Removed Successfully");
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
            case "/semester/new":
                try {
                    transform(semester, req.getParameterMap());
                    semesterEJB.save(semester);
                    handleResponse(resp);

                }catch (Exception ex){
                    messageResponse(resp, false, ex.getMessage());

                }


                break;


        }
    }
}
