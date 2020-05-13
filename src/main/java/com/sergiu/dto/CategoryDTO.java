package com.sergiu.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sergiu.util.AdmissionType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CategoryDTO implements Serializable {

    private Integer id;

    private String name;

    private String discipline;

    private String language;

    private AdmissionType admissionType;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<CandidateDTO> candidateDTOS = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public AdmissionType getAdmissionType() {
        return admissionType;
    }

    public void setAdmissionType(AdmissionType admissionType) {
        this.admissionType = admissionType;
    }

    public List<CandidateDTO> getCandidateDTOS() {
        return candidateDTOS;
    }

    public void setCandidateDTOS(List<CandidateDTO> candidateDTOS) {
        this.candidateDTOS = candidateDTOS;
    }
}
