package com.student.rest;

import com.student.dto.RestResponse;
import com.student.ejb.StudentEjbI;
import com.student.models.Student;
import com.student.utils.CustomException;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/students")
public class StudentApi {
    @EJB
    private StudentEjbI studentEjb;

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response list(){
        Student student=new Student();
        return Response.ok().entity(studentEjb.list(student,0,0)).build();
    }

    @POST
    @Path("save")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(Student student)  {
        try {
            studentEjb.save(student);
            return Response.ok().entity(new RestResponse("Done",true)).build();
        } catch (CustomException e) {
            return Response.status(400).entity(new RestResponse( e.getMessage(),true)).build();

        }
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") int id){
        try{
            studentEjb.delete(id);
            return Response.ok().entity(new RestResponse("Done",true)).build();

        } catch (CustomException e) {
            return Response.status(400).entity(new RestResponse( e.getMessage(),true)).build();

        }


    }
}
