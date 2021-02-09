package org.sejonguniv.if_2020.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.Serializable;
import java.lang.reflect.Constructor;

public class People implements Serializable {

    String state;
    String generation;
    String name;
    String department;
    String studentId;
    String phone;
    String contact;
    ManageStatus manageStatus;

    public People(String state, String generation, String name, String department, String studentId, String phone, String contact, ManageStatus manageStatus) {
        this.state = state;
        this.generation = generation;
        this.name = name;
        this.department = department;
        this.studentId = studentId;
        this.phone = phone;
        this.contact = contact;
        this.manageStatus = manageStatus;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studenId) {
        this.studentId = studenId;
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

    public String getPhone() {
        return phone;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phone = phone;
    }

    public ManageStatus getManageStatus() {
        return manageStatus;
    }

    public void setManageStatus(ManageStatus manageStatus) {
        this.manageStatus = manageStatus;
    }


    public int itemSize() {
//        Class peopleClass = People.class;
//        Constructor[] constructors = peopleClass.getDeclaredConstructors();
//        int parametersSize = 0;
//        for (Constructor constructor : constructors) {
//            parametersSize = constructor.getParameterCount();
//
//        }
        //return parametersSize;
        return 11;
    }

    public void setValue(int input, String value) {
        switch (input) {
            case 0: {
                this.state = value;
                break;
            }
            case 1: {
                this.generation = value;
                break;
            }
            case 2: {
                this.name = value;
                break;
            }
            case 3: {
                this.department = value;
                break;
            }
            case 4: {
                this.studentId = value;
                break;
            }
            case 5: {
                this.phone = value;
                break;
            }
            case 6: {
                this.contact = value;
                break;
            }
            case 7: {
                this.manageStatus.firstDues = value;
                break;
            }
            case 8: {
                this.manageStatus.secondDues = value;
                break;
            }
            case 9: {

                this.manageStatus.openingMeeting = value;
                break;
            }
            case 10: {
                this.manageStatus.finalMeeting = value;
                break;
            }
            default: {
                break;
            }
        }
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
            case 3: {
                return department;
            }
            case 4: {
                return studentId;
            }
            case 5: {
                return phone;
            }
            case 6: {
                return contact;
            }
            case 7: {
                return manageStatus.firstDues;
            }
            case 8: {
                return manageStatus.secondDues;
            }
            case 9: {
                return manageStatus.openingMeeting;
            }
            case 10: {
                return manageStatus.finalMeeting;
            }
            default: {
                break;
            }
        }
        return "ERROR";
    }
}
