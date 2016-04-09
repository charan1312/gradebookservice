package com.asu.edu.cse564.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.asu.edu.cse564.model.GradeBook;
import com.asu.edu.cse564.model.GradeBooks;
import com.asu.edu.cse564.model.Student;
import com.fasterxml.jackson.databind.ObjectMapper;

public class StudentService {
    
    public ObjectMapper mapper = new ObjectMapper();
    
    //private static int count;
    //private static int scount;
    
    //NEED TO SEE IF WE HAVE TO AUTOGENERATE THE ID
    public Student addStudent(int gbid, int sid, String sName) {
        GradeBook gradeBook;
        System.out.println("Into add student:" + GradeBooks.gradeBooks.size() );
        Iterator<GradeBook> gbIterator =  GradeBooks.gradeBooks.iterator();
        int i = 0 ; 
        int flag = 0;
        while(gbIterator.hasNext()) {
            ++i;
            GradeBook g = gbIterator.next();
            System.out.println("Inside while loop:" + g.getId() + " " +g.getName());
            if(g.getId() == gbid) {
                System.out.println("INSIDE MATCH");
                flag = 1;
                break;
            }
        }
        System.out.println("Flag val:" + flag + "I val:" + i);
        if(flag == 1) {
            gradeBook = GradeBooks.gradeBooks.get(--i);
            Student student = new Student(sid, sName);
            gradeBook.studentsMap.put(sid, student);
            return student;
        }
        //GradeBook gradeBook = GradeBooks.gradeBooks.get(0);
        //Student student = new Student(sid, sName);
        //GradeBook.studentsMap.put(sid, student);
        return null;
    }
    
 /*   public void deleteStudent(int sid) {
        GradeBook.studentsMap.remove(sid);
    }
    
    public void updateStudent(int sid, String name) {
        Student s = GradeBook.studentsMap.get(sid);
        s.setsName(name);
    }

    public List<Student> getAllStudents(int sid) {
        return (List<Student>) GradeBook.studentsMap.values();
    }
*/
  
    public Student getstudent(int gbid, int sid) {
        GradeBook gradeBook;//GradeBook.studentsMap.get(sid);
        Iterator<GradeBook> gbIterator =  GradeBooks.gradeBooks.iterator();
        int i = 0 ; 
        int flag = 0;
        while(gbIterator.hasNext()) {
            ++i;
            GradeBook g = gbIterator.next();
            System.out.println("Inside while loop:" + g.getId() + " " +g.getName());
            if(g.getId() == gbid) {
                System.out.println("INSIDE MATCH");
                flag = 1;
                break;
            }
        }
        System.out.println("Flag val:" + flag + "I val:" + i);
        if(flag == 1) {
            gradeBook = GradeBooks.gradeBooks.get(--i);
            Student student = gradeBook.studentsMap.get(sid);
            return student;
        }
        return null;
    }

    public List<Student> getAllStudentForGradeBook(int gbid) {
        GradeBook gradeBook;//GradeBook.studentsMap.get(sid);
        Iterator<GradeBook> gbIterator =  GradeBooks.gradeBooks.iterator();
        int i = 0 ; 
        int flag = 0;
        while(gbIterator.hasNext()) {
            ++i;
            GradeBook g = gbIterator.next();
            System.out.println("Inside while loop:" + g.getId() + " " +g.getName());
            if(g.getId() == gbid) {
                System.out.println("INSIDE MATCH");
                flag = 1;
                break;
            }
        }
        System.out.println("Flag val:" + flag + "I val:" + i);
        if(flag == 1) {
            gradeBook = GradeBooks.gradeBooks.get(--i);
            List<Student> students = new ArrayList<Student>(gradeBook.studentsMap.values());
            return students;
        }
        
        return null;
    }
    
    
    public int deleteAllStudentForGradeBook(int gbid) {
        GradeBook gradeBook;//GradeBook.studentsMap.get(sid);
        Iterator<GradeBook> gbIterator =  GradeBooks.gradeBooks.iterator();
        int i = 0 ; 
        int flag = 0;
        while(gbIterator.hasNext()) {
            ++i;
            GradeBook g = gbIterator.next();
            System.out.println("Inside while loop:" + g.getId() + " " +g.getName());
            if(g.getId() == gbid) {
                System.out.println("INSIDE MATCH");
                flag = 1;
                break;
            }
        }
        System.out.println("Flag val:" + flag + "I val:" + i);
        if(flag == 1) {
            gradeBook = GradeBooks.gradeBooks.get(--i);
            gradeBook.studentsMap.clear();
            return 1;
        }
        
        return 0;
    }

    public int deleteStudentWithIdForGradeBook(int gbid, int sid) {
        GradeBook gradeBook;
        Iterator<GradeBook> gbIterator =  GradeBooks.gradeBooks.iterator();
        int i = 0 ; 
        int flag = 0;
        while(gbIterator.hasNext()) {
            ++i;
            GradeBook g = gbIterator.next();
            System.out.println("Inside while loop:" + g.getId() + " " +g.getName());
            if(g.getId() == gbid) {
                System.out.println("INSIDE MATCH");
                flag = 1;
                break;
            }
        }
        System.out.println("Flag val:" + flag + "I val:" + i);
        if(flag == 1) {
            gradeBook = GradeBooks.gradeBooks.get(--i);
            gradeBook.studentsMap.remove(sid);
            return 1;
        }
        
        return 0;
    }
    
    public Student updateStudentWithIdForGradeBook(int gbid, int sid, String name) {
        GradeBook gradeBook;//GradeBook.studentsMap.get(sid);
        Iterator<GradeBook> gbIterator =  GradeBooks.gradeBooks.iterator();
        int i = 0 ; 
        int flag = 0;
        while(gbIterator.hasNext()) {
            ++i;
            GradeBook g = gbIterator.next();
            System.out.println("Inside while loop:" + g.getId() + " " +g.getName());
            if(g.getId() == gbid) {
                System.out.println("INSIDE MATCH");
                flag = 1;
                break;
            }
        }
        System.out.println("Flag val:" + flag + "I val:" + i);
        if(flag == 1) {
            gradeBook = GradeBooks.gradeBooks.get(--i);
            Student student = gradeBook.studentsMap.get(sid);
            student.setsName(name);
            return student;
        }
        
        return null;
    }

}
