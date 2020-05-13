package com.sergiu.service;

import java.io.InputStream;

public interface FilesService {

    void readAndInsertMainResources(InputStream inputStream) throws Exception;

    void readAndInsertGradesIntoDataBase(InputStream inputStream) throws Exception;
}
