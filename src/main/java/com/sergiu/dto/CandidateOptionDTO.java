package com.sergiu.dto;

import com.sergiu.util.AdmissionOption;

import java.io.Serializable;


public class CandidateOptionDTO implements Serializable {

    private String id;

    private Long cnp;

    private AdmissionOption admissionOption;

    private Integer priority;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getCnp() {
        return cnp;
    }

    public void setCnp(Long cnp) {
        this.cnp = cnp;
    }

    public AdmissionOption getAdmissionOption() {
        return admissionOption;
    }

    public void setAdmissionOption(AdmissionOption admissionOption) {
        this.admissionOption = admissionOption;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }
}
