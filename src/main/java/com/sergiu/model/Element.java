package com.sergiu.model;

import com.sergiu.entity.Category;
import com.sergiu.entity.Hall;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Element implements Comparable {
    private Category category;

    private Hall hall;

    public Element(Category category, Hall hall) {
        this.category = category;
        this.hall = hall;
    }

    public BigDecimal getCohesion() {

        if (hall.getUtilizableSize() == 0 || category.getCandidateEntities().size() == 0) {
            return BigDecimal.ZERO;
        }

        if (hall.getUtilizableSize() == category.getCandidateEntities().size()) {
            return BigDecimal.ONE;
        }

        BigDecimal candidates = new BigDecimal(category.getCandidateEntities().size());
        BigDecimal availableSpaces = new BigDecimal(hall.getUtilizableSize());

        if (hall.getUtilizableSize() > category.getCandidateEntities().size()) {

            return candidates.divide(availableSpaces, 2, RoundingMode.FLOOR);
        } else {
            return (candidates.divide(availableSpaces, 2, RoundingMode.FLOOR).multiply(new BigDecimal(-1)));
        }
    }

    public int restOfCandidates() {
        int nrOfCandidates = category.getCandidateEntities().size();
        int nrOfAvailableSpaces = hall.getUtilizableSize();
        if (nrOfCandidates < nrOfAvailableSpaces) {
            return nrOfCandidates;
        } else {
            return nrOfAvailableSpaces;
        }
    }

    @Override
    public int compareTo(Object o) {
        Element object = (Element) o;
        int resultCohesion = this.getCohesion().compareTo(object.getCohesion());
        if (resultCohesion != 0)
            return resultCohesion;
        int resultCandidatesComparison = this.category.getCandidateEntities().size() - object.getCategory().getCandidateEntities().size();

        if (resultCandidatesComparison != 0) {
            return resultCandidatesComparison;
        }

        int resultHallSpots = this.hall.getUtilizableSize() - object.getHall().getUtilizableSize();
        if (resultHallSpots != 0) {
            return resultHallSpots;
        }

        int resultNameCategory = this.category.getName().compareTo(object.getCategory().getName());
        if (resultNameCategory != 0) {
            return resultNameCategory;
        }

        int resultNameHall = this.hall.getName().compareTo(object.getHall().getName());
        if (resultNameHall != 0) {
            return resultNameHall;
        }


        return 0;
    }

    @Override
    public String toString() {
        return "Cohesion=" + getCohesion() + " category_id=" + category.getId() + " with hall_id" + hall.getId();
    }

    public Category getCategory() {
        return category;
    }

    public Hall getHall() {
        return hall;
    }

    @Override
    public boolean equals(Object o) {
        Element object = (Element) o;

        return (this.getCategory().getId() == object.getCategory().getId()
                && this.getHall().getId() == object.getHall().getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(category, hall);
    }
}
