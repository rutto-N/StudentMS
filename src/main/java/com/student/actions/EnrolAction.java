package com.student.actions;

import com.student.ejb.EnrolmentEJB;
import com.student.ejb.ScoreEJB;
import com.student.interfaces.StudentDaoI;
import com.student.interfaces.UnitDaoI;
import com.student.models.Enrolment;
import com.student.models.Student;
import com.student.utils.CustomException;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Enrolments", urlPatterns = {"/enrol/new","/enrolments",
        "/enrol/list","/enrol-per-unit", "/student-enrolments","grouped-enrolments"})
public class EnrolAction extends BaseServlet{

    Enrolment enrolment=new Enrolment();

    @Inject
    StudentDaoI studentDao;
    @Inject
    UnitDaoI unitDao;

    @EJB
    EnrolmentEJB enrolmentEJB;

    @EJB
    ScoreEJB scoreEJB;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String url = req.getServletPath();
        switch (url) {
            case "/enrol/list":
                transform(enrolment, req.getParameterMap());
                handleResponse(resp, enrolmentEJB.list(enrolment,0,0));

                break;
            case "/student-enrolments":
                Student student = studentDao.getStudentByEmail((String) req.getSession().getAttribute("email"));
                handleResponse(resp, enrolmentEJB.getUnitsEnrolledPerStudent(student,0,0));

                break;
            case "/grouped-enrolments":

                handleResponse(resp, enrolmentEJB.enrolmentsGroupedByStudents(enrolment,0,0));

                break;

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String url = req.getServletPath();

        switch (url){
            case "/enrol/new":
                    System.out.println(req.getSession().getAttribute("email"));
                    Student student = studentDao.getStudentByEmail((String) req.getSession().getAttribute("email"));
                try {
                    enrolmentEJB.enrolStudentUnits(student);
                    handleResponse(resp);
                } catch (CustomException e) {
//                    exceptionResponse(resp, false, "Could not enrol");
                    handleResponse(resp);


                }

                break;
            case "/enrol-per-unit":
                transform(enrolment, req.getParameterMap());
                System.out.println(enrolment.getUnitId()+"======unit Id============");

                System.out.println(unitDao.getUnitById(enrolment.getUnitId())+"==========selected unit  ++++++++      ");

                scoreEJB.submitStudentScores(enrolmentEJB.
                        getStudentsEnrolledPerUnit(unitDao.getUnitById(enrolment.getUnitId()),0,0).getList());
                handleResponse(resp);

                break;

        }


    }

}
