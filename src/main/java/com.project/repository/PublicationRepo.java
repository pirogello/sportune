package com.project.repository;

import com.project.model.Publication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PublicationRepo extends JpaRepository<Publication, UUID> {
}
