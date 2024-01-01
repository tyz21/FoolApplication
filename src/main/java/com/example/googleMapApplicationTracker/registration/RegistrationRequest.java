package com.example.googleMapApplicationTracker.registration;

import com.example.googleMapApplicationTracker.validator.UserInfo;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.validation.constraints.Email;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@UserInfo
public class RegistrationRequest {
    @Email
    private final String userName;
    private final String password;
}
