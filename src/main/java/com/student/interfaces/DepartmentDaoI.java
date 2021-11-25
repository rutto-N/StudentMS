package com.student.interfaces;

import com.student.dao.ModelListWrapper;
import com.student.models.Department;
import com.student.utils.CustomException;

public interface DepartmentDaoI {

    Department save(Department department) throws CustomException;

    ModelListWrapper<Department> list(Department filter, int start, int limit);

    Department getDepartmentById(int id);

    Department getDepartmentByName(String name);

    Department delete(Department department) throws CustomException;
}
