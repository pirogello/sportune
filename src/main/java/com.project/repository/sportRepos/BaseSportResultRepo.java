package com.project.repository.sportRepos;

import com.project.model.sport.result.BaseSportResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BaseSportResultRepo extends JpaRepository<BaseSportResult, UUID> {
}
