package com.chamdev.museumproject.service.object;

import com.chamdev.museumproject.exceptions.ObjectNotFoundException;
import com.chamdev.museumproject.model.Category;
import com.chamdev.museumproject.model.Object;
import com.chamdev.museumproject.repository.ObjectRepository;
import com.chamdev.museumproject.service.image.ImageService;
import com.chamdev.museumproject.utils.enums.CategoryType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ObjectServiceImpl implements ObjectService {

    private final ObjectRepository objectRepository;
    private final ImageService imageService;

    @Override
    public Object findObjectById(Long objectId) {
        return objectRepository.findById(objectId)
                .orElseThrow( () -> new ObjectNotFoundException("Object with id " + objectId + " not found"));
    }

    @Override
    public void addObject(Long id, String name, String description, CategoryType categoryType, List<MultipartFile> imageFiles) {
        objectRepository.findById(id).ifPresentOrElse(
                object -> updateObjectAndSave(id, name, description, categoryType, imageFiles, object),
                ()->{
                    Object object = new Object();
                    updateObjectAndSave(object.getId(), name, description, categoryType, imageFiles, object);
                }
        );
    }

    private void updateObjectAndSave(Long id, String name, String description, CategoryType categoryType, List<MultipartFile> imageFiles, Object object) {
        createObject(id, name, description, categoryType, imageFiles, object);
        objectRepository.save(object);
    }

    private void createObject(Long id, String name, String description, CategoryType categoryType, List<MultipartFile> imageFiles, Object object) {
        object.setName(name);
        object.setDescription(description);
        object.setCategory(new Category(categoryType));
        object.setImages(imageService.addImagesForObjectId(imageFiles, id));
    }
}
