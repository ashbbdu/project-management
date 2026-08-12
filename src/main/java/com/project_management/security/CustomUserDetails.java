package com.project_management.security;

import com.project_management.entities.UserEntity;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {
    private final UserEntity user;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

//        return List.of(
//                new SimpleGrantedAuthority("ROLE_" + user.getRole())
//        );
//        System.out.println(user.getRole());
//        System.out.println("Permsssion : " +
//                user.getRole().getPermissions().stream()
//                        .map(res -> res.getPermission()).toList());

        System.out.println("USER = " + user.getEmail());
        System.out.println("ROLE = " + user.getRole().getRole());
        return List.of(
                new SimpleGrantedAuthority("ROLE_USER")
                ,
                new SimpleGrantedAuthority("PROJECT_READ")
                ,
                new SimpleGrantedAuthority("PROJECT_CREATE")
        );

    }

    @Override
    public @Nullable String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
