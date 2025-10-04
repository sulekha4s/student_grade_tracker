package com.school.model;

public class Student {

    private int rollNumber;
    private String name;
    private Marks marks;

    public Student(int rollNumber, String name, Marks marks) {
        this.rollNumber = rollNumber;
        this.name = name;
        this.marks = marks;
    }

    public Student(int rollNumber, String name) {
        this.rollNumber = rollNumber;
        this.name = name;
        this.marks = new Marks(-1, -1, -1);
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(int rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Marks getMarks() {
        return marks;
    }

    public void setMarks(Marks marks) {
        this.marks = marks;
    }
}
