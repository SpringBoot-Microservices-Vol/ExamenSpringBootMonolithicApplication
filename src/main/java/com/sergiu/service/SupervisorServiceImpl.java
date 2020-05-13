package com.sergiu.service;

import java.util.List;

import com.sergiu.entity.Supervisor;
import com.sergiu.exception.ResourceNotConsistentData;
import com.sergiu.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sergiu.dto.SupervisorDTO;
import com.sergiu.repository.SupervisorRepository;
import com.sergiu.transformer.SupervisorsTransformer;

@Service
public class SupervisorServiceImpl implements SupervisorService {

    @Autowired
    private SupervisorRepository supervisorRepository;

    @Autowired
    private SupervisorsTransformer supervisorsTransformer;

    @Override
    public List<SupervisorDTO> getAllSupervisors() {
        return supervisorsTransformer.toDTO(supervisorRepository.findAll());
    }

    @Override
    public void createSupervisor(SupervisorDTO supervisorDTO) {
        supervisorRepository.save(supervisorsTransformer.toEntity(supervisorDTO));
    }

    @Override
    public SupervisorDTO getSupervisorById(Integer id) {
        return supervisorsTransformer.toDTO(supervisorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supervisor", "id", id)));
    }

    @Override
    public SupervisorDTO updateSupervisor(Integer id, SupervisorDTO supervisorDTO) {
        if (id != supervisorDTO.getId()) {
            throw new ResourceNotConsistentData("Supervisor", "id", id, supervisorDTO.getId());
        }
        supervisorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supervisor", "id", id));

        return supervisorsTransformer.toDTO(supervisorRepository.save(supervisorsTransformer.toEntity(supervisorDTO)));
    }

    @Override
    public void deleteSupervisor(Integer id) {
        Supervisor entity = supervisorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supervisor", "id", id));

        supervisorRepository.delete(entity);
    }
}
