package com.sergiu.repository;

import com.sergiu.entity.Hall;
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
public class HallsRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private HallRepository hallRepository;

    @Before
    public void init() {

        entityManager.persist(new Hall("Sala1", 60, 30));
        entityManager.persist(new Hall("Sala2", 50, 25));

        entityManager.flush();
    }

    @Test
    public void testFindByIdOnHallsRepository() {
        Hall found = hallRepository.findById(1).get();
        assertEquals(found.getId().intValue(), 1);
    }

    @Test
    public void testDeleteByIdOnHallsRepository() {
        int totalNrOfHalls = hallRepository.findAll().size();
        hallRepository.deleteById(3);
        assertEquals(totalNrOfHalls - 1, hallRepository.findAll().size());
    }
}
