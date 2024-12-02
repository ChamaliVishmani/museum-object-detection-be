package com.chamdev.museumproject.repository;

import com.chamdev.museumproject.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Integer> {
}
