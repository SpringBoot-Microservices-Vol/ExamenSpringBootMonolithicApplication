package com.sergiu.repository;

import com.sergiu.entity.Candidate;
import com.sergiu.util.AdmissionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {

    List<Candidate> findAllByCategory_Id(Integer categoryId);

    Optional<Candidate> findByCnp(Long cnp);

    void deleteByCnp(Long cnp);

    List<Candidate> findAllByHall_IdOrderByCategoryAscLastNameAscFirstNameAsc(Integer hallId);

    List<Candidate> findAllByCategory_AdmissionTypeNot(AdmissionType admissionType);

    List<Candidate> findAllByCategory_AdmissionTypeOrderByLastNameAscFirstNameAsc(AdmissionType admissionType);
}
