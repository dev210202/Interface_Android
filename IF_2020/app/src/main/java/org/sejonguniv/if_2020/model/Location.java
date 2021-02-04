package org.sejonguniv.if_2020.model;

public class Location {
    String lat;
    String lon;

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

    public Location(String lat, String lon) {
        this.lat = lat;
        this.lon = lon;
    }
}
