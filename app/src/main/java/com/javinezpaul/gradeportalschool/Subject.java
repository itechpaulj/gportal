package com.javinezpaul.gradeportalschool;

public class Subject {
    private String id, code, title, programid, ayid;

    public Subject(String id, String code, String title, String programid, String ayid) {
        this.id = id;
        this.code = code;
        this.title = title;
        this.programid = programid;
        this.ayid = ayid;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProgramid() {
        return programid;
    }

    public void setProgramid(String programid) {
        this.programid = programid;
    }

    public String getAyid() {
        return ayid;
    }

    public void setAyid(String ayid) {
        this.ayid = ayid;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", title='" + title + '\'' +
                ", programid='" + programid + '\'' +
                ", ayid='" + ayid + '\'' +
                '}';
    }
}
