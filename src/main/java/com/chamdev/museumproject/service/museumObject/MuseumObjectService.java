package com.chamdev.museumproject.service.museumObject;

import com.chamdev.museumproject.model.MuseumObject;
import com.chamdev.museumproject.utils.enums.CategoryType;

public interface MuseumObjectService {
    MuseumObject findObjectById(Long objectId);

    void addMuseumObject(Long id, String name, String description, CategoryType categoryType);
}
