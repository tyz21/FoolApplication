package com.example.googleMapApplicationTracker.registration;

import com.example.googleMapApplicationTracker.appUser.entity.AppUser;
import com.example.googleMapApplicationTracker.appUser.entity.Image;
import com.example.googleMapApplicationTracker.appUser.repository.AppUserRepository;
import com.example.googleMapApplicationTracker.appUser.entity.AppUserRole;
import com.example.googleMapApplicationTracker.appUser.repository.ImageRepository;
import com.example.googleMapApplicationTracker.appUser.service.AppUserService;
import com.example.googleMapApplicationTracker.appUser.utility.ApiResponse;
import com.example.googleMapApplicationTracker.validator.EmailValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {
    private final AppUserService appUserService;
    private final ImageRepository imageRepository;
    private final AppUserRepository appUserRepository;
    private final EmailValidator emailValidator;

    public ApiResponse<String> register(RegistrationRequest request) {
        if (appUserRepository.existsByUserName(request.getUserName())) {
            return new ApiResponse<>("email already exists", true);
        }
        boolean isValidEmail = emailValidator.
                test(request.getUserName());

        if (!isValidEmail) {
            return new ApiResponse<>("email not valid", true, 0, request.getUserName(), null);
        }

        var newUser = new AppUser(
                request.getUserName(),
                request.getPassword(),
                AppUserRole.USER
        );

        createImageForUSer(newUser);
        appUserService.signUpUser(newUser);

        return new ApiResponse<>("Success!", false, newUser.getId(), request.getUserName(), null);
    }

    private void createImageForUSer(AppUser newUser) {
        Image image = new Image();
        newUser.setImage(image);
        imageRepository.save(image);
    }
}

