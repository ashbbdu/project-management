package com.project_management.repositories;

import com.project_management.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity , Long> {
   public Optional<UserEntity> findByEmail(String email);
}
