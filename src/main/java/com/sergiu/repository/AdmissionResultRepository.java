package com.sergiu.repository;

import com.sergiu.entity.AdmissionResult;
import com.sergiu.util.ListAllocationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdmissionResultRepository extends JpaRepository<AdmissionResult, Long> {

    List<AdmissionResult> findAllByListName(ListAllocationType listType);

    List<AdmissionResult>findAllByListNameIsNullOrListNameIsNot(ListAllocationType listAllocationType);
}
