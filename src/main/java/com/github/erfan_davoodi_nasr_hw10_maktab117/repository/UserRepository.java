package com.github.erfan_davoodi_nasr_hw10_maktab117.repository;

import com.github.erfan_davoodi_nasr_hw10_maktab117.model.User;

public interface UserRepository {
    User save(User user);

    User findByPhoneNumber(String phoneNumber);
}
