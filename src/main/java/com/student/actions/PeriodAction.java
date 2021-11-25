package com.student.actions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.student.ejb.PeriodEJB;
import com.student.interfaces.CourseDaoI;
import com.student.interfaces.PeriodDaoI;
import com.student.interfaces.YearOfStudyDaoI;
import com.student.models.Unit;
import com.student.utils.MessageResponse;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Session", urlPatterns = {"/session/new","/session/delete","/sessions","/session"})
public class PeriodAction extends BaseServlet {
    @EJB
    PeriodEJB periodEJB;

    @Inject
    PeriodDaoI periodDao;

    
    @Inject
    CourseDaoI courseDao;

    @Inject
    YearOfStudyDaoI yearOfStudyDao;

    private Unit unit=new Unit();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session= req.getSession();
        String action=req.getServletPath();
        ObjectMapper mapper=new ObjectMapper();
        switch (action){
            case "/sessions":
                resp.setContentType("application/json");
                resp.getWriter().print(mapper.writeValueAsString(periodEJB.allSessions()));
                break;
            case "/session":
                resp.setContentType("application/json");
                resp.getWriter().print(mapper.writeValueAsString(periodDao.findByCourseAndYearOfStudy(11,25)));
//                resp.getWriter().print(mapper.writeValueAsString(periodDao.
//                        getStudentsByCourseAndYearOfStudy(courseDao.getCourseById(11),
//                                yearOfStudyDao.getYearOfStudyById(25))));
                break;
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String url=req.getServletPath();
        MessageResponse messageResponse;
        System.out.println("+++++++++++++++++++++++++++++");

        switch (url){
            case "/session/new":
                String[] inputs=req.getParameterValues("unitId");
               List<Unit> units=new ArrayList<>();
                for (String input:inputs) {
                    System.out.println(input+"=========");//id of the unit
                    Unit unit=new Unit();
                    unit.setId(Integer.parseInt(input));
                    units.add(unit);
                }
                transform(unit,req.getParameterMap());
                System.out.println("=========================");
                System.out.println(unit);
                System.out.println("=========================");
                System.out.println(units);


//                messageResponse= periodEJB.newSession(req.getParameterMap(),units.toArray(new Unit[units.size()]));
//                System.out.println(messageResponse);
//                if (messageResponse.isSuccess()){
//                    resp.getWriter().print(new ObjectMapper().writeValueAsString(messageResponse));
//                }
//                resp.getWriter().print(new ObjectMapper().writeValueAsString(messageResponse));
                break;


        }


    }
}
