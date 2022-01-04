package com.student.rest;


import com.student.dto.RestResponse;
import com.student.ejb.DepartmentEjbI;
import com.student.models.Department;
import com.student.utils.CustomException;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/departments")
public class DepartmentsApi {
    @EJB
    private DepartmentEjbI departmentEjb;

    @GET
//    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response list(){
        Department department=new Department();
        return Response.ok().entity(departmentEjb.list(department,0,0)).build();
    }

    @POST
//    @Path("save")
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(Department department)  {
        try {
            departmentEjb.save(department);
            return Response.ok().entity(new RestResponse("Done",true)).build();
        } catch (CustomException e) {
            return Response.status(400).entity(new RestResponse( e.getMessage(),true)).build();

        }
    }


}
