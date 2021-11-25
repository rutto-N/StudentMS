package com.student.ejb;

import com.student.dao.ModelListWrapper;
import com.student.dao.ScoreDao;
import com.student.dao.YearDao;
import com.student.models.Enrolment;
import com.student.models.Score;
import com.student.models.Student;
import com.student.utils.CustomException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
@Stateless
public class ScoreEJB {
    @Inject
    ScoreDao scoreDao;

    @Inject
    YearDao yearDao;


    public Score save(Score score) throws CustomException {
        if (score == null)
            throw new CustomException("Invalid !!");
        if (scoreDao.checkIfExists(score.getUnit(), score.getStudent()) == null)
            score = scoreDao.save(score);
        else
            throw new CustomException("Already recorded marks this units");
        return score;
    }
    public ModelListWrapper<Score> list(Score filter, int start, int limit){
        return scoreDao.list(filter, start, limit);
    }

    public ModelListWrapper<Score> getScoresPerStudent(Student filter, int start, int limit){
        return scoreDao.getScoresPerStudent(filter, start, limit);
    }

    public void submitStudentScores(List<Enrolment> enrolments){
        List<Score> scores=new ArrayList<>();
        for (Enrolment enrolment:enrolments) {
            Score score=new Score();
            score.setStudent(enrolment.getStudent());
            score.setMarks(new Random().nextInt(100));
            score.setUnit(enrolment.getUnit());
            score.setAcademicYear(yearDao.activeAcademicYear());
            score.setGrade(gradeStudent(score.getMarks()));
            scores.add(score);
        }
        for (Score score:scores) {
            try {
                this.save(score);
//                scoreDao.getScoreByStudentId(score.getStudent(), score.getUnit());
            } catch (CustomException e) {
                e.printStackTrace();
            }

        }

    }

    public String gradeStudent(double marks){
        if (marks>=70)
            return "A";
        else if(marks>=60 && marks<70)
            return "B";
        else if(marks>=50 && marks<60)
            return "C";
        else if(marks>=40 && marks<50)
            return "D";
        else
            return "F";


    }
}
