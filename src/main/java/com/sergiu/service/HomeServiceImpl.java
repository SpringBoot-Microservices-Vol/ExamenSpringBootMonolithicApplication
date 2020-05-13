package com.sergiu.service;

import com.sergiu.dto.HomeDTO;
import com.sergiu.repository.CandidateRepository;
import com.sergiu.repository.HallRepository;
import com.sergiu.repository.SupervisorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HomeServiceImpl implements HomeService {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private SupervisorRepository supervisorRepository;

    @Autowired
    private HallRepository hallRepository;

    @Override
    public HomeDTO getHomeView() {
        HomeDTO homeDTO = new HomeDTO();
        homeDTO.setNumberOfCandidates(candidateRepository.count());
        homeDTO.setNumberOfSupervisors(supervisorRepository.count());
        homeDTO.setNumberOfHalls(hallRepository.count());
        return homeDTO;
    }
}
