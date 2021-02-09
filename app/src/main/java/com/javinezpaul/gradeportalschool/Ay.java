package com.javinezpaul.gradeportalschool;

public class Ay {
    private String id, sem, year1, year2, yearlevel;

    public Ay(String id, String sem, String year1, String year2, String yearlevel) {
        this.id = id;
        this.sem = sem;
        this.year1 = year1;
        this.year2 = year2;
        this.yearlevel = yearlevel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSem() {
        return sem;
    }

    public void setSem(String sem) {
        this.sem = sem;
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

    public String getYearlevel() {
        return yearlevel;
    }

    public void setYearlevel(String yearlevel) {
        this.yearlevel = yearlevel;
    }

    @Override
    public String toString() {
        return "Ay{" +
                "id='" + id + '\'' +
                ", sem='" + sem + '\'' +
                ", year1='" + year1 + '\'' +
                ", year2='" + year2 + '\'' +
                ", yearlevel='" + yearlevel + '\'' +
                '}';
    }
}
