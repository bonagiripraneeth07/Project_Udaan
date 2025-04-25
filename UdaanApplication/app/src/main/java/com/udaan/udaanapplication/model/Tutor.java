package com.udaan.udaanapplication.model;



import java.util.Arrays;
import java.util.Date;


public class Tutor {


    private int id ;
    private String  location ;
    private int pincode;
    private String gender;
    private String name;
    private  String password;
    private String education;
//    private String preferredSubject;
//    private String preferredTime;
 //    private Date postedDate;
//    @JoinColumn(name = "tutoring")
//    @OneToOne
//    private Tutoring tutoring;
    private String imageName;
    private String imageType;

    private  String imageData;


    private ContactDetails contactDetails;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getPincode() {
        return pincode;
    }

    public void setPincode(int pincode) {
        this.pincode = pincode;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
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

    public ContactDetails getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(ContactDetails contactDetails) {
        this.contactDetails = contactDetails;
    }
    public Tutor() {
    }


    public Tutor(int id, String location, int pincode, String gender, String name, String password, String education, String preferredSubject, String preferredTime, Date postedDate, String imageName, String imageType, String imageData, ContactDetails contactDetails) {
        this.id = id;
        this.location = location;
        this.pincode = pincode;
        this.gender = gender;
        this.name = name;
        this.password = password;
        this.education = education;

        this.imageName = imageName;
        this.imageType = imageType;
        this.imageData = imageData;
        this.contactDetails = contactDetails;
    }


    @Override
    public String toString() {
        return "Tutor{" +
                "id=" + id +
                ", location='" + location + '\'' +
                ", pincode=" + pincode +
                ", gender='" + gender + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", education='" + education + '\'' +
                ", imageName='" + imageName + '\'' +
                ", imageType='" + imageType + '\'' +
                ", imageData='" + imageData + '\'' +
                ", contactDetails=" + contactDetails +
                '}';
    }
}
