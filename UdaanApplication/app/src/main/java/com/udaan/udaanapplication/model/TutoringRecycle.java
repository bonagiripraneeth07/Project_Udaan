package com.udaan.udaanapplication.model;

import java.util.List;

public class TutoringRecycle {
   private List<String> subject ;
    private  String preferredTime;
    private String preferredDate;
    private int id ;

    public TutoringRecycle(List<String> subject, String preferredTime, String preferredDate, int id) {
        this.subject = subject;
        this.preferredTime = preferredTime;
        this.preferredDate = preferredDate;
        this.id = id;
    }

    public List<String> getSubject() {
        return subject;
    }

    public void setSubject(List<String> subject) {
        this.subject = subject;
    }

    public String getPreferredTime() {
        return preferredTime;
    }

    public void setPreferredTime(String preferredTime) {
        this.preferredTime = preferredTime;
    }

    public String getPreferredDate() {
        return preferredDate;
    }

    public void setPreferredDate(String preferredDate) {
        this.preferredDate = preferredDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TutoringRecycle() {
    }
}
