package com.chamdev.museumproject.repository;

import com.chamdev.museumproject.model.Object;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ObjectRepository extends JpaRepository<Object,Long> {
}
