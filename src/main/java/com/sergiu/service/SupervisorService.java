package com.sergiu.service;

import com.sergiu.dto.SupervisorDTO;

import java.util.List;

public interface SupervisorService {
    List<SupervisorDTO> getAllSupervisors();

    void createSupervisor(SupervisorDTO supervisorDTO);

    SupervisorDTO getSupervisorById(Integer id);

    SupervisorDTO updateSupervisor(Integer id, SupervisorDTO supervisorDTO);

    void deleteSupervisor(Integer id);
}
