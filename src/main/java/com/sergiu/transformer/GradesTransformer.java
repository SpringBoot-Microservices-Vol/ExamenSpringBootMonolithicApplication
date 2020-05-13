package com.sergiu.transformer;

import com.sergiu.dto.CandidateDTO;
import com.sergiu.dto.GradesDTO;
import com.sergiu.entity.Candidate;
import com.sergiu.entity.Grades;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GradesTransformer {

    private ModelMapper modelMapper;

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public List<GradesDTO> toDTO(List<Grades> entities) {
        return entities.stream()
                .map(entity -> toDTO(entity))
                .collect(Collectors.toList());
    }

    public GradesDTO toDTO(Grades entity) {
        GradesDTO gradesDTO = modelMapper.map(entity, GradesDTO.class);
        if (entity.getCandidate() != null) {
            gradesDTO.setCandidateDTO(modelMapper.map(entity.getCandidate(), CandidateDTO.class));
        }
        return gradesDTO;
    }

    public Grades toEntity(GradesDTO gradesDTO) {
        Grades grades = modelMapper.map(gradesDTO, Grades.class);
        if (gradesDTO.getCandidateDTO() != null) {
            grades.setCandidate(modelMapper.map(gradesDTO.getCandidateDTO(), Candidate.class));
        }
        return grades;
    }
}
