package com.ProjectUdaan.Udaan.repo;

import com.ProjectUdaan.Udaan.model.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TutorRepo extends JpaRepository<Tutor, Integer> {
    Tutor findByName(String username);

    List<Tutor> findByPincode(int pincode);
}
