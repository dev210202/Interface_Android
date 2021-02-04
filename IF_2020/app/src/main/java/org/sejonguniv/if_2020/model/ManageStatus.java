package org.sejonguniv.if_2020.model;

import java.io.Serializable;

public class ManageStatus implements Serializable {

    String firstDues;
    String secondDues;
    String openingMeeting;
    String finalMeeting;

    public ManageStatus(String firstDues, String secondDues, String openingMeeting, String finalMeeting) {
        this.firstDues = firstDues;
        this.secondDues = secondDues;
        this.openingMeeting = openingMeeting;
        this.finalMeeting = finalMeeting;
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
}
