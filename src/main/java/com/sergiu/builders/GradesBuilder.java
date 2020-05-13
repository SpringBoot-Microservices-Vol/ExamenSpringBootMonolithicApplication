package com.sergiu.builders;

import com.sergiu.dto.GradesDTO;

import java.util.List;

public class GradesBuilder {
    public static GradesDTO buildDTO(List<String> fields) {
        GradesDTO gradesDTO = new GradesDTO();
        gradesDTO.setCnp(Long.valueOf(fields.get(0)));
        gradesDTO.setFirstGrade(Double.valueOf(fields.get(1)));
        gradesDTO.setNameProfessorOne(fields.get(2));
        gradesDTO.setSecondGrade(Double.valueOf(fields.get(3)));
        gradesDTO.setNameProfessorTwo(fields.get(4));
        return gradesDTO;
    }
}
