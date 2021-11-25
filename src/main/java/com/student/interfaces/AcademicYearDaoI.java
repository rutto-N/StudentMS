package com.student.interfaces;

import com.student.dao.ModelListWrapper;
import com.student.models.AcademicYear;
import com.student.utils.CustomException;

public interface AcademicYearDaoI {
    AcademicYear save(AcademicYear academicYear) throws CustomException;

    ModelListWrapper<AcademicYear> list(AcademicYear filter, int start, int limit);

    AcademicYear getAcademicYearById(int id);
}
