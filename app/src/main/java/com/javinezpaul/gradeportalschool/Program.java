package com.javinezpaul.gradeportalschool;

public class Program {
    private String id;
    private String code;
    private String name;
    private String major;
    private String collegeid;

    public Program(String id, String code, String name, String major, String collegeid) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.major = major;
        this.collegeid = collegeid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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


    @Override
    public String toString() {
        return "Program{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", major='" + major + '\'' +
                ", collegeid='" + collegeid + '\'' +
                '}';
    }
}

