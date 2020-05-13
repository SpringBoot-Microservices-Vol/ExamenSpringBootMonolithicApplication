package com.sergiu.dto;

import com.sergiu.entity.Candidate;
import com.sergiu.util.ListAllocationType;

import java.io.Serializable;

public class AdmissionResultDTO implements Serializable {

    private Long cnp;

    private Candidate candidate;

    private Double testGrade;

    private Double finalGrade;

    private ListAllocationType listName;

    public Long getCnp() {
        return cnp;
    }

    public void setCnp(Long cnp) {
        this.cnp = cnp;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    public Double getTestGrade() {
        return testGrade;
    }

    public void setTestGrade(Double testGrade) {
        this.testGrade = testGrade;
    }

    public Double getFinalGrade() {
        return finalGrade;
    }

    public void setFinalGrade(Double finalGrade) {
        this.finalGrade = finalGrade;
    }

    public ListAllocationType getListName() {
        return listName;
    }

    public void setListName(ListAllocationType listName) {
        this.listName = listName;
    }
}
