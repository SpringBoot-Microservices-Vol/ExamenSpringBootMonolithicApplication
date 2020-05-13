package com.sergiu.service;

import com.sergiu.dto.HallDTO;
import com.sergiu.entity.Hall;
import com.sergiu.exception.ResourceNotConsistentData;
import com.sergiu.exception.ResourceNotFoundException;
import com.sergiu.repository.HallRepository;
import com.sergiu.transformer.HallsTransformer;
import org.apache.commons.lang.mutable.MutableInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HallsServiceImpl implements HallsService {

    @Autowired
    private HallRepository hallRepository;

    @Autowired
    private HallsTransformer hallsTransformer;

    @Override
    public List<HallDTO> getAllHalls() {
        return hallsTransformer.toDTO(hallRepository.findAll());
    }

    @Override
    public void createHall(HallDTO hallDTO) {
        hallRepository.save(hallsTransformer.toEntity(hallDTO));
    }

    @Override
    public HallDTO getHallById(Integer id) {
        return hallsTransformer.toDTO(hallRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hall", "id", id)));
    }

    @Override
    public HallDTO updateHall(Integer id, HallDTO hallDTO) {

        if (id != hallDTO.getId()) {
            throw new ResourceNotConsistentData("Hall", "id", id, hallDTO.getId());
        }

        hallRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hall", "id", id));
        return hallsTransformer.toDTO(hallRepository.save(hallsTransformer.toEntity(hallDTO)));
    }

    @Override
    public void deleteHall(Integer id) {
        Hall entity = hallRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hall", "id", id));
        hallRepository.delete(entity);
    }

    @Override
    public Integer totalSeatsAvailable() {
        Long result = hallRepository.findAll().stream().mapToLong(Hall::getUtilizableSize).sum();
        return result.intValue();
    }


    @Override
    public List<Hall> selectHalls(Integer numberCandidates) {
        MutableInt bestValue = new MutableInt(Integer.MAX_VALUE);
        List<Hall> halls = hallRepository.findAll();
        List<Hall> result = new ArrayList<>();
        Map<Integer, List<Hall>> myMap = new HashMap<>();

        findSubset(halls, 0, 0, numberCandidates, result, myMap, bestValue);

        return myMap.get(bestValue.intValue());
    }

    private void findSubset(List<Hall> halls, int sum, int startIndex, Integer numberCandidates, List<Hall> solution, Map<Integer, List<Hall>> myMap, MutableInt bestValue) {

        if (numberCandidates == sum) {
            bestValue.setValue(sum);
            myMap.put(bestValue.intValue(), new ArrayList(solution));
            solution.remove(solution.get(solution.size()-1));
            return;
        }

        if (sum > numberCandidates && sum < bestValue.intValue()) {
            bestValue.setValue(sum);
            myMap.put(bestValue.intValue(), new ArrayList(solution));
            solution.remove(solution.get(solution.size()-1));
            return;
        } else {

            for (int i = startIndex; i < halls.size(); i++) {
                solution.add(halls.get(i));
                findSubset(halls, sum + halls.get(i).getUtilizableSize(), i + 1, numberCandidates, solution, myMap, bestValue);
            }
            solution.clear();
        }
    }
}
