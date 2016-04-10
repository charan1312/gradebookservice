package com.asu.edu.cse564.service;

import java.util.Iterator;
import java.util.List;

import com.asu.edu.cse564.model.GradeBook;
import com.asu.edu.cse564.model.GradeBooks;
import com.asu.edu.cse564.model.Quiz;
import com.asu.edu.cse564.model.Student;
import com.fasterxml.jackson.databind.ObjectMapper;

public class QuizService {

    public ObjectMapper mapper = new ObjectMapper();
    
    public Quiz addQuizForStudentWithIdForGradeBook(int gbid, int sid, int aid, String name) {
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
                Quiz quiz = new Quiz(aid, name);
                s.getQuizs().add(quiz);
                return quiz;
            } else {
                return null;
            }
        } 
        
        return null;
    }
    
    
    public int deleteQuizForStudentWithIdForGradeBook(int gbid,int sid, int aid) {
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
                    Iterator<Quiz> itr = s.getQuizs().iterator();
                    int ia = 0 ; 
                    aflag = 0;
                    while(itr.hasNext()) {
                        ++ia;
                        Quiz a = itr.next();
                        if(a.getId() == aid) {
                            aflag = 1;
                            break;
                        }
                    }
                    if(aflag == 1) {
                        s.getQuizs().remove(--ia);
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

    
    public Quiz updateQuizForStudentWithIdForGradeBook(int gbid, int sid, int aid, int grade, String feedback) {
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
                    Iterator<Quiz> itr = s.getQuizs().iterator();
                    int ia = 0 ; 
                    aflag = 0;
                    while(itr.hasNext()) {
                        ++ia;
                        Quiz a = itr.next();
                        if(a.getId() == aid) {
                            aflag = 1;
                            break;
                        }
                    }
                    if(aflag == 1) {
                        Quiz a = s.getQuizs().get(--ia);
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
    
    public Quiz getQuizForStudentWithIdForGradeBook(int gbid, int sid, int aid) {
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
                    Iterator<Quiz> itr = s.getQuizs().iterator();
                    int ia = 0 ; 
                    aflag = 0;
                    while(itr.hasNext()) {
                        ++ia;
                        Quiz a = itr.next();
                        if(a.getId() == aid) {
                            aflag = 1;
                            break;
                        }
                    }
                    if(aflag == 1) {
                        Quiz a = s.getQuizs().get(--ia);
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


    public List<Quiz> getAllQuizForStudentWithIdForGradeBook( int gbid, int sid) {
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
                    List<Quiz> quizs = s.getQuizs();
                    return quizs;                    
                } else {
                    return null;
                }
            } 
        }
        return null;
    }

}
