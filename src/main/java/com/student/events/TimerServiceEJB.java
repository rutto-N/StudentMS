package com.student.events;

import com.student.interfaces.SemesterDaoI;

import javax.annotation.Resource;
import javax.ejb.*;
import javax.inject.Inject;
import java.util.Date;
import java.util.logging.Logger;

@Stateless
public class TimerServiceEJB {
    @Resource
    TimerService timerService;
    public static final Logger logger=Logger.getLogger("events.TimerServiceEJB");

    @Inject
    SemesterDaoI semesterDao;


    public TimerServiceEJB() {

    }

    public void setTimer(long interval) {
        logger.info("timer timeout"+new Date());
//
//        String monthSchedule = "*/3";
//        ScheduleExpression schedule = new ScheduleExpression()
////                .month(monthSchedule);
//                .minute(monthSchedule);
//        TimerConfig timerConfig = new TimerConfig();
////        timerConfig.setInfo("Start semester 2");
//        timerConfig.setInfo("After 3 mins");
//        timerConfig.setPersistent(true);
//        timerService.createCalendarTimer(schedule,timerConfig);
        timerService.createTimer(interval,"ghghgyey");
    }
    @Timeout
    public void programmaticTimeout(Timer timer){
        logger.info("Programmatic timeout"+new Date());
        semesterDao.openSemester(semesterDao.getSemesterByName("2"));
    }


}
