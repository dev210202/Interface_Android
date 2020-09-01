package org.sejonguniv.if_2020.model;

public class People {

    String state;
    String generation;
    String department;
    String studentID;
    String name;
    String phoneNumber;
    String firstDues;
    String secondDues;
    String openingMeeting;
    String finalMeeting;

    public People(String state, String generation, String department, String studentID, String name, String phoneNumber, String firstDues, String secondDues, String openingMeeting, String finalMeeting) {
        this.state = state;
        this.generation = generation;
        this.department = department;
        this.studentID = studentID;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.firstDues = firstDues;
        this.secondDues = secondDues;
        this.openingMeeting = openingMeeting;
        this.finalMeeting = finalMeeting;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getFirstDues() {
        return firstDues;
    }

    public void setFirstDues(String firstDues) {
        this.firstDues = firstDues;
    }

    public String getSecondDues() {
        return secondDues;
    }

    public void setSecondDues(String secondDues) {
        this.secondDues = secondDues;
    }

    public String getOpeningMeeting() {
        return openingMeeting;
    }

    public void setOpeningMeeting(String openingMeeting) {
        this.openingMeeting = openingMeeting;
    }

    public String getFinalMeeting() {
        return finalMeeting;
    }

    public void setFinalMeeting(String finalMeeting) {
        this.finalMeeting = finalMeeting;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int itemSize(){
        return 10;
    }

    public String getData(int i){


        switch (i){
            case 0 :{
                return state;
            }
            case 1:{
                return generation;
            }
            case 2:{
                return department;
            }
            case 3:{
                return studentID;
            }
            case 4:{
                return name;
            }
            case 5:{
                return phoneNumber;
            }
            case 6:{
                return firstDues;
            }
            case 7:{
                return secondDues;
            }
            case 8:{
                return openingMeeting;
            }
            case 9:{
                return finalMeeting;
            }
            default:{
                return "X";
            }
        }
    }
}
