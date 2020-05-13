package com.sergiu.transformer;

import com.sergiu.dto.HallDTO;
import com.sergiu.entity.Hall;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class HallsTransformer {

    @Autowired
    private ModelMapper modelMapper;

    public HallDTO toDTO(Hall entity) {
        return modelMapper.map(entity, HallDTO.class);
    }

    public List<HallDTO> toDTO(List<Hall> entities) {
        return entities.stream()
                .map(entity -> toDTO(entity))
                .collect(Collectors.toList());
    }

    public Hall toEntity(@Valid HallDTO hallDTO) {
        return modelMapper.map(hallDTO, Hall.class);
    }
}
