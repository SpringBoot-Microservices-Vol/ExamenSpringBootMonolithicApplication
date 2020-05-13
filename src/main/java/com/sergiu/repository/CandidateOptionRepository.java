package com.sergiu.repository;

import com.sergiu.entity.CandidateOption;
import com.sergiu.entity.CandidateOptionId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CandidateOptionRepository extends JpaRepository<CandidateOption, CandidateOptionId> {

    List<CandidateOption> findAllByCandidateOptionId_CnpOrderByPriority(Long cnp);
}
