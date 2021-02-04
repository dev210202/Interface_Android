package org.sejonguniv.if_2020.model;

import java.io.Serializable;

public class PassKey implements Serializable {

    String passkey;
    String startTime;
    String endTime;


    public PassKey(String passkey, String startTime, String endTime) {
        this.passkey = passkey;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getPasskey() {
        return passkey;
    }

    public void setPasskey(String passkey) {
        this.passkey = passkey;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
