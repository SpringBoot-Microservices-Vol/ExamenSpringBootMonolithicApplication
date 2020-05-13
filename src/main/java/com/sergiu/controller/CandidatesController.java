package com.sergiu.controller;

import com.sergiu.dto.CandidateDTO;
import com.sergiu.service.CandidatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
public class CandidatesController {

    private CandidatesService candidatesService;

    @Autowired
    public void setCandidatesService(CandidatesService candidatesService) {
        this.candidatesService = candidatesService;
    }

    @GetMapping("/candidates")
    public List<CandidateDTO> getAllCandidates() {
        return candidatesService.getAllCandidates();
    }

    @PostMapping("/candidates")
    public ResponseEntity<?> createCandidate(@Valid @RequestBody CandidateDTO candidateDTO) {
        candidatesService.createCandidate(candidateDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/candidates/{cnp}")
    public CandidateDTO getCandidateByCnp(@PathVariable(value = "cnp") Long cnp) {
        return candidatesService.getCandidateByCnp(cnp);
    }

    @PutMapping("/candidates/{cnp}")
    public CandidateDTO updateCandidate(@PathVariable(value = "cnp") Long cnp,
                                        @Valid @RequestBody CandidateDTO candidateDTO) {
        return candidatesService.updateCandidate(cnp, candidateDTO);
    }

    @DeleteMapping("/candidates/{cnp}")
    public ResponseEntity<?> deleteCandidate(@PathVariable(value = "cnp") Long cnp) {
        candidatesService.deleteCandidate(cnp);
        return ResponseEntity.ok().build();
    }
}
