package com.asu.edu.cse564.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

public class GradeBooksUIInp {

    //private static final Logger LOG = LoggerFactory.getLogger(GradeBooksUIInp.class);
    
    private String comboText;
    private int gradeBookId;
    private int gradeItemId;
    private int studentId;
    private String name;
    private int grade;
    private String feedback;
    
    public GradeBooksUIInp() {
        //LOG.info("Creating an GradeBooks object");
    }

    public String getComboText() {
        return comboText;
    }

    public void setComboText(String comboText) {
        this.comboText = comboText;
    }

    public int getGradeBookId() {
        return gradeBookId;
    }

    public void setGradeBookId(int gradeBookId) {
        this.gradeBookId = gradeBookId;
    }

    public int getGradeItemId() {
        return gradeItemId;
    }

    public void setGradeItemId(int gradeItemId) {
        this.gradeItemId = gradeItemId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
     
    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();
        GradeBooksUIInp inp = new GradeBooksUIInp();
        inp.setName("CSE 564 GRADEBOOK");
        inp.setFeedback("Feed back");
        
        String a = "www.google.com/services/rest/get/88";
       System.out.println(a.substring(0,a.lastIndexOf("/") ));
        System.out.println(a.lastIndexOf("/"));
        
        try {
            System.out.println(mapper.writeValueAsString(inp));
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
