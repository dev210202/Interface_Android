package org.sejonguniv.if_2020.model;

public class People {

    String studentId;
    String name;
    String groupNum;
    String contact;

    public People(String studentId, String name, String groupNum, String contact) {

        this.studentId = studentId;
        this.name = name;
        this.groupNum = groupNum;
        this.contact = contact;
    }


    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroupNum() {
        return groupNum;
    }

    public void setGroupNum(String groupNum) {
        this.groupNum = groupNum;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
    public int itemSize(){
        return 4;
    }

    public String getData(int i){


        switch (i){
            case 0 :{
                return studentId;
            }
            case 1:{
                return name;
            }
            case 2:{
                return groupNum;
            }
            case 3:{
                return contact;
            }

            default:{
                return "X";
            }
        }
    }
}
