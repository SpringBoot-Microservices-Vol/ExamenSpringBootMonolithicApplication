package com.sergiu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sergiu.entity.Supervisor;

import java.util.Optional;

@Repository
public interface SupervisorRepository extends JpaRepository<Supervisor, Integer> {

    Optional<Supervisor> findByFirstName(String firstName);

    void deleteByFirstName(String firstName);
}
