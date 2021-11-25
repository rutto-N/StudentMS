package com.student.ejb;

import com.student.dao.PeriodDao;
import com.student.interfaces.SemesterDaoI;
import com.student.interfaces.YearOfStudyDaoI;
import com.student.models.AcademicYear;
import com.student.utils.Period;
import com.student.models.Unit;
import com.student.utils.MessageResponse;
import com.student.utils.ResultWrapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Map;

@Stateless
public class PeriodEJB {
    @Inject
    PeriodDao periodDao;

    @Inject
    YearOfStudyDaoI year;

    @Inject
    SemesterDaoI semesterDao;

    @PersistenceContext
    private EntityManager entityManager;

    public MessageResponse newSession(Map<String, String[]> params, Unit[] units) {

        MessageResponse messageResponse = null;
        Period period = new Period();


        if (units != null) {
            System.out.println(Arrays.toString(units));
            try {
                BeanUtils.populate(period, params);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
//            period.setCourse(entityManager.find(Course.class, period.getCourseId()));
            period.setAcademicYear(entityManager.find(AcademicYear.class, period.getAcademicYearId()));
            period.setSemester(semesterDao.getSemesterById(period.getSemesterId()));
            period.setYearOfStudy(year.getYearOfStudyById(period.getYearOfStudyId()));


        }

        if (period != null) {

            period.setSessionId(semesterName(String.valueOf(period.getYearOfStudy().getName()),
                    String.valueOf(period.getSemester().getName())));
            System.out.println(period);

            for (Unit unit : units) {
                period.setUnit(unit);
                Period newSession=entityManager.merge(period);
                if (newSession!=null) {
                    messageResponse = new MessageResponse(true,
                            "Session Added Successfully");

                } else {
                    messageResponse = new MessageResponse(false,
                            "Session with the name provided exists.. Try again");

                }

            }

        }
        messageResponse.setObject(period);


        return messageResponse;
    }

    public ResultWrapper allSessions() {
        ResultWrapper resultWrapper = new ResultWrapper();
        resultWrapper.setList(entityManager.createQuery("SELECT sess FROM Period sess",
                Period.class).getResultList());

        return resultWrapper;
    }



    private  String semesterName(String yearOfStudy,String semester){
        return "Y"+yearOfStudy+"S"+semester.substring(semester.length() - 1);
    }







}