package com.student.actions;

import com.student.dao.LecturerDao;
import com.student.ejb.UnitEJB;
import com.student.interfaces.CourseDaoI;
import com.student.interfaces.SemesterDaoI;
import com.student.interfaces.YearOfStudyDaoI;
import com.student.models.Lecturer;
import com.student.models.Unit;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "Unit", urlPatterns = {"/unit/new","/unit/delete","/units","/active-units","/lec-units"})
public class UnitAction extends BaseServlet {
    @EJB
    UnitEJB unitEJB;

    @Inject
    CourseDaoI courseDao;

    @Inject
    SemesterDaoI semesterDao;

    @Inject
    LecturerDao lecturerDao;

    @Inject
    YearOfStudyDaoI yearOfStudyDao;

    Unit unit=new Unit();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action=req.getServletPath();
        switch (action){
            case "/units":
                transform(unit, req.getParameterMap());
                handleResponse(resp, unitEJB.list(unit, 0, 0).getList());
                break;
            case "/active-units":
                transform(unit, req.getParameterMap());
                handleResponse(resp, unitEJB.allActiveUnits(unit,0,0));
                break;
            case "/lec-units":
                System.out.println("====================Init lect units============================");
                System.out.println(req.getSession().getAttribute("email")+"=====lec mail++++++++++++");

                Lecturer lecturer=lecturerDao.getLecturerByEmail(
                        (String)  req.getSession().getAttribute("email"));
                System.out.println(lecturer);
                System.out.println(unitEJB.lecturerUnits(lecturer, 0,0));
                handleResponse(resp, unitEJB.lecturerUnits(lecturer, 0,0));
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url=req.getServletPath();

        switch (url){
            case "/unit/new":
                try {
                    transform(unit, req.getParameterMap());
                    unit.setCourse(courseDao.getCourseById(unit.getCourseId()));
                    unit.setYearOfStudy(yearOfStudyDao.getYearOfStudyById(unit.getYearOfStudyId()));
                    unit.setSemester(semesterDao.getSemesterById(unit.getSemesterId()));
                    System.out.println(unit);
                    unitEJB.save(unit);
                    messageResponse(resp,true,"Unit Added Successfully");

                }catch (Exception ex){
                    messageResponse(resp, false, ex.getMessage());

                }
                break;


        }
    }
}
