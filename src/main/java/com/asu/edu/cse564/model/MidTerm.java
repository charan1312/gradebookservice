package com.asu.edu.cse564.model;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;


public class MidTerm {

    //private static final Logger LOG = LoggerFactory.getLogger(MidTerm.class);

//    private int id;
//    private String name;
    private int grade = -100;
    private String feedback;
    
    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public MidTerm() {

    }
    
    public int getGrade() {
        return grade;
    }
    
    public void setGrade(int grade) {
        this.grade = grade;
    }
}
