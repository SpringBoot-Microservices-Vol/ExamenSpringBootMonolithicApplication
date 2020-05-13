package com.sergiu.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
public class Grades {

    @Id
    private Long cnp;

    @JsonManagedReference("grades")
    @OneToOne
    @MapsId("cnp")
    @JoinColumn(name = "cnp")
    private Candidate candidate;

    @Column
    private Double firstGrade;

    @Column
    private String nameProfessorOne;

    @Column
    private Double secondGrade;

    @Column
    private String nameProfessorTwo;

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
