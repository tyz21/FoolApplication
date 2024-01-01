package com.example.googleMapApplicationTracker.appUser.service;

import com.example.googleMapApplicationTracker.appUser.entity.Image;
import com.example.googleMapApplicationTracker.appUser.repository.ImageRepository;
import com.example.googleMapApplicationTracker.appUser.utility.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;


@Service
@Slf4j
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;

    public void removePhoto(Long idImage) {
        Image image = imageRepository.findById(idImage).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        imageRepository.delete(image);
    }

    public ApiResponse<String> saveImage(Long id,
                                         MultipartFile file){
        try {
            Image newImage = imageRepository.findById(id).orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
            );
            newImage.setImage(file.getBytes());
            imageRepository.save(newImage);
        } catch (IOException e) {
            return new ApiResponse<>(e.toString(), true);
        }
        if (!file.isEmpty()) {
            return new ApiResponse<>("Success!", false, id);
        } else {
            return ApiResponse.error("You failed to upload, because the file was empty");
        }
    }
    @Transactional(readOnly = true)
    public ApiResponse<String> getImageById(Long id) {
        try {
            var image = imageRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
            return new ApiResponse<>("Success!", false, id, null, image.getImage());
        } catch (Exception e) {
            return ApiResponse.error(e.toString());
        }
    }
}

