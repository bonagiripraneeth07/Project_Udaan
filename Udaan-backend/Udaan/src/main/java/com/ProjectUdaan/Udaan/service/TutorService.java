package com.ProjectUdaan.Udaan.service;

import com.ProjectUdaan.Udaan.model.Tutor;
import com.ProjectUdaan.Udaan.model.TutorLogin;
import com.ProjectUdaan.Udaan.model.TutorWrapper;
import com.ProjectUdaan.Udaan.model.Tutoring;
import com.ProjectUdaan.Udaan.repo.ContactDetailsRepo;
import com.ProjectUdaan.Udaan.repo.TutorRepo;
import com.ProjectUdaan.Udaan.repo.TutoringRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.List;

@Service
public class TutorService {
    @Autowired
    private AuthenticationManager authenticationManager;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    @Autowired
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Autowired
   private TutorRepo tutorRepo;
    @Autowired
    private ContactDetailsRepo contactDetailsRepo;
    @Autowired
    private TutoringRepo tutoringRepo;
        public Tutor registerTutor(Tutor tutor, MultipartFile imageFile) throws IOException {

            Tutor userNameExists = tutorRepo.findByName(tutor.getName());

            if (userNameExists != null && userNameExists.getName() != null) {
                throw new RuntimeException("Username already taken.");
             }else{
                tutor.setImageName(imageFile.getOriginalFilename());
                tutor.setImageType(imageFile.getContentType());
                tutor.setImageData(imageFile.getBytes());
                contactDetailsRepo.save(tutor.getContactDetails());
                System.out.println("from 37 service" +imageFile.getName());
                tutor.setPassword(encoder.encode(tutor.getPassword()));
                tutorRepo.save(tutor);
                return tutor;
            }


        }

    public TutorWrapper tutorLogin(TutorLogin tutorLogin) {

            Tutor foundTutor = tutorRepo.findByName(tutorLogin.getName());
        System.out.println(tutorLogin.getName());
        System.out.println(tutorLogin.getPassword());
            if(foundTutor==null){
                return new TutorWrapper();
            }
            if(passwordEncoder().matches(CharBuffer.wrap(tutorLogin.getPassword()), foundTutor.getPassword())){
                System.out.println("password is correct " + foundTutor.getPassword());
                List<Tutoring> foundTutorings = new ArrayList<>();
                foundTutorings=tutoringRepo.findByTutorId(foundTutor.getId());

                TutorWrapper tutorWrapper = new TutorWrapper();
                tutorWrapper.setName(foundTutor.getName());
                tutorWrapper.setTutorId(foundTutor.getId());
                tutorWrapper.setTutorings(foundTutorings);
                tutorWrapper.setLocation(foundTutor.getLocation());
                tutorWrapper.setGender(foundTutor.getGender());
                tutorWrapper.setEducation(foundTutor.getEducation());
                tutorWrapper.setAddress(foundTutor.getContactDetails().getAddress());
                tutorWrapper.setPhoneNumber(foundTutor.getContactDetails().getPhoneNumber());
                tutorWrapper.setEmail(foundTutor.getContactDetails().getEmail());
                tutorWrapper.setImageData(foundTutor.getImageData());
                tutorWrapper.setImageName(foundTutor.getImageName());
                tutorWrapper.setImageType(foundTutor.getImageType());
              return  tutorWrapper;
            }else{
                System.out.println("not right password");
                System.out.println(tutorLogin.getPassword());
               return new TutorWrapper();

            }
    }

    public String addTutoring(Tutoring tutoring) {
            System.out.println(tutoring.getSubject() +" from subject5s");
            tutoringRepo.save(tutoring);
            return "New Tutoring Added Successfully" ;

    }

    public String  deleteTutoring(Integer id) {
            Tutoring foundTutoring = tutoringRepo.findById(id).orElse(null);
            if (foundTutoring!=null){
                tutoringRepo.deleteById(id);
                return  "Deleted successful";
            }else{
              return  "Somthing went wrong";
            }
    }

    public  List<TutorWrapper>getTutors(String location, int pincode) {
        List<Tutor> foundTutors = tutorRepo.findByPincode(pincode);
        List<TutorWrapper> tutorWrappers = new ArrayList<>();

        List<Tutoring> foundTutorings = new ArrayList<>();
        for( Tutor t :foundTutors ){
            foundTutorings=tutoringRepo.findByTutorId(t.getId());
            System.out.println(tutoringRepo.findByTutorId(15));
        }
        System.out.println(foundTutorings);
//        = tutoringRepo.findByTutorId(foundTutors.);
        for( Tutor t :foundTutors){
            List<Tutoring> tutorings = tutoringRepo.findByTutorId(t.getId());
            TutorWrapper tutorWrapper = new TutorWrapper();
            tutorWrapper.setName(t.getName());
            tutorWrapper.setTutorId(t.getId());
            tutorWrapper.setTutorings(tutorings);
            tutorWrapper.setLocation(t.getLocation());
            tutorWrapper.setGender(t.getGender());
            tutorWrapper.setEducation(t.getEducation());
            tutorWrapper.setAddress(t.getContactDetails().getAddress());
            tutorWrapper.setPhoneNumber(t.getContactDetails().getPhoneNumber());
            tutorWrapper.setEmail(t.getContactDetails().getEmail());
            tutorWrapper.setImageData(t.getImageData());
            tutorWrapper.setImageName(t.getImageName());
            tutorWrapper.setImageType(t.getImageType());
            tutorWrappers.add(tutorWrapper);
        }
        if (!foundTutors.isEmpty()){
            return tutorWrappers;
        }else{
            return new ArrayList<>() ;
        }
    }

    public TutorWrapper getTutor(int tutorId) {
            Tutor foundTutor = tutorRepo.findById(tutorId).orElse(null);


        if (foundTutor!=null){
            List<Tutoring> foundTutorings = new ArrayList<>();
            foundTutorings=tutoringRepo.findByTutorId(tutorId);
            System.out.println(tutoringRepo.findByTutorId(19));

            TutorWrapper tutorWrapper = new TutorWrapper();
            tutorWrapper.setName(foundTutor.getName());
            tutorWrapper.setTutorId(foundTutor.getId());
            tutorWrapper.setTutorings(foundTutorings);
            tutorWrapper.setLocation(foundTutor.getLocation());
            tutorWrapper.setGender(foundTutor.getGender());
            tutorWrapper.setEducation(foundTutor.getEducation());
            tutorWrapper.setAddress(foundTutor.getContactDetails().getAddress());
            tutorWrapper.setPhoneNumber(foundTutor.getContactDetails().getPhoneNumber());
            tutorWrapper.setEmail(foundTutor.getContactDetails().getEmail());
            tutorWrapper.setImageData(foundTutor.getImageData());
            tutorWrapper.setImageName(foundTutor.getImageName());
            tutorWrapper.setImageType(foundTutor.getImageType());
            return tutorWrapper;
        }else{
            return new TutorWrapper();
        }
    }
}
