package com.javinezpaul.gradeportalschool;

public class StudentsGrade {
    private String gradeid, grade, date, note, teachescode, studentname, userid, cardid, subjectid, subjecttitle;

    public StudentsGrade(String gradeid, String grade, String date, String note, String teachescode, String studentname, String userid, String cardid, String subjectid, String subjecttitle) {
        this.gradeid = gradeid;
        this.grade = grade;
        this.date = date;
        this.note = note;
        this.teachescode = teachescode;
        this.studentname = studentname;
        this.userid = userid;
        this.cardid = cardid;
        this.subjectid = subjectid;
        this.subjecttitle = subjecttitle;
    }

    public String getGradeid() {
        return gradeid;
    }

    public void setGradeid(String gradeid) {
        this.gradeid = gradeid;
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

    public String getTeachescode() {
        return teachescode;
    }

    public void setTeachescode(String teachescode) {
        this.teachescode = teachescode;
    }

    public String getStudentname() {
        return studentname;
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getCardid() {
        return cardid;
    }

    public void setCardid(String cardid) {
        this.cardid = cardid;
    }

    public String getSubjectid() {
        return subjectid;
    }

    public void setSubjectid(String subjectid) {
        this.subjectid = subjectid;
    }

    public String getSubjecttitle() {
        return subjecttitle;
    }

    public void setSubjecttitle(String subjecttitle) {
        this.subjecttitle = subjecttitle;
    }

    @Override
    public String toString() {
        return "StudentsGrade{" +
                "gradeid='" + gradeid + '\'' +
                ", grade='" + grade + '\'' +
                ", date='" + date + '\'' +
                ", note='" + note + '\'' +
                ", teachescode='" + teachescode + '\'' +
                ", studentname='" + studentname + '\'' +
                ", userid='" + userid + '\'' +
                ", cardid='" + cardid + '\'' +
                ", subjectid='" + subjectid + '\'' +
                ", subjecttitle='" + subjecttitle + '\'' +
                '}';
    }
}
