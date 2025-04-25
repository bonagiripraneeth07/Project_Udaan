package com.udaan.udaanapplication.model;

import java.util.List;

public class TutorDetailsRecycle {
    List<String> subjects ;
    String tutorName;
    int tutorId;
    String postedDate;
    String preferredTime;

    public TutorDetailsRecycle(List<String> subjects, String tutorName, int tutorId, String postedDate, String preferredTime) {
        this.subjects = subjects;
        this.tutorName = tutorName;
        this.tutorId = tutorId;
        this.postedDate = postedDate;
        this.preferredTime = preferredTime;
    }

    public TutorDetailsRecycle() {
    }

    public List<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
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

    public String getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(String postedDate) {
        this.postedDate = postedDate;
    }

    public String getPreferredTime() {
        return preferredTime;
    }

    public void setPreferredTime(String preferredTime) {
        this.preferredTime = preferredTime;
    }
}
