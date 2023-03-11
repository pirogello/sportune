package com.project.repository.userRepos;

import com.project.model.user.BaseUser;
import com.project.model.user.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.UUID;

@NoRepositoryBean
public interface AbstractBaseUserRepo<T extends BaseUser> extends JpaRepository<T, UUID> {
    T findByUsername(String username);
}
