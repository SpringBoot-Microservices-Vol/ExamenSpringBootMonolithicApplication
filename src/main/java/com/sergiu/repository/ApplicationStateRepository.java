package com.sergiu.repository;

import com.sergiu.database.ApplicationState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationStateRepository extends JpaRepository<ApplicationState, Integer> {
}
