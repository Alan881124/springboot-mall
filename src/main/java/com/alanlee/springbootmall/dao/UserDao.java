package com.alanlee.springbootmall.dao;

import com.alanlee.springbootmall.dto.UserRegisterRequest;
import com.alanlee.springbootmall.model.User;

public interface UserDao {

    User getUserById(Integer userId);

    Integer createUser(UserRegisterRequest userRegisterRequest);

    User getUserByEmail(String email);
}
