package com.asu.edu.cse564.model;

import java.util.List;

public class Student {
    private int sid;
    private String sName;
    private List<Assignment> assigments;
    private List<Quiz> quizs;
    private List<Lab> labs;
    private MidTerm midTerm;
    
    public int getsId() {
        return sid;
    }
    public void setsId(int sId) {
        this.sid = sId;
    }
    
    public String getsName() {
        return sName;
    }
    public void setsName(String sName) {
        this.sName = sName;
    }
    
    public List<Assignment> getAssigments() {
        return assigments;
    }
    public void setAssigments(List<Assignment> assigments) {
        this.assigments = assigments;
    }
    
    public List<Quiz> getQuizs() {
        return quizs;
    }
    public void setQuizs(List<Quiz> quizs) {
        this.quizs = quizs;
    }
    
    public List<Lab> getLabs() {
        return labs;
    }
    public void setLabs(List<Lab> labs) {
        this.labs = labs;
    }
    
    public MidTerm getMidTerm() {
        return midTerm;
    }
    public void setMidTerm(MidTerm midTerm) {
        this.midTerm = midTerm;
    }
}
