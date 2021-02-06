package org.sejonguniv.if_2020.model;

import java.io.Serializable;

public class CalendarData implements Serializable {
    int id;
    String date;
    String title;
    String content;

    public CalendarData(String date, String title, String content) {
        this.date = date;
        this.title = title;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
