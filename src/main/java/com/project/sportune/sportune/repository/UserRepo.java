package com.project.sportune.sportune.repository;

import com.project.sportune.sportune.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepo extends JpaRepository<User, UUID> {
}
