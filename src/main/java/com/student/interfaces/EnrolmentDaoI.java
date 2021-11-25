package com.student.interfaces;

import com.student.dao.ModelListWrapper;
import com.student.models.Enrolment;
import com.student.utils.CustomException;

public interface EnrolmentDaoI {

    Enrolment save(Enrolment enrolment) throws CustomException;
    Enrolment delete(Enrolment enrolment) throws CustomException;
    ModelListWrapper<Enrolment> enrolmentsGroupedByStudents(Enrolment filter, int start, int limit);

}
