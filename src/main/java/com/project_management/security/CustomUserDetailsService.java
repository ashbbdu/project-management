package com.project_management.security;

import com.project_management.entities.UserEntity;
import com.project_management.exceptions.ResourceNotFoundException;
import com.project_management.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return null;
//    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       UserEntity user = userRepository.findByEmail(username).orElseThrow(() ->
               new ResourceNotFoundException("User with email " + username + " does not exists !" ));

       return new CustomUserDetails(user); // because Spring wants UserDetails and CustomUserDetails implements UserDetails
//        and if we have implemented UserDetails from UserEntity then we can directly return UserEntity
    }
}
