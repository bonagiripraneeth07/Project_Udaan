package com.udaan.udaanapplication.model;


import java.util.List;

public class TutorWrapper {

    private String  location ;
    private String gender;
    private String name;
    private String education;
    private String imageName;
    private String imageType;
    private  String imageData;
    private String address;
    private long phoneNumber;
    private String email;
    private int tutorId;
    private List<Tutoring> tutorings ;
//    private  String preferredTime;
//    private Date postedDate;

    public TutorWrapper() {
    }

    public List<Tutoring> getTutorings() {
        return tutorings;
    }

    public TutorWrapper(String location, String gender, String name, String education, String imageName, String imageType,String imageData, String address, long phoneNumber, String email, int tutorId, List<Tutoring> tutorings) {
        this.location = location;
        this.gender = gender;
        this.name = name;
        this.education = education;
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
        return education;
    }

    public void setEducation(String education) {
        education = education;
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

    public String getImageData() {
        return imageData;
    }

    public void setImageData(String imageData) {
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

