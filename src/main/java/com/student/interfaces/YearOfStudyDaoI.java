package com.student.interfaces;

import com.student.dao.ModelListWrapper;
import com.student.models.YearOfStudy;
import com.student.utils.CustomException;

public interface YearOfStudyDaoI {
    YearOfStudy save(YearOfStudy yearOfStudy) throws CustomException;

    ModelListWrapper<YearOfStudy> list(YearOfStudy filter, int start, int limit);

    YearOfStudy getYearOfStudyById(int id);
    YearOfStudy getYearOfStudyByName(int name);


}
