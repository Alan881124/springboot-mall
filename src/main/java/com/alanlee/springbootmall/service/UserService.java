package com.alanlee.springbootmall.service;

import com.alanlee.springbootmall.dto.UserRegisterRequest;
import com.alanlee.springbootmall.model.User;

public interface UserService {

    User getUserById(Integer userId);

    Integer register(UserRegisterRequest userRegisterRequest);


}
