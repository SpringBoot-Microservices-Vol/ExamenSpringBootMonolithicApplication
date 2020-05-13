package com.sergiu.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sergiu.util.StatusExam;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "candidates")
public class Candidate implements Serializable {

    @Id
    @Column
    private Long cnp;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @JsonBackReference("category")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column
    private String highSchool;

    @Column
    private Double bacGrade;

    @Column
    private Double bacBestGrade;

    @JsonBackReference("grades")
    @OneToOne(mappedBy = "candidate")
    private Grades grades;

    @JsonBackReference("hall")
    @OneToOne
    @JoinTable(name = "distribution", joinColumns = {@JoinColumn(name = "cnp_candidate")}, inverseJoinColumns = {
            @JoinColumn(name = "id_hall")})
    private Hall hall;

    @Column
    @Enumerated(EnumType.STRING)
    private StatusExam statusExam;

    @JsonBackReference("option")
    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL)
    private List<CandidateOption> candidateOptions = new ArrayList<>();

    public Candidate() {
    }

    public Candidate(Long cnp) {
        this.cnp = cnp;
    }

    public Candidate(Long cnp, String firstName, String lastName, Category category, String highSchool) {
        this.cnp = cnp;
        this.firstName = firstName;
        this.lastName = lastName;
        this.category = category;
        this.highSchool = highSchool;
    }


    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public Long getCnp() {
        return cnp;
    }

    public void setCnp(Long cnp) {
        this.cnp = cnp;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getHighSchool() {
        return highSchool;
    }

    public void setHighSchool(String highSchool) {
        this.highSchool = highSchool;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Double getBacGrade() {
        return bacGrade;
    }

    public void setBacGrade(Double bacGrade) {
        this.bacGrade = bacGrade;
    }

    public Double getBacBestGrade() {
        return bacBestGrade;
    }

    public void setBacBestGrade(Double bacBestGrade) {
        this.bacBestGrade = bacBestGrade;
    }

    public Grades getGrades() {
        return grades;
    }

    public void setGrades(Grades grades) {
        this.grades = grades;
    }

    public StatusExam getStatusExam() {
        return statusExam;
    }

    public void setStatusExam(StatusExam statusExam) {
        this.statusExam = statusExam;
    }

    public List<CandidateOption> getCandidateOptions() {
        return candidateOptions;
    }

    public void setCandidateOptions(List<CandidateOption> candidateOptions) {
        this.candidateOptions = candidateOptions;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cnp == null) ? 0 : cnp.hashCode());
        result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = prime * result + ((highSchool == null) ? 0 : highSchool.hashCode());
        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Candidate other = (Candidate) obj;
        if (cnp == null) {
            if (other.cnp != null)
                return false;
        } else if (!cnp.equals(other.cnp))
            return false;
        if (firstName == null) {
            if (other.firstName != null)
                return false;
        } else if (!firstName.equals(other.firstName))
            return false;
        if (highSchool == null) {
            if (other.highSchool != null)
                return false;
        } else if (!highSchool.equals(other.highSchool))
            return false;
        if (lastName == null) {
            if (other.lastName != null)
                return false;
        } else if (!lastName.equals(other.lastName))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Candidate{" +
                "cnp=" + cnp +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", category=" + category +
                ", highSchool='" + highSchool + '\'' +
                '}';
    }
}
