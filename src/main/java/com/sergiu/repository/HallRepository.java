package com.sergiu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sergiu.entity.Hall;

public interface HallRepository extends JpaRepository<Hall, Integer> {

}
