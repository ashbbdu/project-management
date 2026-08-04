package com.project_management.services;


import com.project_management.dto.users.CreateUserDto;
import com.project_management.dto.users.ViewUserDto;

import java.util.List;


public interface UserService {

    public ViewUserDto createUser(CreateUserDto createUserDto);

    public List<ViewUserDto> list ();

}