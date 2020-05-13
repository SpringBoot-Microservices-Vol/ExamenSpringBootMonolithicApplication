package com.sergiu.controller;

import com.sergiu.service.DistributionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class DistributionController {
    private static final Logger LOGGER = LoggerFactory.getLogger(DistributionController.class);

    private DistributionService distributionService;

    @Autowired
    public void setDistributionService(DistributionService distributionService) {
        this.distributionService = distributionService;
    }

    @GetMapping(path = "/distribution/clear")
    public void clear() {
        LOGGER.info("Distribution table has bee reset");
        distributionService.clearDistribution();
    }

    @GetMapping(path = "/distribution/start")
    public void start() {
        LOGGER.info("Distribution table has bee filed with data");
        distributionService.distributeCandidatesIntoHalls();
    }
}
