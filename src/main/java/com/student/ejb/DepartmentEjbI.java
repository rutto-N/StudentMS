package com.student.ejb;

import com.student.dao.ModelListWrapper;
import com.student.models.Department;
import com.student.utils.CustomException;

public interface DepartmentEjbI {
    Department save(Department department) throws CustomException;
    ModelListWrapper<Department> list(Department filter, int start, int limit);
    Department delete(int id) throws CustomException;
}
