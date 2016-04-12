package com.asu.edu.cse564.resource;

import java.net.URI;
import java.util.List;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.asu.edu.cse564.model.ErrMessage;
import com.asu.edu.cse564.model.Lab;
import com.asu.edu.cse564.model.GradeBooksUIInp;
import com.asu.edu.cse564.service.LabService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Path("/gradebooks/gradebook/{gid}/Student/{sid}/Lab")
@Singleton
public class LabResource {

   
    LabService labService = new LabService();
    ObjectMapper mapper = new ObjectMapper();
    
    @Context
    private UriInfo context;

    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{aid}")
    public Response addLabForStudentWithIdForGradeBook(@PathParam("gid") int gid, @PathParam("sid") int sid, @PathParam("aid") int aid,GradeBooksUIInp gradeBooksUIInp) {
        Response response = null;
        URI locationURI;
        String jsonString = null;
        ErrMessage err = new ErrMessage();
        //if(!(gradeBooksUIInp.getGrade()==null))
        // We can check for presence of grade value as non-zero,but 0 can also be an update item so no check as of now
        if((gid != 0 && sid != 0 && aid != 0) && gradeBooksUIInp.getName() != null ) {
            Lab lab = labService.addLabForStudentWithIdForGradeBook(gid, sid, aid, gradeBooksUIInp.getName() );
            if(lab != null ) {
                try {
                    jsonString = mapper.writeValueAsString(lab);
                } catch (JsonProcessingException e) {
                // TODO Auto-generated catch block
                    e.printStackTrace();
                } 
                locationURI = URI.create(context.getAbsolutePath().toString());
                System.out.println(locationURI);
                response = Response.status(Response.Status.CREATED).location(locationURI).entity(jsonString).build();
            } else {
                locationURI = URI.create(context.getAbsolutePath().toString());
                err.setErrorMessage("Resource Not Found");
                response = Response.status(Response.Status.NOT_FOUND).location(locationURI).entity(err).build();  //NO_CONTENT aa??
                //response = Response.status(Response.Status.NOT_FOUND).build();  //NO_CONTENT aa??
            }
        } else {
            locationURI = URI.create(context.getAbsolutePath().toString());
            err.setErrorMessage("Either an Id value is zero or Lab Name is empty - Cant Create");
            response = Response.status(Response.Status.BAD_REQUEST).location(locationURI).entity(err).build();
            //response = Response.status(Response.Status.BAD_REQUEST).location(locationURI).build();
        }
        return response;
    }
    
    
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{aid}")
    public Response deleteLabForStudentWithIdForGradeBook(@PathParam("gid") int gid, @PathParam("sid") int sid, @PathParam("aid") int aid,GradeBooksUIInp gradeBooksUIInp) {
        Response response = null;
        URI locationURI;
        ErrMessage err = new ErrMessage();
        if(gid != 0 && sid != 0 && aid != 0) {
            int status = labService.deleteLabForStudentWithIdForGradeBook(gid, sid, aid);
            if(status == 1) {
                locationURI = URI.create(context.getAbsolutePath().toString());
                System.out.println(locationURI);
                response = Response.status(Response.Status.NO_CONTENT).location(locationURI).build();
                //response = Response.status(Response.Status.NO_CONTENT).build();
            } else {
                locationURI = URI.create(context.getAbsolutePath().toString());
                err.setErrorMessage("Resource Not Found");
                response = Response.status(Response.Status.NOT_FOUND).location(locationURI).entity(err).build();  //NO_CONTENT aa??
                //response = Response.status(Response.Status.NOT_FOUND).build();  //NO_CONTENT aa??
            }
        }
        else {
            locationURI = URI.create(context.getAbsolutePath().toString());
            err.setErrorMessage("Neither of the Id value can be zero - Cant Delete");
            response = Response.status(Response.Status.BAD_REQUEST).location(locationURI).entity(err).build();
            //response = Response.status(Response.Status.BAD_REQUEST).location(locationURI).build();
        }
            
        return response;
    }    
    
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{aid}")
    public Response updateLabForStudentWithIdForGradeBook(@PathParam("gid") int gid, @PathParam("sid") int sid, @PathParam("aid") int aid,GradeBooksUIInp gradeBooksUIInp) {
        Response response = null;
        URI locationURI;
        String jsonString = null;
        ErrMessage err =  new ErrMessage();
        //if(!(gradeBooksUIInp.getGrade()==null))
        // We can check for presence of grade value as non-zero,but 0 can also be an update item so no check as of now
        if(gid != 0 && sid != 0 && aid != 0) {
            Lab lab = labService.updateLabForStudentWithIdForGradeBook(gid, sid, aid, gradeBooksUIInp.getGrade() ,gradeBooksUIInp.getFeedback() );
            if(lab != null ) {
                try {
                    jsonString = mapper.writeValueAsString(lab);
                } catch (JsonProcessingException e) {
                // TODO Auto-generated catch block
                    e.printStackTrace();
                } 
                locationURI = URI.create(context.getAbsolutePath().toString());
                System.out.println(locationURI);
                response = Response.status(Response.Status.OK).location(locationURI).entity(jsonString).build();
            } else {
                locationURI = URI.create(context.getAbsolutePath().toString());
                err.setErrorMessage("Resource Not Found");
                response = Response.status(Response.Status.NOT_FOUND).location(locationURI).entity(err).build();  //NO_CONTENT aa??
                //response = Response.status(Response.Status.NOT_FOUND).build();  //NO_CONTENT aa??
            }
        } else {
            locationURI = URI.create(context.getAbsolutePath().toString());
            err.setErrorMessage("Neither of the Id value can be zero - Cant Update Grade");
            response = Response.status(Response.Status.BAD_REQUEST).location(locationURI).entity(err).build();
            //response = Response.status(Response.Status.BAD_REQUEST).location(locationURI).build();
        }
        return response;
    }
    
    
    
    @GET
    //@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{aid}")
    public Response getLabForStudentWithIdForGradeBook(@PathParam("gid") int gid, @PathParam("sid") int sid, @PathParam("aid") int aid) {
        Response response = null;
        URI locationURI;
        String jsonString = null;
        ErrMessage err = new ErrMessage();
        
        if(gid != 0 && sid != 0 && aid != 0) {
            // We can check for presence of grade item value as non-zero,but 0 can also be an update item so no check as of now 
            Lab lab = labService.getLabForStudentWithIdForGradeBook(gid, sid, aid);
            if(lab != null ) {
                try {
                    jsonString = mapper.writeValueAsString(lab);
                } catch (JsonProcessingException e) {
                // TODO Auto-generated catch block
                    e.printStackTrace();
                } 
                locationURI = URI.create(context.getAbsolutePath().toString());
                System.out.println(locationURI);
                response = Response.status(Response.Status.OK).location(locationURI).entity(jsonString).build();
                //response = Response.status(Response.Status.OK).entity(jsonString).build();
            } else {
                locationURI = URI.create(context.getAbsolutePath().toString());
                err.setErrorMessage("Resource Not Found");
                response = Response.status(Response.Status.NOT_FOUND).location(locationURI).entity(err).build();  //NO_CONTENT aa??
                //response = Response.status(Response.Status.NOT_FOUND).build();  //NO_CONTENT aa??
            }
        } else {
            locationURI = URI.create(context.getAbsolutePath().toString());
            err.setErrorMessage("Neither of the Id value can be zero - Cant Read");
            response = Response.status(Response.Status.BAD_REQUEST).location(locationURI).entity(err).build();
            //response = Response.status(Response.Status.BAD_REQUEST).location(locationURI).build();
        }
        
        return response;
    }
    
    
    @GET
    //@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    //@Path("{aid}")
    public Response getAllLabForStudentWithIdForGradeBook(@PathParam("gid") int gid, @PathParam("sid") int sid) {
        Response response = null;
        URI locationURI;
        String jsonString = null;
        ErrMessage err = new ErrMessage();
        
        if(gid != 0 && sid != 0) {
            // We can check for presence of grade item value as non-zero,but 0 can also be an update item so no check as of now 
            List<Lab> labs = labService.getAllLabForStudentWithIdForGradeBook(gid, sid);
            if(labs != null ) {
                try {
                    jsonString = mapper.writeValueAsString(labs);
                } catch (JsonProcessingException e) {
                // TODO Auto-generated catch block
                    e.printStackTrace();
                } 
                locationURI = URI.create(context.getAbsolutePath().toString());
                System.out.println(locationURI);
                response = Response.status(Response.Status.OK).location(locationURI).entity(jsonString).build();
                //response = Response.status(Response.Status.OK).entity(jsonString).build();
            } else {
                locationURI = URI.create(context.getAbsolutePath().toString());
                err.setErrorMessage("Resource Not Found");
                response = Response.status(Response.Status.NOT_FOUND).location(locationURI).entity(err).build();  //NO_CONTENT aa??
                //response = Response.status(Response.Status.NOT_FOUND).build();  //NO_CONTENT aa??
            }
        } else {
            locationURI = URI.create(context.getAbsolutePath().toString());
            err.setErrorMessage("Neither of the Id value can be zero - Cant Read");
            response = Response.status(Response.Status.BAD_REQUEST).location(locationURI).entity(err).build();
            //response = Response.status(Response.Status.BAD_REQUEST).location(locationURI).build();
        }
        
        return response;
    }
    
}
