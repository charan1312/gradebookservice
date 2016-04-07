package com.asu.edu.cse564.model;

import java.util.List;

public class GradeBook {

    private static List<Student> gradeBook;

    public static List<Student> getGradeBook() {
        return gradeBook;
    }

    public static void setGradeBook(List<Student> gradeBook) {
        GradeBook.gradeBook = gradeBook;
    }
    
}
