package com.mugishap.springboot.v1.services;

import com.mugishap.springboot.v1.exceptions.InvalidFileException;
import com.mugishap.springboot.v1.models.File;
import org.springframework.web.multipart.MultipartFile;

public interface IFileService {

        File getById(Long id);

        File create(MultipartFile document, String directory);

        boolean delete(Long id);

        String getFileExtension(String fileName);

        String handleFileName(String fileName, Long id) throws InvalidFileException;

        boolean isValidExtension(String fileName) throws InvalidFileException;

        File getByName(String filename);
}
