package com.student.actions;

import com.student.ejb.DepartmentEjbI;
import com.student.models.Department;
import com.student.utils.CustomException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "Department", urlPatterns = {"/department/new","/department-delete","/departments"})
public class DepartmentAction extends BaseServlet {

    @EJB
    DepartmentEjbI departmentEJB;

   Department department=new Department();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action=req.getServletPath();
        switch (action){
            case "/departments":
                transform(department, req.getParameterMap());
                handleResponse(resp, departmentEJB.list(department, 0, 0).getList());
                break;
            case "/department-delete":
                transform(department, req.getParameterMap());
                try {
                    departmentEJB.delete(department.getId());
                    messageResponse(resp,true,"Department Removed Successfully");
                } catch (CustomException e) {
                    e.getMessage();
                }

                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url=req.getServletPath();
        switch (url){
            case "/department/new":
                try {
                    transform(department, req.getParameterMap());
                    departmentEJB.save(department);
                    messageResponse(resp,true,"Department Added Successfully");

                }catch (Exception ex){
                    messageResponse(resp, false, ex.getMessage());

                }

                break;


        }
    }
}
