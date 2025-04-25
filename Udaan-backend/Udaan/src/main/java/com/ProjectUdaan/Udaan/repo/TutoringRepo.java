package com.ProjectUdaan.Udaan.repo;

import com.ProjectUdaan.Udaan.model.Tutoring;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TutoringRepo extends JpaRepository<Tutoring, Integer> {
    List<Tutoring> findByTutorId(int id);
}
