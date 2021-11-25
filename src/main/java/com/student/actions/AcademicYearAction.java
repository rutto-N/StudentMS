package com.student.actions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.student.dao.YearDao;
import com.student.ejb.AcademicYearEJB;
import com.student.models.AcademicYear;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "AcademicYear", urlPatterns = {"/year/new","/year/delete","/years","/open"})
public class AcademicYearAction extends BaseServlet {
    @EJB
    AcademicYearEJB academicYearEJB;

    @Inject
    YearDao yearDao;
    private AcademicYear academicYear=new AcademicYear();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action=req.getServletPath();
        ObjectMapper mapper=new ObjectMapper();
        switch (action){
            case "/years":
                transform(academicYear, req.getParameterMap());
                handleResponse(resp, academicYearEJB.list(academicYear, 0, 0).getList());
//                resp.setContentType("application/json");
//                resp.getWriter().print(mapper.writeValueAsString(academicYearEJB.allAcademicYears()));
                break;
            case "/open":

                academicYearEJB.open(yearDao.getById(225));
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url=req.getServletPath();
        System.out.println("+++++++++++++++++++++++++++++");

        switch (url){
            case "/year/new":
                try {

                    transform(academicYear, req.getParameterMap());
                    academicYearEJB.save(academicYear);
                    handleResponse(resp);
                } catch (Exception ex) {
                    messageResponse(resp, false, ex.getMessage());

                }

                break;
            case "/open":
                transform(academicYear, req.getParameterMap());
                academicYearEJB.open(yearDao.getById(academicYear.getYearId()));
                handleResponse(resp);
                break;


        }




    }
}
