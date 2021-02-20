package com.javinezpaul.gradeportalschool;

public class Grades {
    private String gradeid, subjectid, subjectcode, subjecttitle, grade, date, note;

    public Grades(String gradeid, String subjectid, String subjectcode, String subjecttitle, String grade, String date, String note) {
        this.gradeid = gradeid;
        this.subjectid = subjectid;
        this.subjectcode = subjectcode;
        this.subjecttitle = subjecttitle;
        this.grade = grade;
        this.date = date;
        this.note = note;
    }

    public String getGradeid() {
        return gradeid;
    }

    public void setGradeid(String gradeid) {
        this.gradeid = gradeid;
    }

    public String getSubjectid() {
        return subjectid;
    }

    public void setSubjectid(String subjectid) {
        this.subjectid = subjectid;
    }

    public String getSubjectcode() {
        return subjectcode;
    }

    public void setSubjectcode(String subjectcode) {
        this.subjectcode = subjectcode;
    }

    public String getSubjecttitle() {
        return subjecttitle;
    }

    public void setSubjecttitle(String subjecttitle) {
        this.subjecttitle = subjecttitle;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Grades{" +
                "gradeid='" + gradeid + '\'' +
                ", subjectid='" + subjectid + '\'' +
                ", subjectcode='" + subjectcode + '\'' +
                ", subjecttitle='" + subjecttitle + '\'' +
                ", grade='" + grade + '\'' +
                ", date='" + date + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
