package com.javinezpaul.gradeportalschool;

public class Program {
    private int id;
    private String code;
    private String name;
    private String major;
    private String collegeid;
    private String year1;
    private String year2;

    public Program(int id, String code, String name, String major, String collegeid, String year1, String year2) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.major = major;
        this.collegeid = collegeid;
        this.year1 = year1;
        this.year2 = year2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getCollegeid() {
        return collegeid;
    }

    public void setCollegeid(String collegeid) {
        this.collegeid = collegeid;
    }

    public String getYear1() {
        return year1;
    }

    public void setYear1(String year1) {
        this.year1 = year1;
    }

    public String getYear2() {
        return year2;
    }

    public void setYear2(String year2) {
        this.year2 = year2;
    }

    @Override
    public String toString() {
        return "Program{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", major='" + major + '\'' +
                ", collegeid='" + collegeid + '\'' +
                ", year1='" + year1 + '\'' +
                ", year2='" + year2 + '\'' +
                '}';
    }
}

