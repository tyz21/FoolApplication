package com.example.googleMapApplicationTracker.appUser.controller;

import com.example.googleMapApplicationTracker.appUser.service.ImageService;
import com.example.googleMapApplicationTracker.appUser.utility.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins = "https://gamefool.gamefi.codes")
@RequestMapping("/image")
public class ImageController {
    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @DeleteMapping("/delete/{idPhoto}")
    public void deletePhoto(@PathVariable Long idPhoto) {
        imageService.removePhoto(idPhoto);
    }

    @ResponseBody
    @RequestMapping(value = "/save", headers = "Content-Type= multipart/form-data", method = RequestMethod.POST)
    public ApiResponse<String> save(
            @RequestParam(value = "id", required = true) Long id,
            @RequestParam(value = "file", required = true) MultipartFile file) {
     return imageService.saveImage(id, file);
    }

    @Transactional(readOnly = true)
    @ResponseBody
    @RequestMapping(value = "/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<String> getImageByAppUserId(@RequestParam(value = "id", required = true) Long id) {
        return imageService.getImageById(id);
    }
}
