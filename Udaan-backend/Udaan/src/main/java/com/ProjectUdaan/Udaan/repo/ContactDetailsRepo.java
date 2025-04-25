package com.ProjectUdaan.Udaan.repo;

import com.ProjectUdaan.Udaan.model.ContactDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactDetailsRepo extends JpaRepository<ContactDetails,Integer> {
}
