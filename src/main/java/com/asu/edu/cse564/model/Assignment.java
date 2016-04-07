package com.asu.edu.cse564.model;

//import javax.xml.bind.annotation.XmlElement;
//import javax.xml.bind.annotation.XmlRootElement;
//import javax.xml.bind.annotation.XmlType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


//@XmlRootElement(name = "assignment")
//@XmlType(propOrder={
//    "id",
//    "sid",
//    "name",
//    "grade"})
public class Assignment {

    private static final Logger LOG = LoggerFactory.getLogger(Assignment.class);

    private int id;
    private String name;
    private String grade;
    
    public Assignment() {
        // TODO Auto-generated constructor stub
        LOG.info("Creating the Assignment class");
    }
    
    public int getId() {
        return id;
    }
    
//    @XmlElement
    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
//    @XmlElement
    public void setName(String name) {
        this.name = name;
    }
    public String getGrade() {
        return grade;
    }
    
//    @XmlElement
    public void setGrade(String grade) {
        this.grade = grade;
    }
}
