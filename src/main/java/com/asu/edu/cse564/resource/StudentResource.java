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
import com.asu.edu.cse564.model.GradeBooksUIInp;
import com.asu.edu.cse564.model.Lab;
import com.asu.edu.cse564.model.Quiz;
import com.asu.edu.cse564.model.Student;
import com.asu.edu.cse564.service.StudentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Path("/gradebooks/gradebook/{gid}/Student")
@Singleton
public class StudentResource {

    StudentService studentService = new StudentService();
    ObjectMapper mapper = new ObjectMapper();
    
    @Context
    private UriInfo context;
    
    @POST                                                   //DONE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    //@Path("/gradebooks/gradebook/{gid}/Student")
    public Response createNewStudentForGradeBook(@PathParam("gid") int gid, GradeBooksUIInp gradeBooksUIInp) {
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
                //response = Response.status(Response.Status.NOT_FOUND).build();
            }
        }
        else {
            locationURI = URI.create(context.getAbsolutePath().toString());
            //response = Response.status(Response.Status.BAD_REQUEST).location(locationURI).build();
            String error = "Either Name or Student Id is Missing";
            response = Response.status(Response.Status.BAD_REQUEST).location(locationURI).entity(error).build();
        }
        return response;
    }
    
    
    @GET                                                                       //DONE
    //@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{sid}")
    public Response getStudentWithIdForGradeBook(@PathParam("gid") int gid, @PathParam("sid") int sid) {
        System.out.println("Into the get student--" + "---" + gid +"---"  + sid );
        Response response = null; 
        URI locationURI;
        if( gid !=0 && sid != 0 ) {
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
                //response = Response.status(Response.Status.OK).entity(jsonString).build();
            } else {
                locationURI = URI.create(context.getAbsolutePath().toString());
                response = Response.status(Response.Status.NOT_FOUND).location(locationURI).build();
                //response = Response.status(Response.Status.NOT_FOUND).build();
            }
        }
        else {
            locationURI = URI.create(context.getAbsolutePath().toString());
            //response = Response.status(Response.Status.NOT_FOUND).location(locationURI).build(); //BAD_REQUEST aa?
            String error = "Student Id or Grade Id Cant be Zero or Empty";
            response = Response.status(Response.Status.BAD_REQUEST).location(locationURI).entity(error).build(); //BAD_REQUEST aa?
        }
        return response;
    }

    @GET                                               //DONE
    //@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    //@Path("{sid}")
    public Response getAllStudentForGradeBook(@PathParam("gid") int gid) {
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
                response = Response.status(Response.Status.OK).location(locationURI).entity(jsonString).build();
                //response = Response.status(Response.Status.OK).entity(jsonString).build();
            } else {
                locationURI = URI.create(context.getAbsolutePath().toString());
                response = Response.status(Response.Status.NOT_FOUND).location(locationURI).build();
                //response = Response.status(Response.Status.NOT_FOUND).build();
            }
        }
        else {
            locationURI = URI.create(context.getAbsolutePath().toString());
            //response = Response.status(Response.Status.NOT_FOUND).location(locationURI).build();
            String error = "GradeBook Id Cant be Zero or Empty";
            response = Response.status(Response.Status.BAD_REQUEST).location(locationURI).entity(error).build();
        }
        return response;
    }
    
    @DELETE                                                    //DONE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{sid}")
    public Response deleteStudentWithIdForGradeBook(@PathParam("gid") int gid, @PathParam("sid") int sid, GradeBooksUIInp gradeBooksUIInp) {
        Response response = null;
        URI locationURI;
        if( gid != 0  && sid != 0 ) {
            int status = studentService.deleteStudentWithIdForGradeBook(gid,sid);
            if(status == 1) {
                locationURI = URI.create(context.getAbsolutePath().toString());
                System.out.println(locationURI);
                response = Response.status(Response.Status.NO_CONTENT).location(locationURI).build();
                //response = Response.status(Response.Status.NO_CONTENT).build();
            } else {
                locationURI = URI.create(context.getAbsolutePath().toString());
                response = Response.status(Response.Status.NOT_FOUND).location(locationURI).build();  //NO_CONTENT aa??
                //response = Response.status(Response.Status.NOT_FOUND).build();  //NO_CONTENT aa??
            }
        } else {
            locationURI = URI.create(context.getAbsolutePath().toString());
            String error = "Student Id or Grade Id Cant be Zero or Empty";
            response = Response.status(Response.Status.BAD_REQUEST).location(locationURI).entity(error).build();
        }
        
        return response;
    }
    
    
    @DELETE                                                             //DONE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteAllStudentForGradeBook(@PathParam("gid") int gid, GradeBooksUIInp gradeBooksUIInp) {
        Response response = null;
        URI locationURI;
        if( gid != 0 ) {        
            int status = studentService.deleteAllStudentForGradeBook(gid);
            if(status == 1) {
                locationURI = URI.create(context.getAbsolutePath().toString());
                System.out.println(locationURI);
                response = Response.status(Response.Status.NO_CONTENT).location(locationURI).build();
                //response = Response.status(Response.Status.NO_CONTENT).build();
            } else {
                locationURI = URI.create(context.getAbsolutePath().toString());
                response = Response.status(Response.Status.NOT_FOUND).location(locationURI).build();
                //response = Response.status(Response.Status.NOT_FOUND).build();
            }
        } else {
            locationURI = URI.create(context.getAbsolutePath().toString());
            String error = "Grade Id Cant be Zero or Empty";
            response = Response.status(Response.Status.BAD_REQUEST).location(locationURI).entity(error).build();
        }
        return response;
    }
    
    
    @PUT                                             //DONE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{sid}")
    public Response updateStudentWithIdForGradeBook(@PathParam("gid") int gid, @PathParam("sid") int sid, GradeBooksUIInp gradeBooksUIInp) {
        Response response = null;
        URI locationURI;
        
        String jsonString = null;
        if(gid != 0 && sid != 0 && !(gradeBooksUIInp.getName().equals("") || gradeBooksUIInp.getName() == null)) {
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
                //response = Response.status(Response.Status.NOT_FOUND).build();
            }
        } else {
            locationURI = URI.create(context.getAbsolutePath().toString());
            //response = Response.status(Response.Status.BAD_REQUEST).location(locationURI).build();
            String error = "Ids cant be zero/empty or Student Name cant be Empty to Update";
            response = Response.status(Response.Status.BAD_REQUEST).location(locationURI).entity(error).build();
        }
        
        return response;
    }
    
    
    @POST                                                  //DONE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("Assignment")
    public Response createNewAssignmentForAllStudentForGradeBook(@PathParam("gid") int gid, GradeBooksUIInp gradeBooksUIInp) {
        System.out.println("Into the student resources--" + "---" + gid +"---"  + gradeBooksUIInp.getGradeBookId() + gradeBooksUIInp.getStudentId() + gradeBooksUIInp.getName() );
        Response response = null; 
        URI locationURI;
        if( gid!=0 && !(gradeBooksUIInp.getName().equals("") || gradeBooksUIInp.getName() == null)     //) && 
            //!(gradeBooksUIInp.getStudentId() == 0 )
          ) {
            Assignment assignment = studentService.addAssignmentForAllStudentForGradeBook(gid, gradeBooksUIInp.getName());
            System.out.println("Student Res:" + assignment);
            if(assignment != null) {
                String jsonString = null;
                try {
                    jsonString = mapper.writeValueAsString(assignment);
                } catch (JsonProcessingException e) {
                // TODO Auto-generated catch block
                    e.printStackTrace();
                }                
                //locationURI = URI.create(context.getAbsolutePath() + "/" + gradeBook.getId());
                locationURI = context.getAbsolutePathBuilder().path(String.valueOf(assignment.getId())).build();
                System.out.println(locationURI);
                response = Response.status(Response.Status.CREATED).location(locationURI).entity(jsonString).build();
            } else {
                locationURI = URI.create(context.getAbsolutePath().toString());
                response = Response.status(Response.Status.NOT_FOUND).location(locationURI).build();
                //response = Response.status(Response.Status.NOT_FOUND).build();
            }
        }
        else {
            locationURI = URI.create(context.getAbsolutePath().toString());
            //response = Response.status(Response.Status.BAD_REQUEST).location(locationURI).build();
            String error = "Gradebook ID and Assignment Name cant be Empty to Create";
            response = Response.status(Response.Status.BAD_REQUEST).location(locationURI).entity(error).build();
        }
        
        return response;
    }    
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("Quiz")
    public Response createNewQuizForAllStudentForGradeBook(@PathParam("gid") int gid, GradeBooksUIInp gradeBooksUIInp) {
        System.out.println("Into the student resources--" + "---" + gid +"---"  + gradeBooksUIInp.getGradeBookId() + gradeBooksUIInp.getStudentId() + gradeBooksUIInp.getName() );
        Response response = null; 
        URI locationURI;
        if( gid!=0 && !(gradeBooksUIInp.getName().equals("") || gradeBooksUIInp.getName() == null)     //) && 
            //!(gradeBooksUIInp.getStudentId() == 0 )
          ) {
            Quiz quiz = studentService.addQuizForAllStudentForGradeBook(gid, gradeBooksUIInp.getName());
            System.out.println("Student Res:" + quiz);
            if(quiz != null) {
                String jsonString = null;
                try {
                    jsonString = mapper.writeValueAsString(quiz);
                } catch (JsonProcessingException e) {
                // TODO Auto-generated catch block
                    e.printStackTrace();
                }                
                //locationURI = URI.create(context.getAbsolutePath() + "/" + gradeBook.getId());
                locationURI = context.getAbsolutePathBuilder().path(String.valueOf(quiz.getId())).build();
                System.out.println(locationURI);
                response = Response.status(Response.Status.CREATED).location(locationURI).entity(jsonString).build();
            } else {
                locationURI = URI.create(context.getAbsolutePath().toString());
                response = Response.status(Response.Status.NOT_FOUND).location(locationURI).build();
                //response = Response.status(Response.Status.NOT_FOUND).build();
            }
        }
        else {
            locationURI = URI.create(context.getAbsolutePath().toString());
            //response = Response.status(Response.Status.BAD_REQUEST).location(locationURI).build();
            String error = "Gradebook ID and Quiz Name cant be Empty to Create";
            response = Response.status(Response.Status.BAD_REQUEST).location(locationURI).entity(error).build();
        }
        
        return response;
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("Lab")
    public Response createNewLabForAllStudentForGradeBook(@PathParam("gid") int gid, GradeBooksUIInp gradeBooksUIInp) {
        System.out.println("Into the student resources--" + "---" + gid +"---"  + gradeBooksUIInp.getGradeBookId() + gradeBooksUIInp.getStudentId() + gradeBooksUIInp.getName() );
        Response response = null; 
        URI locationURI;
        if( gid!=0 && !(gradeBooksUIInp.getName().equals("") || gradeBooksUIInp.getName() == null)     //) && 
            //!(gradeBooksUIInp.getStudentId() == 0 )
          ) {
            Lab lab = studentService.addLabForAllStudentForGradeBook(gid, gradeBooksUIInp.getName());
            System.out.println("Student Res:" + lab);
            if(lab != null) {
                String jsonString = null;
                try {
                    jsonString = mapper.writeValueAsString(lab);
                } catch (JsonProcessingException e) {
                // TODO Auto-generated catch block
                    e.printStackTrace();
                }                
                //locationURI = URI.create(context.getAbsolutePath() + "/" + gradeBook.getId());
                locationURI = context.getAbsolutePathBuilder().path(String.valueOf(lab.getId())).build();
                System.out.println(locationURI);
                response = Response.status(Response.Status.CREATED).location(locationURI).entity(jsonString).build();
            } else {
                locationURI = URI.create(context.getAbsolutePath().toString());
                response = Response.status(Response.Status.NOT_FOUND).location(locationURI).build();
                //response = Response.status(Response.Status.NOT_FOUND).build();
            }
        }
        else {
            locationURI = URI.create(context.getAbsolutePath().toString());
            //response = Response.status(Response.Status.BAD_REQUEST).location(locationURI).build();
            String error = "Gradebook ID and Lab Name cant be Empty to Create";
            response = Response.status(Response.Status.BAD_REQUEST).location(locationURI).entity(error).build();
        }
        
        return response;
    }
}
