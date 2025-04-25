package com.ProjectUdaan.Udaan.model;

import jakarta.persistence.Lob;

import java.util.Date;
import java.util.List;

public class TutorWrapper {

    private String  location ;
     private String gender;
    private String name;
     private String Education;
    private String imageName;
    private String imageType;
    private  byte[] imageData;
    private String address;
    private long phoneNumber;
    private String email;
    private int tutorId;
    private List<Tutoring> tutorings ;


    public TutorWrapper() {
    }

    public List<Tutoring> getTutorings() {
        return tutorings;
    }

    public TutorWrapper(String location, String gender, String name, String education, String imageName, String imageType, byte[] imageData, String address, long phoneNumber, String email, int tutorId, List<Tutoring> tutorings) {
        this.location = location;
        this.gender = gender;
        this.name = name;
        Education = education;
        this.imageName = imageName;
        this.imageType = imageType;
        this.imageData = imageData;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.tutorId = tutorId;
        this.tutorings = tutorings;

    }

    public void setTutorings(List<Tutoring> tutorings) {
        this.tutorings = tutorings;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEducation() {
        return Education;
    }

    public void setEducation(String education) {
        Education = education;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTutorId() {
        return tutorId;
    }

    public void setTutorId(int tutorId) {
        this.tutorId = tutorId;
    }




}
