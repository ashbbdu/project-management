package com.project_management.services;


import com.project_management.dto.users.CreateUserDto;
import com.project_management.dto.users.ViewUserDto;
import com.project_management.entities.UserEntity;
import com.project_management.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Slf4j // we need not to configure this because we are using lombok
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    public ViewUserDto createUser(CreateUserDto createUserDto) {
        log.debug("Mapping CreateUserDto to UserEntity");
        UserEntity user = modelMapper.map(createUserDto , UserEntity.class);
        log.debug("Saving user with email: {}", user.getEmail());

       UserEntity savedUser = userRepository.save(user);

        log.info("User saved successfully with id: {}", savedUser.getId());
        return modelMapper.map(savedUser , ViewUserDto.class);

    }
}
