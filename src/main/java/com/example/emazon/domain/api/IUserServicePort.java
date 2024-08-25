package com.example.emazon.domain.api;

import com.example.emazon.domain.model.User;

public interface IUserServicePort {
    User save(User user);
    User findByEmail(String email);
    User findById(Integer id);
}
