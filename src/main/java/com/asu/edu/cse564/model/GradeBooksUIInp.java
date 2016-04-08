package com.asu.edu.cse564.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GradeBooksUIInp {

    private static final Logger LOG = LoggerFactory.getLogger(GradeBooksUIInp.class);
    
    private String comboText;
    private int gradeBookId;
    private int gradeItemId;
    private int studentId;
    private String name;
    private int grade;
    
    public GradeBooksUIInp() {
        LOG.info("Creating an GradeBooks object");
    }

    public String getComboText() {
        return comboText;
    }

    public void setComboText(String comboText) {
        this.comboText = comboText;
    }

    public int getGradeBookId() {
        return gradeBookId;
    }

    public void setGradeBookId(int gradeBookId) {
        this.gradeBookId = gradeBookId;
    }

    public int getGradeItemId() {
        return gradeItemId;
    }

    public void setGradeItemId(int gradeItemId) {
        this.gradeItemId = gradeItemId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "GradeBooks [comboText=" + comboText + ", gradeBookId="
                + gradeBookId + ", gradeItemId=" + gradeItemId + ", studentId="
                + studentId + ", name=" + name + ", grade=" + grade + "]";
    }
}
