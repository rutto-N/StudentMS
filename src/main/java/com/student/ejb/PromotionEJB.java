package com.student.ejb;

import com.student.dao.YearOfStudyDao;
import com.student.interfaces.StudentDaoI;
import com.student.models.Enrolment;
import com.student.models.Score;
import com.student.models.Student;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class PromotionEJB {

    @EJB
    EnrolmentEJB enrolmentEJB;

    @EJB
    ScoreEJB scoreEJB;

    @Inject
    YearOfStudyDao yearOfStudyDao;


    @Inject
    StudentDaoI studentDao;

    private Enrolment enrolment=new Enrolment();

    public void promoteStudents(){
        List<Enrolment> enrolments=enrolmentEJB.enrolmentsGroupedByStudents(enrolment,0,0).getList();
        System.out.println(enrolments.size()+"================size of enrolments grouped student===================");
        Student student=null;


//        for (Enrolment enrol:enrolments) {
//            List<Enrolment> studentEnrolments = enrolmentDao.
//                    getUnitsEnrolledPerStudent(enrol.getStudent(),0,0).getList();
//            System.out.println(studentEnrolments.size()+"======size++++++++");
            List<Score> scores = null;
            for (Enrolment e : enrolments) {
                scores = scoreEJB.getScoresPerStudent(studentDao.getStudentById(e.getStudent().getId()), 0, 0).getList();
                student=e.getStudent();
                System.out.println(scores.size()+"===================================");

            }
            List<Double> grades=new ArrayList<>();


            if ( scores.size()>0 ){
                for (Score score:scores) {

                    grades.add(score.getMarks());
                }
                System.out.println(grades.size()+"=========size of grades list+++++");

                double average=getAverage(grades);
                promoteStudent(average, student.getId());

            }

//        }

    }

    public Double getAverage(List<Double> grades){
        double total=0;
        for (Double grade:grades) {
            total=total+grade;
            System.out.println(grade);

        }



        return total / grades.size();

    }

    public int promoteStudent(double average,int studentId){
        Student student= studentDao.getStudentById(studentId);

        System.out.println(student.getYearOfStudy().getName()+"=====year of study+++++");

        if (average>=15){
            //update student academic year in students table
            int year= Integer.valueOf(student.getYearOfStudy().getName());
            if (year<4){
                int newYear=year+1;
               return studentDao.updateYearOfStudy(studentId,yearOfStudyDao.getYearOfStudyByName
                       (newYear));
            }else {
                System.out.println("You have finished your final year");
            }

        }
        return 0;

    }


    //get enrolments per student

    //for each enrolment check if a row exists that matches the unit and student id in scores
    //get the marks and add to an array
//    then average values in the array
    //update student year in students table

}
