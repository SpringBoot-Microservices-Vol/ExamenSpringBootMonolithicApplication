package com.sergiu.repository;

import com.sergiu.entity.Category;
import com.sergiu.util.AdmissionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    List<Category> findAllByAdmissionType(AdmissionType admissionType);

    Category findByName(String name);
}
