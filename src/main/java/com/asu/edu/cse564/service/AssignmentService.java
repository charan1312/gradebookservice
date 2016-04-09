package com.asu.edu.cse564.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.asu.edu.cse564.model.AsgList;
import com.asu.edu.cse564.model.Assignment;
import com.asu.edu.cse564.model.GradeBook;
import com.asu.edu.cse564.model.Student;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AssignmentService {

    public ObjectMapper mapper = new ObjectMapper();
    
    //HashMap<Integer, Student> studentMap = GradeBook.studentsMap;
    private static int asgcount = 0;
    
    //public void addAssignmentForAllStudents(int aid,String aTitle) {
    public void addAssignmentForAllStudents(String aTitle) {        
        int asgnmentcount;
        List<Student> studentsList = (List<Student>) studentMap.values();
        Student student = studentsList.get(0);
        asgnmentcount = student.getAssigments().size();
        
        Assignment asg = new Assignment(++asgnmentcount,aTitle);
        
        for(Student s: studentMap.values()) {
            s.getAssigments().add(asg);
        }
    }

    public void addParticularAssignmentForParticularStudent(String aTitle) {
        int asgnmentcount;
        List<Student> studentsList = (List<Student>) studentMap.values();
        Student student = studentsList.get(0);
        asgnmentcount = student.getAssigments().size();
        
        Assignment asg = new Assignment(++asgnmentcount,aTitle);
        
        for(Student s: studentMap.values()) {
            s.getAssigments().add(asg);
        }
    }

    
    public void deleteParticularAssignmentForAllStudents(int aid) {
        for(Student s: studentMap.values()) {
            s.getAssigments().remove(--aid);
        }
    }

    public void deleteParticularAssignmentForParticularStudent(int aid,int sid) {
        Student s = studentMap.get(sid);
        s.getAssigments().remove(--aid);
    }
    
    
    //THIS METHOD DOES NOT HAVE THE GRADE VALUE FROM THE Assignment CLASS   
    public List<AsgList> getAssignmentList() {
        
        List<Student> studentsList = (List<Student>) studentMap.values();
        Student s = studentsList.get(0);
        //Student s = studentMap.get(0);
        List<Assignment> asgs = s.getAssigments();
        List<AsgList> asgList = new ArrayList<AsgList>();
        for( Assignment a : asgs) {
            AsgList asg = new AsgList();
            asg.setId(a.getId());
            asg.setName(a.getName());
            asgList.add(asg);
        }
        
        return asgList;
    }
    
    // GET THE ASSIGNMENT GRADE FOR A STUDENT  ---- THINK IF A MAP IS NEEDED FOR SURE?????
    public List<Assignment> getAllAssignmentGradesForStudent(int sid) {
        
        Student s = studentMap.get(sid);
        List<Assignment> asgs = s.getAssigments();
        
        return asgs;
    }

    public Assignment getParticularAssignmentGradeForStudent(int aid, int sid) {   
        Student s = studentMap.get(sid);
        Assignment asg = null;
        List<Assignment> asgs = s.getAssigments();
        for(Assignment a : asgs) {
            if(a.getId() == aid)
                asg = a;
        }
    
        if(asg == null)
            return null;
        
        return asg;
    }
    
    public Assignment updateAssignmentGradeForStudent(int sid, int aid, int grade) {        
        Student s = studentMap.get(sid);
        Assignment asgToUpdate = null;
        List<Assignment> asgs = s.getAssigments();
        for(Assignment a : asgs) {
            if(a.getId() == aid) {
                asgToUpdate = a ;
                break;
            }
        }
        
        asgToUpdate.setGrade(grade);
        
        return asgToUpdate;
    }

    public Assignment addAssignmentGradeForStudent(int sid, int aid, int grade) {        
        Student s = studentMap.get(sid);
        Assignment asgToAdd = null;
        List<Assignment> asgs = s.getAssigments();
        for(Assignment a : asgs) {
            if(a.getId() == aid) {
                asgToAdd = a ;
                break;
            }
        }
        
        asgToAdd.setGrade(grade);
        
        return asgToAdd;
    }

}
