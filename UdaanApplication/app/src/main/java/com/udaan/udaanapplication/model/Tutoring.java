package com.udaan.udaanapplication.model;



import java.time.LocalDate;
import java.util.Date;
import java.util.List;


public class Tutoring {

    private int id ;
    private int tutorId;


    private List<String> subject ;
    private  String preferredTime;
    private String postedDate;

    public int getTutorId() {
        return tutorId;
    }

    public Tutoring(int id, int tutorId, List<String> subject, String preferredTime, String postedDate) {
        this.id = id;
        this.tutorId = tutorId;
        this.subject = subject;
        this.preferredTime = preferredTime;
        this.postedDate = postedDate;
    }

    public void setTutorId(int tutorId) {
        this.tutorId = tutorId;
    }



    public Tutoring() {
    }

    @Override
    public String toString() {
        return "Tutoring{" +
                "id=" + id +
                ", subject=" + subject +
                ", preferredTime='" + preferredTime + '\'' +
                ", postedDate=" + postedDate +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(String postedDate) {
        this.postedDate = postedDate;
    }
}

