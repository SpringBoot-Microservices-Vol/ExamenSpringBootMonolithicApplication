package com.sergiu.transformer;

import com.sergiu.dto.CandidateOptionDTO;
import com.sergiu.entity.CandidateOption;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CandidateOptionTransformer {

    public List<CandidateOptionDTO> toDTO(List<CandidateOption> entities) {
        return entities.stream()
                .map(entity -> toDTO(entity))
                .collect(Collectors.toList());
    }

    public CandidateOptionDTO toDTO(CandidateOption entity) {
        CandidateOptionDTO candidateOptionDTO = new CandidateOptionDTO();
        candidateOptionDTO.setId(entity.getCandidateOptionId().getCnp() + entity.getCandidateOptionId().getAdmissionOption().toString());
        candidateOptionDTO.setCnp(entity.getCandidateOptionId().getCnp());
        candidateOptionDTO.setAdmissionOption(entity.getCandidateOptionId().getAdmissionOption());
        candidateOptionDTO.setPriority(entity.getPriority());
        return candidateOptionDTO;
    }
}