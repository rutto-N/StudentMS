package com.student.rest;

import com.student.dto.RestResponse;
import com.student.ejb.DeveloperEJB;
import com.student.models.Course;
import com.student.models.Developer;
import com.student.utils.CustomException;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/developers")

public class DeveloperApi {
    @EJB
    DeveloperEJB developerEjb;
    @POST
    @Path("save")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(Developer developer)  {
        System.out.println(developer.toString());
        try {
            developerEjb.save(developer);
            return Response.ok().entity(new RestResponse("Done",true)).build();
        } catch (CustomException e) {
            return Response.status(400).entity(new RestResponse( e.getMessage(),true)).build();

        }
    }
}
