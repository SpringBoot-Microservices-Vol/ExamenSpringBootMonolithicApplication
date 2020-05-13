package com.sergiu.model;

import com.sergiu.entity.Candidate;
import com.sergiu.entity.Category;
import com.sergiu.entity.Hall;
import org.junit.Assert;
import org.junit.Before;

import org.junit.Test;

import java.math.BigDecimal;

public class ElementTest {

    private Hall hall;

    private Category category;
    private Category category2;

    @Before
    public void init() {
        hall = new Hall("Sala1", 12, 7);
        Candidate entity = new Candidate();
        entity.setCnp(1940122374500L);
        entity.setFirstName("Sergiu-Adrian");
        entity.setLastName("Volocaru");
        entity.setHighSchool("Liceul Teoretic Emil Racovita");

        Candidate entity2 = new Candidate();
        entity2.setCnp(1940122374502L);
        entity2.setFirstName("Sergiu-Constatin");
        entity2.setLastName("Volocaru");
        entity2.setHighSchool("Liceul Teoretic Emil Racovita");
        category = new Category();
        category2 = new Category();
        category.getCandidateEntities().add(entity);
        category.getCandidateEntities().add(entity2);
        category.getCandidateEntities().add(entity2);
        category.getCandidateEntities().add(entity);
        category2.getCandidateEntities().add(entity2);
        category2.getCandidateEntities().add(entity2);
    }

    @Test
    public void testCohesion() {
        Element element = new Element(category, hall);
        Assert.assertEquals(new BigDecimal("0.57"), element.getCohesion());
        Assert.assertEquals(4, element.restOfCandidates());
    }

    @Test
    public void testCompareTo() {

        Element element = new Element(category, hall);
        Element element1 = new Element(category2, hall);
        Assert.assertEquals(1, element.compareTo(element1));
    }

    @Test
    public void testCohesionMax() {
        hall = new Hall("Sala1", 8, 4);
        Element element = new Element(category, hall);
        Assert.assertEquals(BigDecimal.ONE, element.getCohesion());
    }

}
