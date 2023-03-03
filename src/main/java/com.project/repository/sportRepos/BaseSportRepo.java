package com.project.repository.sportRepos;

import com.project.model.sport.type.BaseSport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BaseSportRepo extends JpaRepository<BaseSport, UUID> {
}
