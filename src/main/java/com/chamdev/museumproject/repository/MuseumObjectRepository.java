package com.chamdev.museumproject.repository;

import com.chamdev.museumproject.model.MuseumObject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MuseumObjectRepository extends JpaRepository<MuseumObject,Long> {
    MuseumObject findByDetectionLabelId(Long detectionLabelId);
}
