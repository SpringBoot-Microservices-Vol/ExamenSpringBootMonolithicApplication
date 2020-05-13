package com.sergiu.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sergiu.util.AdmissionType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "categories")
public class Category implements Comparable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column
    private String name;

    @Column
    private String discipline;

    @Column
    private String language;

    @Enumerated(EnumType.STRING)
    private AdmissionType admissionType;

    @JsonManagedReference("category")
    @OneToMany(mappedBy = "category")
    private List<Candidate> candidateEntities = new ArrayList<>();

    public Category() {

    }

    public Category(Integer id, String name, String discipline, String language, AdmissionType admissionType) {
        this.id = id;
        this.name = name;
        this.discipline = discipline;
        this.language = language;
        this.admissionType = admissionType;
    }

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

    public List<Candidate> getCandidateEntities() {
        return candidateEntities;
    }

    public void setCandidateEntities(List<Candidate> candidateEntities) {
        this.candidateEntities = candidateEntities;
    }


    @Override
    public int compareTo(Object o) {

        Category category = (Category) o;
        int sizeCompare = category.getCandidateEntities().size() - this.candidateEntities.size();
        if (sizeCompare == 0) {
            return this.id.compareTo(category.getId());
        }
        return sizeCompare;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;
        Category that = (Category) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
