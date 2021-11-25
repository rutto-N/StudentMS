package com.student.ejb;

import com.student.dao.LecturerDao;
import com.student.dao.ModelListWrapper;
import com.student.dao.UserDao;
import com.student.enums.UserType;
import com.student.events.Email;
import com.student.interfaces.UnitDaoI;
import com.student.models.Lecturer;
import com.student.models.Unit;
import com.student.models.User;
import com.student.utils.CustomException;
import com.student.utils.MessageResponse;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.transaction.SystemException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
@Stateless
public class LecturerEJB implements LecturerEjbI {

    @Inject
    Event<Email> emailEvent;

    @Inject
    LecturerDao lecturerDao;

    @Inject
    UserDao userDao;

    @Inject
    UnitDaoI unitDao;


    private MessageResponse messageResponse;


    @Override
    public Lecturer save(Lecturer lecturer) throws CustomException {
        if (lecturer == null)
            throw new CustomException("Invalid Lecturer name!!");
        if (lecturerDao.getLecturerByEmail(lecturer.getEmail())!=null){
            throw new CustomException("Email address taken!!");
        }

        lecturer = lecturerDao.save(lecturer);
        sendMail(lecturer);
        return lecturer;
    }

    @Override
    public ModelListWrapper<Lecturer> list(Lecturer filter, int start, int limit){
        return lecturerDao.list(filter, start, limit);
    }

    @Override
    public void update(String[] units, Lecturer lecturer){
        List<Unit> unitList=new ArrayList<>();
        Lecturer lec=lecturerDao.getLecturerByLecturerId(Integer.parseInt(lecturer.getLecturerId()));


        for (String input : units) {
            System.out.println(input);
            Unit unit=unitDao.getUnitById(Integer.parseInt(input));

            unit.setLecturer(lec);
            try {
                unitDao.update(unit);
            } catch (SystemException e) {
                e.printStackTrace();
            }
            unitList.add(unit);

        }
    }





    @Override
    public void sendMail(Lecturer lecturer){
        if (lecturer != null) {

            if (lecturer!=null) {
                User user=new User();
                user.setUserType(UserType.LECTURER);
                user.setUsername(lecturer.getEmail());
                user.setPassword(String.valueOf(new Random().nextInt(3_000_000)));
                User db=userDao.add(user);
                if (db!=null)
                    emailEvent.fire(new Email(lecturer.getEmail(),"Account Registration",
                            "Hello "+lecturer.getFirstName()+"\nAccount created successfully username:"
                                    +user.getUsername()+ " \n  password:"+user.getPassword()));
                messageResponse = new MessageResponse(true,
                        "Lecturer Added Successfully");

            } else {
                messageResponse = new MessageResponse(false,
                        "Username taken.. Try again");
            }

        }

    }

    @Override
    public Lecturer delete(int id) throws CustomException {
        return lecturerDao.delete(lecturerDao.getLecturerByLecturerId(id));
    }


}
