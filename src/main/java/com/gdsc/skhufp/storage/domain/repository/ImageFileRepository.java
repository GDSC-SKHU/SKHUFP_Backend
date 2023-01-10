package com.gdsc.skhufp.storage.domain.repository;

import com.gdsc.skhufp.storage.domain.model.ImageFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageFileRepository extends JpaRepository<ImageFile, Long> {

}