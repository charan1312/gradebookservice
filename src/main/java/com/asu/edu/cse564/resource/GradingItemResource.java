package com.asu.edu.cse564.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.asu.edu.cse564.service.AssignmentService;

@Path("/gradeitem/{item}")
public class GradingItemResource {

    AssignmentService giService;
    
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response get(@PathParam("item") String item) {
        
        
        
        return null;
    }
}
