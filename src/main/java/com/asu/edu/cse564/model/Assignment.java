package com.asu.edu.cse564.model;

//import javax.xml.bind.annotation.XmlElement;
//import javax.xml.bind.annotation.XmlRootElement;
//import javax.xml.bind.annotation.XmlType;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;


//@XmlRootElement(name = "assignment")
//@XmlType(propOrder={
//    "id",
//    "sid",
//    "name",
//    "grade"})
public class Assignment {

    //private static final Logger LOG = LoggerFactory.getLogger(Assignment.class);

    private int id;
    private String title;
    private int grade = -100;
    private String feedback;
    
    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
    
    public Assignment() {
        // TODO Auto-generated constructor stub
        //LOG.info("Creating the Assignment class");
    }
    
    
    public Assignment(int id, String title) {
        super();
        this.id = id;
        this.title = title;
        
        //Default value of the grades when initially assigned
        //this.grade = -1;
    }


    public int getId() {
        return id;
    }
    
//    @XmlElement
    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return title;
    }
    
//    @XmlElement
    public void setName(String name) {
        this.title = name;
    }
    public int getGrade() {
        return grade;
    }
    
//    @XmlElement
    public void setGrade(int grade) {
        this.grade = grade;
    }
}
