package com.dw.jdbcapp.model;

public class MileGrade {
    private String gradeName;
    private int lowerMileage;
    private int upperMileage;

    public MileGrade() {
    }

    public MileGrade(String grade, int minMileage, int maxMileage) {
        this.gradeName = grade;
        this.lowerMileage = minMileage;
        this.upperMileage = maxMileage;
    }

    public String getGrade() {
        return gradeName;
    }

    public void setGrade(String grade) {
        this.gradeName = grade;
    }

    public int getLowerMileage() {
        return lowerMileage;
    }

    public void setLowerMileage(int lowerMileage) {
        this.lowerMileage = lowerMileage;
    }

    public int getUpperMileage() {
        return upperMileage;
    }

    public void setUpperMileage(int upperMileage) {
        this.upperMileage = upperMileage;
    }

    @Override
    public String toString() {
        return gradeName + ", " + lowerMileage + ", " + upperMileage;
    }
}
