package com.sergiu.controller;

import com.sergiu.dto.CandidateOptionDTO;
import com.sergiu.service.CandidateOptionService;
import com.sergiu.util.AdmissionOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
public class CandidateOptionsController {

    private CandidateOptionService candidateOptionService;

    @Autowired
    public void setCandidateOptionService(CandidateOptionService candidateOptionService) {
        this.candidateOptionService = candidateOptionService;
    }

    @GetMapping("/candidate_option")
    public List<CandidateOptionDTO> getAllCandidatesOptions() {
        return candidateOptionService.getAllCandidatesOptions();
    }

    @PostMapping("/candidate_option")
    public ResponseEntity<?> createCandidateOption(@Valid @RequestBody CandidateOptionDTO candidateOptionDTO) {
        candidateOptionService.add(candidateOptionDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/candidate_option/{cnp}")
    public CandidateOptionDTO updateCandidate(@PathVariable(value = "cnp") Long cnp,
                                              @Valid @RequestBody CandidateOptionDTO candidateOptionDTO) {
        return candidateOptionService.updateCandidateOption(cnp, candidateOptionDTO);
    }

    @DeleteMapping("/candidate_option/{cnp}/{admissionOption}")
    public ResponseEntity<?> deleteCandidate(@PathVariable(value = "cnp") Long cnp, @PathVariable(value = "admissionOption") AdmissionOption admissionOption) {
        candidateOptionService.deleteCandidateOption(cnp, admissionOption);
        return ResponseEntity.ok().build();
    }
}
