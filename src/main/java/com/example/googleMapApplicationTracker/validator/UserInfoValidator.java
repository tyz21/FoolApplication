package com.example.googleMapApplicationTracker.validator;

import com.example.googleMapApplicationTracker.registration.RegistrationRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class UserInfoValidator implements ConstraintValidator<UserInfo, RegistrationRequest> {

    @Override
    public boolean isValid(RegistrationRequest value, ConstraintValidatorContext context) {
        return StringUtils.hasText(value.getUserName()) || StringUtils.hasText(value.getPassword());
    }
}
