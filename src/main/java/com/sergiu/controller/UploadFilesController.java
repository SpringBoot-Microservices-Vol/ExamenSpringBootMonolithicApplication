package com.sergiu.controller;

import com.sergiu.service.FilesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;

@CrossOrigin
@RestController
public class UploadFilesController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UploadFilesController.class);

    private FilesService filesService;

    @Value("classpath:data-base/resources.ods")
    private File resourcesDadaBase;

    @Autowired
    public UploadFilesController(FilesService filesService) {
        this.filesService = filesService;
    }

    //@RequestParam("file") MultipartFile multipartFile
    @PostMapping(path = "files/load_database")
    public ResponseEntity upLoadFileSetupDataBase() throws Exception {

        try {
            LOGGER.info("File [{}] is loaded.\n", resourcesDadaBase.getName());
            filesService.readAndInsertMainResources(new FileInputStream(resourcesDadaBase));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("File [{}] fail to load.", resourcesDadaBase.getName());
            throw e;

        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("File was successfully loaded");
    }

    //@RequestParam("file") MultipartFile multipartFile
    @PostMapping(path = "files/load_grades")
    public ResponseEntity<String> uploadGradesIntoDataBase() throws Exception {

        try {
            LOGGER.info("File [{}] with grads is loaded.\n", resourcesDadaBase.getName());
            filesService.readAndInsertGradesIntoDataBase(new FileInputStream(resourcesDadaBase));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("File [{}] with grades fail to load.", resourcesDadaBase.getName());
            throw e;

        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("File was successfully loaded");

    }
}
