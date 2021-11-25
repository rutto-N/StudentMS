package com.student.actions;

import com.student.dao.LecturerDao;
import com.student.ejb.LecturerEjbI;
import com.student.enums.Gender;
import com.student.models.Lecturer;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Lecturer", urlPatterns = {"/lecturer/new","/lecturers","/lecturer/units"})
public class LecturerAction extends BaseServlet{

    Lecturer lecturer=new Lecturer();

    @Inject
    LecturerDao lecturerDao;

    @EJB
    LecturerEjbI lecturerEJB;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        transform(lecturer, req.getParameterMap());
        handleResponse(resp, lecturerEJB.list(lecturer, 0, 0).getList());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url=req.getServletPath();

        switch (url){
            case "/lecturer/new":

                    try {
                        transform(lecturer, req.getParameterMap());
                        lecturer.setGender(Gender.valueOf(lecturer.getGenderStr()));
                        lecturerEJB.save(lecturer);
                        handleResponse(resp);
                    } catch (Exception ex){
                        messageResponse(resp, false, ex.getMessage());

                    }
                break;
            case "/lecturer/units":
                transform(lecturer,req.getParameterMap());
                lecturerEJB.update(req.getParameterValues("unitId"),lecturer);
                break;
        }

    }

}
