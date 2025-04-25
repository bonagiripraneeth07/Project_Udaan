package com.ProjectUdaan.Udaan.controller;

import com.ProjectUdaan.Udaan.model.Tutor;
import com.ProjectUdaan.Udaan.model.TutorLogin;
import com.ProjectUdaan.Udaan.model.TutorWrapper;
import com.ProjectUdaan.Udaan.model.Tutoring;
import com.ProjectUdaan.Udaan.service.TutorService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin
@RequestMapping("tutor")
@RestController
public class TutorController {
    @Autowired
    private  TutorService tutorService;
    @GetMapping("/hello")
    public String hello(){
        return "hello from controller";
    }
    @PostMapping("/register")
    public Tutor registerTutor(@RequestPart Tutor tutor, @RequestPart MultipartFile imageFile) throws IOException {
        return tutorService.registerTutor(tutor,imageFile);
    }

    @PostMapping("/login")
    public TutorWrapper c(@RequestBody TutorLogin tutorLogin) {
       return tutorService.tutorLogin(tutorLogin);
    }

    @PostMapping("/tutoring")
    public String addTutoring(@RequestBody Tutoring tutoring){
        return tutorService.addTutoring(tutoring);
    }

    @DeleteMapping("/deletetutoring/{id}")
    public String deleteTutoring(@PathVariable int id ){
        return tutorService.deleteTutoring(id);
    }

    @GetMapping("/gettutors/{location}/{pincode}")
    public List<TutorWrapper> getTutors(@PathVariable String location , @PathVariable int pincode){
        return tutorService.getTutors(location,pincode);
    }
    @GetMapping("/gettutor/{tutorId}")
    public TutorWrapper getTutor(@PathVariable int tutorId){
        return tutorService.getTutor(tutorId) ;
    }
}
