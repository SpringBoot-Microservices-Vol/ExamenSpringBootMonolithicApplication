package com.sergiu.entity;

import com.sergiu.util.AdmissionOption;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CandidateOptionId implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column
    private Long cnp;

    @Column
    @Enumerated(EnumType.STRING)
    private AdmissionOption admissionOption;

    public CandidateOptionId() {

    }

    public CandidateOptionId(Long cnp, AdmissionOption admissionOption) {
        this.cnp = cnp;
        this.admissionOption = admissionOption;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CandidateOptionId)) return false;
        CandidateOptionId that = (CandidateOptionId) o;
        return getCnp().equals(that.getCnp()) &&
                getAdmissionOption() == that.getAdmissionOption();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCnp(), getAdmissionOption());
    }
}
