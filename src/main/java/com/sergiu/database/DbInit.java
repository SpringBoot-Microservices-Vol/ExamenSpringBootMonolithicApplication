package com.sergiu.database;

import com.sergiu.repository.ApplicationStateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
public class DbInit implements CommandLineRunner {

    @Autowired
    private ApplicationStateRepository applicationStateRepository;

    @Override
    public void run(String... args) {
        ApplicationState applicationState = new ApplicationState();
        applicationState.setIsImportedResources("false");
        applicationState.setIsDistributedFinalized("false");
        applicationState.setIsDistributed("false");
        applicationState.setIsExamFinish("false");
        applicationStateRepository.save(applicationState);
    }
}
