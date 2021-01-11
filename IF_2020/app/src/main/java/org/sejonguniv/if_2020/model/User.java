package org.sejonguniv.if_2020.model;

public class User {
    String state;
    String generation;
    String name;
    String department;
    String contact;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getGeneration() {
        return generation;
    }

    public void setGeneration(String generation) {
        this.generation = generation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
    public int itemSize(){
        return 5;
    }
    public String getValue(int input) {
        switch (input) {
            case 0: {
                return state;
            }
            case 1: {
                return generation;
            }
            case 2: {
                return name;
            }
            case 3:{
                return department;
            }
            case 4:{
                return contact;
            }
            default:{
                break;
            }
        }
        return "ERROR";
    }
}
