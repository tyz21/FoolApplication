package com.example.googleMapApplicationTracker.login;

import com.example.googleMapApplicationTracker.appUser.entity.AppUser;
import com.example.googleMapApplicationTracker.appUser.repository.AppUserRepository;
import com.example.googleMapApplicationTracker.appUser.utility.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/v1/login")
@CrossOrigin(origins = "https://gamefool.gamefi.codes")
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @GetMapping()
    public ApiResponse<String> login(@RequestParam String userName, @RequestParam String password) {
        return loginService.login(userName, password);
    }
}