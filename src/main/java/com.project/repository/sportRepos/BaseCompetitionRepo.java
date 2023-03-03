package com.project.repository.sportRepos;

import com.project.model.sport.competition.BaseCompetition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BaseCompetitionRepo extends JpaRepository<BaseCompetition, UUID> {
}
