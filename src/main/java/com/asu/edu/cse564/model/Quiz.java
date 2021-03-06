package com.asu.edu.cse564.model;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;


public class Quiz {

    //private static final Logger LOG = LoggerFactory.getLogger(Quiz.class);

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
    
    public Quiz() {
        // TODO Auto-generated constructor stub
        //LOG.info("Creating the Quiz class");
    }
    
    public Quiz(int id, String title) {
        super();
        this.id = id;
        this.title = title;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public int getGrade() {
        return grade;
    }
    
    public void setGrade(int grade) {
        this.grade = grade;
    }
}
