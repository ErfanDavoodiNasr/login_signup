package com.github.erfan_davoodi_nasr_hw10_maktab117.util;

import com.github.erfan_davoodi_nasr_hw10_maktab117.repository.UserRepository;
import com.github.erfan_davoodi_nasr_hw10_maktab117.repository.UserRepositoryImpl;
import com.github.erfan_davoodi_nasr_hw10_maktab117.service.UserService;
import com.github.erfan_davoodi_nasr_hw10_maktab117.service.UserServiceImpl;
import lombok.Getter;

public class ApplicationContext {
    private static UserRepository userRepository;
    @Getter
    private static UserService userService;

    static {
        userRepository = new UserRepositoryImpl();
        userService = new UserServiceImpl(userRepository);
    }
}
