package com.sergiu.repository;

import com.sergiu.entity.Grades;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeRepository extends JpaRepository<Grades, Long> {
}
