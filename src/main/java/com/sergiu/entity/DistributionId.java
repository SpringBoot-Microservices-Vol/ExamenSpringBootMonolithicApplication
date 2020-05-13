package com.sergiu.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class DistributionId implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "cnp_candidate")
    private Long candidateCnp;

    @Column(name = "id_hall")
    private Integer hallId;

    public DistributionId() {

    }

    public DistributionId(Long candidateCnp, Integer hallId) {
        this.candidateCnp = candidateCnp;
        this.hallId = hallId;
    }

    public Long getCandidateCnp() {
        return candidateCnp;
    }

    public void setCandidateCnp(Long candidateId) {
        this.candidateCnp = candidateId;
    }

    public Integer getHallId() {
        return hallId;
    }

    public void setHallId(Integer hallId) {
        this.hallId = hallId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DistributionId)) return false;
        DistributionId that = (DistributionId) o;
        return getCandidateCnp().equals(that.getCandidateCnp()) &&
                getHallId().equals(that.getHallId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCandidateCnp(), getHallId());
    }
}
