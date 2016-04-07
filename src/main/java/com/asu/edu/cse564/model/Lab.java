package com.asu.edu.cse564.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Lab {

    private static final Logger LOG = LoggerFactory.getLogger(Lab.class);

    private int id;
    private String name;
    private String grade;
    
    public Lab() {
        // TODO Auto-generated constructor stub
        LOG.info("Creating the Lab class");
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public String getGrade() {
        return grade;
    }
    
    public void setGrade(String grade) {
        this.grade = grade;
    }
}
