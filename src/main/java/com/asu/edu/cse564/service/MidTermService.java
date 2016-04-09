package com.asu.edu.cse564.service;

import java.util.Iterator;
import java.util.List;

import com.asu.edu.cse564.model.GradeBook;
import com.asu.edu.cse564.model.GradeBooks;
import com.asu.edu.cse564.model.Lab;
import com.asu.edu.cse564.model.MidTerm;
import com.asu.edu.cse564.model.Student;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MidTermService {

    public ObjectMapper mapper = new ObjectMapper();
    
/*    public Lab addLabForStudentWithIdForGradeBook(int gbid, int sid, int aid, String name) {
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
            Student s = gradeBook.studentsMap.get(sid);
            if(s != null) {
                Lab lab = new Lab(aid, name);
                s.getLabs().add(lab);
                return lab;
            } else {
                return null;
            }
        } 
        
        return null;
    }
    
    
    public int deleteForStudentWithIdForGradeBook(int gbid,int sid, int aid) {
        GradeBook gradeBook;
        Iterator<GradeBook> gbIterator =  GradeBooks.gradeBooks.iterator();
        int i = 0 ; 
        int flag = 0, sflag = 0 , aflag = 0;
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
            Student s = gradeBook.studentsMap.get(sid);
            if(s != null) {
                sflag = 1;
                if(sflag == 1) {
                    Iterator<Lab> itr = s.getLabs().iterator();
                    int ia = 0 ; 
                    aflag = 0;
                    while(itr.hasNext()) {
                        ++ia;
                        Lab a = itr.next();
                        if(a.getId() == aid) {
                            aflag = 1;
                            break;
                        }
                    }
                    if(aflag == 1) {
                        s.getLabs().remove(--ia);
                    } else {
                        return 0;
                    }
                } else {
                    return 0;
                }
            }
        } 
        
        return 0;
    }
*/
    
    public MidTerm updateMidTermForStudentWithIdForGradeBook(int gbid, int sid, int grade, String feedback) {
        GradeBook gradeBook;
        Iterator<GradeBook> gbIterator =  GradeBooks.gradeBooks.iterator();
        int i = 0 ; 
        int flag = 0, sflag = 0;
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
            Student s = gradeBook.studentsMap.get(sid);
            if(s != null) {
                sflag = 1;
                if(sflag == 1) {
                    MidTerm m = s.getMidTerm();
                    m.setGrade(grade);
                    m.setFeedback(feedback);
                    return m;
                } else {
                    return null;
                }
            }
        } 
        
        return null;
    }
    
    public MidTerm getMidTermForStudentWithIdForGradeBook(int gbid, int sid) {
        GradeBook gradeBook;
        Iterator<GradeBook> gbIterator =  GradeBooks.gradeBooks.iterator();
        int i = 0 ; 
        int flag = 0, sflag = 0;
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
            Student s = gradeBook.studentsMap.get(sid);
            if(s != null) {
                sflag = 1;
                if(sflag == 1) {
                    return s.getMidTerm();
                } else {
                    return null;
                }
            }
        }

        return null;
    }
    
}
