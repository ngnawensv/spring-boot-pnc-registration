package com.belrose.springbootpncregistration.service;

import com.belrose.springbootpncregistration.dto.UserDto;
import com.belrose.springbootpncregistration.model.IpApiResponse;
import com.belrose.springbootpncregistration.model.UserResponse;

public interface UserService {
    UserResponse getData(UserDto userDto);
}
