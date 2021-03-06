package com.asu.edu.cse564.resource;


import java.net.URI;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.asu.edu.cse564.model.ErrMessage;
import com.asu.edu.cse564.model.GradeBook;
import com.asu.edu.cse564.model.GradeBooksUIInp;
import com.asu.edu.cse564.service.GradeBooksService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Path("/gradebooks")
@Singleton
public class GradeBooksResource {

    //private static final Logger LOG = LoggerFactory.getLogger(GradeBooksResource.class);
    GradeBooksService gradeBooksService = new GradeBooksService();
    ObjectMapper mapper = new ObjectMapper();

    @Context
    private UriInfo context;
//    private ErrorObject err = new ErrorObject();
    
    @GET                                               //DONE
    //@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response showGradeBooksResource() {
        Response response = null;
        String jsonString = gradeBooksService.getAllGradeBooks();
        URI locationURI = URI.create(context.getAbsolutePath().toString());
        System.out.println(locationURI);
        response = Response.status(Response.Status.OK).location(locationURI).entity(jsonString).build();
        //response = Response.status(Response.Status.OK).entity(jsonString).build();
        return response;
    }
 
    @GET                                                 //DONE
    //@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("gradebook/{id}")
    public Response showGradeBookByID(@PathParam("id") int id) {
        Response response = null;
        URI locationURI;
        //ErrorObject err = new ErrorObject();
        ErrMessage err = new ErrMessage();
        if(id != 0) {
            System.out.println("ASKED VALUE: "+ id);
            String jsonString = gradeBooksService.getGradeBook(id);
            if(jsonString != null) {
                locationURI = URI.create(context.getAbsolutePath().toString());//.substring(0, context.getAbsolutePath().toString().lastIndexOf("/")));
                System.out.println(locationURI);
                response = Response.status(Response.Status.OK).location(locationURI).entity(jsonString).build();
                //response = Response.status(Response.Status.OK).entity(jsonString).build();
            } else {
                locationURI = URI.create(context.getAbsolutePath().toString());
                err.setErrorMessage("Resource not found");
                response = Response.status(Response.Status.NOT_FOUND).location(locationURI).entity(err).build();
                //response = Response.status(Response.Status.NOT_FOUND).build();
            }
        } else {
            locationURI = URI.create(context.getAbsolutePath().toString());
            err.setErrorMessage("GradeBook ID Cant be Zero or Empty - Cant Read");
            response = Response.status(Response.Status.BAD_REQUEST).location(locationURI).entity(err).build();
            //response = Response.status(Response.Status.BAD_REQUEST).build();
        }
        return response;
    }

    
    @POST                                                                                   // DONE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("gradebook")
    public Response createNewGradeBook(GradeBooksUIInp gradeBooksUIInp) {
        Response response = null;
        URI locationURI;
        //ErrorObject err = new ErrorObject();
        ErrMessage err = new ErrMessage();
        if(gradeBooksUIInp.getName() != null) {
            GradeBook gradeBook = gradeBooksService.addGradebook(gradeBooksUIInp.getName());
            String jsonString = null;
            try {
                jsonString = mapper.writeValueAsString(gradeBook);
            } catch (JsonProcessingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }                
            //locationURI = URI.create(context.getAbsolutePath() + "/" + gradeBook.getId());
            locationURI = context.getAbsolutePathBuilder().path(String.valueOf(gradeBook.getId())).build();
            System.out.println(locationURI);
            response = Response.status(Response.Status.CREATED).location(locationURI).entity(jsonString).build();
        }
        else {
            locationURI = URI.create(context.getAbsolutePath().toString());
            err.setErrorMessage("Name of the GradeBook cant be empty - Cant Create");
            response = Response.status(Response.Status.BAD_REQUEST).location(locationURI).entity(err).build();
            //response = Response.status(Response.Status.BAD_REQUEST).location(locationURI).build();
        }
        return response;
    }
    
    
    @PUT                                                            //DONE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("gradebook/{id}")
    public Response updateGradeBook(@PathParam("id") int id, GradeBooksUIInp gradeBooksUIInp) {
        Response response = null;
        URI locationURI;
        String jsonString = null;
        //ErrorObject err = new ErrorObject();
        ErrMessage err = new ErrMessage();
        if(id != 0  && gradeBooksUIInp.getName() != null) {
            GradeBook gradeBook = gradeBooksService.updateGradeBook(id, gradeBooksUIInp.getName());//addGradebook(gradeBooksUIInp.getName());
            if(gradeBook != null) {
                try {
                    jsonString = mapper.writeValueAsString(gradeBook);
                } catch (JsonProcessingException e) {
                // TODO Auto-generated catch block
                    e.printStackTrace();
                }                
                //should give full path including the /99
                locationURI = URI.create(context.getAbsolutePath().toString());//.substring(0, context.getAbsolutePath().toString().lastIndexOf("/")));
                System.out.println(locationURI);
                response = Response.status(Response.Status.OK).location(locationURI).entity(jsonString).build();
                //response = Response.status(Response.Status.OK).entity(jsonString).build();
            } else{
                //WE SHOULD DELETE THE PATH /88
                locationURI = URI.create(context.getAbsolutePath().toString());
                err.setErrorMessage("Resource Not Found");
                response = Response.status(Response.Status.NOT_FOUND).location(locationURI).entity(err).build();
                //response = Response.status(Response.Status.NOT_FOUND).build();
            }
        } else {
            locationURI = URI.create(context.getAbsolutePath().toString());
            err.setErrorMessage("ID Cant be Zero or Name of the GradeBook cant be empty - Cant Update");
            response = Response.status(Response.Status.BAD_REQUEST).location(locationURI).entity(err).build();
            //response = Response.status(Response.Status.BAD_REQUEST).location(locationURI).build();
        }
        return response;
    }
    
    @DELETE                                                             //done
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("gradebook/{id}")
    public Response deleteExistingGradeBook(@PathParam("id") int id) {
        Response response = null;
        URI locationURI;
        //ErrorObject err = new ErrorObject();
        ErrMessage err = new ErrMessage();
        if(id != 0) {
            int status = gradeBooksService.deleteGradeBook(id);
            if(status == 1) {
                //CHANGE THE URI TO REMOVE /99 
                locationURI = URI.create(context.getAbsolutePath().toString());//.substring(0, context.getAbsolutePath().toString().lastIndexOf("/")));
                System.out.println(locationURI);
                response = Response.status(Response.Status.NO_CONTENT).location(locationURI).build();
                //response = Response.status(Response.Status.NO_CONTENT).build();
            } else {
                locationURI = URI.create(context.getAbsolutePath().toString());
                err.setErrorMessage("Resource Not Found");
                response = Response.status(Response.Status.NOT_FOUND).location(locationURI).entity(err).build();  //NO_CONTENT aa??
                //response = Response.status(Response.Status.NOT_FOUND).build();  //NO_CONTENT aa??
            }
        } else {
            locationURI = URI.create(context.getAbsolutePath().toString());
            err.setErrorMessage("ID Cant be Zero - Cant Delete");
            response = Response.status(Response.Status.BAD_REQUEST).location(locationURI).entity(err).build();
            //response = Response.status(Response.Status.BAD_REQUEST).location(locationURI).build();
        }
        
        return response;
    }

    @DELETE                                                      //DONE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteAllGradeBooks() {
        Response response = null;
        URI locationURI;
        //ErrorObject err = new ErrorObject();
        ErrMessage err = new ErrMessage();
        int status = gradeBooksService.deleteAllGradeBooks();
        if(status == 1) {
            locationURI = URI.create(context.getAbsolutePath().toString());
            System.out.println(locationURI);
            response = Response.status(Response.Status.NO_CONTENT).location(locationURI).build();
            //response = Response.status(Response.Status.NO_CONTENT).build();
        } else {
            locationURI = URI.create(context.getAbsolutePath().toString());
            err.setErrorMessage("Resource Not Found");
            response = Response.status(Response.Status.NOT_FOUND).location(locationURI).entity(err).build();
            //response = Response.status(Response.Status.NOT_FOUND).build();
        }
        return response;
    }

}