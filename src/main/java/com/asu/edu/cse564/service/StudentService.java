package com.asu.edu.cse564.service;

import java.util.List;

import com.asu.edu.cse564.model.GradeBook;
import com.asu.edu.cse564.model.Student;

public class StudentService {
    
    //NEED TO SEE IF WE HAVE TO AUTOGENERATE THE ID
    public void addStudent(int sid,String sName) {
        Student student = new Student(sid, sName);
        GradeBook.gradeBook.put(sid, student);
    }
    
    public void deleteStudent(int sid) {
        GradeBook.gradeBook.remove(sid);
    }
    
    public void updateStudent(int sid, String name) {
        Student s = GradeBook.gradeBook.get(sid);
        s.setsName(name);
    }

    public Student getstudent(int sid) {
        return GradeBook.gradeBook.get(sid);
    }

    public List<Student> getAllStudents(int sid) {
        return (List<Student>) GradeBook.gradeBook.values();
    }

}
