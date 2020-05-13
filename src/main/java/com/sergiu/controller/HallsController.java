package com.sergiu.controller;

import java.util.List;

import javax.validation.Valid;

import com.sergiu.service.HallsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sergiu.dto.HallDTO;

@CrossOrigin
@RestController
public class HallsController {
    private static final Logger LOGGER = LoggerFactory.getLogger(HallsController.class);

    @Autowired
    private HallsServiceImpl hallsServiceImpl;

    @GetMapping("/halls")
    public List<HallDTO> getAllHalls() {
        return hallsServiceImpl.getAllHalls();
    }

    @PostMapping("/halls")
    public void createHall(@Valid @RequestBody HallDTO hallDTO) {
        hallsServiceImpl.createHall(hallDTO);
    }

    @GetMapping("/halls/{id}")
    public HallDTO getHallById(@PathVariable(value = "id") Integer id) {
        return hallsServiceImpl.getHallById(id);
    }

    @PutMapping("/halls/{id}")
    public HallDTO updateHall(@PathVariable(value = "id") Integer id, @Valid @RequestBody HallDTO hallDTO) {
        return hallsServiceImpl.updateHall(id, hallDTO);
    }

    @DeleteMapping("/halls/{id}")
    public ResponseEntity<?> deleteHall(@PathVariable(value = "id") Integer id) {
        hallsServiceImpl.deleteHall(id);
        return ResponseEntity.ok().build();
    }
}
