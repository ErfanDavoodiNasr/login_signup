package com.github.erfan_davoodi_nasr_hw10_maktab117.service.impl;

import com.github.erfan_davoodi_nasr_hw10_maktab117.model.User;
import com.github.erfan_davoodi_nasr_hw10_maktab117.model.dto.LoginUserRequest;
import com.github.erfan_davoodi_nasr_hw10_maktab117.model.dto.SaveUserRequest;
import com.github.erfan_davoodi_nasr_hw10_maktab117.repository.UserRepository;
import com.github.erfan_davoodi_nasr_hw10_maktab117.service.UserService;

public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(SaveUserRequest userRequest) {
        User user = User.builder()
                .firstname(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .phoneNumber(userRequest.getPhoneNumber())
                .build();
        return userRepository.save(user);
    }

    @Override
    public User findByPhoneNumber(LoginUserRequest userRequest) {
        return userRepository.findByPhoneNumber(userRequest.getPhoneNumber());
    }
}
