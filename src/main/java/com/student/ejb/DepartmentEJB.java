package com.student.ejb;

import com.student.dao.ModelListWrapper;
import com.student.interfaces.DepartmentDaoI;
import com.student.models.Department;
import com.student.utils.CustomException;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class DepartmentEJB implements DepartmentEjbI {

    @Inject
    DepartmentDaoI departmentDao;

    @Override
    public Department save(Department department) throws CustomException {
        if (department == null)
            throw new CustomException("Invalid department name!!");
        if (departmentDao.getDepartmentByName(department.getName())!=null){
            throw new CustomException("Department exists");
        }

        department = departmentDao.save(department);

        return department;

    }

    @Override
    public ModelListWrapper<Department> list(Department filter, int start, int limit) {
        return departmentDao.list(filter, start, limit);
    }

    @Override
    public Department delete(int id) throws CustomException {
        return departmentDao.delete(departmentDao.getDepartmentById(id));
    }


}





