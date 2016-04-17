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

import com.asu.edu.cse564.model.Assignment;
import com.asu.edu.cse564.model.ErrMessage;
import com.asu.edu.cse564.model.GradeBooksUIInp;
import com.asu.edu.cse564.service.AssignmentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Path("/gradebooks/gradebook/{gid}/Student/{sid}/Assignment")
@Singleton
public class AssignmentResource {

   
    AssignmentService assignmentService = new AssignmentService();
    ObjectMapper mapper = new ObjectMapper();
    
    @Context
    private UriInfo context;
    //private ErrorObject err = new ErrorObject();

    @POST                                                                  //DONE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{aid}")
    public Response addAssignmentForStudentWithIdForGradeBook(@PathParam("gid") int gid, @PathParam("sid") int sid, @PathParam("aid") int aid,GradeBooksUIInp gradeBooksUIInp) {
        Response response = null;
        URI locationURI;
        String jsonString = null;
        //ErrorObject err = new ErrorObject();
        ErrMessage err = new ErrMessage();
        //if(!(gradeBooksUIInp.getGrade()==null))
        // We can check for presence of grade value as non-zero,but 0 can also be an update item so no check as of now
        if((gid != 0 && sid != 0 && aid != 0) && gradeBooksUIInp.getName() != null ) {
            Assignment assignment = assignmentService.addAssignmentForStudentWithIdForGradeBook(gid, sid, aid, gradeBooksUIInp.getName() );
            if(assignment != null ) {
                try {
                    jsonString = mapper.writeValueAsString(assignment);
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
            err.setErrorMessage("Either an Id value is zero or Assignment Name is empty - Cant Create");
            response = Response.status(Response.Status.BAD_REQUEST).location(locationURI).entity(err).build();
            //response = Response.status(Response.Status.BAD_REQUEST).location(locationURI).build();
        }
        return response;
    }
    
    
    @DELETE                                               //DONE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{aid}")
    public Response deleteAssignmentForStudentWithIdForGradeBook(@PathParam("gid") int gid, @PathParam("sid") int sid, @PathParam("aid") int aid,GradeBooksUIInp gradeBooksUIInp) {
        Response response = null;
        URI locationURI;
        //ErrorObject err = new ErrorObject();
        ErrMessage err = new ErrMessage();
        if(gid != 0 && sid != 0 && aid != 0) {
            int status = assignmentService.deleteAssignmentForStudentWithIdForGradeBook(gid, sid, aid);
            if(status == 1) {
                locationURI = URI.create(context.getAbsolutePath().toString());
                System.out.println(locationURI);
                response = Response.status(Response.Status.NO_CONTENT).location(locationURI).build(); // OK + NOTHING TO RETURN
                //response = Response.status(Response.Status.NO_CONTENT).build(); // OK + NOTHING TO RETURN
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
            //response = Response.status(Response.Status.BAD_REQUEST).location(locationURI).build();   //
        }
            
        return response;
    }    
    
    
    @PUT                                                   //DONE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{aid}")
    public Response updateAssignmentForStudentWithIdForGradeBook(@PathParam("gid") int gid, @PathParam("sid") int sid, @PathParam("aid") int aid,GradeBooksUIInp gradeBooksUIInp) {
        Response response = null;
        URI locationURI;
        //ErrorObject err = new ErrorObject();
        ErrMessage err = new ErrMessage();
        String jsonString = null;
        //if(!(gradeBooksUIInp.getGrade()==null))
        // We can check for presence of grade value as non-zero,but 0 can also be an update item so no check as of now
        if(gid != 0 && sid != 0 && aid != 0) {
            Assignment assignment = assignmentService.updateAssignmentForStudentWithIdForGradeBook(gid, sid, aid, gradeBooksUIInp.getGrade() ,gradeBooksUIInp.getFeedback() );
            if(assignment != null ) {
                try {
                    jsonString = mapper.writeValueAsString(assignment);
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
    
    
    
    @GET                                                           //DONE
    //@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{aid}")
    public Response getAssignmentForStudentWithIdForGradeBook(@PathParam("gid") int gid, @PathParam("sid") int sid, @PathParam("aid") int aid) {
        Response response = null;
        URI locationURI;
        String jsonString = null;
        //ErrorObject err = new ErrorObject();
        ErrMessage err = new ErrMessage();
        if(gid != 0 && sid != 0 && aid != 0) {
            // We can check for presence of grade item value as non-zero,but 0 can also be an update item so no check as of now 
            Assignment assignment = assignmentService.getAssignmentForStudentWithIdForGradeBook(gid, sid, aid);
            if(assignment != null ) {
                try {
                    jsonString = mapper.writeValueAsString(assignment);
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
    
    
    @GET                                                      //DONE
    //@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    //@Path("{aid}")
    public Response getAllAssignmentForStudentWithIdForGradeBook(@PathParam("gid") int gid, @PathParam("sid") int sid) {
        Response response = null;
        URI locationURI;
        String jsonString = null;
        //ErrorObject err = new ErrorObject();
        ErrMessage err = new ErrMessage();
        if(gid != 0 && sid != 0) {
            // We can check for presence of grade item value as non-zero,but 0 can also be an update item so no check as of now 
            List<Assignment> assignments = assignmentService.getAllAssignmentForStudentWithIdForGradeBook(gid, sid);
            if(assignments != null ) {
                try {
                    jsonString = mapper.writeValueAsString(assignments);
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
    
    
    
/*
 * 
 *  @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createNewAssigmentForAllStudentForGradeBook(@PathParam("gid") int gid, GradeBooksUIInp gradeBooksUIInp) {
        System.out.println("Into the student resources--" + "---" + gid +"---"  + gradeBooksUIInp.getGradeBookId() + gradeBooksUIInp.getStudentId() + gradeBooksUIInp.getName() );
        Response response = null; 
        URI locationURI;
        if( !(gradeBooksUIInp.getName().equals("") || gradeBooksUIInp.getName() == null) && 
            !(gradeBooksUIInp.getStudentId() == 0 )
          ) {
            Student student = studentService.addStudent(gid, gradeBooksUIInp.getStudentId(),gradeBooksUIInp.getName());
            System.out.println("Student Res:" + student);
            if(student != null) {
                String jsonString = null;
                try {
                    jsonString = mapper.writeValueAsString(student);
                } catch (JsonProcessingException e) {
                // TODO Auto-generated catch block
                    e.printStackTrace();
                }                
                //locationURI = URI.create(context.getAbsolutePath() + "/" + gradeBook.getId());
                locationURI = context.getAbsolutePathBuilder().path(String.valueOf(student.getsId())).build();
                System.out.println(locationURI);
                response = Response.status(Response.Status.CREATED).location(locationURI).entity(jsonString).build();
            } else {
                locationURI = URI.create(context.getAbsolutePath().toString());
                response = Response.status(Response.Status.NOT_FOUND).location(locationURI).build();
            }
        }
        else {
            locationURI = URI.create(context.getAbsolutePath().toString());
            response = Response.status(Response.Status.BAD_REQUEST).location(locationURI).build();
        }
        return response;
    }
    
    
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{sid}")
    public Response getStudentWithIdForGradeBook(@PathParam("gid") int gid, @PathParam("sid") int sid, GradeBooksUIInp gradeBooksUIInp) {
        System.out.println("Into the get student--" + "---" + gid +"---"  + sid );
        Response response = null; 
        URI locationURI;
        if( sid != 0 ) {
            Student student = studentService.getstudent(gid, sid);
            System.out.println("Student Res:" + student);
            if(student != null) {
                String jsonString = null;
                try {
                    jsonString = mapper.writeValueAsString(student);
                } catch (JsonProcessingException e) {
                // TODO Auto-generated catch block
                    e.printStackTrace();
                }                
                //locationURI = URI.create(context.getAbsolutePath() + "/" + gradeBook.getId());
                locationURI = URI.create(context.getAbsolutePath().toString());
                System.out.println(locationURI);
                response = Response.status(Response.Status.CREATED).location(locationURI).entity(jsonString).build();
            } else {
                locationURI = URI.create(context.getAbsolutePath().toString());
                response = Response.status(Response.Status.NOT_FOUND).location(locationURI).build();
            }
        }
        else {
            locationURI = URI.create(context.getAbsolutePath().toString());
            response = Response.status(Response.Status.BAD_REQUEST).location(locationURI).build();
        }
        return response;
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    //@Path("{sid}")
    public Response getAllStudentForGradeBook(@PathParam("gid") int gid, GradeBooksUIInp gradeBooksUIInp) {
        System.out.println("Into the get student--" + "---" + gid +"---");
        Response response = null; 
        URI locationURI;
        if( gid != 0 ) {
            List<Student> students = studentService.getAllStudentForGradeBook(gid);
            System.out.println("Student Res:" + students);
            if(students != null) {
                String jsonString = null;
                try {
                    jsonString = mapper.writeValueAsString(students);
                } catch (JsonProcessingException e) {
                // TODO Auto-generated catch block
                    e.printStackTrace();
                }                
                //locationURI = URI.create(context.getAbsolutePath() + "/" + gradeBook.getId());
                locationURI = URI.create(context.getAbsolutePath().toString());
                System.out.println(locationURI);
                response = Response.status(Response.Status.CREATED).location(locationURI).entity(jsonString).build();
            } else {
                locationURI = URI.create(context.getAbsolutePath().toString());
                response = Response.status(Response.Status.NOT_FOUND).location(locationURI).build();
            }
        }
        else {
            locationURI = URI.create(context.getAbsolutePath().toString());
            response = Response.status(Response.Status.BAD_REQUEST).location(locationURI).build();
        }
        return response;
    }
    
  
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteAllStudentForGradeBook(@PathParam("gid") int gid, GradeBooksUIInp gradeBooksUIInp) {
        Response response = null;
        URI locationURI;
        
        int status = studentService.deleteAllStudentForGradeBook(gid);
        if(status == 1) {
            locationURI = URI.create(context.getAbsolutePath().toString());
            System.out.println(locationURI);
            response = Response.status(Response.Status.OK).location(locationURI).build();
        } else {
            locationURI = URI.create(context.getAbsolutePath().toString());
            response = Response.status(Response.Status.NOT_FOUND).location(locationURI).build();
        }
        return response;
    }
    
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{sid}")
    public Response updateStudentWithIdForGradeBook(@PathParam("gid") int gid, @PathParam("sid") int sid, GradeBooksUIInp gradeBooksUIInp) {
        Response response = null;
        URI locationURI;
        
        String jsonString = null;
        if(!(gradeBooksUIInp.getName().equals("") || gradeBooksUIInp.getName().equals(null))) {
            Student student = studentService.updateStudentWithIdForGradeBook(gid, sid, gradeBooksUIInp.getName());//addGradebook(gradeBooksUIInp.getName());
            if(student != null) {
                try {
                    jsonString = mapper.writeValueAsString(student);
                } catch (JsonProcessingException e) {
                // TODO Auto-generated catch block
                    e.printStackTrace();
                }                
                //should give full path including the /99
                locationURI = URI.create(context.getAbsolutePath().toString());//.substring(0, context.getAbsolutePath().toString().lastIndexOf("/")));
                System.out.println(locationURI);
                response = Response.status(Response.Status.OK).location(locationURI).entity(jsonString).build();
            } else{
                //WE SHOULD DELETE THE PATH /88
                locationURI = URI.create(context.getAbsolutePath().toString());
                response = Response.status(Response.Status.NOT_FOUND).location(locationURI).build();
            }
        } else {
            locationURI = URI.create(context.getAbsolutePath().toString());
            response = Response.status(Response.Status.BAD_REQUEST).location(locationURI).build();
        }
        return response;
    }
*/
    
}
