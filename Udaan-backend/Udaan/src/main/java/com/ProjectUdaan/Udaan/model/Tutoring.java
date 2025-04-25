package com.ProjectUdaan.Udaan.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Data
@CrossOrigin
public class Tutoring {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
   private int tutorId;
    @ElementCollection

    private List<String> subject ;
    private  String preferredTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
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
