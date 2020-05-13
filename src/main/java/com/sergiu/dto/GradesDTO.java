package com.sergiu.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class GradesDTO implements Serializable {

    private Long cnp;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private CandidateDTO candidateDTO;

    private Double firstGrade;

    private String nameProfessorOne;

    private Double secondGrade;

    private String nameProfessorTwo;

    public Long getCnp() {
        return cnp;
    }

    public void setCnp(Long cnp) {
        this.cnp = cnp;
    }

    public CandidateDTO getCandidateDTO() {
        return candidateDTO;
    }

    public void setCandidateDTO(CandidateDTO candidateDTO) {
        this.candidateDTO = candidateDTO;
    }

    public Double getFirstGrade() {
        return firstGrade;
    }

    public void setFirstGrade(Double firstGrade) {
        this.firstGrade = firstGrade;
    }

    public String getNameProfessorOne() {
        return nameProfessorOne;
    }

    public void setNameProfessorOne(String nameProfessorOne) {
        this.nameProfessorOne = nameProfessorOne;
    }

    public Double getSecondGrade() {
        return secondGrade;
    }

    public void setSecondGrade(Double secondGrade) {
        this.secondGrade = secondGrade;
    }

    public String getNameProfessorTwo() {
        return nameProfessorTwo;
    }

    public void setNameProfessorTwo(String nameProfessorTwo) {
        this.nameProfessorTwo = nameProfessorTwo;
    }
}
