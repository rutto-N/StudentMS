package com.student.ejb;

import com.student.dao.ModelListWrapper;
import com.student.dao.UserDao;
import com.student.enums.UserType;
import com.student.events.Email;
import com.student.interfaces.CourseDaoI;
import com.student.interfaces.StudentDaoI;
import com.student.interfaces.YearOfStudyDaoI;
import com.student.models.Student;
import com.student.models.User;
import com.student.utils.CustomException;
import com.student.utils.MessageResponse;
import com.student.utils.ResultWrapper;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Random;

@Stateless
public class StudentEJB implements StudentEjbI {

    @Inject
    CourseDaoI courseDao;

    @Inject
    YearOfStudyDaoI yearDao;

    @Inject
    StudentDaoI studentDao;

    @Inject
    UserDao userDao;

    @Inject
    Event<Email> emailEvent;


    @PersistenceContext
    private EntityManager entityManager;

    MessageResponse messageResponse=null;


    @Override
    public Student save(Student student) throws CustomException {
        if (student == null)
            throw new CustomException("Invalid Student name!!");
        if (student!=null){
            student = studentDao.save(student);
            System.out.println(student.toString());
            sendMail(student);
        }
        return student;
    }


    @Override
    public ResultWrapper allStudents(){
        ResultWrapper resultWrapper=new ResultWrapper();
        resultWrapper.setList(entityManager.createQuery("SELECT s FROM Student s", Student.class).getResultList());


        return resultWrapper;
    }

    public ModelListWrapper<Student> list(Student filter, int start, int limit){
        return studentDao.list(filter, start, limit);
    }

    @Override
    public void sendMail(Student student){
        if (student != null) {

            if (student!=null) {
                User user=new User();
                user.setUserType(UserType.STUDENT);
                user.setUsername(student.getEmail());
                user.setPassword(String.valueOf(new Random().nextInt(3_000_000)));
                User db=userDao.add(user);
                if (db!=null)
                    emailEvent.fire(new Email(student.getEmail(),"Account Registration",
                            "Hello "+student.getFirstName()+"\nAccount created successfully username:"+user.getUsername()+
                                    " \n  password:"+user.getPassword()));
                messageResponse = new MessageResponse(true,
                        "Student Added Successfully");

            } else {
                messageResponse = new MessageResponse(false,
                        "Student with the name provided exists.. Try again");
            }

        }

    }

    public Student delete(int id) throws CustomException {
        return studentDao.delete(studentDao.getStudentById(id));
    }



}
