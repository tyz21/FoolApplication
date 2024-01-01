package com.example.googleMapApplicationTracker.registration;

import com.example.googleMapApplicationTracker.appUser.utility.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(path = "api/v1/registration", produces = "application/json")
@CrossOrigin(origins = "https://gamefool.gamefi.codes")
@RequiredArgsConstructor
@Validated
public class RegistrationController {
    private final RegistrationService registrationService;

    @PostMapping()
    public ApiResponse<String> register(@RequestBody RegistrationRequest request,
                                        BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            System.out.println("create failed");
        }
        if (request.getUserName().equals("") || request.getPassword().equals("")) {
            return ApiResponse.error("Exception: username or password is null");
        }
        return registrationService.register(request);
    }
}