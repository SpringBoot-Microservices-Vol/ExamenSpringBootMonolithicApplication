package com.sergiu.service;

import com.sergiu.dto.CandidateDTO;
import com.sergiu.entity.Candidate;
import com.sergiu.entity.Category;
import com.sergiu.exception.ResourceNotFoundException;
import com.sergiu.repository.CandidateRepository;
import com.sergiu.repository.CategoryRepository;
import com.sergiu.transformer.CandidatesTransformer;
import com.sergiu.util.AdmissionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidatesServiceImpl implements CandidatesService {

    private CandidateRepository candidateRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    public void setCandidateRepository(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    @Autowired

    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Autowired
    CandidatesTransformer transformer;

    @Override
    public List<CandidateDTO> getAllCandidates() {
        return transformer.toDTO(candidateRepository.findAll());
    }

    @Override
    public void createCandidate(CandidateDTO candidateDTO) {
        Candidate candidate = new Candidate();
        candidate.setCnp(candidateDTO.getCnp());
        candidate.setFirstName(candidateDTO.getFirstName());
        candidate.setLastName(candidateDTO.getLastName());
        candidate.setHighSchool(candidateDTO.getHighSchool());
        candidate.setBacGrade(candidateDTO.getBacGrade());
        candidate.setBacBestGrade(candidateDTO.getBacBestGrade());
        candidate.setStatusExam(candidateDTO.getStatusExam());

        if (candidateDTO.getCategoryName() != null) {
            Category category = categoryRepository.findByName(candidateDTO.getCategoryName());
            candidate.setCategory(category);
        }
        candidateRepository.save(candidate);
    }

    @Override
    public CandidateDTO getCandidateByCnp(Long cnp) {
        return transformer.toDTO(candidateRepository.findByCnp(cnp)
                .orElseThrow(() -> new ResourceNotFoundException("Candidate", "cnp", cnp)));
    }

    @Override
    public CandidateDTO updateCandidate(Long cnp, CandidateDTO candidateDTO) {
        Candidate candidate = candidateRepository.findByCnp(cnp)
                .orElseThrow(() -> new ResourceNotFoundException("Candidate", "cnp", cnp));

        candidate.setFirstName(candidateDTO.getFirstName());
        candidate.setLastName(candidateDTO.getLastName());
        candidate.setHighSchool(candidateDTO.getHighSchool());
        candidate.setBacGrade(candidateDTO.getBacGrade());
        candidate.setBacBestGrade(candidateDTO.getBacBestGrade());
        candidate.setStatusExam(candidateDTO.getStatusExam());

        if (candidate.getCategory() != null && !candidate.getCategory().getName().equals(candidateDTO.getCategoryName())) {
            Category category = categoryRepository.findByName(candidateDTO.getCategoryName());
            candidate.setCategory(category);
        }
        return transformer.toDTO(candidateRepository.save(candidate));
    }

    @Override
    public void deleteCandidate(Long cnp) {
        candidateRepository.deleteById(cnp);
    }

    @Override
    public Integer totalCandidates() {
        return candidateRepository.findAllByCategory_AdmissionTypeNot(AdmissionType.OLIMPIC).size();
    }
}
