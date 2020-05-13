package com.sergiu.database;

import com.sergiu.repository.ApplicationStateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
public class DbController {

    @Autowired
    private ApplicationStateRepository applicationStateRepository;

    @Autowired
    private DbInit dbInit;

    @GetMapping("/data_base")
    public ApplicationState getApplicationState() {
        return applicationStateRepository.findById(1).get();
    }

    @PostMapping("/data_base")
    public void setApplicationState(@Valid @RequestBody ApplicationState applicationState) {
        ApplicationState entity = applicationStateRepository.findById(1).get();
        entity.setIsDistributed(applicationState.getIsDistributed());
        entity.setIsDistributedFinalized(applicationState.getIsDistributedFinalized());
        entity.setIsExamFinish(applicationState.getIsExamFinish());
        entity.setIsImportedResources(applicationState.getIsImportedResources());
        applicationStateRepository.save(entity);
    }
}
