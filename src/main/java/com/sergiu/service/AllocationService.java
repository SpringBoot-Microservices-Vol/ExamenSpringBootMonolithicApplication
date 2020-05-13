package com.sergiu.service;

import com.sergiu.dto.AdmissionResultDTO;
import com.sergiu.entity.AdmissionResult;
import com.sergiu.model.AllocationModel;

public interface AllocationService {
    void startAllocateCandidates();

    void rejectCandidate(Long cnp);

    void updateAdmissionResult(AdmissionResult admissionResult);

    AdmissionResultDTO getCandidateResult(Long cnp);

    AllocationModel getAllocationDetails();
}
