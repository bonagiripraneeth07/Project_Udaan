package com.ProjectUdaan.Udaan.service;

import com.ProjectUdaan.Udaan.model.Tutor;
import com.ProjectUdaan.Udaan.model.UserPricipal;
import com.ProjectUdaan.Udaan.repo.TutorRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailServices implements UserDetailsService {

    @Autowired
    private TutorRepo tutorRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Tutor checkTutor = tutorRepo.findByName(username);
        if (checkTutor==null) {
            System.out.println("User 404" );
            System.out.println(username );
            throw new UsernameNotFoundException("User 404");
        }
        return new UserPricipal(checkTutor);

    }
}
