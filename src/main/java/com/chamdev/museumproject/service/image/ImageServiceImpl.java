package com.chamdev.museumproject.service.image;

import com.chamdev.museumproject.dto.ImageDto;
import com.chamdev.museumproject.model.Image;
import com.chamdev.museumproject.model.Object;
import com.chamdev.museumproject.repository.ImageRepository;
import com.chamdev.museumproject.repository.ObjectRepository;
import com.chamdev.museumproject.service.object.ObjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService{
    private final ObjectRepository objectRepository;
    private final ObjectService objectService;
    private final ImageRepository imageRepository;

    @Override
    public List<Image> addImagesForObjectId(List<MultipartFile> files, Long objectId) {
        try {
            Object object = objectService.findObjectById(objectId);
            return saveImages(files,object);
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private List<Image> saveImages(List<MultipartFile> imageFiles, Object object) throws IOException, SQLException {
        List<Image> savedImages = new ArrayList<>();
        for (MultipartFile imageFile : imageFiles) {
            Image savedImage = saveImage(object, imageFile);
            savedImages.add(savedImage);
        }
        return savedImages;
    }

    private ImageDto createImageDtoFromImage(Image savedImage) {
        ImageDto imageDto = new ImageDto();
        imageDto.setId(savedImage.getId());
        imageDto.setFileName(savedImage.getFilename());
        imageDto.setDownloadUrl(savedImage.getDownloadUrl());
        return imageDto;
    }

    private Image saveImage(Object object, MultipartFile file) throws IOException, SQLException {
        Image image =createImageToSave(file, object);
        Image savedImage = imageRepository.save(image);
        savedImage.setDownloadUrl(createImageUrlUsingId(savedImage.getId()));
        imageRepository.save(savedImage);
        return savedImage;
    }

    private Image createImageToSave(MultipartFile file, Object object) throws IOException, SQLException {
        Image image = new Image();
        image.setFilename(file.getOriginalFilename());
        image.setFileType(file.getContentType());
        image.setImage(new SerialBlob(file.getBytes()));
        image.setObject(object);
        image.setDownloadUrl(createImageUrlUsingId(image.getId()));
        return image;
    }

    private String createImageUrlUsingId(Long imageId) {
        return "api/v1/images/image/download/" + imageId;
    }


}
