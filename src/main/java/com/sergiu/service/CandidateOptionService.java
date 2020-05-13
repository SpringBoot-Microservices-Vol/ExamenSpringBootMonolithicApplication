package com.sergiu.service;

import com.sergiu.dto.CandidateOptionDTO;
import com.sergiu.util.AdmissionOption;

import java.util.List;

public interface CandidateOptionService {
    List<CandidateOptionDTO> getAllCandidatesOptions();

    void add(CandidateOptionDTO candidateOptionDTO);

    CandidateOptionDTO updateCandidateOption(Long cnp, CandidateOptionDTO candidateOptionDTO);

    void deleteCandidateOption(Long cnp, AdmissionOption admissionOption);
}
