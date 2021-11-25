package com.student.ejb;

import com.student.dao.ModelListWrapper;
import com.student.dao.YearDao;
import com.student.events.TimerServiceEJB;
import com.student.interfaces.SemesterDaoI;
import com.student.models.AcademicYear;
import com.student.utils.CustomException;
import com.student.utils.DateHelper;
import com.student.utils.MessageResponse;
import org.apache.commons.beanutils.BeanUtils;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Map;
@Stateless
public class AcademicYearEJB {

    @Inject
    YearDao yearDao;

    @Inject
    SemesterDaoI semesterDao;

    @EJB
    TimerServiceEJB timerServiceEJB;

    @EJB
    PromotionEJB promotionEJB;

    @PersistenceContext
    private EntityManager entityManager;

    public AcademicYear save(AcademicYear academicYear) throws CustomException {
        if (academicYear == null)
            throw new CustomException("Cant be null!!");
        if (academicYear!=null){
            Date start= DateHelper.stringToDate(academicYear.getStartDate());
            Date end=DateHelper.stringToDate(academicYear.getEndDate());

            String year=DateHelper.combine(start,end);
            academicYear.setYear(year);
            //check if academic year exists
            if (academicYear.getStartDate().compareTo(academicYear.getEndDate())>0){//start after end
                throw new CustomException("Start date cant be after end date");

            }else if(academicYear.getStartDate().compareTo(academicYear.getEndDate())<0){//start before end
                AcademicYear newAcademicYear=entityManager.merge(academicYear);

            }else if(academicYear.getStartDate().compareTo(academicYear.getEndDate())==0){//dates equal
                throw new CustomException("Start date cant be equal to end date");
            }
        }

        academicYear = yearDao.save(academicYear);

        return academicYear;
    }

    public MessageResponse save(Map<String, String[]> params){

        MessageResponse messageResponse=null;

        AcademicYear academicYear=new AcademicYear();
        try {
            BeanUtils.populate(academicYear,params);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        Date start= DateHelper.stringToDate(academicYear.getStartDate());
        Date end=DateHelper.stringToDate(academicYear.getEndDate());

        String year=DateHelper.combine(start,end);
        academicYear.setYear(year);

        if (academicYear!=null){
            //check if academic year exists
            if (start.compareTo(end)>0){//start after end
                messageResponse = new MessageResponse(false,
                        "Start date cant be after end date");

            }else if(start.compareTo(end)<0){//start before end
                AcademicYear newAcademicYear=entityManager.merge(academicYear);
                if (newAcademicYear!=null){
                    messageResponse = new MessageResponse(true,
                            "Academic year Added Successfully");
                }

            }else if(start.compareTo(end)==0){//dates equal
                messageResponse = new MessageResponse(false,
                        "Start date cant be equal to end date");
            }
        }
            messageResponse.setObject(academicYear);

        return messageResponse;
    }

    public ModelListWrapper<AcademicYear> list(AcademicYear filter, int start, int limit){
        return yearDao.list(filter, start, limit);
    }

    public AcademicYear open(AcademicYear academicYear){
//        promotionEJB.promoteStudents();

        yearDao.openAcademicYear(yearDao.getById(academicYear.getId()));
        semesterDao.openSemester(semesterDao.getSemesterByName("1"));
//        LocalDate date=LocalDate.now();
//        System.out.println(date+"====now date");
//        LocalDate nextDate=date.plusMonths(3);
//        System.out.println(nextDate+".......");
//        System.out.println(date.plusMonths(3)+"====intervals");

//        timerServiceEJB.setTimer(date.plusMonths(3));
//        timerServiceEJB.setTimer(180000);//3 minutes delay before move to semester 2

        return academicYear;

    }



}
