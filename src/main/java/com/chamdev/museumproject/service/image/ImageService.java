package com.chamdev.museumproject.service.image;

import com.chamdev.museumproject.dto.ImageDto;
import com.chamdev.museumproject.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface ImageService {
    List<Image> addImagesForObjectId(List<MultipartFile> files, Long objectId);
}
