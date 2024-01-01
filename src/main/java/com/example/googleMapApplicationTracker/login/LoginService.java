package com.example.googleMapApplicationTracker.login;

import com.example.googleMapApplicationTracker.appUser.entity.AppUser;
import com.example.googleMapApplicationTracker.appUser.repository.AppUserRepository;
import com.example.googleMapApplicationTracker.appUser.utility.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final AppUserRepository appUserRepository;
    private final AuthenticationManager authenticationManager;

    public ApiResponse<String> login(String userName, String password) {
        try {
            if (userName.isEmpty() || password.isEmpty()) {
                return ApiResponse.error("userName or password is empty");
            }
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    userName,
                    password
            );
            AppUser appUser;
            var existsUser = appUserRepository.findByUserName(userName);
            if (existsUser.isEmpty()){
                return ApiResponse.error("no such user");
            } else {
                appUser = existsUser.get();
            }
            Authentication authenticated = authenticationManager.authenticate(authentication);
            if (!authenticated.isAuthenticated()) {
                return ApiResponse.error("user not authenticated");
            }

            SecurityContextHolder.getContext().setAuthentication(authenticated);
            return new ApiResponse<>("Success!", false, appUser.getId(), appUser.getUsername(), appUser.getImage().getImage());

        } catch (AuthenticationException e) {
            return ApiResponse.error("check username or password");
        }
    }
}
