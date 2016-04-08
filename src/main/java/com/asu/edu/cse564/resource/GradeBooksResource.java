package com.asu.edu.cse564.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.asu.edu.cse564.model.GradeBooksUIInp;

@Path("/gradebooks")
public class GradeBooksResource {

    private static final Logger LOG = LoggerFactory.getLogger(GradeBooksResource.class);
    
    @Context
    private UriInfo context;
    
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createGradeBooksResource(GradeBooksUIInp gradeBooksUIInp) {
        
        
        return null;
    }

}
