package com.sergiu.controller;

import com.sergiu.dto.AdmissionResultDTO;
import com.sergiu.exception.FrameworkException;
import com.sergiu.model.AllocationModel;
import com.sergiu.service.AllocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class AllocationController {

    private AllocationService allocationService;

    @Autowired
    public void setAllocationService(AllocationService allocationService) {
        this.allocationService = allocationService;
    }

    @GetMapping("admission/{cnp}")
    public AdmissionResultDTO getCandidateResult(@PathVariable(name = "cnp") Long cnp) {

        return allocationService.getCandidateResult(cnp);
    }

    @GetMapping(path = "/allocation")
    public ResponseEntity<?> startAllocation() {
        try {
            allocationService.startAllocateCandidates();
            return ResponseEntity.ok().build();
        } catch (FrameworkException e) {
            return ResponseEntity.notFound().header(e.getMessage()).build();
        }

    }

    @GetMapping(path="allocation/details")
    public AllocationModel allocationDetails(){
        return allocationService.getAllocationDetails();
    }

    @GetMapping(path = "/admission/reject/{cnp}")
    public ResponseEntity<?> rejectCandidate(@PathVariable(name = "cnp") Long cnp) {
        allocationService.rejectCandidate(cnp);
        return ResponseEntity.ok().build();
    }
}
