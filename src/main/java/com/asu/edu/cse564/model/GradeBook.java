package com.asu.edu.cse564.model;

import java.util.HashMap;
import java.util.Map;

public class GradeBook {

    //public static List<Student> gradeBook;
    public static HashMap<Integer, Student> gradeBook;
    private int id;
    private String name;
    
    public GradeBook() {
        gradeBook = new HashMap<Integer, Student>();
    }

    public GradeBook(int id,String gradeBookName) {
        this.id = id;
        this.name = gradeBookName;
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

}
