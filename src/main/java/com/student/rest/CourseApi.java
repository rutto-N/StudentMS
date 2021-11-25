package com.student.rest;

import com.student.dto.RestResponse;
import com.student.ejb.CourseEjbI;
import com.student.models.Course;
import com.student.utils.CustomException;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/courses")
public class CourseApi {

    @EJB
    private CourseEjbI courseEjb;

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response list(){
        Course course=new Course();
        return Response.ok().entity(courseEjb.list(course,0,0)).build();
    }

    @POST
    @Path("save")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(Course course)  {
        try {
            courseEjb.save(course);
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
            courseEjb.delete(id);
            return Response.ok().entity(new RestResponse("Done",true)).build();

        } catch (CustomException e) {
            return Response.status(400).entity(new RestResponse( e.getMessage(),true)).build();

        }


    }

}
