package com.sergiu.dto;

import java.io.Serializable;

public class HomeDTO implements Serializable {

    private Long numberOfCandidates;

    private Long numberOfSupervisors;

    private Long numberOfHalls;

    public Long getNumberOfCandidates() {
        return numberOfCandidates;
    }

    public void setNumberOfCandidates(Long numberOfCandidates) {
        this.numberOfCandidates = numberOfCandidates;
    }

    public Long getNumberOfSupervisors() {
        return numberOfSupervisors;
    }

    public void setNumberOfSupervisors(Long numberOfSupervisors) {
        this.numberOfSupervisors = numberOfSupervisors;
    }

    public Long getNumberOfHalls() {
        return numberOfHalls;
    }

    public void setNumberOfHalls(Long numberOfHalls) {
        this.numberOfHalls = numberOfHalls;
    }
}
