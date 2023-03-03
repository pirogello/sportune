package com.project.repository.userRepos;

import com.project.model.user.Organizer;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface OrganizerRepo extends AbstractBaseUserRepo<Organizer> {
}
