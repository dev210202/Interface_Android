package org.sejonguniv.if_2020.model;

public class MeetingTime {
    String startTime;
    String endTime;

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

    public MeetingTime(String startTime, String endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
