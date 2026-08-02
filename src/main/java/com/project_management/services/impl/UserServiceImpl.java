package com.project_management.services.impl;

import com.project_management.dto.users.CreateUserDto;
import com.project_management.dto.users.ViewUserDto;
import com.project_management.entities.UserEntity;
import com.project_management.repositories.UserRepository;
import com.project_management.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j // we need not to configure this because we are using lombok
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    @Override
    public ViewUserDto createUser(CreateUserDto createUserDto) {
        UserEntity user = new UserEntity();

        user.setFirstName(createUserDto.getFirstName());
        user.setLastName(createUserDto.getLastName());
        user.setEmail(createUserDto.getEmail());
        user.setDesignation(createUserDto.getDesignation());

        log.debug("Saving user with email: {}", user.getEmail());

        UserEntity savedUser = userRepository.save(user);

        ViewUserDto viewUserDto = new ViewUserDto();
        viewUserDto.setId(user.getId());
        viewUserDto.setFirstName(user.getFirstName());
        viewUserDto.setLastName(user.getLastName());
        viewUserDto.setEmail(user.getEmail());
        viewUserDto.setDesignation(user.getDesignation());
        viewUserDto.setCreatedAt(user.getCreatedAt());
        viewUserDto.setUpdatedAt(user.getUpdatedAt());


        log.info("User saved successfully with id: {}", savedUser.getId());
        return viewUserDto;

    }
}
