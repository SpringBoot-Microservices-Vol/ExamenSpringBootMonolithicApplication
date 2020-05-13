package com.sergiu.repository;

import com.sergiu.entity.Candidate;
import com.sergiu.entity.CandidateOption;
import com.sergiu.entity.CandidateOptionId;
import com.sergiu.util.AdmissionOption;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class CandidatesOptionRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private CandidateOptionRepository candidateOptionRepository;

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
    public void testAddCandidateOptions() {
        candidateRepository.count();
        CandidateOption candidateOption = new CandidateOption();
        CandidateOptionId candidateOptionId = new CandidateOptionId(1940122374500L, AdmissionOption.RO_BUGET);
        candidateOption.setCandidateOptionId(candidateOptionId);
        candidateOption.setPriority(1);
        Candidate candidate = new Candidate();
        candidate.setCnp(1940122374500L);
        candidateOption.setCandidate(candidate);
        candidateOptionRepository.save(candidateOption);
        Assert.assertEquals(1, candidateOptionRepository.count());
    }

    @Test(expected = JpaObjectRetrievalFailureException.class)
    public void testAddOptionForNonExistentCandidate() {
        candidateRepository.count();
        CandidateOption candidateOption = new CandidateOption();
        CandidateOptionId candidateOptionId = new CandidateOptionId(1940122374501L, AdmissionOption.RO_BUGET);
        candidateOption.setCandidateOptionId(candidateOptionId);
        candidateOption.setPriority(1);
        Candidate candidate = new Candidate();
        candidate.setCnp(1940122374501L);
        candidateOption.setCandidate(candidate);
        candidateOptionRepository.save(candidateOption);
        Assert.assertEquals(1, candidateOptionRepository.count());
    }
}
