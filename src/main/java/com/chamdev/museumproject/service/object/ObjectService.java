package com.chamdev.museumproject.service.object;

import com.chamdev.museumproject.model.Object;
import com.chamdev.museumproject.utils.enums.CategoryType;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ObjectService {
    Object findObjectById(Long objectId);

    void addObject(Long id, String name, String description, CategoryType categoryType, List<MultipartFile> imageFiles);
}
