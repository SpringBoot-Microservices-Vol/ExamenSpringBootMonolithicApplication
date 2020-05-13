package com.sergiu.service;

import com.sergiu.dto.GradesDTO;

import java.util.List;

public interface GradesService {
    List<GradesDTO> getAllGrades();

    void add(GradesDTO gradesDTO);

    GradesDTO updateGrades(Long cnp, GradesDTO gradesDTO);

    void deleteGrades(Long cnp);
}
