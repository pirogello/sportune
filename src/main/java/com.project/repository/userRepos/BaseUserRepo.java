package com.project.repository.userRepos;

import com.project.model.user.BaseUser;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface BaseUserRepo extends AbstractBaseUserRepo<BaseUser> {
}
