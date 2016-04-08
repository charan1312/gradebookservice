package com.asu.edu.cse564.service;

import java.util.List;

import com.asu.edu.cse564.model.GradeBook;
import com.asu.edu.cse564.model.GradeBooks;

public class GradeBooksService {

    public GradeBook addGradebook(String gradeBookName) {
        int lastCount = GradeBooks.gradeBooks.size();
        GradeBook gradeBook = new GradeBook(++lastCount,gradeBookName);
        GradeBooks.gradeBooks.add(gradeBook);
        
        return gradeBook;
    }
    
    public void deleteGradeBook(int gradeBookId) {
        GradeBooks.gradeBooks.remove(--gradeBookId);
    }
    
    public GradeBook updateGradeBook(int gradeBookId, String newName) {
        GradeBook gradeBook = GradeBooks.gradeBooks.get(--gradeBookId);
        gradeBook.setName(newName);
        
        return gradeBook;
    }

    public GradeBook getGradebook(int gradeBookId) {
        return GradeBooks.gradeBooks.get(--gradeBookId);
    }

    public List<GradeBook> getAllGradeBooks(int sid) {
        return GradeBooks.gradeBooks;
    }
}
