package com.asu.edu.cse564.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MidTerm {

    private static final Logger LOG = LoggerFactory.getLogger(MidTerm.class);

//    private int id;
//    private String name;
    private String grade;
    
    public MidTerm() {
        // TODO Auto-generated constructor stub
        LOG.info("Creating the MidTerm class");
    }
    
//    public int getId() {
//        return id;
//    }
//    
//    public void setId(int id) {
//        this.id = id;
//    }
    
//    public String getName() {
//        return name;
//    }
//    
//    public void setName(String name) {
//        this.name = name;
//    }
    
    public String getGrade() {
        return grade;
    }
    
    public void setGrade(String grade) {
        this.grade = grade;
    }
}
