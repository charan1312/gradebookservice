package com.asu.edu.cse564.resource;

import java.net.URI;
//import java.util.List;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
//import javax.ws.rs.DELETE;
//import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.asu.edu.cse564.model.MidTerm;
import com.asu.edu.cse564.model.GradeBooksUIInp;
import com.asu.edu.cse564.service.MidTermService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Path("/gradebooks/gradebook/{gid}/Student/{sid}/MidTerm")
@Singleton
public class MidTermResource {

   
    MidTermService midtermService = new MidTermService();
    ObjectMapper mapper = new ObjectMapper();
    
    @Context
    private UriInfo context;

/* 
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{aid}")
    public Response addQuizForStudentWithIdForGradeBook(@PathParam("gid") int gid, @PathParam("sid") int sid, @PathParam("aid") int aid,GradeBooksUIInp gradeBooksUIInp) {
        Response response = null;
        URI locationURI;
        String jsonString = null;
        //if(!(gradeBooksUIInp.getGrade()==null))
        // We can check for presence of grade value as non-zero,but 0 can also be an update item so no check as of now
        if(gid != 0 && sid != 0 || aid != 0) {
            Quiz quiz = quizService.addQuizForStudentWithIdForGradeBook(gid, sid, aid, gradeBooksUIInp.getName() );
            if(quiz != null ) {
                try {
                    jsonString = mapper.writeValueAsString(quiz);
                } catch (JsonProcessingException e) {
                // TODO Auto-generated catch block
                    e.printStackTrace();
                } 
                locationURI = URI.create(context.getAbsolutePath().toString());
                System.out.println(locationURI);
                response = Response.status(Response.Status.OK).location(locationURI).entity(jsonString).build();
            } else {
                locationURI = URI.create(context.getAbsolutePath().toString());
                response = Response.status(Response.Status.NO_CONTENT).location(locationURI).build();  //NO_CONTENT aa??
            }
        } else {
            locationURI = URI.create(context.getAbsolutePath().toString());
            response = Response.status(Response.Status.NO_CONTENT).location(locationURI).build();
        }
        return response;
    }
    
    
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{aid}")
    public Response deleteQuizForStudentWithIdForGradeBook(@PathParam("gid") int gid, @PathParam("sid") int sid, @PathParam("aid") int aid,GradeBooksUIInp gradeBooksUIInp) {
        Response response = null;
        URI locationURI;
        if(gid != 0 && sid != 0 || aid != 0) {
            int status = quizService.deleteQuizForStudentWithIdForGradeBook(gid, sid, aid);
            if(status == 1) {
                locationURI = URI.create(context.getAbsolutePath().toString());
                System.out.println(locationURI);
                response = Response.status(Response.Status.OK).location(locationURI).build();
            } else {
                locationURI = URI.create(context.getAbsolutePath().toString());
                response = Response.status(Response.Status.NOT_FOUND).location(locationURI).build();  //NO_CONTENT aa??
            }
        }
        else {
            locationURI = URI.create(context.getAbsolutePath().toString());
            response = Response.status(Response.Status.NOT_FOUND).location(locationURI).build();            
        }
            
        return response;
    }    
 */   
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    //@Path("{aid}")
    public Response updateMidTermForStudentWithIdForGradeBook(@PathParam("gid") int gid, @PathParam("sid") int sid, GradeBooksUIInp gradeBooksUIInp) {
        Response response = null;
        URI locationURI;
        String jsonString = null;
        //if(!(gradeBooksUIInp.getGrade()==null))
        // We can check for presence of grade value as non-zero,but 0 can also be an update item so no check as of now
        if(gid != 0 && sid != 0 ) {
            MidTerm midterm = midtermService.updateMidTermForStudentWithIdForGradeBook(gid, sid, gradeBooksUIInp.getGrade(), gradeBooksUIInp.getFeedback() );
            if(midterm != null ) {
                try {
                    jsonString = mapper.writeValueAsString(midterm);
                } catch (JsonProcessingException e) {
                // TODO Auto-generated catch block
                    e.printStackTrace();
                } 
                locationURI = URI.create(context.getAbsolutePath().toString());
                System.out.println(locationURI);
                response = Response.status(Response.Status.OK).location(locationURI).entity(jsonString).build();
            } else {
                locationURI = URI.create(context.getAbsolutePath().toString());
                response = Response.status(Response.Status.NOT_FOUND).location(locationURI).build();  //NO_CONTENT aa??
            }
        } else {
            locationURI = URI.create(context.getAbsolutePath().toString());
            response = Response.status(Response.Status.NOT_FOUND).location(locationURI).build();
        }
        return response;
    }
    
    
    
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    //@Path("{aid}")
    public Response getMidTermForStudentWithIdForGradeBook(@PathParam("gid") int gid, @PathParam("sid") int sid, GradeBooksUIInp gradeBooksUIInp) {
        Response response = null;
        URI locationURI;
        String jsonString = null;
        if(gid != 0 && sid != 0 ) {
            // We can check for presence of grade item value as non-zero,but 0 can also be an update item so no check as of now 
            MidTerm midterm = midtermService.getMidTermForStudentWithIdForGradeBook(gid, sid);
            if(midterm != null ) {
                try {
                    jsonString = mapper.writeValueAsString(midterm);
                } catch (JsonProcessingException e) {
                // TODO Auto-generated catch block
                    e.printStackTrace();
                } 
                locationURI = URI.create(context.getAbsolutePath().toString());
                System.out.println(locationURI);
                response = Response.status(Response.Status.OK).location(locationURI).entity(jsonString).build();
            } else {
                locationURI = URI.create(context.getAbsolutePath().toString());
                response = Response.status(Response.Status.NOT_FOUND).location(locationURI).build();  //NO_CONTENT aa??
            }
        } else {
            locationURI = URI.create(context.getAbsolutePath().toString());
            response = Response.status(Response.Status.NOT_FOUND).location(locationURI).build();
        }
        
        return response;
    }   
}
