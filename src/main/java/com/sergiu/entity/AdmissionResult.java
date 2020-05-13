package com.sergiu.entity;

import com.sergiu.util.ListAllocationType;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "admission_results")
public class AdmissionResult implements Serializable, Comparable {

    @Id
    private Long cnp;

    @OneToOne
    @MapsId("cnp")
    @JoinColumn(name = "cnp")
    private Candidate candidate;

    @Column
    private Double testGrade;

    @Column
    private Double finalGrade;

    @Enumerated(EnumType.STRING)
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

    @Override
    public int compareTo(Object o) {
        AdmissionResult obj = (AdmissionResult) o;

        if (this.finalGrade > obj.getFinalGrade()) {
            return -1;
        }

        if (this.finalGrade < obj.getFinalGrade()) {
            return 1;
        }

        if (this.testGrade > obj.getTestGrade()) {
            return -1;
        }

        if (this.testGrade < obj.getTestGrade()) {
            return 1;
        }

        return -1;
    }
}
