package com.student.actions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.student.ejb.PromotionEJB;
import com.student.ejb.ScoreEJB;
import com.student.ejb.StudentEjbI;
import com.student.enums.Gender;
import com.student.interfaces.CourseDaoI;
import com.student.interfaces.StudentDaoI;
import com.student.interfaces.YearOfStudyDaoI;
import com.student.models.Student;
import com.student.utils.CustomException;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Student", urlPatterns = {"/student/new","/student/delete","/students","/student-per-course",
"/student-scores"})
public class StudentAction extends BaseServlet {

    @EJB
    StudentEjbI studentEJB;

    @EJB
    ScoreEJB scoreEJB;

    @Inject
    StudentDaoI studentDao;

    @Inject
    CourseDaoI courseDao;

    @Inject
    YearOfStudyDaoI year;
    @EJB
    PromotionEJB promotionEJB;

    Student student=new Student();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action=req.getServletPath();
        ObjectMapper mapper=new ObjectMapper();
        switch (action){
            case "/students":
                transform(student, req.getParameterMap());
                handleResponse(resp, studentEJB.list(student, 0, 0).getList());
                break;
            case "/student-per-course":
                transform(student, req.getParameterMap());

                //session variables course,year
                handleResponse(resp, studentDao.studentsByCourseAndYearOfStudy(183, "1",0,0).getList());
                break;
            case "/student-scores":
                Student student = studentDao.getStudentByEmail((String) req.getSession().getAttribute("email"));
                handleResponse(resp, scoreEJB.getScoresPerStudent(student,0,0));
                break;



        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url=req.getServletPath();

        switch (url){
            case "/student/new":
                transform(student, req.getParameterMap());
                System.out.println(student.getYearOfStudyId());
                student.setYearOfStudy(year.getYearOfStudyByName(student.getYearOfStudyId()));
                student.setCourse(courseDao.getCourseById(student.getCourseId()));
                student.setGender(Gender.valueOf(student.getGenderStr()));
                try {
                    studentEJB.save(student);
                } catch (CustomException e) {
                    e.getMessage();
                }
                handleResponse(resp);


                break;


        }


    }
}
