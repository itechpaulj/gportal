package com.javinezpaul.gradeportalschool;

public class TeachersCode {
    private String teacherscodeid, teacherscode,
            collegeid, programid, sectionid, subjectid, ayid, date,
            collegecode, collegename,
            programcode, programname,
            sectioncode,
            subjectcode, subjecttitle,
            ayear1, ayear2, ayearlevel, aysem;

    public TeachersCode(String teacherscodeid, String teacherscode, String collegeid, String programid, String sectionid, String subjectid, String ayid, String date, String collegecode, String collegename, String programcode, String programname, String sectioncode, String subjectcode, String subjecttitle, String ayear1, String ayear2, String ayearlevel, String aysem) {
        this.teacherscodeid = teacherscodeid;
        this.teacherscode = teacherscode;
        this.collegeid = collegeid;
        this.programid = programid;
        this.sectionid = sectionid;
        this.subjectid = subjectid;
        this.ayid = ayid;
        this.date = date;
        this.collegecode = collegecode;
        this.collegename = collegename;
        this.programcode = programcode;
        this.programname = programname;
        this.sectioncode = sectioncode;
        this.subjectcode = subjectcode;
        this.subjecttitle = subjecttitle;
        this.ayear1 = ayear1;
        this.ayear2 = ayear2;
        this.ayearlevel = ayearlevel;
        this.aysem = aysem;
    }

    public String getTeacherscodeid() {
        return teacherscodeid;
    }

    public void setTeacherscodeid(String teacherscodeid) {
        this.teacherscodeid = teacherscodeid;
    }

    public String getTeacherscode() {
        return teacherscode;
    }

    public void setTeacherscode(String teacherscode) {
        this.teacherscode = teacherscode;
    }

    public String getCollegeid() {
        return collegeid;
    }

    public void setCollegeid(String collegeid) {
        this.collegeid = collegeid;
    }

    public String getProgramid() {
        return programid;
    }

    public void setProgramid(String programid) {
        this.programid = programid;
    }

    public String getSectionid() {
        return sectionid;
    }

    public void setSectionid(String sectionid) {
        this.sectionid = sectionid;
    }

    public String getSubjectid() {
        return subjectid;
    }

    public void setSubjectid(String subjectid) {
        this.subjectid = subjectid;
    }

    public String getAyid() {
        return ayid;
    }

    public void setAyid(String ayid) {
        this.ayid = ayid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCollegecode() {
        return collegecode;
    }

    public void setCollegecode(String collegecode) {
        this.collegecode = collegecode;
    }

    public String getCollegename() {
        return collegename;
    }

    public void setCollegename(String collegename) {
        this.collegename = collegename;
    }

    public String getProgramcode() {
        return programcode;
    }

    public void setProgramcode(String programcode) {
        this.programcode = programcode;
    }

    public String getProgramname() {
        return programname;
    }

    public void setProgramname(String programname) {
        this.programname = programname;
    }

    public String getSectioncode() {
        return sectioncode;
    }

    public void setSectioncode(String sectioncode) {
        this.sectioncode = sectioncode;
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

    public String getAyear1() {
        return ayear1;
    }

    public void setAyear1(String ayear1) {
        this.ayear1 = ayear1;
    }

    public String getAyear2() {
        return ayear2;
    }

    public void setAyear2(String ayear2) {
        this.ayear2 = ayear2;
    }

    public String getAyearlevel() {
        return ayearlevel;
    }

    public void setAyearlevel(String ayearlevel) {
        this.ayearlevel = ayearlevel;
    }

    public String getAysem() {
        return aysem;
    }

    public void setAysem(String aysem) {
        this.aysem = aysem;
    }

    @Override
    public String toString() {
        return "TeachersCode{" +
                "teacherscodeid='" + teacherscodeid + '\'' +
                ", teacherscode='" + teacherscode + '\'' +
                ", collegeid='" + collegeid + '\'' +
                ", programid='" + programid + '\'' +
                ", sectionid='" + sectionid + '\'' +
                ", subjectid='" + subjectid + '\'' +
                ", ayid='" + ayid + '\'' +
                ", date='" + date + '\'' +
                ", collegecode='" + collegecode + '\'' +
                ", collegename='" + collegename + '\'' +
                ", programcode='" + programcode + '\'' +
                ", programname='" + programname + '\'' +
                ", sectioncode='" + sectioncode + '\'' +
                ", subjectcode='" + subjectcode + '\'' +
                ", subjecttitle='" + subjecttitle + '\'' +
                ", ayear1='" + ayear1 + '\'' +
                ", ayear2='" + ayear2 + '\'' +
                ", ayearlevel='" + ayearlevel + '\'' +
                ", aysem='" + aysem + '\'' +
                '}';
    }
}
