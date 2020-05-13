package com.sergiu.repository;

import com.sergiu.entity.Candidate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class CandidatesRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CandidateRepository candidateRepository;

    @Before
    public void init() {
        Candidate entity = new Candidate();
        entity.setCnp(1940122374500L);
        entity.setFirstName("Sergiu-Adrian");
        entity.setLastName("Volocaru");
        entity.setHighSchool("Liceul Teoretic Emil Racovita");

        entityManager.persist(entity);

        Candidate entity2 = new Candidate();
        entity2.setCnp(1940122374502L);
        entity2.setFirstName("Sergiu-Constatin");
        entity2.setLastName("Volocaru");
        entity2.setHighSchool("Liceul Teoretic Emil Racovita");

        entityManager.persist(entity2);
        entityManager.flush();
    }

    @After
    public void destroy() {
        entityManager.flush();
    }

    @Test
    public void testFindByCnpOnCandidateRepository() {
        Candidate found = candidateRepository.findByCnp(1940122374500L).get();
        assertEquals(found.getCnp(), Long.valueOf(1940122374500L));
    }

    @Test
    public void testDeleteOnCandidateRepository() {
        int totalNrOfCandidates = candidateRepository.findAll().size();
        candidateRepository.deleteByCnp(1940122374500L);
        assertEquals(totalNrOfCandidates - 1, candidateRepository.findAll().size());
    }
}
