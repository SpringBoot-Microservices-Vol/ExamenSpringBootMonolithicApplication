package com.sergiu.service;

import com.sergiu.entity.*;
import com.sergiu.model.Element;
import com.sergiu.repository.DistributionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

@Service
public class DistributionServiceImpl implements DistributionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DistributionServiceImpl.class);

    private CandidatesService candidatesService;
    private CategoryService categoryService;
    private HallsService hallsService;

    private DistributionRepository distributionRepository;

    @Autowired
    public void setHallsService(HallsService hallsService) {
        this.hallsService = hallsService;
    }

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Autowired
    public void setCandidatesService(CandidatesService candidatesService) {
        this.candidatesService = candidatesService;
    }

    @Autowired
    public void setDistributionRepository(DistributionRepository distributionRepository) {
        this.distributionRepository = distributionRepository;
    }

    @Override
    public boolean isSufficientSeatsForExam() {
        return hallsService.totalSeatsAvailable() >= candidatesService.totalCandidates();
    }

    @Override
    public void clearDistribution() {
        distributionRepository.deleteAll();
    }

    @Override
    public void distributeCandidatesIntoHalls() {
        distributionRepository.deleteAll();

        SortedSet<Element> setOfCategoriesWithHalls = fillSet();

        while (!setOfCategoriesWithHalls.isEmpty()) {

            displayContent(setOfCategoriesWithHalls);

            LOGGER.info("====> SIZE OF SUBSETS IS :" + setOfCategoriesWithHalls.size() + "<=======");
            Element element = setOfCategoriesWithHalls.last();
            if (element.getCohesion().doubleValue() >= Double.parseDouble("0")) {
                setOfCategoriesWithHalls.remove(element);
            }
            if (!element.getCohesion().equals(BigDecimal.ZERO)) {

                List<Candidate> listMove;
                if (element.restOfCandidates() == 0) {
                    listMove = element.getCategory().getCandidateEntities().subList(0, element.getHall().getUtilizableSize());
                } else {
                    listMove = element.getCategory().getCandidateEntities().subList(0, element.restOfCandidates());
                }
                int size = listMove.size();
                element.getHall().getListCandidates().addAll(listMove);
                List<Candidate> remainingList = getRemainingCandidates(element, size);
                element.getCategory().setCandidateEntities(remainingList);

                insertCandidatesIntoHall(listMove, element.getHall());
                LOGGER.info("Insert [{}] candidates from category [{}] into hall [{}]! ", size, element.getCategory().getId(), element.getHall().getId());
            }

            setOfCategoriesWithHalls = refreshElements(setOfCategoriesWithHalls);
        }
    }

    private void displayContent(SortedSet<Element> setOfCategoriesWithHalls) {
        for (Element element : setOfCategoriesWithHalls) {
            LOGGER.info(element.toString());
        }
    }

    private List<Candidate> getRemainingCandidates(Element element, int size) {
        if (size == element.getCategory().getCandidateEntities().size()) {
            return new ArrayList<>();
        }
        return element.getCategory().getCandidateEntities().subList(size, element.getCategory().getCandidateEntities().size());
    }

    private SortedSet<Element> refreshElements(SortedSet<Element> elementSortedSet) {
        SortedSet<Element> result = new TreeSet<>();
        for (Element element : elementSortedSet) result.add(element);
        return result;
    }

    @Override
    public SortedSet<Element> fillSet() {
        List<Category> categories = categoryService.getAllCategoriesWithCandidates();
        List<Hall> halls = hallsService.selectHalls(candidatesService.totalCandidates());

        SortedSet<Element> result = new TreeSet<>();
        for (Category category : categories) {
            for (Hall hall : halls) {
                result.add(new Element(category, hall));
            }
        }
        return result;
    }

    private void insertCandidatesIntoHall(List<Candidate> candidateEntities, Hall hall) {
        List<Distribution> distributions = new ArrayList<>();
        for (Candidate candidate : candidateEntities) {
            Distribution distribution = new Distribution(new DistributionId(candidate.getCnp(), hall.getId()));
            distributions.add(distribution);
        }
        filterNullValue(distributions);
        if (!distributions.isEmpty()) {
            for (Distribution distribution : distributions) {
                distributionRepository.flush();
                distributionRepository.saveAndFlush(distribution);
            }
        }
    }

    private void filterNullValue(List<Distribution> distributionEntities) {
        distributionEntities.removeIf(d -> null == d.getDistributionId());
    }
}