package org.sejonguniv.if_2020.model;

import java.io.Serializable;

public class RegistPasskey implements Serializable {
    String passkey;
    String startTime;
    String endTime;
    String lat;
    String lon;

    public RegistPasskey(String passkey, String startTime, String endTime, String lat, String lon) {
        this.passkey = passkey;
        this.startTime = startTime;
        this.endTime = endTime;
        this.lat = lat;
        this.lon = lon;
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

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }
}
