package com.student.actions;

import com.student.ejb.YearOfStudyEJB;
import com.student.models.YearOfStudy;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



@WebServlet(name = "Group", urlPatterns = {"/group/new","/group/delete","/groups"})
public class YearOfStudyAction extends BaseServlet {

    @EJB
    YearOfStudyEJB yearOfStudyEJB;
    YearOfStudy year=new YearOfStudy();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action=req.getServletPath();

        switch (action){
            case "/groups":
                transform(year, req.getParameterMap());
                handleResponse(resp, yearOfStudyEJB.list(year, 0, 0).getList());
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url=req.getServletPath();

        switch (url){
            case "/group/new":
                try {
                    transform(year, req.getParameterMap());
                    yearOfStudyEJB.save(year);
                    handleResponse(resp);

                }catch (Exception ex){
                    messageResponse(resp, false, ex.getMessage());

                }

                break;


        }
    }
}
