package com.chamdev.museumproject.service.museumObject;

import com.chamdev.museumproject.exceptions.ObjectNotFoundException;
import com.chamdev.museumproject.model.Category;
import com.chamdev.museumproject.model.MuseumObject;
import com.chamdev.museumproject.repository.MuseumObjectRepository;
import com.chamdev.museumproject.utils.enums.CategoryType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MuseumObjectServiceImpl implements MuseumObjectService {

    private final MuseumObjectRepository museumObjectRepository;

    @Override
    public MuseumObject findObjectById(Long objectId) {
        return museumObjectRepository.findById(objectId)
                .orElseThrow( () -> new ObjectNotFoundException("Object with id " + objectId + " not found"));
    }

    @Override
    public void addMuseumObject(Long detectionLabelId, String name, String description, CategoryType categoryType) {
        MuseumObject museumObject =  museumObjectRepository.findByDetectionLabelId(detectionLabelId);

        if(museumObject == null) {
            museumObject = new MuseumObject();
        }

       updateObjectAndSave(name,description,categoryType,museumObject);
    }

    private void updateObjectAndSave(String name, String description, CategoryType categoryType, MuseumObject museumObject) {
        createObjectWithNameDescriptionAndCategory(name, description, categoryType, museumObject);
        museumObjectRepository.save(museumObject);
    }

    private void createObjectWithNameDescriptionAndCategory(String name, String description, CategoryType categoryType, MuseumObject museumObject) {
        museumObject.setName(name);
        museumObject.setDescription(description);
        museumObject.setCategory(new Category(categoryType));
    }
}
