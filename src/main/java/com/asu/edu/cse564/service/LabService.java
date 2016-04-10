package com.asu.edu.cse564.service;

import java.util.Iterator;
import java.util.List;

import com.asu.edu.cse564.model.GradeBook;
import com.asu.edu.cse564.model.GradeBooks;
import com.asu.edu.cse564.model.Lab;
import com.asu.edu.cse564.model.Student;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LabService {

    public ObjectMapper mapper = new ObjectMapper();
    
    public Lab addLabForStudentWithIdForGradeBook(int gbid, int sid, int aid, String name) {
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
    
    
    public int deleteLabForStudentWithIdForGradeBook(int gbid,int sid, int aid) {
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
                        return 1;
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

    
    public Lab updateLabForStudentWithIdForGradeBook(int gbid, int sid, int aid, int grade, String feedback) {
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
                        Lab a = s.getLabs().get(--ia);
                        a.setGrade(grade);
                        a.setFeedback(feedback);
                        return a;
                    } else {
                        return null;
                    }
                } else {
                    return null;
                }
            }
        } 
        
        return null;
    }
    
    public Lab getLabForStudentWithIdForGradeBook(int gbid, int sid, int aid) {
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
                        Lab a = s.getLabs().get(--ia);
                        //a.setGrade(grade);
                        return a;
                    } else {
                        return null;
                    }
                } else {
                    return null;
                }
            }
        }

        return null;
    }


    public List<Lab> getAllLabForStudentWithIdForGradeBook( int gbid, int sid) {
        GradeBook gradeBook;
        Iterator<GradeBook> gbIterator =  GradeBooks.gradeBooks.iterator();
        int i = 0 ; 
        int flag = 0, sflag = 0 ;
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
                    List<Lab> labs = s.getLabs();
                    return labs;                    
                } else {
                    return null;
                }
            } 
        }
        return null;
    }

}
