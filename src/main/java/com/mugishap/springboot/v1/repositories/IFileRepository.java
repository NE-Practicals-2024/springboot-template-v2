package com.mugishap.springboot.v1.repositories;

import com.mugishap.springboot.v1.models.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IFileRepository extends JpaRepository<File, Long> {
    File getFileByName(String filename);

}

