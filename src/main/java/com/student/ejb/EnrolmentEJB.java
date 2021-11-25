package com.student.ejb;

import com.student.dao.EnrolmentDao;
import com.student.dao.ModelListWrapper;
import com.student.dao.YearDao;
import com.student.interfaces.SemesterDaoI;
import com.student.interfaces.StudentDaoI;
import com.student.interfaces.UnitDaoI;
import com.student.models.Enrolment;
import com.student.models.Lecturer;
import com.student.models.Student;
import com.student.models.Unit;
import com.student.utils.CustomException;
import com.student.utils.MessageResponse;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class EnrolmentEJB {
    @Inject
    EnrolmentDao enrolmentDao;

    @Inject
    SemesterDaoI semesterDao;

    @Inject
    StudentDaoI studentDao;

    @Inject
    UnitDaoI unitDao;

    @Inject
    YearDao yearDao;
    private MessageResponse messageResponse;


    public MessageResponse save(List<Enrolment> enrolments) throws CustomException {
        for (Enrolment enrolment : enrolments) {

            if (enrolment == null) {
                throw new CustomException("Invalid Enrolment!!");
            }
            if (enrolmentDao.checkEnrolled(enrolment.getUnit(), enrolment.getStudent()) != null) {
                System.out.println("enrolment is not null");
                throw new CustomException("Already Enrolled for this unit");

            } else {
                enrolmentDao.save(enrolment);
                messageResponse=new MessageResponse(true, "Enrolled Successfully");


            }
        }
        return messageResponse;


    }

    public void enrolStudentUnits(Student student) throws CustomException {
        Enrolment enrolment = new Enrolment();
        enrolment.setSemester(semesterDao.activeSemester());
        enrolment.setStudent(student);


        List<Unit> units = unitDao.unitsByCourseAndYearOfStudy(student.getYearOfStudy().getId(),
                student.getCourse().getId(), enrolment.getSemester().getId()).getList();


        List<Enrolment> enrolments = new ArrayList<>();

        for (Unit unit : units) {
            Enrolment enrol = new Enrolment();
            enrol.setEnrolmentId(this.enrolmentName(unit.getYearOfStudy().getId(),
                    unit.getSemester().getName()));
            enrol.setUnit(unitDao.getUnitById(unit.getId()));
            enrol.setStudent(studentDao.getStudentById(student.getId()));
            enrol.setSemester(unit.getSemester());
            enrol.setCourse(unit.getCourse());
            enrol.setAcademicYear(yearDao.activeAcademicYear());
            enrol.setUnit(unitDao.getUnitById(unit.getId()));
            enrol.setStudent(student);
            enrol.setUnit(unit);
            enrolments.add(enrol);

        }
        save(enrolments);


    }


    public List<Enrolment> enrolStudent(List<Unit> units, Enrolment enrolment) {


        List<Enrolment> enrolments = new ArrayList<>();

        for (Unit unit : units) {
            Enrolment enrol = new Enrolment();
            enrol.setEnrolmentId(enrolmentName(unit.getYearOfStudy().getId(),
                    unit.getSemester().getName()));
            enrol.setUnit(unitDao.getUnitById(unit.getId()));
            enrol.setStudent(studentDao.getStudentById(enrolment.getStudent().getId()));
            enrol.setSemester(unit.getSemester());
            enrol.setCourse(unit.getCourse());
            enrol.setUnit(unitDao.getUnitById(unit.getId()));
            enrol.setStudent(enrolment.getStudent());
            enrol.setUnit(unit);
            enrolments.add(enrol);

        }
        return enrolments;

    }

    public String enrolmentName(int yearOfStudy, String semester) {
        return "Y" + yearOfStudy + "S" + semester;
    }

    public ModelListWrapper<Enrolment> list(Enrolment filter, int start, int limit) {
        return enrolmentDao.list(filter, start, limit);
    }
    public ModelListWrapper<Enrolment> enrolmentsGroupedByStudents(Enrolment filter, int start, int limit) {
        return enrolmentDao.enrolmentsGroupedByStudents(filter, start, limit);
    }

    public ModelListWrapper<Enrolment> getStudentsEnrolledPerUnit(Unit unit, int start, int limit) {
        return enrolmentDao.getStudentEnrolledPerUnit(unit,start,limit);


    }

    public ModelListWrapper<Enrolment> getUnitsEnrolledPerStudent(Student student, int start, int limit) {
        return enrolmentDao.getUnitsEnrolledPerStudent(student,start,limit);


    }

    public ModelListWrapper<Enrolment>  getEnrolmentsPerLecturer(Lecturer lecturer) {
        return enrolmentDao.getEnrolmentsPerLecturer(lecturer);
    }
}
