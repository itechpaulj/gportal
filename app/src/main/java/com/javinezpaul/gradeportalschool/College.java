package com.javinezpaul.gradeportalschool;

public class College {
    private String id;
    private String code;
    private String desc;

    public College(String id, String code, String desc) {
        this.id = id;
        this.code = code;
        this.desc = desc;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "College{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
