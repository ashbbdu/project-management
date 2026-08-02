package com.project_management.services;


import com.project_management.dto.users.CreateUserDto;
import com.project_management.dto.users.ViewUserDto;



public interface UserService {

    public ViewUserDto createUser(CreateUserDto createUserDto);


}