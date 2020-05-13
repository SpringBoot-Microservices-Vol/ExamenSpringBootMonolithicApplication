package com.sergiu.builders;

import com.sergiu.entity.Candidate;
import com.sergiu.entity.CandidateOption;
import com.sergiu.entity.CandidateOptionId;
import com.sergiu.util.AdmissionOption;

import java.util.List;

public class CandidateOptionBuilder {
    public static CandidateOption build(List<String> fieldsValue) {
        CandidateOption candidateOption = new CandidateOption();
        CandidateOptionId candidateOptionId = new CandidateOptionId();
        candidateOptionId.setCnp(Long.valueOf(fieldsValue.get(0)));
        candidateOptionId.setAdmissionOption(AdmissionOption.get(fieldsValue.get(1)));
        candidateOption.setCandidateOptionId(candidateOptionId);
        candidateOption.setCandidate(new Candidate(Long.valueOf(fieldsValue.get(0))));
        candidateOption.setPriority(Integer.valueOf(fieldsValue.get(2)));
        return candidateOption;
    }
}
