package com.sergiu.repository;

import com.sergiu.entity.Supervisor;
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
public class SupervisorsRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private SupervisorRepository supervisorRepository;

    @Before
    public void init() {
        Supervisor entity = new Supervisor();

        entity.setFirstName("Supervisor1");
        entity.setLastName("LastName1");
        entityManager.persist(entity);

        Supervisor entity2 = new Supervisor();
        entity2.setFirstName("Supervisor2");
        entity2.setLastName("LastName2");
        entityManager.persist(entity2);

        entityManager.flush();
    }

    @Test
    public void testFindByNameOnHallsRepository() {
        Supervisor found = supervisorRepository.findByFirstName("Supervisor1").get();
        assertEquals(found.getFirstName(), "Supervisor1");
    }

    @Test
    public void testDeleteByNameOnHallsRepository() {
        int totalNrOfHalls = supervisorRepository.findAll().size();
        supervisorRepository.deleteByFirstName("Supervisor1");
        assertEquals(totalNrOfHalls - 1, supervisorRepository.findAll().size());
    }
}
