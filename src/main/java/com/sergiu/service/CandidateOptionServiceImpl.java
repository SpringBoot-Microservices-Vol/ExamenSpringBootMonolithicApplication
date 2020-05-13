package com.sergiu.service;

import com.sergiu.dto.CandidateOptionDTO;
import com.sergiu.entity.Candidate;
import com.sergiu.entity.CandidateOption;
import com.sergiu.entity.CandidateOptionId;
import com.sergiu.repository.CandidateOptionRepository;
import com.sergiu.transformer.CandidateOptionTransformer;
import com.sergiu.util.AdmissionOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidateOptionServiceImpl implements CandidateOptionService {

    private CandidateOptionRepository candidateOptionRepository;

    private CandidateOptionTransformer candidateOptionTransformer;

    @Autowired
    public void setCandidateOptionRepository(CandidateOptionRepository candidateOptionRepository) {
        this.candidateOptionRepository = candidateOptionRepository;
    }

    @Autowired
    public void setCandidateOptionTransformer(CandidateOptionTransformer candidateOptionTransformer) {
        this.candidateOptionTransformer = candidateOptionTransformer;
    }

    @Override
    public List<CandidateOptionDTO> getAllCandidatesOptions() {
        return candidateOptionTransformer.toDTO(candidateOptionRepository.findAll());
    }

    @Override
    public void add(CandidateOptionDTO candidateOptionDTO) {
        CandidateOption candidateOption = new CandidateOption();
        candidateOption.setCandidateOptionId(new CandidateOptionId(candidateOptionDTO.getCnp(), candidateOptionDTO.getAdmissionOption()));
        candidateOption.setCandidate(new Candidate(candidateOptionDTO.getCnp()));
        candidateOption.setPriority(candidateOptionDTO.getPriority());
        candidateOptionRepository.save(candidateOption);
    }

    @Override
    public CandidateOptionDTO updateCandidateOption(Long cnp, CandidateOptionDTO candidateOptionDTO) {
        CandidateOption candidateOption = candidateOptionRepository.findById(new CandidateOptionId(candidateOptionDTO.getCnp(), candidateOptionDTO.getAdmissionOption())).get();
        candidateOption.setPriority(candidateOptionDTO.getPriority());
        candidateOption.getCandidateOptionId().setAdmissionOption(candidateOptionDTO.getAdmissionOption());
        return candidateOptionTransformer.toDTO(candidateOptionRepository.save(candidateOption));
    }

    @Override
    public void deleteCandidateOption(Long cnp, AdmissionOption admissionOption) {
        candidateOptionRepository.deleteById(new CandidateOptionId(cnp, admissionOption));
    }
}
