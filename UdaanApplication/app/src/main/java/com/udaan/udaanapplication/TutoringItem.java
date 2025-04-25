package com.udaan.udaanapplication;

import java.util.List;

public class TutoringItem {

    private String tutorName;
    private int tutorId;
    private String preferredTime;
    private String postedDate;
    private List<String> subjects;

    public TutoringItem() {
    }

    public String getTutorName() {
        return tutorName;
    }

    public void setTutorName(String tutorName) {
        this.tutorName = tutorName;
    }

    public int getTutorId() {
        return tutorId;
    }

    public void setTutorId(int tutorId) {
        this.tutorId = tutorId;
    }

    public String getPreferredTime() {
        return preferredTime;
    }

    public void setPreferredTime(String preferredTime) {
        this.preferredTime = preferredTime;
    }

    public String getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(String postedDate) {
        this.postedDate = postedDate;
    }

    public List<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }

    public TutoringItem(String tutorName, int tutorId, String preferredTime, String postedDate, List<String> subjects) {
        this.tutorName = tutorName;
        this.tutorId = tutorId;
        this.preferredTime = preferredTime;
        this.postedDate = postedDate;
        this.subjects = subjects;
    }
}
