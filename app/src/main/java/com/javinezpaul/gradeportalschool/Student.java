package com.javinezpaul.gradeportalschool;

public class Student {
    private String id, lname, fname, mname, collegeid, programid, sectionid, viewcode, image;

    public Student(String id, String lname, String fname, String mname, String collegeid, String programid, String sectionid, String viewcode, String image) {
        this.id = id;
        this.lname = lname;
        this.fname = fname;
        this.mname = mname;
        this.collegeid = collegeid;
        this.programid = programid;
        this.sectionid = sectionid;
        this.viewcode = viewcode;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
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

    public String getViewcode() {
        return viewcode;
    }

    public void setViewcode(String viewcode) {
        this.viewcode = viewcode;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", lname='" + lname + '\'' +
                ", fname='" + fname + '\'' +
                ", mname='" + mname + '\'' +
                ", collegeid='" + collegeid + '\'' +
                ", programid='" + programid + '\'' +
                ", sectionid='" + sectionid + '\'' +
                ", viewcode='" + viewcode + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
