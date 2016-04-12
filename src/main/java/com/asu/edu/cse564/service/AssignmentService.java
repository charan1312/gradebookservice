package com.asu.edu.cse564.service;

import java.util.Iterator;
import java.util.List;

import com.asu.edu.cse564.model.Assignment;
import com.asu.edu.cse564.model.GradeBook;
import com.asu.edu.cse564.model.GradeBooks;
import com.asu.edu.cse564.model.Student;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AssignmentService {

    public ObjectMapper mapper = new ObjectMapper();
    
    public Assignment addAssignmentForStudentWithIdForGradeBook(int gbid, int sid, int aid, String name) {
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
            Student s = gradeBook.students.get(sid);
            if(s != null) {
                Assignment assignment = new Assignment(aid, name);
                s.getAssignments().add(assignment);
                return assignment;
            } else {
                return null;
            }
        } 
        
        return null;
    }
    
    public int deleteAssignmentForStudentWithIdForGradeBook(int gbid,int sid, int aid) {
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
            Student s = gradeBook.students.get(sid);
            if(s != null) {
                sflag = 1;
                if(sflag == 1) {
                    Iterator<Assignment> itr = s.getAssignments().iterator();
                    int ia = 0 ; 
                    aflag = 0;
                    while(itr.hasNext()) {
                        ++ia;
                        Assignment a = itr.next();
                        if(a.getId() == aid) {
                            aflag = 1;
                            break;
                        }
                    }
                    if(aflag == 1) {
                        s.getAssignments().remove(--ia);
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

    
    public Assignment updateAssignmentForStudentWithIdForGradeBook(int gbid, int sid, int aid, int grade, String feedback) {
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
            Student s = gradeBook.students.get(sid);
            if(s != null) {
                sflag = 1;
                if(sflag == 1) {
                    Iterator<Assignment> itr = s.getAssignments().iterator();
                    int ia = 0 ; 
                    aflag = 0;
                    while(itr.hasNext()) {
                        ++ia;
                        Assignment a = itr.next();
                        if(a.getId() == aid) {
                            aflag = 1;
                            break;
                        }
                    }
                    if(aflag == 1) {
                        Assignment a = s.getAssignments().get(--ia);
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
    
    public Assignment getAssignmentForStudentWithIdForGradeBook(int gbid, int sid, int aid) {
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
            Student s = gradeBook.students.get(sid);
            if(s != null) {
                sflag = 1;
                if(sflag == 1) {
                    Iterator<Assignment> itr = s.getAssignments().iterator();
                    int ia = 0 ; 
                    aflag = 0;
                    while(itr.hasNext()) {
                        ++ia;
                        Assignment a = itr.next();
                        if(a.getId() == aid) {
                            aflag = 1;
                            break;
                        }
                    }
                    if(aflag == 1) {
                        Assignment a = s.getAssignments().get(--ia);
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


    public List<Assignment> getAllAssignmentForStudentWithIdForGradeBook( int gbid, int sid) {
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
            Student s = gradeBook.students.get(sid);
            if(s != null) {
                sflag = 1;
                if(sflag == 1) {
                    List<Assignment> assignments = s.getAssignments();
                    return assignments;                    
                } else {
                    return null;
                }
            } 
        }
        return null;
    }
    
    
    /*    
    //HashMap<Integer, Student> studentMap = GradeBook.studentsMap;
    private static int asgcount = 0;
    
    //public void addAssignmentForAllStudents(int aid,String aTitle) {
    public void addAssignmentForAllStudents(String aTitle) {        
        int asgnmentcount;
        List<Student> studentsList = (List<Student>) studentMap.values();
        Student student = studentsList.get(0);
        asgnmentcount = student.getAssigments().size();
        
        Assignment asg = new Assignment(++asgnmentcount,aTitle);
        
        for(Student s: studentMap.values()) {
            s.getAssigments().add(asg);
        }
    }

    public void addParticularAssignmentForParticularStudent(String aTitle) {
        int asgnmentcount;
        List<Student> studentsList = (List<Student>) studentMap.values();
        Student student = studentsList.get(0);
        asgnmentcount = student.getAssigments().size();
        
        Assignment asg = new Assignment(++asgnmentcount,aTitle);
        
        for(Student s: studentMap.values()) {
            s.getAssigments().add(asg);
        }
    }

    
    public void deleteParticularAssignmentForAllStudents(int aid) {
        for(Student s: studentMap.values()) {
            s.getAssigments().remove(--aid);
        }
    }

    //THIS METHOD DOES NOT HAVE THE GRADE VALUE FROM THE Assignment CLASS   
    public List<AsgList> getAssignmentList() {
        
        List<Student> studentsList = (List<Student>) studentMap.values();
        Student s = studentsList.get(0);
        //Student s = studentMap.get(0);
        List<Assignment> asgs = s.getAssigments();
        List<AsgList> asgList = new ArrayList<AsgList>();
        for( Assignment a : asgs) {
            AsgList asg = new AsgList();
            asg.setId(a.getId());
            asg.setName(a.getName());
            asgList.add(asg);
        }
        
        return asgList;
    }
    
    // GET THE ASSIGNMENT GRADE FOR A STUDENT  ---- THINK IF A MAP IS NEEDED FOR SURE?????
    public List<Assignment> getAllAssignmentGradesForStudent(int sid) {
        
        Student s = studentMap.get(sid);
        List<Assignment> asgs = s.getAssigments();
        
        return asgs;
    }

    public Assignment getParticularAssignmentGradeForStudent(int aid, int sid) {   
        Student s = studentMap.get(sid);
        Assignment asg = null;
        List<Assignment> asgs = s.getAssigments();
        for(Assignment a : asgs) {
            if(a.getId() == aid)
                asg = a;
        }
    
        if(asg == null)
            return null;
        
        return asg;
    }
    
    public Assignment updateAssignmentGradeForStudent(int sid, int aid, int grade) {        
        Student s = studentMap.get(sid);
        Assignment asgToUpdate = null;
        List<Assignment> asgs = s.getAssigments();
        for(Assignment a : asgs) {
            if(a.getId() == aid) {
                asgToUpdate = a ;
                break;
            }
        }
        
        asgToUpdate.setGrade(grade);
        
        return asgToUpdate;
    }

    public Assignment addAssignmentGradeForStudent(int sid, int aid, int grade) {        
        Student s = studentMap.get(sid);
        Assignment asgToAdd = null;
        List<Assignment> asgs = s.getAssigments();
        for(Assignment a : asgs) {
            if(a.getId() == aid) {
                asgToAdd = a ;
                break;
            }
        }
        
        asgToAdd.setGrade(grade);
        
        return asgToAdd;
    }
*/



}
