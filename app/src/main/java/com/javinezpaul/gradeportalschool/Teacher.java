package com.javinezpaul.gradeportalschool;

public class Teacher {
    private String id, lname, fname, mname, collegeid, image;

    public Teacher(String id, String lname, String fname, String mname, String collegeid, String image) {
        this.id = id;
        this.lname = lname;
        this.fname = fname;
        this.mname = mname;
        this.collegeid = collegeid;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id='" + id + '\'' +
                ", lname='" + lname + '\'' +
                ", fname='" + fname + '\'' +
                ", mname='" + mname + '\'' +
                ", collegeid='" + collegeid + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
