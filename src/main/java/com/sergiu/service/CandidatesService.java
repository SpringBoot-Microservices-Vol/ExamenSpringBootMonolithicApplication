package com.sergiu.service;

import com.sergiu.dto.CandidateDTO;

import java.util.List;

public interface CandidatesService {
    List<CandidateDTO> getAllCandidates();

    void createCandidate(CandidateDTO candidateDTO);

    CandidateDTO getCandidateByCnp(Long cnp);

    CandidateDTO updateCandidate(Long cnp, CandidateDTO candidateDTO);

    void deleteCandidate(Long cnp);

    Integer totalCandidates();
}
