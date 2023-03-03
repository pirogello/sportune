package com.project.repository.userRepos;

import com.project.model.user.Trainer;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface TrainerRepo extends AbstractBaseUserRepo<Trainer> {
}
