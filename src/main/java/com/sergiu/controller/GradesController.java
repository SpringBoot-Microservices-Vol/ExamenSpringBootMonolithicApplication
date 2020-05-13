package com.sergiu.controller;

import com.sergiu.dto.GradesDTO;
import com.sergiu.service.GradesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
public class GradesController {

    private GradesService gradesService;

    @Autowired
    public void setGradesService(GradesService gradesService) {
        this.gradesService = gradesService;
    }

    @GetMapping("/grades")
    public List<GradesDTO> getAllGrades() {
        return gradesService.getAllGrades();
    }

    @PostMapping("/grades")
    public ResponseEntity<?> addGrade(@Valid @RequestBody GradesDTO gradesDTO) {
        gradesService.add(gradesDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/grades/{cnp}")
    public GradesDTO updateGrade(@PathVariable(value = "cnp") Long cnp, @Valid @RequestBody GradesDTO gradesDTO) {
        return gradesService.updateGrades(cnp, gradesDTO);
    }

    @DeleteMapping("/grades/{cnp}")
    public ResponseEntity<?> deleteGrades(@PathVariable(value = "cnp") Long cnp) {
        gradesService.deleteGrades(cnp);
        return ResponseEntity.ok().build();
    }
}
