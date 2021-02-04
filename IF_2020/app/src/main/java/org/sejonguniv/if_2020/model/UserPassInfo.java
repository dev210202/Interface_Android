package org.sejonguniv.if_2020.model;

public class UserPassInfo {
    String name;
    String generation;
    String studentId;
    String dateTime;

    public UserPassInfo(String name, String generation, String studentId, String dateTime) {
        this.name = name;
        this.generation = generation;
        this.studentId = studentId;
        this.dateTime = dateTime;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGeneration() {
        return generation;
    }

    public void setGeneration(String generation) {
        this.generation = generation;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
