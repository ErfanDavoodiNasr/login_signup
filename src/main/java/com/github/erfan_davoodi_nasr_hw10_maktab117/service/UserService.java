package com.github.erfan_davoodi_nasr_hw10_maktab117.service;


import com.github.erfan_davoodi_nasr_hw10_maktab117.model.User;
import com.github.erfan_davoodi_nasr_hw10_maktab117.model.dto.LoginUserRequest;
import com.github.erfan_davoodi_nasr_hw10_maktab117.model.dto.SaveUserRequest;

public interface UserService {
    User save(SaveUserRequest userRequest);

    User findByPhoneNumber(LoginUserRequest userRequest);
}
