package com.asu.edu.cse564.service;

import java.util.Iterator;
import java.util.List;

import com.asu.edu.cse564.model.GradeBook;
import com.asu.edu.cse564.model.GradeBooks;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GradeBooksService {

    public ObjectMapper mapper = new ObjectMapper();
    
    private static int count;
    
    public GradeBook addGradebook(String gradeBookName) {
        
        //int lastCount = GradeBooks.gradeBooks.size();
        //GradeBook gradeBook = new GradeBook(++lastCount,gradeBookName);
        GradeBook gradeBook = new GradeBook(++count,gradeBookName);
        GradeBooks.gradeBooks.add(gradeBook);
        
        return gradeBook;
    }
    
    public int deleteGradeBook(int gradeBookId) {
        Iterator<GradeBook> itr = GradeBooks.gradeBooks.iterator();
        int i = 0 ; 
        int flag = 0;
        while(itr.hasNext()) {
            ++i;
            GradeBook g = itr.next();
            if(g.getId() == gradeBookId) {
                flag = 1;
                break;
            }
            //GradeBooks.gradeBooks.remove(g.getId() - 1);
            //itr.remove();
        }        
        if(flag == 1) {
            GradeBooks.gradeBooks.remove(--i);
        }
            return flag;
/*        
        if(GradeBooks.gradeBooks.size() >= gradeBookId) {
            GradeBooks.gradeBooks.remove(--gradeBookId);
            return 1;
        } else {
            return 0;
        }
*/        
    }

    public int deleteAllGradeBooks() {
        if(GradeBooks.gradeBooks.size() > 0) {
            GradeBooks.gradeBooks.clear();
            //Iterator<GradeBook> itr = GradeBooks.gradeBooks.iterator();
            //while(itr.hasNext()) {
                //GradeBook g = itr.next();
                //GradeBooks.gradeBooks.remove(g.getId() - 1);
                //itr.remove();
            //}
            return 1;
        } else {
            return 0;
        }
        /*if(GradeBooks.gradeBooks.size() > 0) {
            for(int i = 0 ; i < GradeBooks.gradeBooks.size(); i++) {
                GradeBooks.gradeBooks.remove(0);
            }
            return 1;
        } else {
            return 0;
        }*/
    }

    public GradeBook updateGradeBook(int gradeBookId, String newName) {
        Iterator<GradeBook> itr = GradeBooks.gradeBooks.iterator();
        int i = 0 ; 
        int flag = 0;
        while(itr.hasNext()) {
            ++i;
            GradeBook g = itr.next();
            if(g.getId() == gradeBookId) {
                flag = 1;
                break;
            }
        }
        if(flag == 1) {
            //if(GradeBooks.gradeBooks.size() >= gradeBookId) {
            //GradeBook gradeBook = GradeBooks.gradeBooks.get(--gradeBookId);
            GradeBook gradeBook = GradeBooks.gradeBooks.get(--i);
            gradeBook.setName(newName);
            return gradeBook;
        } else {
            return null;
        }
        
    }

    public String getGradeBook(int gradeBookId) {
        Iterator<GradeBook> itr = GradeBooks.gradeBooks.iterator();
        int i = 0 ; 
        int flag = 0;
        while(itr.hasNext()) {
            ++i;
            GradeBook g = itr.next();
            if(g.getId() == gradeBookId) {
                flag = 1;
                break;
            }
        }
        //if(GradeBooks.gradeBooks.size() >= gradeBookId) {
        if(flag == 1) {
            try {
                return mapper.writeValueAsString(GradeBooks.gradeBooks.get(--i));
            } catch (JsonProcessingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
            
        return null;
    }

    public String getAllGradeBooks() {
        
        try {
            return mapper.writeValueAsString(GradeBooks.gradeBooks);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
