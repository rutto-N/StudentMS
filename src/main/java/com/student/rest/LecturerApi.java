package com.lecturer.rest;

import com.student.dto.RestResponse;
import com.student.ejb.LecturerEjbI;
import com.student.models.Lecturer;
import com.student.utils.CustomException;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/lecturers")
public class LecturerApi {
    @EJB
    private LecturerEjbI lecturerEjb;

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response list(){
        Lecturer lecturer=new Lecturer();
        return Response.ok().entity(lecturerEjb.list(lecturer,0,0)).build();
    }

    @POST
    @Path("save")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(Lecturer lecturer)  {
        try {
            lecturerEjb.save(lecturer);
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
            lecturerEjb.delete(id);
            return Response.ok().entity(new RestResponse("Done",true)).build();

        } catch (CustomException e) {
            return Response.status(400).entity(new RestResponse( e.getMessage(),true)).build();

        }


    }
}
