package com.sergiu.service;

import com.sergiu.model.Element;

import java.util.SortedSet;

public interface DistributionService {

    boolean isSufficientSeatsForExam();

    void distributeCandidatesIntoHalls();

    SortedSet<Element> fillSet();

    void clearDistribution();
}
