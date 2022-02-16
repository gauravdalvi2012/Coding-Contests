package com.example.codingcontests;

public class ContestsModel {

    private String name;
    private String url;
    private String start_time;
    private String end_time;
    private String duration;
    private String in_24_hours;
    private String status;

    public ContestsModel(String name, String url, String start_time, String end_time, String duration, String in_24_hours, String status) {
        this.name = name;
        this.url = url;
        this.start_time = start_time;
        this.end_time = end_time;
        this.duration = duration;
        this.in_24_hours = in_24_hours;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getIn_24_hours() {
        return in_24_hours;
    }

    public void setIn_24_hours(String in_24_hours) {
        this.in_24_hours = in_24_hours;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
