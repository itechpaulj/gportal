package com.javinezpaul.gradeportalschool;

public class Section {
    private String id, code, programid;

    public Section(String id, String code, String programid) {
        this.id = id;
        this.code = code;
        this.programid = programid;
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

    public String getProgramid() {
        return programid;
    }

    public void setProgramid(String programid) {
        this.programid = programid;
    }

    @Override
    public String toString() {
        return "Section{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", programid='" + programid + '\'' +
                '}';
    }
}
